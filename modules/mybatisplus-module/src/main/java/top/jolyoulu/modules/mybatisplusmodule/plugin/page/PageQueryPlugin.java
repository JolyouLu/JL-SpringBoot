package top.jolyoulu.modules.mybatisplusmodule.plugin.page;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.scripting.xmltags.TextSqlNode;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import top.jolyoulu.modules.mybatisplusmodule.plugin.AbstractPlugin;
import top.jolyoulu.modules.mybatisplusmodule.plugin.utils.MybatisPluginUtil;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.*;

/**
 * @Author: JolyouLu
 * @Date: 2023/3/19 15:01
 * @Description
 */
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
})
@Slf4j
public class PageQueryPlugin extends AbstractPlugin implements Interceptor {

    public PageQueryPlugin() {
        super();
    }

    public PageQueryPlugin(boolean debug) {
        super(debug);
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        try {
            Object[] args = invocation.getArgs();
            MappedStatement ms = (MappedStatement) args[0];
            Object parameter = args[1];
            RowBounds rowBounds = (RowBounds) args[2];
            ResultHandler resultHandler = (ResultHandler) args[3];
            Executor executor = (Executor) invocation.getTarget();
            CacheKey cacheKey;
            BoundSql boundSql;
            //由于逻辑关系，只会进入一次
            if (args.length == 4) {
                //4 个参数时
                boundSql = ms.getBoundSql(parameter);
                cacheKey = executor.createCacheKey(ms, parameter, rowBounds, boundSql);
            } else {
                //6 个参数时
                cacheKey = (CacheKey) args[4];
                boundSql = (BoundSql) args[5];
            }
            if (Objects.isNull(boundSql)) {
                boundSql = ms.getBoundSql(parameter);
            }
            JlPage<?> jlPage = JlPageLocal.get();
            if (Objects.nonNull(jlPage)) {
                jlPage.setTotal(0L); //默认总数0
                if (jlPage.getOptimize()) {
                    optimizeCount(executor, ms, parameter, jlPage);
                } else {
                    notOptimizeCount(executor, ms, parameter, jlPage);
                }
                if (jlPage.getTotal().equals(0L)){
                    return null;
                }
                //查询末尾追加LIMIT
                String sql = boundSql.getSql();
                String limitSql = gentLimitSql(sql, jlPage);
                BoundSql limitBoundSql = boundSql(ms, limitSql, parameter);
                executor.query(ms,parameter,rowBounds,resultHandler,cacheKey,limitBoundSql);
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return invocation.proceed();
    }

    //利用EXPLAIN优化COUNT
    private void optimizeCount(Executor executor, MappedStatement ms, Object parameter, JlPage jlPage) throws SQLException {
        //拼接一条查询sql
        String countSql = "EXPLAIN " + gentCountSql(ms.getBoundSql(parameter).getSql());
        BoundSql countBoundSql = boundSql(ms, countSql, parameter);
        MappedStatement countMs = mappedStatement(ms, countSql);
        log("执行的countSql ==> {}", countSql);
        MybatisPluginUtil.runQuerySql(executor, countMs, parameter, new CacheKey(), countBoundSql, rs -> {
            List<Object> list = rs.getResultList();
            if (Objects.nonNull(list) && !list.isEmpty() && list.get(0) instanceof Map){
                HashMap<String,Object> map = (HashMap<String, Object>) list.get(0);
                if (map.containsKey("rows")){
                    jlPage.setTotal(((BigInteger)map.get("rows")).longValue());
                }
            }
        });
    }

    //非优化的COUNT，默认的分页合计
    private void notOptimizeCount(Executor executor, MappedStatement ms, Object parameter, JlPage jlPage) throws SQLException {
        //拼接一条查询sql
        String countSql = gentCountSql(ms.getBoundSql(parameter).getSql());
        BoundSql countBoundSql = boundSql(ms, countSql, parameter);
        MappedStatement countMs = mappedStatement(ms, countSql);

        log("执行的countSql ==> {}", countSql);
        MybatisPluginUtil.runQuerySql(executor, countMs, parameter, new CacheKey(), countBoundSql, rs -> {
            List<Object> list = rs.getResultList();
            if (Objects.nonNull(list) && !list.isEmpty() && list.get(0) instanceof Map){
                HashMap<String,Object> map = (HashMap<String, Object>) list.get(0);
                if (map.containsKey("count")){
                    jlPage.setTotal((Long) map.get("count"));
                }
            }
        });
    }

    //构建一个BoundSql
    private BoundSql boundSql(MappedStatement ms, String sql, Object parameter) {
        return new BoundSql(ms.getConfiguration(), sql, ms.getBoundSql(parameter).getParameterMappings(), parameter);
    }

    //构建一个MappedStatement
    private MappedStatement mappedStatement(MappedStatement ms, String sql) {
        SqlSource sqlSource = new DynamicSqlSource(ms.getConfiguration(), new TextSqlNode(sql));
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId() + "_COUNT", sqlSource, SqlCommandType.SELECT);
        List<ResultMap> resultMaps = new ArrayList<>();
        resultMaps.add(new ResultMap.Builder(ms.getConfiguration(), ms.getId() + "_COUNT-Inline", Map.class, new ArrayList<>()).build());
        builder.resultMaps(resultMaps);
        return builder.build();
    }

    //获取COUNT SQL
    private String gentCountSql(String sql) {
        return "SELECT COUNT(*) AS count FROM (" + sql + ") tmp";
    }

    //拼接LIMIT SQL
    private String gentLimitSql(String sql, JlPage jlPage) {
        return sql + " limit " + jlPage.getLimitStart() + " , " + jlPage.getPageSize();
    }
}
