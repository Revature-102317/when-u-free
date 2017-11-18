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
    
    static{
	System.out.println(1);
	// Create registry
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure("hibernate.cfg.xml")
            .build();
	System.out.println(2);
        // Create MetadataSources
        MetadataSources sources = new MetadataSources(registry);
	System.out.println(3);
        // Create Metadata
        Metadata metadata = sources.getMetadataBuilder().build();
	System.out.println(4);
	SchemaExport export = new SchemaExport();
	export.setOutputFile("create-database.ddl").create(EnumSet.of(TargetType.SCRIPT), metadata);
	System.out.println(5);
        // Create SessionFactory
        sf = metadata.getSessionFactoryBuilder().build();
    }

    public static Session getSession(){
	return sf.openSession();
    }

    public static void main(String[] args){
    }
}
