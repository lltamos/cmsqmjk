<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!DOCTYPE html>
<html class="no-js">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title></title>
</head>
<body>
<form action="/cms/1/savehtmlinfo"  method="post" enctype="multipart/form-data">
        <input type="hidden" name="type" value="1">
        <input type="file" multiple="multiple" name="upfile">
        <input type="submit">
</form>
</body>  

<script type="text/javascript">

</script>
</html>