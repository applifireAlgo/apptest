package com.app.config;
import java.util.EnumSet;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.SessionTrackingMode;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import com.athena.config.appsetUp.model.AppConfiguration;
import java.io.File;

@Configuration
public class WebInitializer implements WebApplicationInitializer {

    private final String APP_PKG = getPackage();

    private final String SEARCHENGINE_HOME = getSolrHome();

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        WebApplicationContext context = getContext();
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/secure/*");
        servletContext.addListener(new ContextLoaderListener(context));
        servletContext.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));
    }

    private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(APP_PKG);
        return context;
    }

    public void setSystemProperty(String propertyName, String propertyValue) {
        System.setProperty(propertyName, propertyValue);
    }

    public void setsearchEngineDispatcher(ServletContext servletContext) {
        FilterRegistration.Dynamic dynaFilterReg = servletContext.addFilter("SolrRequestFilter", "org.apache.solr.servlet.SolrDispatchFilter");
        dynaFilterReg.addMappingForUrlPatterns(null, true, "/*");
    }

    public String getsearchEngineHomeDir(ServletContext servletContext) {
        AppConfiguration appConfiguration = appSetup(servletContext);
        String searchEngineHomeDir = SEARCHENGINE_HOME;
        if (appConfiguration.getSearchEngineConfig().getBasePath() != null && appConfiguration.getSearchEngineConfig().getBasePath().length() > 0) {
            searchEngineHomeDir = appConfiguration.getSearchEngineConfig() != null && appConfiguration.getSearchEngineConfig().getBasePath() != null && appConfiguration.getSearchEngineConfig().getBasePath().length() > 0 ? appConfiguration.getSearchEngineConfig().getBasePath() : SEARCHENGINE_HOME;
        }
        return searchEngineHomeDir;
    }

    public AppConfiguration appSetup(ServletContext servletContext) {
        com.athena.config.app.xmlParser.AppXMLLoader appXMLLoader = null;
        try {
            appXMLLoader = new com.athena.config.app.xmlParser.AppXMLLoader();
            appXMLLoader.loadAppProperties(new File(servletContext.getRealPath("/WEB-INF/conf/appConfiguration.xml")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appXMLLoader.getAppConfiguration();
    }

    public static boolean issearchEngineHomeExistOrNot(String searchEngineHome) {
        File file = new File(searchEngineHome);
        if (!file.exists()) {
            System.out.println("|*******************************************************************|");
            System.out.println("|                                                              		|");
            System.out.println("|                                                              		|");
            System.out.println("|                                                              		|");
            System.out.println("|          PLEASE CHECK                                        		|");
            System.out.println("|          searchEngine HOME NOT Exists, searchEngine WILL NOT WORK |");
            System.out.println("|          PATH For searchEngine HOME :" + searchEngineHome + "   		|");
            System.out.println("|          Is NOT Exists                                       		|");
            System.out.println("|                                                              		|");
            System.out.println("|                                                              		|");
            System.out.println("|                                                              		|");
            System.out.println("|*******************************************************************|");
            return false;
        }
        return true;
    }

    public String getSolrHome() {
        return "/home/applifire/workspace/EERXPJTYG0KKIURV6LQYZQ/apptestSolr-4.9.0";
    }

    public String getPackage() {
        return "com.app";
    }
}
