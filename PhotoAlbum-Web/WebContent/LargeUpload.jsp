<%@ page language="java" import="javazoom.upload.*,java.util.*,java.io.*" %>
<%@ page errorPage="ExceptionHandler.jsp" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% String path = (String) session.getAttribute("path"); %>
<% String directory = "c:/temp" ; %>
<% String tmpdirectory = directory+"/tmp"; %>
<% boolean createsubfolders = true; %>
<% boolean allowresume = true; %>
<% boolean allowoverwrite = true; %>
<% String encoding = "UTF-8"; %>
<% boolean keepalive = false; %>

<jsp:useBean id="upBean" scope="page" class="javazoom.upload.UploadBean" >
  <jsp:setProperty name="upBean" property="folderstore" value="<%= directory %>" />
  <jsp:setProperty name="upBean" property="parser" value="<%= MultipartFormDataRequest.CFUPARSER %>"/>
  <jsp:setProperty name="upBean" property="parsertmpdir" value="<%= tmpdirectory %>"/>
  <jsp:setProperty name="upBean" property="filesizelimit" value="8589934592"/>
  <jsp:setProperty name="upBean" property="overwrite" value="<%= allowoverwrite %>"/>
  <jsp:setProperty name="upBean" property="dump" value="true"/>
</jsp:useBean>

<%@page import="photoalbum.PhotoAlbumManipulator"%>
<%@page import="photoalbum.entities.Category"%>
<html>
<head>
<title>Качете снимката си във фото албум </title>
<style TYPE="text/css">
<!--
.style1 {
	font-size: 12px;
	font-family: Verdana;
}
-->
</style>

</head>
<body>
<ul class="style1">
<%
      if (MultipartFormDataRequest.isMultipartFormData(request))
      {
         // Parse multipart HTTP POST request.
         MultipartFormDataRequest mrequest = null;
         try
         {
         	mrequest = new MultipartFormDataRequest(request,null,-1,MultipartFormDataRequest.CFUPARSER,encoding,allowresume);
         } catch (Exception e)
	   {
	       // Cancel current upload (e.g. Stop transfer)
               // Only if allowresume = false
	   }
         String todo = null;
         if (mrequest != null) todo = mrequest.getParameter("todo");
         if ( (todo != null) && (todo.equalsIgnoreCase("upload")) )
         {
    	   String account = mrequest.getParameter("account");
    	   if (account == null) account="";
    	   else if (!account.startsWith("/")) account = "/"+account;
           upBean.setFolderstore(directory+account);
           Hashtable files = mrequest.getFiles();
           if ( (files != null) && (!files.isEmpty()) )
           {
             UploadFile file = (UploadFile) files.get("uploadfile");
             if (file != null) out.println("<li>Вие качихте файл : "+file.getFileName()+" <BR>с големина ("+file.getFileSize()+" bytes)"+"<BR> Тип на файла : "+file.getContentType());
             PhotoAlbumManipulator edit = new PhotoAlbumManipulator();
             Category category = (Category)session.getAttribute("categoryPhoto");
             // Folders and subfolders creation support.
             String relative = mrequest.getParameter("relativefilename");
            
             if ((createsubfolders == true) && (relative != null))
             {
               int inda=relative.length();
               int indb=file.getFileName().length();
               if (inda > indb)
               {
                 String subfolder = relative.substring(0,(inda-indb)-1);
                 subfolder = subfolder.replace('\\','/').replace('/',java.io.File.separatorChar);
                 upBean.setFolderstore(directory+account+java.io.File.separator+subfolder);
               }
             }
             if (keepalive == false) response.setHeader("Connection","close");
             // Chunks recomposion support.
             String chunkidStr = mrequest.getParameter("chunkid");
             String chunkamountStr = mrequest.getParameter("chunkamount");
             String chunkbaseStr = mrequest.getParameter("chunkbase");
             if ((chunkidStr != null) && (chunkamountStr != null) && (chunkbaseStr != null))
             {
               // Always overwrite chunks.
               upBean.setOverwrite(true);
               upBean.store(mrequest, "uploadfile");
               upBean.setOverwrite(allowoverwrite);
               int chunkid = Integer.parseInt(chunkidStr);
               int chunkamount = Integer.parseInt(chunkamountStr);
               if (chunkid == chunkamount)
               {
                 // recompose file.
                 String fname = upBean.getFolderstore()+java.io.File.separator+chunkbaseStr;
                 File fread = new File(fname);
                 if (fread.canRead() && (upBean.getOverwrite()==false)) fname = upBean.loadOverwriteFilter().process(fname);
                 FileOutputStream fout = new FileOutputStream(fname);
                 byte[] buffer = new byte[4096];
                 for (int c=1;c<=chunkamount;c++)
                 {
                   File filein = new File(upBean.getFolderstore()+java.io.File.separator+chunkbaseStr+"."+c);
                   FileInputStream fin = new FileInputStream(filein);
                   int read = -1;
                   while ((read = fin.read(buffer)) > 0) fout.write(buffer,0,read);
                   fin.close();
                   edit.addPhoto(category,filein);
                   filein.delete();
                 }
                 fout.close();
                 
               }
             }
             else upBean.store(mrequest, "uploadfile");
             upBean.setFolderstore(directory+account);
              
           }
           else
           {
            String emptydirectory = mrequest.getParameter("emptydirectory");
             if ((emptydirectory != null) && (!emptydirectory.equals("")))
             {
               File dir = new File(directory+account+"/"+emptydirectory);
               dir.mkdirs();
   //          out.println(dir.getPath());
             }
             out.println("<li>No uploaded files");
           }
         }
         else out.println("<BR> todo="+todo);
      }
%>
</ul>
<form method="post" action="LargeUpload.jsp" name="upform" enctype="multipart/form-data">
  <table width="60%" border="0" cellspacing="1" cellpadding="1" align="center" class="style1">
    <tr>
      <td align="left"><b>Изберете снимка :</b></td>
    </tr>
    <tr>
      <td align="left">
        <input type="hidden" name="todo" value="upload">
          <input type="file" name="uploadfile" size="50">
      </td>
    </tr>
    <tr>
      <td align="left">
        <input type="submit" name="Submit" value="Upload">
        <input type="reset" name="Reset" value="Cancel">
        </td>
    </tr>
   
    <tr><td> <br><br>
    	<div style="color: red"><a class="grey" href="MainPage.jsp" title="Вход в photo album">Начална страница</a></div>
	</td></tr>
  </table>

  
</form>
</body>
</html>
