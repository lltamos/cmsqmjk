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
</body>  

<script type="text/javascript">
function typeOs(){
	 var u = navigator.userAgent;
	    var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
	    var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
	   if(isAndroid==true){
		   window.location.href="http://www.yhapp.cqmjk.com/download"
	   }
	    if(isiOS==true){
	    	window.location.href="http://itunes.apple.com/app/id1229065448"
	    }
	   /*  alert('是否是Android：'+isAndroid);
	    alert('是否是iOS：'+isiOS); */
}
typeOs();
</script>
</html>