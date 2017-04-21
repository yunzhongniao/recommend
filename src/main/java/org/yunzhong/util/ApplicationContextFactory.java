package org.yunzhong.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by yunzhong on 2017/4/21.
 */
@Component
public class ApplicationContextFactory implements ApplicationContextAware {
    private static ApplicationContext context;

    /**
     *
     * @param appContext
     * @throws BeansException
     */
    public void setApplicationContext(ApplicationContext appContext) throws BeansException{
        ApplicationContextFactory.context = appContext;
    }

    
    
    /**
     * 
     * @param: classParam
     * @author: yunzhong
     * @Date: 2017/4/21 10:08
     */ 
    public static <T> T  getBean (Class<T> classParam){
        return context.getBean(classParam);
    }

    /**
     *
     * @param className
     * @Author yunzhong
     * @return
     */
    public static Object getBean(String className){
        return context.getBean(className);
    }
}
