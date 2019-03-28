<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:page>
	<jsp:attribute name="title">Register</jsp:attribute>
	<jsp:attribute name="header">Create a new account</jsp:attribute>
	<jsp:body>
		<form method="POST">
			<fieldset>
				<div class="row">
					<label for="f_name">Last name : </label>
					<input type="text" id="f_name" name="name" value="${ user.name }">
					<c:if test="${ !empty nameError }">
						<div class="error">${ nameError }</div>
					</c:if>
				</div>
				<div class="row">
					<label for="f_fname">First name : </label>
					<input type="text" id="f_fname" name="fname" value="${ user.firstName }">
					<c:if test="${ !empty fnameError }">
						<div class="error">${ fnameError }</div>
					</c:if>
				</div>
				<div class="row">
					<label for="f_email">Email : </label>
					<input type="email" id="f_email" name="email" value="${ user.email }">
					<c:if test="${ !empty emailError }">
						<div class="error">${ emailError }</div>
					</c:if>
				</div>
				<div class="row">
					<label for="f_pass">Password : </label>
					<input type="password" id="f_pass" name="pass">
					<c:if test="${ !empty passError }">
						<div class="error">${ passError }</div>
					</c:if>
				</div>
				<div class="row">
					<label for="f_pass_confirm">Confirm password : </label>
					<input type="password" id="f_pass_confirm" name="pass2">
					<c:if test="${ !empty pass2Error }">
						<div class="error">${ pass2Error }</div>
					</c:if>
				</div>
				<input type="submit">
			</fieldset>
		</form>
		<t:footer></t:footer>
	</jsp:body>
</t:page>