package com.wjx.bookstore.dao.impl;

import com.wjx.bookstore.dao.Dao;
import com.wjx.bookstore.utils.ReflectionUtils;
import com.wjx.bookstore.web.ConnectionContext;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import com.wjx.bookstore.db.JDBCUtils;

import java.sql.*;
import java.util.List;

/**
 * Created by admin on 12/18/2016.
 */
public class BaseDao<T> implements Dao<T> {

    private QueryRunner queryRunner=new QueryRunner();
    private Class<T> clazz;

    public BaseDao() {
        clazz= ReflectionUtils.getSuperGenericType(getClass());
    }

    @Override
    public long insert(String sql, Object... args) {
        //自动增长id值
        long id=0;
        //数据库连接
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            //获取数据库连接
            connection= ConnectionContext.getInstance().get();
            //SQL语句
            preparedStatement=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //遍历SQL语句输入值
            if(args!=null) {
                for(int i=0;i<args.length;i++) {
                    //SQL从1开始
                    preparedStatement.setObject(i+1,args[i]);
                }
            }
            //执行SQL语句
            preparedStatement.executeUpdate();
            //获取生成的主键值
            resultSet=preparedStatement.getGeneratedKeys();
            //获取id值
            if(resultSet.next()) {
                id=resultSet.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }   finally {
            JDBCUtils.release(resultSet,preparedStatement);
        }
        return id;
    }

    @Override
    public void update(String sql, Object... args) {
        Connection connection=null;
        try {
            connection=ConnectionContext.getInstance().get();
            queryRunner.update(connection,sql,args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public T query(String sql, Object... args) {
        Connection connection=null;
        try {
            connection=ConnectionContext.getInstance().get();
            return queryRunner.query(connection,sql,new BeanHandler<>(clazz),args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<T> queryForList(String sql, Object... args) {
        Connection connection=null;
        try {
            connection=ConnectionContext.getInstance().get();
            return queryRunner.query(connection, sql, new BeanListHandler<>(clazz), args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <V> V getSingleVal(String sql, Object... args) {
        Connection connection=null;
        try {
            connection=ConnectionContext.getInstance().get();
            return (V)queryRunner.query(connection,sql,new ScalarHandler(),args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void batch(String sql, Object[]... params) {
        Connection connection=null;
        try {
            connection=ConnectionContext.getInstance().get();
            queryRunner.batch(connection,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
