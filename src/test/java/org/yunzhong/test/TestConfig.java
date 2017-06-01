package org.yunzhong.test;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.github.pagehelper.PageHelper;
import com.jolbox.bonecp.BoneCPDataSource;

/**
 * Mybatis相关配置
 *
 * @author li.hzh
 * @date 2016-02-29
 */
@Configuration
@ComponentScan(basePackages = "org.yunzhong", excludeFilters = {})
@MapperScan(basePackages = { "org.yunzhong.service" }, sqlSessionFactoryRef = TestConfig.SQL_SESSION_FACTORY_NAME)
@EnableConfigurationProperties
public class TestConfig {
    public static final String SQL_SESSION_FACTORY_NAME = "sessionFactory";

    private Interceptor pageHelper;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertiesResolver() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name = "datasource")
    @Primary
    @DependsOn("propertiesResolver")
    public DataSource dataSource() {
        BoneCPDataSource dataSource = new BoneCPDataSource();
        return dataSource;
    }

    @Bean(name = SQL_SESSION_FACTORY_NAME)
    @Primary
    @DependsOn(value = { "pageHelper", "propertiesResolver" })
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPlugins(new Interceptor[] { pageHelper });
        return sessionFactoryBean.getObject();
    }

    @Bean
    @Primary
    @DependsOn("propertiesResolver")
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

    /**
     * MyBatis分页插件
     */
    @Bean(name = "pageHelper")
    @DependsOn("propertiesResolver")
    public Interceptor pageHelper() {
        pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
