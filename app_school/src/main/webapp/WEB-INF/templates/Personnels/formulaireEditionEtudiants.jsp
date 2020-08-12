<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Formulaire pour l'édition de l'étudiant</title>
</head>
<body>

	<br />
	<div align="center">
		<h1>Formulaire édition d'un étudiant</h1>
	</div>

	<br />
	<div align="center">

		<form:form modelAttribute="attribut_etudiants" method="POST"
			action="${pageContext.request.contextPath}/gestionEtudiants/edit">

			<%-- affichage de tous les messages d'erreurs --%>
			<form:errors path="*" cssClass="erreur_validation" element="div" />

			<tr>
				<c:if test="${idPersonne} != 0"></c:if>
				<td><form:hidden path="idPersonne"/></td>
				
			</tr>

			<table width="60%">
				<tr>
					<td><form:label path="nom">Nom :</form:label></td>
					<td><form:input path="nom" /></td>
					<td><form:errors path="nom"
							cssStyle="color:red; font-style:italic;" /></td>
				</tr>
				<tr>
					<td><form:label path="prenom">Prénom :</form:label></td>
					<td><form:input path="prenom" /></td>
					<td><form:errors path="prenom"
							cssStyle="color:red; font-style:italic;" /></td>
				</tr>
				<tr>
					<td><form:label path="dateDeNaissance">Date de naissance :</form:label></td>
					<td><form:input type="date" path="dateDeNaissance" /></td>
					<td><form:errors path="dateDeNaissance"
							cssStyle="color:red; font-style:italic;" /></td>
				</tr>
				<tr>
					<td><form:label path="email">E-mail :</form:label></td>
					<td><form:input path="email" /></td>
					<td><form:errors path="email"
							cssStyle="color:red; font-style:italic;" /></td>
				</tr>
				<tr>
					<td><form:label path="identifiant">Identifiant :</form:label></td>
					<td><form:input path="identifiant" /></td>
					<td><form:errors path="identifiant"
							cssStyle="color:red; font-style:italic;" /></td>
				</tr>
				<tr>
					<td><form:label path="motDePasse">Mot de passe :</form:label></td>
					<td><form:input path="motDePasse" /></td>
					<td><form:errors path="motDePasse"
							cssStyle="color:red; font-style:italic;" /></td>
				</tr>

				<tr>
					<td><input type="submit" value="Ajouter" /></td>
				</tr>
				<tr>
					<c:if test="${idPersonne} != 0"></c:if>
					<td><input type="submit" value="Modifier"/></td>
				</tr>
			</table>

		</form:form>
	</div>
</body>
</html>