package org.yunzhong.test;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.yunzhong.service.dao.BaseDAO;

import com.github.pagehelper.PageHelper;
import com.jolbox.bonecp.BoneCPDataSource;


@Configuration
@ComponentScan(basePackages ={ "org.yunzhong.controller","org.yunzhong.service"}, excludeFilters = {})
@EnableTransactionManagement
@EnableConfigurationProperties
public class TestDataSourceConfig {

	private static final String TYPE_ALIASES_PACKAGE = "org.yunzhong.service.model";
	private static final String MAPPER_LOCATION = "classpath:mapper/*-mapper.xml";
	private static final String CONFIG_LOCATION = "classpath:mybatis-config.xml";
	private static final String REPOSITORY_BASE_PACKAGE = "org.yunzhong";

	private BoneCPDataSource datasource;
	private SqlSessionFactoryBean sqlSessionFactoryBean;
	private Interceptor pageHelper;
	
	@ConfigurationProperties(prefix = "dataSource")
	@Bean(name = "dataSource")
	public BoneCPDataSource dataSource() {
		datasource = new BoneCPDataSource();
		return datasource;
	}

	@Bean(autowire = Autowire.BY_NAME)
	@DependsOn(value = "sqlSessionFactory")
	public SqlSession sqlSessionTemplate() throws Exception {
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactoryBean.getObject());
		return sqlSessionTemplate;
	}

	@Bean(autowire = Autowire.BY_NAME)
	@DependsOn(value = {"dataSource", "pageHelper"})
	public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
		sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(datasource);
		sqlSessionFactoryBean.setTypeAliasesPackage(TYPE_ALIASES_PACKAGE);
		sqlSessionFactoryBean.setTypeAliasesSuperType(Serializable.class);
		sqlSessionFactoryBean.setMapperLocations(getResources(MAPPER_LOCATION));
		sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});
		sqlSessionFactoryBean.setConfigLocation(getResources(CONFIG_LOCATION)[0]);
		return sqlSessionFactoryBean;
	}

	@Bean(autowire = Autowire.BY_NAME)
	@DependsOn(value = {"dataSource"})
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(datasource);
		return transactionManager;
	}

	private Resource[] getResources(String pathPattern) throws IOException {
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = resolver.getResources(pathPattern);
		return resources;
	}

	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage(REPOSITORY_BASE_PACKAGE);
		mapperScannerConfigurer.setMarkerInterface(BaseDAO.class);
		return mapperScannerConfigurer;
	}

	/**
	 * MyBatis分页插件
	 */
	@Bean
	public Interceptor pageHelper() {
		pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("dialect", "postgresql");
		pageHelper.setProperties(properties);
		return pageHelper;
	}


}