<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:page>
	<jsp:attribute name="title">Connected</jsp:attribute>
	<jsp:attribute name="header">Logged in</jsp:attribute>
	<jsp:body>
		<p>
			Welcome ${ user.firstName } ${ user.name }. You were correctly logged in.<br>
		</p>
		<t:footer></t:footer>
	</jsp:body>
</t:page>