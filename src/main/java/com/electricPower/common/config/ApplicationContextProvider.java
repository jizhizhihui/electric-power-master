package com.electricPower.common.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) {
        ApplicationContextProvider.context = context;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static <T> T getBean(String name) {
        return (T) context.getBean(name);
    }

    public static <T> T getBean(String name, ApplicationContext applicationContext) {
        return (T) applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        Map<String, T> beanMaps = context.getBeansOfType(clazz);
        if (beanMaps != null && !beanMaps.isEmpty()) {
            return beanMaps.values().iterator().next();
        } else {
            return null;
        }
    }

    public static <T> T getBean(Class<T> clazz, ApplicationContext applicationContext) {
        Map<String, T> beanMaps = applicationContext.getBeansOfType(clazz);
        if (beanMaps != null && !beanMaps.isEmpty()) {
            return beanMaps.values().iterator().next();
        } else {
            return null;
        }
    }
}
