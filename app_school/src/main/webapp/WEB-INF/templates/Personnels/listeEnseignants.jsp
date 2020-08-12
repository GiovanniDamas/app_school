<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Page gestion des Enseignants</title>

<link
	href="${pageContext.request.contextPath}/resources/styles/bootstrap.css"
	rel="stylesheet">
	
</head>
<body>

		<jsp:include page="/WEB-INF/generic/header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/generic/sidebar.jsp"></jsp:include> 

	<div style="width: 70%; float: right; margin-right: 10%">

		<a
			href="${pageContext.request.contextPath}/gestionEnseignants/form-edit?idPersonne=0"
			class="btn btn-primary btn-md active" role="button"
			aria-pressed="true" style="align-content: left"> Ajout d'un
			étudiant</a>

		<table class="table table-bordered">

			<h3 align="center">Liste des Enseignant</h3>
			<thead style="background-color: black; color: white">
				<tr>
					<th scope="col">#</th>
					<th scope="col">Nom</th>
					<th scope="col">Prénom</th>
					<th scope="col">E-mail</th>
					<th scope="col">Identifiant</th>
					<th scope="col">Mot de passe</th>
					<th scope="col">Modifier</th>
					<th scope="col">Supprimer</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${attribut_liste_enseignants}" var="ens">
					<tr>
						<th scope="row">${ens.idPersonne}</th>
						<th>${ens.nom}</th>
						<th>${ens.prenom}</th>
						<td>${ens.email}</td>
						<td>${ens.identifiant}</td>
						<td>${ens.motDePasse}</td>
						<td><a
							href="${pageContext.request.contextPath}/gestionEnseignants/form-edit?idPersonne=${ens.idPersonne}"
							aria-pressed="true"><i class="fas fa-pencil-alt fa-3x"></i>Modifier
						</a></td>

						<!-- colonne pour la suppression de l'emploe -->
						<td><a
							href="${pageContext.request.contextPath}/gestionEnseignants/delete?idPersonne=${ens.idPersonne}">Supprimer</a>
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