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
<link href="${pageContext.request.contextPath}/resources/styles/perso.css"
    rel="stylesheet">
<script src="https://kit.fontawesome.com/9dde17f0e3.js" crossorigin="anonymous"></script>  
</head>
<body>

	<jsp:include page="/WEB-INF/generic/header.jsp"></jsp:include>

	<%-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
	<%-- ++ AFFICHAGE LISTE DE PRESENCE DES ETUDIANTS AUX COURS DE LA BDD ++ --%>
	<%-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
<div class="content">
	 <h3>Liste de présence des étudiants</h3>
	 
	 <br/><br/>
	 	 	
	 <table class="table" style="text-align: center">
	 	
	 	<thead class="thead-light">
    		<tr>
      			<th scope="col">Id</th>
      			<th scope="col">Absence</th>
    			<th scope="col">Motif</th>
      			<th scope="col">Etudiant</th>
      			<th scope="col">Cours</th>
      			<th scope="col">Modifier</th>
      			<th scope="col">Supprimer</th>
    		</tr>
  	  </thead>
  	  
  	  <c:forEach items="${attribut_liste_presence}" var="presence">
  	  
  	  	<tbody>
	 	  
	 	  	<tr>
	 			<td>${presence.idEtudiantCours}</td>

	 			<td>
	 				<c:if test="${presence.absence==true}"><input type="checkbox" checked="checked" disabled="disabled"></c:if>
	 				<c:if test="${presence.absence==false}"><input type="checkbox" value="" disabled="disabled"></c:if>
	 			</td>

	 			<td>${presence.motif}</td>

	 			<td>${presence.etudiant}</td>

	 			<td>${presence.cours.libelle}</td>

	 		<%-- lien pour modifier une ligne --%> 		
	 			<td>
	 				<%-- au click on appelle le controller 'GestionEtudiantCoursController' et sa méthode chargerModifCoursBdd --%>
	 				<a href="${pageContext.request.contextPath}/etudiants-cours/formulaire-modif-presence?etudiantCoursId=${presence.idEtudiantCours}">
	 					<i class="fas fa-pencil-alt"></i>
	 				</a>
	 			</td>
	 		
	 		<%-- lien pour supprimer une ligne --%> 		
	 			<td>
	 				<%-- au click on appelle le controller 'GestionCEtudiantCoursController' et sa méthode supprimerEtudiantCoursBdd --%>
	 				<a href="${pageContext.request.contextPath}/etudiants-cours/supprimer?etudiantCoursId=${presence.idEtudiantCours}">
	 					<i class="fas fa-trash-alt"></i>
	 				</a>
	 			</td>
	 		</tr>
	 		
	 		</tbody>
	 	</c:forEach>
	 		
	 	</table>	 	

	<br/>
	
	<%-- lien pour ajouter une ligne dans la bdd : au click on appelle le controller GestionEtudiantCoursController et sa méthode chargerEtudiantCoursBdd --%>
	<a href="${pageContext.request.contextPath}/etudiants-cours/formulaire-ajout-presence">Ajouter absence étudiant</a>
</div>
</body>
</html>