<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<link href="${pageContext.request.contextPath}/resources/styles/bootstrap.css"
    rel="stylesheet">

<title>Liste des matières</title>
</head>
<body>
	
	<!-- =========================================================== -->
	<!-- ======== Header (navabar) ================================= -->
	<!-- =========================================================== -->
	
	
	<jsp:include page="/WEB-INF/generic/header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/generic/sidebar.jsp"></jsp:include> 
	<!-- =========================================================== -->
	<!-- ======== Content ========================================== -->
	<!-- =========================================================== -->
	
	<h1 style="margin-left:20px">
		<u>Liste des matières:</u>
	</h1>

	<table border="1" width="70%" style="margin: 0 0 10px 20px">

		<tr>
		
			<td colspan="5" align="right"><a
				href="${pageContext.request.contextPath}/employe/add-employe-form">
					Ajout d'une matière </a></td>
		</tr>

		<!-- en tête de la table -->
		<tr>
			<th>Libelle</th>
		
			<th>Modifier</th>
			<th>Supprimer</th>
		</tr>

		<!-- données de la table -->
		<c:forEach items="${attribut_liste_matieres }" var="mat">
			<tr>
				<td>${mat.libelle}</td>
				

			
				<td>
				
					<a
					href="${pageContext.request.contextPath}/employe/update-employe-form?idemploye=${employe.idEmploye}">
						Modifier</a>
				</td>
				

			
				<td>
				
					<a
					href="${pageContext.request.contextPath}/employe/delete/${employe.idEmploye}">
						Supprimer</a>
				</td>

			</tr>

		</c:forEach>
	</table>
	
	<button type="button" class="btn btn-danger" style="margin-left:20px">Hello</button>


	<!-- =========================================================== -->
	<!-- ======== FOOTER  ========================================== -->
	<!-- =========================================================== -->
	
	<%@include file="../generic/footer.jsp" %>
	
	
</body>
</html>