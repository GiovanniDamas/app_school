<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des cours</title>
   
	<!-- Lien vers feuille de style de Bootstrap -->
	<link href="${pageContext.request.contextPath}/resources/styles/bootstrap.css"
		rel="stylesheet">

	<!-- Lien vers feuille de style perso de index -->
	<link href="${pageContext.request.contextPath}/resources/styles/index.css"
	    rel="stylesheet">

    <!-- Lien vers font awesome 4.7.0-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">

	<!-- Lien vers la font de la sidebar -->
    <link href="https://fonts.googleapis.com/css2?family=Cookie&display=swap" rel="stylesheet">
    
    <!--  lien font-awesome -->
    <script src="https://use.fontawesome.com/releases/v5.14.0/js/all.js" data-auto-a11y="true"></script>

</head>

<body>

	<!-- ===================================================== -->
	<!-- =============== HEADER ============================= -->
	<!-- ===================================================== -->
    <div id="divhaute" class="container-fluid col-lg-12">
		<h1 id="titre">SchoolApp</h1>
		
		<div id="connexion">
		<s:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_ENSEIGNANT', 'ROLE_ETUDIANT')">
			<h5>
			Bienvenue, ${attribut_personne_connecte.prenom} ${attribut_personne_connecte.nom}
			</h5>
		</s:authorize>
		
		<br/>
		
		<s:authorize
			access="hasAnyRole('ROLE_ETUDIANT', 'ROLE_ADMIN', 'ROLE_ENSEIGNANT')">
				<a href="${pageContext.request.contextPath}/logout" id="deconnexion"
				type="button" class="btn btn-dark" style="align-content: right"> <span class="fas fa-sign-out-alt" ></span> D�connexion</a>
		</s:authorize>

		<s:authorize access="hasRole('ROLE_ANONYMOUS')">
			<a href="${pageContext.request.contextPath}/login.jsp" id="connexion"
				type="button" class="btn btn-dark" > <span class="fa fa-user-circle" ></span> Se Connecter</a>
		</s:authorize>
		</div>

	</div>

<div class="wrapper">

	<!-- ===================================================== -->
	<!-- =============== SIDEBAR ============================= -->
	<!-- ===================================================== -->
    <nav class="sidebar" id="sidebar">
    
      <!--
        <button type="button" class="toggler" id="sidebarCollapse" > <span class="fa fa-arrow-left fa-2x"></span></button>
        -->

		<div class="sidebar-header">
			<a   href="${pageContext.request.contextPath}/index.jsp" ><span class="fa fa-home" style="margin-right: 5px;"></span>Accueil</a>
		</div>
		
    <ul class="sidebar-links">
      <li > <a  href="${pageContext.request.contextPath}/gestionEtudiants/listeEtudiants">Etudiant</a>   </li>   
      <li > <a  href="${pageContext.request.contextPath}/gestionEnseignants/listeEnseignants">Enseignant</a>    </li>
      <li > <a  href="#">Promotion</a>   </li>
      <li > <a  href="${pageContext.request.contextPath}/matiere/liste">Mati�re(s)</a>   </li>
      <li > <a  href="${pageContext.request.contextPath}/cours/liste">Cours</a>   </li>
      <li > <a  href="${pageContext.request.contextPath}/etudiants-cours/liste">Absence</a>   </li>
      <li > <a  href="${pageContext.request.contextPath}/aide/listeAide">Aide</a>   </li>  
    </ul>
    
	</nav>
		
	<!-- ===================================================== -->
	<!-- =============== CONTENT ============================= -->
	<!-- ===================================================== -->
  <div id="content" style="width:100%" align="left">
     <button type="button" id="sidebarCollapse" class="navbar-btn">
         <span></span>
         <span></span>
         <span></span>
     </button>

	<div style="padding: 30px;">
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
  					<label class="form-check-label" for="inlineRadio1">Mati�re</label>
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
	 <%-- 
	 <s:authentication property="authorities" var="authorites"/>
	 
	 <c:if test="${authorites.authority} == 'ROLE_ENSEIGNANT' "></c:if>

	 	 <br/><br/><br/><br/>
	 
	 <div class="content">
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
	 --%>	 
	 <%-- ================================================================================================== --%>
	 	 
	 <div>
	 <form class="form-inline" action="${pageContext.request.contextPath}/cours/recherche-matiere">
	 	 <label for="recherche-matiere">Rechercher par mati�re : </label>	 	
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
	 
 	<table class="table table-hover" style="width:90%; margin-left:5%";">
	 	
 	  <thead style="text-align: center;">
    		<tr>
      			<th scope="col">Id Cours</th>
      			<th scope="col">Libell�</th>
    			<th scope="col">Date</th>
      			<th scope="col">Dur�e</th>
      			<th scope="col">Description</th>
      			<th scope="col">Mati�re</th>
     			<th scope="col">Promotion</th>
      			<th scope="col">Modifier</th>
      			<th scope="col">Supprimer</th>
    		</tr>
  	  </thead>
	 
	 <c:forEach items="${attribut_liste_cours}" var="cours">
	 	
	 	  <tbody style="text-align: center;">
	 	  
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
	<a 	href="${pageContext.request.contextPath}/cours/formulaire-ajout"
		class="btn btn-primary btn-md active" role="button"
		aria-pressed="true" style="align-content: left;margin-left:5%" > 	
		Ajouter un cours
	</a>
	
	<br/><br/>
	</div>
	</div><!-- end content -->
</div><!-- end wrapper -->

	<!-- ===================================================== -->
	<!-- =============== FOOTER ============================== -->
	<!-- ===================================================== -->
  <div class="clear" style="clear:both"></div>
     
	<footer class="footer" >
  
        <p>2020 Copyright � Groupe2 : Gio, Hannah, Marl�ne &#x26; Gab  </p>
         
  </footer>

	<!-- ===================================================================== -->
	<!-- ==================  SCRIPTS  ======================================== -->
	<!-- ===================================================================== -->	

 	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/jquery-3.4.1.min.js"></script>	
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/bootstrap.js"></script>	

	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/sidebar.js"></script>	
    
</body>
</html>