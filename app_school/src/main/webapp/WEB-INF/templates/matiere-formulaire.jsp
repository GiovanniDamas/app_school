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
	
	<jsp:include page="/WEB-INF/generic/header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/generic/sidebar.jsp"></jsp:include>


	<!-- =========================================================== -->
	<!-- ======== Content ========================================== -->
	<!-- =========================================================== -->

	<h2 style="margin-left: 20px">
		<u>Formulaire d'ajout/édition d'une matière :</u>
	</h2>
	<div class="form-group">

		<form:form modelAttribute="matiereCommand"  method="POST"
			action="${pageContext.request.contextPath}/matiere/add">

			<%-- affichage de tous les messages d'erreurs --%>
			<form:errors path="*" cssClass="erreur_validation" element="div" />

			<table width="60%">


				<tr>

					<td><form:hidden path="idMatiere" /></td>

				</tr>

				<tr>
					<td><form:label class="col-form-label" path="libelle"> Libellé :</form:label></td>
					<td><form:input class="form-control" path="libelle" /></td>
					<td><form:errors path="libelle"
							cssStyle="color:red; font-style:italic;" /></td>
				</tr>
				
				
				
				
				<c:if test="${idMatiere == null}"  >
					<tr>
						<td><input type="submit" value="Ajouter" /></td>
					</tr>
				</c:if>
				
				<c:if test="${idMatiere != null}">
					<tr>
						<td><input type="submit" value="Modifier" /></td>
					</tr>
				</c:if>
			</table>
		</form:form>
	</div>

	<!-- =========================================================== -->
	<!-- ======== FOOTER  ========================================== -->
	<!-- =========================================================== -->
	<%@include file="../generic/footer.jsp"%>
</body>
</html>