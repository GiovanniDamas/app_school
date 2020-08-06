<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des cours</title>
</head>
<body>

	<%-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
	<%-- ++++++++++++ AFFICHAGE LISTE DES COURS DE LA BDD ++++++++++++++++++ --%>
	<%-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>

	 <h2><u>Liste de l'ensemble des cours</u></h2>
	 
	 <c:forEach items="${attribut_liste_cours}" var="cours">
	 	
	 	<table>
	 		<tr>
	 			<td>Id Cours : </td>
	 			<td>${cours.idCours}</td>
	 		</tr>
	 		<tr>
	 			<td>Libellé : </td>
	 			<td>${cours.libelle}</td>
	 		</tr>
	 		<tr>
	 			<td>Date : </td>
	 			<td>${cours.date}</td>
	 		</tr> 		
	 		<tr>
	 			<td>Durée : </td>
	 			<td>${cours.duree}</td>
	 		</tr>
	 		<tr>
	 			<td>Description : </td>
	 			<td>${cours.description}</td>
	 		</tr>	
	 		<tr>
	 			<td>Id matière : </td>
	 			<td>${cours.matieres}</td>
	 		</tr>
	 		<tr>
	 			<td>Id promotion : </td>
	 			<td>${cours.promotions}</td>
	 		</tr>
	 		
	 		<%-- lien pour modifier un cours --%> 		
	 		<tr>
	 			<td>
	 				<%-- au click on appelle le controller 'GestionCoursController' et sa méthode chargerModifCoursBdd --%>
	 				<a href="${pageContext.request.contextPath}/cours/formulaire-modif?coursId=${cours.idCours}">modifier cours</a>
	 			</td>
	 		</tr>
	 		
	 		<%-- lien pour supprimer un cours --%> 		
	 		<tr>
	 			<td>
	 				<%-- au click on appelle le controller 'GestionCoursController' et sa méthode supprimerCoursBdd --%>
	 				<a href="${pageContext.request.contextPath}/cours/supprimer?coursId=${cours.idCours}">supprimer cours</a>
	 			</td>
	 		</tr>
	 		
	 	</table>
	 	
	 	<hr/>
	 	
	 </c:forEach>

	<br/>
	
	<%-- lien pour ajouter un cours dans la bdd : au click on appelle le controller GestionCoursController et sa méthode chargerCoursBdd --%>
	<a href="${pageContext.request.contextPath}/cours/formulaire-ajout">ajouter cours</a>

</body>
</html>