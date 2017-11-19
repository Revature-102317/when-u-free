package com.whenufree.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.tool.schema.TargetType;
//import org.hibernate.tool.hbm2ddl.Target;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import java.util.EnumSet;


public class SessionFactoryUtil{
    
    private static SessionFactory sf;
    
    //Used by main method for generating DDL
    private static Metadata metadata;
	
    static{
	// Create registry
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure("hibernate.cfg.xml")
            .build();
        // Create MetadataSources
        MetadataSources sources = new MetadataSources(registry);
        // Create Metadata
        metadata = sources.getMetadataBuilder().build();
        // Create SessionFactory
        sf = metadata.getSessionFactoryBuilder().build();
    }

    /**
     * Empty method used by the context listener to force static initialization
     * upon start-up
     */
    public static void init(){
    }

    /**
     * Gets a session from the SessionFactory
     *
     * @return a session
     */
    public static Session getSession(){
	return sf.openSession();
    }

    /**
     * Gets the SessionFactory, used by the ContextListener to close the
     * factory upon destruction.
     *
     * @return the SessionFactory
     */
    public static SessionFactory getSessionFactory(){
	return sf;
    }

    /**
     * Main method, used to automatically generated DDL from objects for 
     * schema creation.
     */
    public static void main(String[] args){
	//Export DDL for schema
	SchemaExport export = new SchemaExport();
	export.setOutputFile("create-database.ddl").create(EnumSet.of(TargetType.SCRIPT), metadata);

    }
}
