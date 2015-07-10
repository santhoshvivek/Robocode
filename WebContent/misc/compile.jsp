<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="javax.tools.*" %>
<%
//Create Robot File - Start

String robocodeDirStr = "C:\\ProgFiles\\robocode\\";
String robotsDirStr = robocodeDirStr + "robots\\";
File robotsDir = new File(robotsDirStr);
// if the directory does not exist, create it
if (!robotsDir.exists()) {
System.out.println("creating directory: " + robotsDir);
boolean robotsDirMkdirResult = robotsDir.mkdir();  
}

String robotsUserDirStr = (String) session.getAttribute("userpackagex");
String robotFileNameStr = request.getParameter("robocode_file_name");
String robotJavaFileNameStr = robotFileNameStr+".java";
String robotFileContent = request.getParameter("robocode_file_content");
String robotFileFullPathStr = robotsDirStr+robotsUserDirStr+"\\"+robotJavaFileNameStr;
//out.println(robotFileFullPath);

File robotUserDir = new File(robotsDirStr+robotsUserDirStr);
// if the directory does not exist, create it
if (!robotUserDir.exists()) {
System.out.println("creating directory: " + robotUserDir);
boolean robotUserDirMkdirResult = robotUserDir.mkdir();  
}

//File creation
File robotFileFullPathFile = new File(robotFileFullPathStr);
if (robotFileFullPathFile.exists()){
	robotFileFullPathFile.delete();
}
boolean robotFileCreatedResult = robotFileFullPathFile.createNewFile();

//File appending
Writer objWriter = new BufferedWriter(new FileWriter(robotFileFullPathStr));
objWriter.write(robotFileContent);
objWriter.flush();
objWriter.close();

//Create Robot File - End


// Compilation - Start
JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
System.out.println(compiler);

//String contextpath = request.getContextPath();
//out.println(contextpath);
String contextrealpath = request.getRealPath("/");
//out.println(contextrealpath);

List<String> optionList = new ArrayList<String>();  
//optionList.addAll(Arrays.asList("-cp", "C:\\ProgFiles\\robocode\\libs\\robocode.jar")); 
optionList.addAll(Arrays.asList("-cp", contextrealpath+"/WEB-INF/lib/robocode.jar")); 

DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromStrings(Arrays.asList(robotFileFullPathStr));
JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, optionList, null, compilationUnits);
boolean success = task.call();
fileManager.close();
System.out.println("Success: " + success);
out.println(success);

//Properties prop = System.getProperties(); 
//out.println(prop.getProperty("java.class.path"));

%>