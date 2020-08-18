<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ajouter un étudiant à un cours</title>

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
 	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/jquery-3.4.1.min.js"></script>	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/bootstrap.bundle.min.js"></script>	

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
	<h3>Ajout de l'absence d'un étudiant à un cours</h3>
	
	<br/>	
	
	<%-- à la soumission envoi d'une requête HTTP en POST vers 'GestionEtudiantCoursController' et sa méthode ajouterEtudiantCoursBdd--%>
	
	<form:form modelAttribute="attribut_etudiant_cours" method="POST" action="${pageContext.request.contextPath}/etudiants-cours/ajouter">

	<%-- affichage de tous les messages d'erreurs --%>		
	<form:errors path="*" class="alert alert-dismissible alert-danger" element="div"/>	
		
		<fieldset>
			
			<div>
				<legend style="font-size: 12pt;">Absence</legend>
  				<form:checkbox path="absence"/>
  				<form:errors path="absence" cssStyle="color:red; font-style:italic;"/>
			</div>	
		
		<br/>
		
			<div class="form-group">
			  	<form:label class="col-form-label" path="motif">Motif</form:label>
  				<form:textarea class="form-control" placeholder="Entrer la description du cours" path="motif"/>
  				<form:errors path="motif" cssStyle="color:red; font-style:italic;"/>
			</div>
			
			<div class="form-group">
			  	<form:label path="etudiant.idPersonne">Etudiant</form:label>			  	
         		<form:select path="etudiant.idPersonne" class="form-control">
    				<option value="">--Sélectionner l'étudiant</option>
    					<c:forEach items="${attribut_etudiants}" var="etudiant">
        					<form:option value="${etudiant.idPersonne}"><c:out value="${etudiant.nom} ${etudiant.prenom}"/></form:option>
    					</c:forEach>
				</form:select>			
         		<form:errors path="etudiant.idPersonne" cssStyle="color:red; font-style:italic;"/>
			</div>
			
			<div class="form-group">
			  	<form:label path="cours.idCours">Cours</form:label>
				<form:select path="cours.idCours" class="form-control">
              			<form:option value="" label="--Sélectionner le cours"/>
            			<form:options items="${attribut_cours}" itemValue="idCours" itemLabel="libelle"/>
         		</form:select>  				
         		<form:errors path="cours.idCours" cssStyle="color:red; font-style:italic;"/>
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

	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/sidebar.js"></script>	
</body>
</html>