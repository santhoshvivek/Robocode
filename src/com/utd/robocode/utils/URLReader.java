package com.utd.robocode.utils;
import java.net.*;
import java.io.*;
import java.util.regex.*;

public class URLReader {
    public static int fetchResults(String URL) throws Exception {

    	String result=URL;
    	//String result1="http://testapp-utd.rhcloud.com/PerlJava";
        URL oracle = new URL(result);
        BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));
        String inputLine;
        int percentDuplicate = 0;
        while ((inputLine = in.readLine()) != null)
        {
        	String fileName;    
        	String re1=".*?";	// Non-greedy match on filler
            String re2="(deidei\\.java)";	// Fully Qualified Domain Name 1
            String txt=inputLine;
            Pattern p = Pattern.compile(re1+re2,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
            Matcher m = p.matcher(txt);
            if (m.find())
            {
                String fqdn1=m.group(1);
                System.out.println(inputLine);
                fileName=fqdn1.toString();
                
                String reg1=".*?";	// Non-greedy match on filler
        	    String reg2="(\\([0-9]+%\\))";	// Round Braces 1
        	    String txt2=inputLine;
        	    Pattern pa = Pattern.compile(reg1+reg2,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        	    Matcher ma = pa.matcher(txt2);
        	    if (ma.find())
        	    {
        	    	
        	    	String print="";
        	        String rbraces1=ma.group(1);
        	        String intermediate=rbraces1.toString();
        	        int length=intermediate.length();
        	        if(length==5){
        	        print=intermediate.substring(1, 3);}
        	        else if(length==6){
        	        	print=intermediate.substring(1,4);
        	        }
        	        else if(length==4){
        	        	
        	        	print=intermediate.substring(1,2);
        	        }
        	        percentDuplicate=Integer.parseInt(print);
        	        System.out.println(fileName+"-"+percentDuplicate);
        	    }
            }
        	
        	     ///*else{*/System.out.println("NM");//}
        }
        in.close();
        return percentDuplicate;
    }
    
}