<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Insert title here</title>
</head>
<body>
<s:form action="regist" namespace="/actions" method="POST" enctype="multipart/form-data">
	<s:property value="msg" />
	<s:textfield name="username" label="�û���"></s:textfield>
	<s:password name="password" label="����"></s:password>
	<s:password name="repassword" label="ȷ������"></s:password>
	<s:textfield name="email" label="email"></s:textfield>
	<s:file name="avatar" label="ͷ��"></s:file>
	<s:radio name="gender" label="�Ա�" list="#{'1': 'boy', '0': 'girl' }" listKey='key' listValue='value'></s:radio>
	<s:textarea name="bio" label="���˼��"></s:textarea>
	<s:submit value="ע��"></s:submit>
</s:form>
</body>
</html>