<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="javax.tools.*" %>
<%
//System.setProperty("java.home", "C:\\Program Files (x86)\\Java\\jdk1.7.0_51");

JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
System.out.println(compiler);

String contextpath = request.getContextPath();
out.println(contextpath);
String contextrealpath = request.getRealPath("/");
out.println(contextrealpath);

List<String> optionList = new ArrayList<String>();  
optionList.addAll(Arrays.asList("-cp", contextrealpath+"/WEB-INF/lib/robocode.jar")); 

DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromStrings(Arrays.asList("C:\\robocode_robots\\mrobots\\Mtest.java"));
JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, optionList, null, compilationUnits);
boolean success = task.call();
fileManager.close();
System.out.println("Success: " + success);
out.println("Success: " + success);



Properties prop = System.getProperties(); 
out.println(prop.getProperty("java.class.path"));

//compiler.getTask(out, fileManager, diagnosticListener, options, classes, compilationUnits)
//(null, null, "-cp", "C:\\ProgFiles\\robocode\\libs\\robocode.jar", "C:\\ProgFiles\\robocode\\robots\\sample\\Crazy.java");
%>