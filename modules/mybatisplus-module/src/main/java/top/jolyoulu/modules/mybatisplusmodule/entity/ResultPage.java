package top.jolyoulu.modules.mybatisplusmodule.entity;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author: JolyouLu
 * @Date: 2023/3/18 14:37
 * @Description
 */
@Data
public class ResultPage<T>{

    private Long total;
    private Long page;
    private Long pageSize;
    private List<T> list;

    /**
     * 将page中的结果进行转换
     * @param source
     * @param format
     * @return
     * @param <S>
     * @param <R>
     */
    public static <S,R> ResultPage<R> conversion(Page<S> source, Function<S,R> format){
        ResultPage<R> res = new ResultPage<R>();
        res.setPage(source.getCurrent());
        res.setPageSize(source.getSize());
        res.setTotal(source.getTotal());
        if (Objects.nonNull(source.getRecords()) && !source.getRecords().isEmpty()){
            res.setList(source.getRecords().stream().map(format).collect(Collectors.toList()));
        }else {
            res.setList(new ArrayList<>());
        }
        return res;
    }
}
