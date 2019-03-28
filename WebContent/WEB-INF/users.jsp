<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:page>
	<jsp:attribute name="title">User list</jsp:attribute>
	<jsp:attribute name="header">List of registered users</jsp:attribute>
	<jsp:body>
		<c:choose>
			<c:when test="${ !empty error }">
				<p class="error">${ error }</p>
			</c:when>
			<c:when test="${ !empty users }">
				<ul>
					<c:forEach items="${ users }" var="user">
						<li><strong>${ user.firstName } ${ user.name } (email: ${ user.email })</strong></li>
					</c:forEach>
				</ul>
			</c:when>
			<c:otherwise><p>No registered users.</p></c:otherwise>
		</c:choose>
		<t:footer></t:footer>
	</jsp:body>
</t:page>