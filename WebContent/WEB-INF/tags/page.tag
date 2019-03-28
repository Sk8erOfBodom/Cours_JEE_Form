<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="title" fragment="true" %>
<%@ attribute name="header" fragment="true" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><jsp:invoke fragment="title" /></title>
		
		<link rel="stylesheet" href="css/main.css">
	</head>
	<body>
		<h1><jsp:invoke fragment="header" /></h1>
		<header>
			<c:choose>
				<c:when test="${ !empty sessionScope.user }">
					<p>
						<span>Bienvenue ${ sessionScope.user.firstName } ${ sessionScope.user.name }.</span>
						<a href="disconnect">Disconnect</a>
					</p>
				</c:when>
				<c:otherwise>
					<p>
						<a href="login">Login</a>
						<a href="register">Register</a>
					</p>
				</c:otherwise>
			</c:choose>
		</header>
		<jsp:doBody />
	</body>
</html>