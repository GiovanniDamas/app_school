<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Formulaire pour l'édition de l'étudiant</title>


<link
	href="${pageContext.request.contextPath}/resources/styles/bootstrap.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/styles/perso.css"
	rel="stylesheet">

</head>
<body>

	<!-- =========================================================== -->
	<!-- ======== Header (navabar) ================================= -->
	<!-- =========================================================== -->

	<jsp:include page="/WEB-INF/generic/header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/generic/sidebar.jsp"></jsp:include>

	<%-- ====================================================== --%>
	<%-- ========= FORMULAIRE POUR AJOUTER UN COURS =========== --%>
	<%-- ====================================================== --%>
	<br />

	<div align="center">
		<h1>Formulaire édition d'un étudiant</h1>
	</div>

	<br />
	<div align="center">

		<form:form modelAttribute="attribut_etudiants" method="POST"
			action="${pageContext.request.contextPath}/gestionEtudiants/edit"
			enctype="multipart/form-data">

			<tr>
				<c:if test="${idPersonne} != 0"></c:if>
				<td><form:hidden path="idPersonne" /></td>

			</tr>

			<fieldset>
				<div class="form-group" style="margin-left: 30%; margin-right: 30%">
					<form:label class="col-form-label" path="nom">Nom</form:label>
					<form:input type="text" class="form-control" path="nom" />
					<form:errors path="nom" cssStyle="color:red; font-style:italic;" />
				</div>
				<div class="form-group" style="margin-left: 30%; margin-right: 30%">
					<form:label class="col-form-label" path="prenom">Prénom</form:label>
					<form:input type="text" class="form-control" path="prenom" />
					<form:errors path="prenom" cssStyle="color:red; font-style:italic;" />
				</div>
				<div class="form-group" style="margin-left: 30%; margin-right: 30%">
					<form:label class="col-form-label" path="dateDeNaissance">Date de Naissance</form:label>
					<form:input type="date" class="form-control" path="dateDeNaissance" />
					<form:errors path="dateDeNaissance"
						cssStyle="color:red; font-style:italic;" />
				</div>

				<div class="form-group" style="margin-left: 30%; margin-right: 30%">
					<form:label class="col-form-label" path="email">Email</form:label>
					<form:input type="text" class="form-control" path="email" />
					<form:errors path="email" cssStyle="color:red; font-style:italic;" />
				</div>

				<div class="form-group" style="margin-left: 30%; margin-right: 30%">
					<form:label class="col-form-label" path="identifiant">Identifiant</form:label>
					<form:input type="text" class="form-control" path="identifiant" />
					<form:errors path="identifiant"
						cssStyle="color:red; font-style:italic;" />
				</div>

				<div class="form-group" style="margin-left: 30%; margin-right: 30%">
					<form:label class="col-form-label" path="motDePasse">Mot de Passe</form:label>
					<form:input type="text" class="form-control" path="motDePasse" />
					<form:errors path="motDePasse"
						cssStyle="color:red; font-style:italic;" />
				</div>

				<div class="form-group" style="margin-left: 30%; margin-right: 30%">
					<form:label path="promotion.idPromotion" >Promotion</form:label>
					

					<form:select path="promotion.idPromotion" class="form-control">
						<form:option value="" label="--Sélectionner la promotion" />
						<form:options items="${attribut_promotions}"
							itemValue="idPromotion" itemLabel="libelle"  />
					</form:select>

					<form:errors path="promotion.idPromotion"
						cssStyle="color:red; font-style:italic;" />
				</div>
				<div class="form-group">
					<form:label path="photo">Photo</form:label>
					<input type="file" name="file" />
				</div>

				<div class="form-group">
					<input type="submit" value="Ajouter" />

					<c:if test="${idPersonne} != 0"></c:if>
					<input type="submit" value="Modifier" />
				</div>

			</fieldset>

		</form:form>
	</div>
</body>
</html>