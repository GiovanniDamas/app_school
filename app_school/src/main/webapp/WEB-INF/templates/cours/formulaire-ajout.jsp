<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
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
				<!-- Bouton d'aide lan�ant un modal -->
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
			    <a class="dropdown-item" href="${pageContext.request.contextPath}/logout"><span class="fas fa-sign-out-alt" ></span> D�connexion</a>
			  </div>
			</div>
			<%-- 
				<a href="${pageContext.request.contextPath}/logout" id="deconnexion"
				type="button" class="btn btn-dark" style="align-content: right"> <span class="fas fa-sign-out-alt" ></span> D�connexion</a>--%>
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

	<%-- ====================================================== --%>
	<%-- ========= FORMULAIRE POUR AJOUTER UN COURS =========== --%>
	<%-- ====================================================== --%>
	<br/>
	
	<%-- � la soumission envoi d'une requ�te HTTP en POST vers 'GestionCoursController' et sa m�thode ajouterCoursBdd--%>
	<div style="padding: 30px;">
	<h3>Ajout d'un nouveau cours</h3>
	
	<br/>
	
	<c:if test="${attribut_idmatiere eq null}">	
		
	<form class="form-group form-inline" action="${pageContext.request.contextPath}/cours/choixpromotion">
		
			<div class="form-group form-inline">
			
			<label class="my-1 mr-2" for="choix-matiere">Mati�re</label>
			<select id="choix-matiere" name="id-matiere" class="form-control custom-select my-1 mr-sm-2">
			<c:forEach items="${attribut_matieres}" var="matiere">
				<option value="${matiere.idMatiere}" label="${matiere.libelle}"/>
			</c:forEach>
         	</select>  
         		
         	<input type="hidden" name="coursId" value="0"/>				
			<button type="submit" class="btn btn-primary my-1">Valider</button>  
			</div>
			
	</form>
	
	</c:if>						
	

	<c:if test="${attribut_idmatiere ne null}">	

	<form class="form-group form-inline">
		
			<div class="form-group form-inline">
			
			<label class="my-1 mr-2" for="choix-matiere">Mati�re</label>
			<select id="choix-matiere" class="form-control custom-select my-1 mr-sm-2" disabled="disabled">
				<option>${attribut_matiere.libelle}</option>
         	</select>  
         		
         	<input type="hidden" name="${attribut_cours}"/>				
         	<input type="hidden" name="${action}"> 	

			<a class="btn btn-primary my-1" href="${pageContext.request.contextPath}/cours/formulaire-ajout" role="button">Modifier</a>  
			</div>
				  
	</form>
	
	</c:if>
	
	
	
	<form:form modelAttribute="attribut_cours" method="POST" action="${pageContext.request.contextPath}/cours/ajouter">	
		
		<c:if test="${attribut_idmatiere eq null}">			
			<fieldset disabled>
		</c:if>
		
		<c:if test="${attribut_idmatiere ne null}">	
			<fieldset>
		</c:if>	
			<div class="form-group">
			  	<form:label class="col-form-label" path="libelle">Libell�</form:label>
  				<form:input type="text" class="form-control" placeholder="Entrer le libell� du cours" path="libelle"/>
  				<form:errors path="libelle" cssStyle="color:red; font-style:italic;"/>
			</div>
			
			<div class="row">
			
			<div class="form-group col-md-4">
			  	<form:label class="col-form-label" path="date">Date</form:label>
  				<form:input type="date" class="form-control" path="date"/>
  				<form:errors path="date" cssStyle="color:red; font-style:italic;"/>
			</div>

			<div class="form-group col-md-4">
			  	<form:label class="col-form-label" path="duree">Dur�e</form:label>
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
			
			<form:input path="matieres.idMatiere" type="hidden" value="${attribut_idmatiere}"/>
			
			<div class="form-group">
			  	<form:label path="promotions.idPromotion">Promotion</form:label>
				<form:select path="promotions.idPromotion" class="form-control">
              			<form:option value="" label="--S�lectionner la promotion"/>
            			<form:options items="${attribut_promotions}" itemValue="idPromotion" itemLabel="libelle"/>
         		</form:select>
         		<form:errors path="promotions.idPromotion" cssStyle="color:red; font-style:italic;"/>
			</div>
			
		
		<br/>
		
		<input type="submit" class="btn btn-dark" value="Ajouter"/>
		
		</fieldset>
			
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
  
        <p>2020 Copyright � Groupe2 : Gio, Hannah, Marl�ne &#x26; Gab  </p>
         
  </footer>

	<!-- ===================================================================== -->
	<!-- ==================  SCRIPTS  ======================================== -->
	<!-- ===================================================================== -->	

	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/sidebar.js"></script>	
    
</body>
</html>