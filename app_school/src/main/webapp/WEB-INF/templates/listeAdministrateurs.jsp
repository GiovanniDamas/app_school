<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Page gestion des Administrateurs</title>
</head>
<body>

	<h1>Liste des Administrateurs</h1>

	<table border="1" width="60%">

		<tr>
			<td colspan="5" align="right">
				<%-- au click sur le lien : envoie d'une requete http en GET vers méthode afficherFormulaireEdition() 
					 du controleur employeController. Cette methode est associée à l'url /employe/add-employe-form
				 --%> <a style="background-color: lightBlue"
				href="${pageContext.request.contextPath}/gestionAdmin/form-edit?idPersonne=0">
					Ajout d'un administrateur </a>
			</td>
		</tr>

		<!-- en tete de la table  -->
		<tr>
			<th>ID Admin</th>
			<th>Nom</th>
			<th>Prenom</th>
			<th>Email</th>
			<th>Identifiant</th>
			<th>Mot de passe</th>
			<th>Modifier</th>
			<th>Supprimer</th>
		</tr>

		<!-- donnée de la table -->
		<c:forEach items="${attribut_liste_admin}" var="admin">
			<tr>
				<td>${admin.idAdmin}</td>
				<td>${admin.nom}</td>
				<td>${admin.prenom}</td>
				<td>${admin.email}</td>
				<td>${admin.identifiant}</td>
				<td>${admin.motDePasse}</td>

				<!-- colonne pour la modification de l'employé -->
				<td>
					<!--
						- passage d'un param de requete nommé 'idemploye' ayant
					--> 
					<a href="${pageContext.request.contextPath}/gestionAdmin/form-edit?idPersonne=${admin.idPersonne}">Modifier</a>
				</td>

				<!-- colonne pour la suppression de l'emploe -->
				<td><a
					href="${pageContext.request.contextPath}/gestionAdmin/delete?idPersonne=${admin.idPersonne}">Supprimer</a>
				</td>
			</tr>
			
		</c:forEach>

	</table>
	
	<a href="${pageContext.request.contextPath}/index.jsp">lien vers accueil</a>

</body>
</html>