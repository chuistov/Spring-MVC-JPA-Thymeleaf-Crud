package com.chuistov.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    // Config class
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    // Adding configuration where ViewResolver is initialized to display JSPs correctly
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
                WebConfig.class
        };
    }

    // Returning URL on which this app will be based
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}