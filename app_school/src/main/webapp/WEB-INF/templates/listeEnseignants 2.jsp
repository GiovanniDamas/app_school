<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Page gestion des Enseignants</title>
</head>
<body>

	<h1>Liste des Enseignants</h1>

	<table border="1" width="60%">

		<tr>
			<td colspan="5" align="right"><a
				style="background-color: lightBlue"
				href="${pageContext.request.contextPath}/gestionEnseignants/form-edit?idPersonne=0">
					Ajout d'un enseignant </a></td>
		</tr>

		<!-- en tete de la table  -->
		<tr>
			<th>ID Enseignant</th>
			<th>Nom</th>
			<th>Prenom</th>
			<th>Email</th>
			<th>Identifiant</th>
			<th>Mot de passe</th>
			<th>Modifier</th>
			<th>Supprimer</th>
		</tr>

		<!-- donnée de la table -->
		<c:forEach items="${attribut_liste_enseignants}" var="ens">
			<tr>
				<td>${ens.idPersonne}</td>
				<td>${ens.nom}</td>
				<td>${ens.prenom}</td>
				<td>${ens.email}</td>
				<td>${ens.identifiant}</td>
				<td>${ens.motDePasse}</td>

				<!-- colonne pour la modification de l'enseignant -->
				<td>
					<!-- 
						- passage d'un param de requete nommé 'idemploye' ayant
					--> <a
					href="${pageContext.request.contextPath}/gestionEnseignants/form-edit?idPersonne=${ens.idPersonne}">Modifier</a>
				</td>

				<!-- colonne pour la suppression de l'emploe -->
				<td><a
					href="${pageContext.request.contextPath}/gestionEnseignants/delete?idPersonne=${ens.idPersonne}">Supprimer</a>
				</td>
			</tr>

		</c:forEach>
	</table>
	
	<a href="${pageContext.request.contextPath}/index.jsp">lien vers accueil</a>

</body>
</html>