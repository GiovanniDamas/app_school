<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listes des promotions</title>

<link
	href="${pageContext.request.contextPath}/resources/styles/bootstrap.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/styles/custom.css"
	rel="stylesheet">

</head>
<body>

	<!-- =========================================================== -->
	<!-- ======== Header (navbar) ================================= -->
	<!-- =========================================================== -->

	<%@include file="/WEB-INF/generic/header.jsp"%>


	<!-- =========================================================== -->
	<!-- ======== Content ========================================== -->
	<!-- =========================================================== -->


	<h1 style="margin-left: 20px">
		<u>Liste des Promotions:</u>
	</h1>

	<table class="table table-hover" style="width: 70%;">

		<tr>

			<td colspan="5" align="right"><a
				href="${pageContext.request.contextPath}/promotion/edit-promotion-form?idPromotion=0">
					Ajout d'une Promotion </a></td>
		</tr>
		<thead>
			<tr>
				<th scope="col">ID</th>
				<th scope="col">Libelle</th>
				<th scope="col">Modifier</th>
				<th scope="col">Supprimer</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${attribut_liste_promotions }" var="prom">
				<tr>
					<td>${prom.idPromotion}</td>
					<td>${prom.libelle}</td>
					<td><a
						href="${pageContext.request.contextPath}/promotion/edit-promotion-form?idPromotion=${prom.idPromotion}">
							Modifier</a></td>
					<td><a
						href="${pageContext.request.contextPath}/promotion/delete?idPromotion=${prom.idPromotion}">
							Supprimer</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<!-- =========================================================== -->
	<!-- ======== FOOTER  ========================================== -->
	<!-- =========================================================== -->
	<%@include file="/WEB-INF/generic/footer.jsp"%>

</body>
</html>