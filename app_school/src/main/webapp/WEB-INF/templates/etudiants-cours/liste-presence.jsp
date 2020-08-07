<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste de présence</title>

<link href="${pageContext.request.contextPath}/resources/styles/bootstrap.css"
    rel="stylesheet">

</head>
<body>

	<jsp:include page="/WEB-INF/generic/header.jsp"></jsp:include>

	<%-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
	<%-- ++ AFFICHAGE LISTE DE PRESENCE DES ETUDIANTS AUX COURS DE LA BDD ++ --%>
	<%-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>

	 <h2><u>Liste de présence des étudiants</u></h2>
	 
	 <c:forEach items="${attribut_liste_presence}" var="presence">
	 	
	 	<table>
	 		<tr>
	 			<td>Id : </td>
	 			<td>${presence.idEtudiantCours}</td>
	 		</tr>
	 		<tr>
	 			<td>Absence : </td>
	 			<td>${presence.absence}</td>
	 		</tr>
	 		<tr>
	 			<td>Motif : </td>
	 			<td>${presence.motif}</td>
	 		</tr> 		
	 		<tr>
	 			<td>Id Etudiant : </td>
	 			<td>${presence.etudiant}</td>
	 		</tr>
	 		<tr>
	 			<td>Id Cours : </td>
	 			<td>${presence.cours}</td>
	 		</tr>	

	 		<%-- lien pour modifier une ligne --%> 		
	 		<tr>
	 			<td>
	 				<%-- au click on appelle le controller 'GestionEtudiantCoursController' et sa méthode chargerModifCoursBdd --%>
	 				<a href="${pageContext.request.contextPath}/etudiant-cours/formulaire-modif-presence?etudiantCoursId=${presence.idEtudiantCours}">modifier cours</a>
	 			</td>
	 		</tr>
	 		
	 		<%-- lien pour supprimer une ligne --%> 		
	 		<tr>
	 			<td>
	 				<%-- au click on appelle le controller 'GestionCEtudiantCoursController' et sa méthode supprimerEtudiantCoursBdd --%>
	 				<a href="${pageContext.request.contextPath}/etudiant-cours/supprimer?etudiantCoursId=${presence.idEtudiantCours}">supprimer cours</a>
	 			</td>
	 		</tr>
	 		
	 	</table>
	 	
	 	<hr/>
	 	
	 </c:forEach>

	<br/>
	
	<%-- lien pour ajouter une ligne dans la bdd : au click on appelle le controller GestionEtudiantCoursController et sa méthode chargerEtudiantCoursBdd --%>
	<a href="${pageContext.request.contextPath}/etudiant-cours/formulaire-ajout-presence">ajouter présence étudiant</a>

</body>
</html>