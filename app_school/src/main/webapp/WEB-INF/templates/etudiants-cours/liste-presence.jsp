<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

	<!-- =========================================================== -->
	<!-- ======== Header (navabar) ================================= -->
	<!-- =========================================================== -->
	
	<jsp:include page="/WEB-INF/generic/header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/generic/sidebar.jsp"></jsp:include>
	
	<%-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
	<%-- ++ AFFICHAGE LISTE DE PRESENCE DES ETUDIANTS AUX COURS DE LA BDD ++ --%>
	<%-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
<div class="content">
	 <h3>Liste de présence des étudiants</h3>
	 
	 <br/>
	 
	 <div>
	 <form class="form-inline" action="${pageContext.request.contextPath}/etudiants-cours/recherche-cours">
	 	 <label for="recherche-cours">Afficher la liste de présence d'un cours : </label>	 	
	 	<select class="form-control ml-2" id="recherche-cours" name="id-cours">
	 		<option value="0" label="Afficher tous les cours">
	 		<c:forEach items="${attribut_cours}" var="cours">
	 			 <option value="${cours.idCours}" label="${cours.libelle}"/>
	 		</c:forEach>
       	</select>
    	<button class="btn btn-outline-success ml-2" type="submit">Rechercher</button>
  	</form>
  	</div>
  	
  	<br/>
  	
  	<div>
	 <form class="form-inline" action="${pageContext.request.contextPath}/etudiants-cours/recherche-etudiant">
	 	 <label for="recherche-etudiant">Afficher les présences/absences d'un étudiant : </label>	 	
	 	<select class="form-control ml-2" id="recherche-etudiant" name="id-etudiant">
	 		<option value="0" label="Afficher tous les étudiants">
	 		<c:forEach items="${attribut_etudiants}" var="etudiant">
	 			 <option value="${etudiant.idPersonne}"><c:out value="${etudiant.nom} ${etudiant.prenom}"/></option>
	 		</c:forEach>
       	</select>
    	<button class="btn btn-outline-success ml-2" type="submit">Rechercher</button>
  	</form>
  	</div>
  	
  	<br/>
  	
  	 <div>
	 <form class="form-inline" action="${pageContext.request.contextPath}/etudiants-cours/recherche-absence-etudiant">
	 	 <label for="recherche-abs-etudiant">Afficher les absences d'un étudiant : </label>	 	
	 	<select class="form-control ml-2" id="recherche-abs-etudiant" name="etudiant-id">
	 		<option value="0" label="Afficher toutes les absences">
	 		<c:forEach items="${attribut_etudiants}" var="etudiant">
	 			 <option value="${etudiant.idPersonne}"><c:out value="${etudiant.nom} ${etudiant.prenom}"/></option>
	 		</c:forEach>
       	</select>
    	<button class="btn btn-outline-success ml-2" type="submit">Rechercher</button>
  	</form>
  	</div>
  	
	 <br/><br/><br/>
	 	 	
	 <table class="table table-hover" style="text-align: center">
	 	
	 	<thead>
    		<tr>
      			<th scope="row">Id</th>
      			<th scope="row">Absence</th>
    			<th scope="row">Motif</th>
      			<th scope="row">Etudiant</th>
      			<th scope="row">Cours</th>
      			<th scope="row">Modifier</th>
      			<th scope="row">Supprimer</th>
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

	 			<td>${presence.etudiant.nom} ${presence.etudiant.prenom}</td>

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

<br/><br/><br/>
	
	<!-- =========================================================== -->
	<!-- ======== FOOTER  ========================================== -->
	<!-- =========================================================== -->
	
	<%@include file="/WEB-INF/generic/footer.jsp" %>
</body>
</html>