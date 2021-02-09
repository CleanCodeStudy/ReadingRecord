package com.javabom.toby.user.dao;

import com.javabom.toby.user.connectionmaker.ConnectionMaker;
import com.javabom.toby.user.connectionmaker.LocalDBConnectionMaker;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class DaoFactory {

    @Bean
    public UserDao userDao() {
        UserDao userDao = new UserDao();
        userDao.setConnectionMaker(connectionMaker());
        return userDao;
    }

    @Bean
    public UserDao dataSourceUserDao() {
        UserDao userDao = new UserDao();
        userDao.setDataSource(dataSource());
        return userDao;
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        return new LocalDBConnectionMaker();
    }

    @Bean
    public JdbcContext jdbcContext() {
        return new JdbcContext();
    }

    @ConditionalOnMissingBean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(org.h2.Driver.class);
        dataSource.setUrl("jdbc:h2:mem:testdb");
        dataSource.setUsername("test");
        dataSource.setPassword("1234");

        return dataSource;
    }
}
