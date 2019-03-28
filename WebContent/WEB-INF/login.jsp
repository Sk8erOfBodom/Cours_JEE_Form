<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:page>
	<jsp:attribute name="title">Login</jsp:attribute>
	<jsp:attribute name="header">Login</jsp:attribute>
	<jsp:body>
		<form method="POST">
			<fieldset>
				<c:if test="${ !empty error }">
					<div class="error">${ error }</div>
				</c:if>
				<div class="row">
					<label for="f_email">Email : </label>
					<input type="text" id="f_email" name="email" value="${ user.email }">
				</div>
				<div class="row">
					<label for="f_pass">Password : </label>
					<input type="password" id="f_pass" name="pass">
				</div>
				<input type="submit">
			</fieldset>
		</form>
		<t:footer></t:footer>
	</jsp:body>
</t:page>