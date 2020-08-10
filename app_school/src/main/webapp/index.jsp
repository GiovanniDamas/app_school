<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/resources/styles/bootstrap.css"
    rel="stylesheet">


</head>
<body>
	
	<a href="${pageContext.request.contextPath}/matiere/liste-matiere"">lien test liste matières</a>
	
	<a href="${pageContext.request.contextPath}/gestionEtudiants/listeEtudiants">lien test liste etudiants</a>
	
	<a href="${pageContext.request.contextPath}/gestionEnseignants/listeEnseignants">lien test liste enseignants</a>
	
	<a href="${pageContext.request.contextPath}/gestionAdmin/listeAdministrateurs">lien test liste admin</a>

	<jsp:include page="/WEB-INF/generic/header.jsp"></jsp:include>
	
		<h1>Hello !</h1>	
		
	<jsp:include page="/WEB-INF/generic/footer.jsp"></jsp:include>


</body>
</html>