<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulaire d'ajout d'un cours</title>

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
        <h1 id="titre"> SchoolApp </h1>
        <a href="${pageContext.request.contextPath}/login.jsp" id="connexion" type="button" class="btn btn-secondary">
            <span class="fa fa-user-circle"></span>
            Connexion
        </a>
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
      <li > <a  href="${pageContext.request.contextPath}/matiere/liste">Matière(s)</a>   </li>
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

	<%-- ====================================================== --%>
	<%-- ========= FORMULAIRE POUR AJOUTER UN COURS =========== --%>
	<%-- ====================================================== --%>
	<br/>
	
	<%-- à la soumission envoi d'une requête HTTP en POST vers 'GestionCoursController' et sa méthode ajouterCoursBdd--%>
	<div style="padding: 30px;">
	<h3>Ajout d'un nouveau cours</h3>
	
	<br/>
	
	<form:form modelAttribute="attribut_cours" method="POST" action="${pageContext.request.contextPath}/cours/ajouter">
	
	<%-- affichage de tous les messages d'erreurs --%>		
	<form:errors path="*" element="div" class="alert alert-dismissible alert-danger"/>	
		
		<fieldset>
			
			<div class="form-group">
			  	<form:label class="col-form-label" path="libelle">Libellé</form:label>
  				<form:input type="text" class="form-control" placeholder="Entrer le libellé du cours" path="libelle"/>
  				<form:errors path="libelle" cssStyle="color:red; font-style:italic;"/>
			</div>
			
			<div class="row">
			
			<div class="form-group col-md-4">
			  	<form:label class="col-form-label" path="date">Date</form:label>
  				<form:input type="date" class="form-control" path="date"/>
  				<form:errors path="date" cssStyle="color:red; font-style:italic;"/>
			</div>

			<div class="form-group col-md-4">
			  	<form:label class="col-form-label" path="duree">Durée</form:label>
			  	<div class="input-group mb-3">
  				<form:input type="number" class="form-control" path="duree"/>
  				<div class="input-group-append">
        			<span class="input-group-text">min</span>
      			</div>
      			</div>
  				<form:errors path="duree" cssStyle="color:red; font-style:italic;"/>
			</div>
			
			</div>		

			<div class="form-group">
			  	<form:label class="col-form-label" path="description">Description</form:label>
  				<form:textarea class="form-control" placeholder="Entrer la description du cours" path="description"/>
  				<form:errors path="description" cssStyle="color:red; font-style:italic;"/>
			</div>
			
			<div class="form-group">
			  	<form:label path="matieres.idMatiere">Matière</form:label>
				<form:select path="matieres.idMatiere" class="form-control">
              			<form:option value="" label="--Sélectionner la matière"/>
            			<form:options items="${attribut_matieres}" itemValue="idMatiere" itemLabel="libelle"/>
         		</form:select>  				
         		<form:errors path="matieres.idMatiere" cssStyle="color:red; font-style:italic;"/>
			</div>
			
			<div class="form-group">
			  	<form:label path="promotions.idPromotion">Promotion</form:label>
				<form:select path="promotions.idPromotion" class="form-control">
              			<form:option value="" label="--Sélectionner la promotion"/>
            			<form:options items="${attribut_promotions}" itemValue="idPromotion" itemLabel="libelle"/>
         		</form:select>  				
         		<form:errors path="promotions.idPromotion" cssStyle="color:red; font-style:italic;"/>
			</div>
			
		</fieldset>
		
		<br/>
		
		<input type="submit" class="btn btn-dark" value="Ajouter"/>
	
	</form:form>
	
	<br/><br/><br/>
	
	</div>
	
	</div><!-- end content -->
</div><!-- end wrapper -->

	<!-- ===================================================== -->
	<!-- =============== FOOTER ============================== -->
	<!-- ===================================================== -->
  <div class="clear" style="clear:both"></div>
     
	<footer class="footer" >
  
        <p>2020 Copyright © Groupe2 : Gio, Hannah, Marlène &#x26; Gab  </p>
         
  </footer>

	<!-- ===================================================================== -->
	<!-- ==================  SCRIPTS  ======================================== -->
	<!-- ===================================================================== -->	

 	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/jquery-3.4.1.min.js"></script>	
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/bootstrap.js"></script>	

	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/sidebar.js"></script>	
    
</body>
</html>