package com.whenufree.web;
    
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.whenufree.utils.SessionFactoryUtil;


@WebListener
public class ContextListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce){
	SessionFactoryUtil.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce){
	SessionFactoryUtil.getSessionFactory().close();
    }
}
