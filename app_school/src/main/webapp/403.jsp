<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="http://www.springframework.org/security/tags"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>403 - Accès refusé</title>
</head>
<body>

	<h1>HTTP Status 403 - Access Denied</h1>

	<s:authorize access="hasRole('ROLE_ANONYMOUS')">
		<h2>Vous n'avez pas accès à cette page</h2>
		<h4>
			<c:url value="/login.jsp" var="loginVar" />

			Veuillez vous connecter pour accéder à ce contenu : <a href="${loginVar}">Se Connecter</a>
		</h4>
	</s:authorize>

</body>
</html>