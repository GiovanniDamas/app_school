<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<link
	href="${pageContext.request.contextPath}/resources/styles/bootstrap.css"
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

	<h1 style="margin-left: 20px">
		<u>Liste des matières:</u>
	</h1>

	<table class="table table-hover" style="width: 70%;">

		<tr>

			<td colspan="5" align="right"><a
				href="${pageContext.request.contextPath}/matiere/edit-matiere-form?idMatiere=0">
					Ajout d'une matière </a></td>
		</tr>

		<!-- en tête de la table -->
		<thead>
			<tr>
				<th scope="col">ID</th>
				<th scope="col">Libelle</th>
				<th scope="col">Modifier</th>
				<th scope="col">Supprimer</th>
			</tr>
		</thead>

		<!-- données de la table -->

		<tbody>
			<c:forEach items="${attribut_liste_matieres }" var="mat">
				<tr>

					<td>${mat.idMatiere}</td>
					<td>${mat.libelle}</td>



					<td><a
						href="${pageContext.request.contextPath}//matiere/edit-matiere-form?idMatiere=${mat.idMatiere}">
							Modifier</a></td>



					<td><a
						href="${pageContext.request.contextPath}/matiere/delete?idMatiere=${mat.idMatiere}">
							Supprimer</a></td>

				</tr>

			</c:forEach>
		</tbody>
	</table>



	<!-- =========================================================== -->
	<!-- ======== FOOTER  ========================================== -->
	<!-- =========================================================== -->

	<%@include file="../generic/footer.jsp"%>


</body>
</html>