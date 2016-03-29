package com.app.config;import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.athena.config.app.xmlParser.AppXMLLoader;
import com.athena.config.appsetUp.model.AppConfiguration;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ImportResource({ "classpath:springConfig.xml" })
public class WebConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private ResourceLoader resourceLoader;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	}

	@Bean
	public ContentNegotiatingViewResolver contentNegotation() {
		ContentNegotiatingViewResolver contentNegotiator = new ContentNegotiatingViewResolver();
		Map<String, String> mapContentType = new HashMap<String, String>();
		java.util.List<View> lstViews = new ArrayList<View>();
		mapContentType.put("html", "text/html");
		mapContentType.put("json", "application/json");
		contentNegotiator.setMediaTypes(mapContentType);
		lstViews.add(new MappingJacksonJsonView());
		contentNegotiator.setDefaultViews(lstViews);
		return contentNegotiator;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean(name = "appConfig")
	public AppConfiguration appConfiguration() throws IOException {
		AppXMLLoader xmlLoader = new AppXMLLoader();
		File appSetup1 = resourceLoader.getResource("WEB-INF/conf/appConfiguration.xml").getFile();
		xmlLoader.loadAppProperties(appSetup1);
		return xmlLoader.getAppConfiguration();
	}

	@Bean(destroyMethod = "close")
	public BasicDataSource dataSource(@Qualifier("appConfig") AppConfiguration appConfig) throws IOException {
		AppBasicDataSource myDs = new AppBasicDataSource(appConfig.getDatabaseConfig().getConnectionConfig().getDriverName(), appConfig.getDatabaseConfig().getConnectionConfig()
				.getUrl().trim(), appConfig.getDatabaseConfig().getConnectionConfig().getUserName(), appConfig.getDatabaseConfig().getConnectionConfig().getPassword());
		return myDs;
	}

}
