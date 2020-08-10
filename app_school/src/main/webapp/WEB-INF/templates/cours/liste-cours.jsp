<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des cours</title>

<link href="${pageContext.request.contextPath}/resources/styles/bootstrap.css"
    rel="stylesheet">
    
<link href="${pageContext.request.contextPath}/resources/styles/perso.css"
    rel="stylesheet">
    
<script src="https://kit.fontawesome.com/9dde17f0e3.js" crossorigin="anonymous"></script>
    
</head>
<body>

	<jsp:include page="/WEB-INF/generic/header.jsp"></jsp:include>

	<%-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
	<%-- ++++++++++++ AFFICHAGE LISTE DES COURS DE LA BDD ++++++++++++++++++ --%>
	<%-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
<div class="content">

	 <h3>Liste de l'ensemble des cours</h3>
	 
	 <div class="recherche">
	 <p>Rechercher selon : </p>
	 <div class="form-check form-check-inline">
  		<input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1"/>
  		<label class="form-check-label" for="inlineRadio1">Mati�re</label>
	</div>
	<div class="form-check form-check-inline">
  		<input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2"/>
  		<label class="form-check-label" for="inlineRadio2">Promotion</label>
	</div>
	<div class="form-check form-check-inline">
  		<input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio3" value="option3" />
  		<label class="form-check-label" for="inlineRadio3">Date</label>
	</div>
	 
  	<form class="form-inline">
    	<input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
    	<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Rechercher</button>
  	</form>
	</div>
	
	 <br/><br/><br/><br/><br/><br/>
	 
 	<table class="table" style="text-align: center">
	 	
 	  <thead class="thead-light">
    		<tr>
      			<th scope="col">Id Cours</th>
      			<th scope="col">Libell�</th>
    			<th scope="col">Date</th>
      			<th scope="col">Dur�e</th>
      			<th scope="col">Description</th>
      			<th scope="col">Id Mati�re</th>
     			<th scope="col">Id Promotion</th>
      			<th scope="col">Modifier</th>
      			<th scope="col">Supprimer</th>
    		</tr>
  	  </thead>
	 
	 <c:forEach items="${attribut_liste_cours}" var="cours">
	 	
	 	  <tbody>
	 	  
	 	  	<tr>
	 			<td>${cours.idCours}</td>

	 			<td>${cours.libelle}</td>

	 			<td><fmt:formatDate value="${cours.date}" pattern="dd-MM-yyyy"/></td>

	 			<td>${cours.duree}</td>

	 			<td>${cours.description}</td>

	 			<td>${cours.matieres.idMatiere}</td>

	 			<td>${cours.promotions.idPromotion}</td>
	 		
	 		
	 		<%-- lien pour modifier un cours --%> 		
	 			<td>
	 				<%-- au click on appelle le controller 'GestionCoursController' et sa m�thode chargerModifCoursBdd --%>
	 				<a href="${pageContext.request.contextPath}/cours/formulaire-modif?coursId=${cours.idCours}">
	 					<i class="fas fa-pencil-alt"></i>
	 				</a>
	 			</td>
	 		
	 		<%-- lien pour supprimer un cours --%> 		
	 			<td>
	 				<%-- au click on appelle le controller 'GestionCoursController' et sa m�thode supprimerCoursBdd --%>
	 				<a href="${pageContext.request.contextPath}/cours/supprimer?coursId=${cours.idCours}">
	 					<i class="fas fa-trash-alt"></i>
					</a>
	 			</td>
	 		</tr>	
	 	  </tbody>
	 	  
	  </c:forEach>	  	
	 
	 </table>	

	<br/>
	
	<%-- lien pour ajouter un cours dans la bdd : au click on appelle le controller GestionCoursController et sa m�thode chargerCoursBdd --%>
	<a href="${pageContext.request.contextPath}/cours/formulaire-ajout">Ajouter un cours</a>
</div>
</body>
</html>