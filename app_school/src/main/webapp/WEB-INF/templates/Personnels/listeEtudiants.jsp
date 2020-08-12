<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Page gestion des Etudiants</title>

<link
	href="${pageContext.request.contextPath}/resources/styles/bootstrap.css"
	rel="stylesheet">

<script src="https://kit.fontawesome.com/9dde17f0e3.js"
	crossorigin="anonymous"></script>


</head>
<body>

	<jsp:include page="/WEB-INF/generic/header.jsp"></jsp:include>
	
	
	<div style="width: 70%; float: right; margin-right: 10%">
		<a
			href="${pageContext.request.contextPath}/gestionEtudiants/form-edit?idPersonne=0"
			class="btn btn-primary btn-md active" role="button"
			aria-pressed="true" style="align-content: left"> Ajout d'un
			étudiant</a>

		<table class="table table-bordered">

			<h3 align="center">Liste des Etudiants</h3>
			<thead style="background-color: black; color: white">
				<tr>
					<th scope="col">#</th>
					<th scope="col">Nom</th>
					<th scope="col">Prénom</th>
					<th scope="col">Date de Naissance</th>
					<th scope="col">E-mail</th>
					<th scope="col">Identifiant</th>
					<th scope="col">Mot de passe</th>
					<th scope="col">Photo</th>
					<th scope="col">Modifier</th>
					<th scope="col">Supprimer</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${attribut_liste_etudiants}" var="etu">

					<tr>
						<th scope="row">${etu.idPersonne}</th>
						<th>${etu.nom}</th>
						<th>${etu.prenom}</th>
						<td>${etu.dateDeNaissance}</td>
						<td>${etu.email}</td>
						<td>${etu.identifiant}</td>
						<td>${etu.motDePasse}</td>
						<td>
						<img src="${pageContext.request.contextPath}/resources/Images/${etu.photo}" height="5%" width="10%">
						</td>
						
						<td><a
							href="${pageContext.request.contextPath}/gestionEtudiants/form-edit?idPersonne=${etu.idPersonne}"
							aria-pressed="true"><i class="fas fa-pencil-alt fa-2x"></i>
						</a></td>

						<!-- colonne pour la suppression de l'emploe -->
						<td><a
							href="${pageContext.request.contextPath}/gestionEtudiants/delete?idPersonne=${etu.idPersonne}">Supprimer</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>

	<div style="float: none; margin-top: 50%">
		<a href="${pageContext.request.contextPath}/index.jsp">lien vers
			accueil</a>

		<jsp:include page="/WEB-INF/generic/footer.jsp"></jsp:include>
	</div>

</body>
</html>