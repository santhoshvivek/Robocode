package com.utd.robocode.utils;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DataStoreUtils {

    private static Connection connection = null;  // For making the connection
    private static Statement statement = null;    // For the SQL statement
    private static ResultSet resultSet = null;    // For the result set, if applicable
    
	// db should be DBConnectionDomain1 as in configuration file for first db
	// db should be DBConnectionDomain2 as in configuration file for second db
   /* public static Connection getConnection(String db) throws Exception{
    	if(connection == null){
    		return new DataStoreUtils().createConnection(db);
    	}else{
    		return connection;
    	}    	
    }
    
    // Method used to execute the query
    public static ResultSet executeQuery(String query) throws Exception{
    	
    	 // Use the connection to create the SQL statement.
        statement = connection.createStatement();

        // Execute the statement.
        statement.execute(query);
        
        ResultSet result = statement.getResultSet();

        // Provide a message when processing is complete.
        System.out.println("Processing complete.");
    	
        return result;
    }*/
    
    public static Connection createConnection(String db){
    	try{
    		// Ensure the SQL Server driver class is available.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Properties prop = new Properties();
            prop.load(new DataStoreUtils().getClass().getResourceAsStream("configuration.properties"));
            //prop.load(new FileReader(getClas"configuration.properties"));
            String connectionString = "";
            
            connectionString = prop.getProperty(db);
            
            // Establish the connection.
            connection = DriverManager.getConnection(connectionString);
            return connection;
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}    	
    	return null;
    }
    
    public static Connection getHibernateConnection(String db){
    	try{
    		
            /*Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Properties prop = new Properties();
            prop.load(new DataStoreUtils().getClass().getResourceAsStream("configuration.properties"));
            //prop.load(new FileReader(getClas"configuration.properties"));
            String connectionString = "";
            
            connectionString = prop.getProperty(db);
            
            // Establish the connection.
            connection = DriverManager.getConnection(connectionString);*/
            return connection;
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}    	
    	return null;
    }
    
    public static SessionFactory sessionFactoryForDomain1 = null;
    public static SessionFactory sessionFactoryForDomain2 = null;
    public static HashMap<String, SessionFactory> sessionFactories = new HashMap<String, SessionFactory>();

    public static HashMap<String, SessionFactory> buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
        	
            //if(db.equalsIgnoreCase("DBConnectionDomain1")){
            	//return new Configuration().configure(new DataStoreUtils().getClass().getResource("hibernate.cfg.xml")).buildSessionFactory();
            	if(sessionFactoryForDomain1 == null){
            		sessionFactoryForDomain1 = new Configuration().configure("hibernate1.cfg.xml").buildSessionFactory();
            	}
            	sessionFactories.put("DBConnectionDomain1", sessionFactoryForDomain1);
            //}else{
            	//return new Configuration().configure("hibernate1.cfg.xml").buildSessionFactory();            	
            	if(sessionFactoryForDomain2 == null){
            		sessionFactoryForDomain2 = new Configuration().configure("hibernate2.cfg.xml").buildSessionFactory();
            	}
            	sessionFactories.put("DBConnectionDomain2", sessionFactoryForDomain2);
            //}
            	return sessionFactories;
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
