package top.jolyoulu.modules.druidmodule.utils.db;

import java.sql.Connection;

/**
 * @Author: JolyouLu
 * @Date: 2023/4/5 13:33
 * @Description
 */
public abstract class AbstractDBUtils{
    protected Connection connection;

    public AbstractDBUtils(Connection connection) {
        this.connection = connection;
    }

    public abstract boolean check();

    public abstract void close();
}
