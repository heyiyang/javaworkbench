<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Insert title here</title>
</head>
<body>
<s:form action="login" namespace="/actions" >
	<s:property value="msg" />
	<s:textfield name="username" label="�û���"></s:textfield>
	<s:password name="password" label="����"></s:password>
	<s:submit value="��½"></s:submit>
</s:form>
<a href="<%=path %>/regist">ע��</a>
</body>
</html>