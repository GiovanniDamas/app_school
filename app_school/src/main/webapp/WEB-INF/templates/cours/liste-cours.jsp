<%@ page language="java" contentType="text/html; charset=UTF-8"
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

	<!-- =========================================================== -->
	<!-- ======== Header (navabar) ================================= -->
	<!-- =========================================================== -->
	
	<jsp:include page="/WEB-INF/generic/header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/generic/sidebar.jsp"></jsp:include>
	
	<%-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
	<%-- ++++++++++++ AFFICHAGE LISTE DES COURS DE LA BDD ++++++++++++++++++ --%>
	<%-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
<div class="content">

	 <h3>Liste de l'ensemble des cours</h3>
	 
	 <br/>
	 
	 <%-- 
	 
	 <form id="rechercher" name="rechercher">
	 
	 <fieldset class="form-group col-12">
		<div class="row">
			<legend class="col-form-label col-sm-2 pt-0">Rechercher selon : </legend>
		 	<div class="col-sm-10">

				<div class="form-check form-check-inline">
  					<input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1"/>
  					<label class="form-check-label" for="inlineRadio1">Matière</label>
				</div>
	
				<div class="form-check form-check-inline">
  					<input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2"/>
  					<label class="form-check-label" for="inlineRadio2">Promotion</label>
				</div>
	
				<div class="form-check form-check-inline">
  					<input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio3" value="option3"/>
  					<label class="form-check-label" for="inlineRadio3">Date</label>
				</div>
	
				<div class="form-check form-check-inline">
  					<input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio3" value="option3" />
  					<label class="form-check-label" for="inlineRadio3">Afficher tous</label>
				</div>
			</div>
		</div>
  	</fieldset>
  	
  	 <button class="btn btn-outline-success my-2 my-sm-0 col-2" type="submit">Valider</button>
  		 
	</form>
	
	 <br/><br/><br/><br/>
	 
	 --%>
	 
	 <%-- ================== TEST COURS D'UN ENSEIGNANT ======================================== --%>
	 
	 	 <br/><br/><br/><br/>
	 
	 <div>
	 <form class="form-inline" action="${pageContext.request.contextPath}/cours/recherche-enseignant">
	 	 <label for="recherche-enseignant">Rechercher cours d'un enseignant : </label>	 	
	 	<select class="form-control ml-2" id="recherche-enseignant" name="id-enseignant">
	 		<option value="0" label="Afficher tous les cours">
	 		<c:forEach items="${attribut_enseignant}" var="ens">
	 			 <option value="${ens.idPersonne}" label="${ens.nom}"/>
	 		</c:forEach>
       	</select>
    	<button class="btn btn-outline-success ml-2" type="submit">Rechercher</button>
  	</form>
  	</div>
	 
	 	 <br/><br/><br/><br/>
	 
	 <%-- ================================================================================================== --%>
	 
	 
	 <div>
	 <form class="form-inline" action="${pageContext.request.contextPath}/cours/recherche-matiere">
	 	 <label for="recherche-matiere">Rechercher par matière : </label>	 	
	 	<select class="form-control ml-2" id="recherche-matiere" name="id-matiere">
	 		<option value="0" label="Afficher tous les cours">
	 		<c:forEach items="${attribut_matieres}" var="matiere">
	 			 <option value="${matiere.idMatiere}" label="${matiere.libelle}"/>
	 		</c:forEach>
       	</select>
    	<button class="btn btn-outline-success ml-2" type="submit">Rechercher</button>
  	</form>
  	</div>
  	
  	<br/>
  	
  	<div>
	 <form class="form-inline" action="${pageContext.request.contextPath}/cours/recherche-promotion">
	 	 <label for="recherche-promotion">Rechercher par promotion : </label>	 	
	 	<select class="form-control ml-2" id="recherche-promotion" name="id-promo">
	 		<option value="0" label="Afficher tous les cours">
	 		<c:forEach items="${attribut_promotions}" var="promotion">
	 			 <option value="${promotion.idPromotion}" label="${promotion.libelle}"/>
	 		</c:forEach>
       	</select>
    	<button class="btn btn-outline-success ml-2" type="submit">Rechercher</button>
  	</form>
  	</div>
  	
  	  	<br/>
  	
  	<div>
	 <form class="form-inline" action="${pageContext.request.contextPath}/cours/recherche-date">
	 	 <label for="recherche-date">Rechercher par date :  </label>
	 	 <input id="recherche-date" name="date" type="date" class="form-control"/>	 	
    	<button class="btn btn-outline-success ml-2" type="submit">Rechercher</button>
  	</form>
  	</div>
  	
  	<br/><br/>
	 
 	<table class="table table-hover" style="text-align: center">
	 	
 	  <thead>
    		<tr>
      			<th scope="col">Id Cours</th>
      			<th scope="col">Libellé</th>
    			<th scope="col">Date</th>
      			<th scope="col">Durée</th>
      			<th scope="col">Description</th>
      			<th scope="col">Matière</th>
     			<th scope="col">Promotion</th>
      			<th scope="col">Modifier</th>
      			<th scope="col">Supprimer</th>
    		</tr>
  	  </thead>
	 
	 <c:forEach items="${attribut_liste_cours}" var="cours">
	 	
	 	  <tbody>
	 	  
	 	  	<tr>
	 			<td>${cours.idCours}</td>

	 			<td>${cours.libelle}</td>

	 			<td><fmt:formatDate value="${cours.date}" pattern="dd/MM/yyyy"/></td>

	 			<td>${cours.duree} min</td>

	 			<td>${cours.description}</td>

	 			<td>${cours.matieres.idMatiere}</td>

	 			<td>${cours.promotions.idPromotion}</td>
	 		
	 		
	 		<%-- lien pour modifier un cours --%> 		
	 			<td>
	 				<%-- au click on appelle le controller 'GestionCoursController' et sa méthode chargerModifCoursBdd --%>
	 				<a href="${pageContext.request.contextPath}/cours/formulaire-modif?coursId=${cours.idCours}">
	 					<i class="fas fa-pencil-alt"></i>
	 				</a>
	 			</td>
	 		
	 		<%-- lien pour supprimer un cours --%> 		
	 			<td>
	 				<%-- au click on appelle le controller 'GestionCoursController' et sa méthode supprimerCoursBdd --%>
	 				<a href="${pageContext.request.contextPath}/cours/supprimer?coursId=${cours.idCours}">
	 					<i class="fas fa-trash-alt"></i>
					</a>
	 			</td>
	 		</tr>	
	 	  </tbody>
	 	  
	  </c:forEach>	  	
	 
	 </table>	

	<br/>
	
	<%-- lien pour ajouter un cours dans la bdd : au click on appelle le controller GestionCoursController et sa méthode chargerCoursBdd --%>
	<a href="${pageContext.request.contextPath}/cours/formulaire-ajout">Ajouter un cours</a>
</div>

<br/><br/><br/>

	<!-- =========================================================== -->
	<!-- ======== FOOTER  ========================================== -->
	<!-- =========================================================== -->
	
	<%@include file="/WEB-INF/generic/footer.jsp" %>
</body>
</html>