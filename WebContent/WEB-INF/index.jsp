<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:page>
	<jsp:attribute name="title">Index</jsp:attribute>
	<jsp:attribute name="header">Welcome</jsp:attribute>
	<jsp:body>
		<p>
			You can see the <a href="users">list of registered users here</a>.
		</p>
	</jsp:body>
</t:page>