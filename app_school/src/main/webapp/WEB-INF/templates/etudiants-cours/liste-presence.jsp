<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liste de présence</title>


 
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
	<link href="https://fonts.googleapis.com/css2?family=Fredericka+the+Great&display=swap" rel="stylesheet"> <!-- 'Fredericka the Great' -->


<link href="${pageContext.request.contextPath}/resources/styles/perso.css"
    rel="stylesheet">
    
<script src="https://kit.fontawesome.com/9dde17f0e3.js" crossorigin="anonymous"></script> 

 	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/jquery-3.4.1.min.js"></script>	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/bootstrap.bundle.min.js"></script>	

</head>

<body>

	<!-- ===================================================== -->
	<!-- =============== HEADER ============================= -->
	<!-- ===================================================== -->


	<div id="divhaute" class="container-fluid col-lg-12">
		<h1 id="titre">SchoolApp</h1>
		
		<div id="connexion">

			<c:if test="${attribut_help != null}">
				<!-- Bouton d'aide lançant un modal -->
				<button type="button" class="btn" 
						data-toggle="modal" data-target="#exampleModal" 
						style="padding:0;max-width:16x;max-height:16px" >
				  	<span class="fa fa-info-circle fa-1x" style="align:center;color:white"></span>
				</button>
						
				<!-- Modal -->
				<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" style="color:black">
				  <div class="modal-dialog" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="exampleModalLabel">${attribut_help.urlPage}</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				      </div>
				      <div class="modal-body" style="text-align:center;">
				        <p>${attribut_help.contenu}</p>
				      </div>					
					</div><!-- end modal content -->
				  </div>
				</div> 
		  	</c:if>
		  <br/>		
		
		<s:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_ENSEIGNANT', 'ROLE_ETUDIANT')">
			<h5 style="margin-top:5px;margin-bottom:20px">
			Bienvenue, ${attribut_personne_connecte.prenom} ${attribut_personne_connecte.nom}
			</h5>
		</s:authorize>
		
		<s:authorize
			access="hasAnyRole('ROLE_ETUDIANT', 'ROLE_ADMIN', 'ROLE_ENSEIGNANT')">
			
			<div class="dropdown">
			  <button class="btn btn-secondary dropdown-toggle btn-dark" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			     <span class="fa fa-user-circle" ></span> Mon compte
			  </button>
			  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
			    <a class="dropdown-item" href="${pageContext.request.contextPath}/gestionCompte/compte"><span class="fa fa-address-card " ></span> Mes informations</a>
			    <a class="dropdown-item" href="${pageContext.request.contextPath}/logout"><span class="fas fa-sign-out-alt" ></span> Déconnexion</a>
			  </div>
			</div>
			<%-- 
				<a href="${pageContext.request.contextPath}/logout" id="deconnexion"
				type="button" class="btn btn-dark" style="align-content: right"> <span class="fas fa-sign-out-alt" ></span> Déconnexion</a>--%>
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
		<nav class="sidebar" id="sidebar"> <!--
        <button type="button" class="toggler" id="sidebarCollapse" > <span class="fa fa-arrow-left fa-2x"></span></button>
        -->


		<div class="sidebar-header">
			<a href="${pageContext.request.contextPath}/index.jsp"><span
				class="fa fa-home" style="margin-right: 5px;"></span>Accueil</a>
		</div>

		<ul class="sidebar-links">
			<li><a	href="${pageContext.request.contextPath}/gestionEtudiants/listeEtudiants">Etudiant</a>		</li>
			<li><a	href="${pageContext.request.contextPath}/gestionEnseignants/listeEnseignants">Enseignant</a>	</li>
			<li><a href="${pageContext.request.contextPath}/promotion/liste-promotion">Promotion</a></li>
			<li><a href="${pageContext.request.contextPath}/matiere/liste-matiere">Matière(s)</a>	</li>
			<li><a href="${pageContext.request.contextPath}/cours/liste">Cours</a>	</li>
			<li><a	href="${pageContext.request.contextPath}/etudiants-cours/liste">Absence</a>	</li>
			<s:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_ENSEIGNANT')">
				<li><a href="${pageContext.request.contextPath}/aide/listeAide">Aide</a>	</li>
			</s:authorize>
		</ul>

		</nav>

	
	<!-- ===================================================== -->
	<!-- =============== CONTENT ============================= -->
	<!-- ===================================================== -->
  <div id="content" style="width:100%">
     <button type="button" id="sidebarCollapse" class="navbar-btn">
         <span></span>
         <span></span>
         <span></span>
     </button>
     
    <div style="padding: 30px;"> 
     		
     <s:authorize access="hasRole('ROLE_ETUDIANT')">	 
	 	<h3>Mes absences</h3>
	 </s:authorize>
	 
     <s:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_ENSEIGNANT')">
	 	<h3>Absences des étudiants</h3>
	 	<br/><br/>

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
 	  	 
 	</s:authorize>
 	
  	<br/><br/>

	<table class="table table-hover" style="width:90%;margin-left:5%";">
	 	
	 	<thead class="thead-light" style="text-align: center;">
    		<tr>
    			<th scope="row">Motif</th>
      			<th scope="row">Etudiant</th>
      			<th scope="row">Cours</th>
    		</tr>
  	  </thead>
  	  
  	  <c:forEach items="${attribut_liste_absence}" var="absence">
  	  
  	  	<tbody style="text-align: center;">
	 	  
	 	  	<tr>

	 			<td>${absence.motif}</td>

	 			<td>${absence.etudiant.nom} ${absence.etudiant.prenom}</td>

	 			<td>${absence.cours.libelle}</td>
	 		
	 		</tbody>
	 	</c:forEach>
	 		
	 	</table>	 	

	<br/>

  	<br/><br/>		

	<s:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_ENSEIGNANT')">
	 	<h3>Listes des étudiants</h3>
	 	<br/><br/>
	 
	 <div>
	 <form class="form-inline" action="${pageContext.request.contextPath}/etudiants-cours/recherche-cours">
	 	 <label for="recherche-cours">Afficher la liste de présence d'un cours : </label>	 	
	 	<select class="form-control ml-2" id="recherche-cours" name="id-cours">
	 		<option value="0" label="Tous les cours">
	 		<c:forEach items="${attribut_cours}" var="cours">
	 			 <option value="${cours.idCours}" label="${cours.libelle}"/>
	 		</c:forEach>
       	</select>
    	<button class="btn btn-outline-success ml-2" type="submit">Rechercher</button>
  	</form>
  	</div>
  	
  	
	 <br/><br/><br/>
	 	 	
	 <table class="table table-hover" style="width:90%;margin-left:5%";text-align: center">
	 	
	 	<thead class="thead-light" style="text-align: center;">
    		<tr>
      			<th scope="row">Absence</th>
    			<th scope="row">Motif</th>
      			<th scope="row">Etudiant</th>
      			<th scope="row">Cours</th>
      			<th scope="row">Modifier</th>
    		</tr>
  	  </thead>
  	  
  	  <c:forEach items="${attribut_liste_presence}" var="presence">
  	  
  	  	<tbody style="text-align: center;">
	 	  
	 	  	<tr>
	 			<td>
	 				<c:if test="${presence.absence==true}"><input type="checkbox" checked="checked" disabled="disabled"></c:if>
	 				<c:if test="${presence.absence==false}"><input type="checkbox" value="" disabled="disabled"></c:if>
	 			</td>

	 			<td>${presence.motif}</td>

	 			<td>${presence.etudiant.nom} ${presence.etudiant.prenom}</td>

	 			<td>${presence.cours.libelle} du <fmt:formatDate value="${presence.cours.date}" pattern="dd/MM/yyyy"/></td>

	 		<%-- lien pour modifier une ligne --%> 		
	 			<td>
	 				<%-- au click on appelle le controller 'GestionEtudiantCoursController' et sa méthode chargerModifCoursBdd --%>
	 				<a href="${pageContext.request.contextPath}/etudiants-cours/formulaire-modif-presence?etudiantCoursId=${presence.idEtudiantCours}">
	 					<i class="fas fa-pencil-alt"></i>
	 				</a>
	 			</td>
	 		
	 		</tbody>
	 	</c:forEach>
	 		
	 	</table>	 	

	<br/>
	
	</s:authorize>
	
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