package com.xuhao.payroll.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            // 加载MyBatis配置文件
            String resource = "config/mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("初始化MyBatis失败", e);
        }
    }

    /**
     * 获取SqlSession实例
     * 
     * @return SqlSession实例
     */
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

    /**
     * 获取SqlSession实例（自动提交）
     * 
     * @param autoCommit 是否自动提交
     * @return SqlSession实例
     */
    public static SqlSession getSqlSession(boolean autoCommit) {
        return sqlSessionFactory.openSession(autoCommit);
    }

    /**
     * 关闭SqlSession
     * 
     * @param session 要关闭的SqlSession实例
     */
    public static void closeSqlSession(SqlSession session) {
        if (session != null) {
            session.close();
        }
    }
}