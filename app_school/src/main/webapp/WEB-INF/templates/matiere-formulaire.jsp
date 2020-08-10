<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formulaire d'ajout d'une matière</title>

<link
	href="${pageContext.request.contextPath}/resources/styles/bootstrap.css"
	rel="stylesheet">

</head>
<body>
	<!-- =========================================================== -->
	<!-- ======== Header (navbar) ================================= -->
	<!-- =========================================================== -->
	<%@include file="../generic/header.jsp"%>


	<!-- =========================================================== -->
	<!-- ======== Content ========================================== -->
	<!-- =========================================================== -->

	<h1 style="margin-left: 20px">
		<u>Formulaire d'ajout d'une matière :</u>
	</h1>

	<div align="center">

		<form:form modelAttribute="attribut_matiere" method="POST"
			action="${pageContext.request.contextPath}/matiere/edit-matiere-form">

			<%-- affichage de tous les messages d'erreurs --%>
			<form:errors path="*" cssClass="erreur_validation" element="div" />

			<tr>
				<c:if test="${idMatiere} != 0"></c:if>
				<td><form:hidden path="idMatiere" /></td>

			</tr>

			<table width="60%">
				<tr>
					<td><form:label path="libelle">Libellé :</form:label></td>
					<td><form:input path="libelle" /></td>
					<td><form:errors path="libelle"
							cssStyle="color:red; font-style:italic;" /></td>
				</tr>

				<tr>
					<td><input type="submit" value="Ajouter" /></td>
				</tr>
				<tr>
					<c:if test="${idPersonne} != 0"></c:if>
					<td><input type="submit" value="Modifier" /></td>
				</tr>
			</table>

		</form:form>
	</div>

	<!-- =========================================================== -->
	<!-- ======== FOOTER  ========================================== -->
	<!-- =========================================================== -->
	<%@include file="../generic/footer.jsp"%>
</body>
</html>