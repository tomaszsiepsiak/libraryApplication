package com.klb.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class ApplicationInitializer implements WebApplicationInitializer {


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(AppConfig.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);

        ServletRegistration.Dynamic servletRegistration
                = servletContext.addServlet("dispatcher", dispatcherServlet);

        servletRegistration.setLoadOnStartup(1);  //1 - priorytet ze chcemy aby apka serwera zainicjalizowala jaka pierwsza ta aplikacje
        servletRegistration.addMapping("/");  //okreslamy poczatek mapowania w url

        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);  //do nadpisywania innego kodowania w requestach
        servletContext.addFilter("characterEncodingFilter", characterEncodingFilter)
                .addMappingForUrlPatterns(null, true, "/*");

        //mowimy ze wszystkie beany w aplikacji sa zwiazane ze spring security
        DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy();
        servletContext.addFilter("springSecurityFilterChain", delegatingFilterProxy)
                .addMappingForUrlPatterns(null, true, "/*");


    }
}