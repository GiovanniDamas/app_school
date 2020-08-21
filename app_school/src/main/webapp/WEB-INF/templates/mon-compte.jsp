<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="s" uri="http://www.springframework.org/security/tags"%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<!-- Lien vers feuille de style de Bootstrap -->
	<link href="${pageContext.request.contextPath}/resources/styles/bootstrap.css"
		rel="stylesheet">

	<!-- Lien vers feuille de style perso de index -->
	<link href="${pageContext.request.contextPath}/resources/styles/index.css"
	    rel="stylesheet">
	    
	<!-- Lien vers feuille de style perso de compte -->
	<link href="${pageContext.request.contextPath}/resources/styles/compte.css"
	    rel="stylesheet">	    

    <!-- Lien vers font awesome 4.7.0-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
    <script src="https://use.fontawesome.com/releases/v5.14.0/js/all.js" data-auto-a11y="true"></script>

	<!-- Lien vers la font de la sidebar -->
    <link href="https://fonts.googleapis.com/css2?family=Cookie&display=swap" rel="stylesheet">
    
   	<link href="https://fonts.googleapis.com/css2?family=Fredericka+the+Great&display=swap" rel="stylesheet"> <!-- 'Fredericka the Great' --> 

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
				<span></span> <span></span> <span></span>
			</button>
	
		
			<div class="container-fluid" style="align:center">
	
	
				<h1 style="text-align:center">Informations Personnelles</h1>
	
				<div class="container informations" >
				
					<div class="row informations-header"> 
					
						<h2 class="col-md-4">Profil</h2>	
						<div class="col-md-6"></div> 
						
						<div class="col-md-2">
							<figure class="photo" > 
								
								<c:if test="${attribut_personne_connecte.role == 'ROLE_ENSEIGNANT' }">
									<img src="${pageContext.request.contextPath}/resources/Images/conseiller.jpg" alt="Ens"/>
								</c:if>
								
								<c:if test="${attribut_personne_connecte.role == 'ROLE_ETUDIANT' }">
									<img src="${pageContext.request.contextPath}/resources/Images/${attribut_personne_connecte.photo}" alt="Etu"/>
								</c:if>
								<c:if test="${attribut_personne_connecte.role == 'ROLE_ADMIN' }">
									<img src="${pageContext.request.contextPath}/resources/Images/administrateur.png" alt="A"/>
								</c:if>
								
							</figure> 	
						</div>
					</div><!-- end informations-header -->
					<hr align="center" size="1px" width="100%" color="gray"/>
				
				
					<div class="row info">	<div class="col-md-4 info-header">Nom :</div> 	<div class="col-md-8 info-content">${attribut_personne_connecte.nom }</div>	</div>
					<hr align="center" size="1px" width="100%" color="gray"/>
					
					<div class="row info">	<div class="col-md-4 info-header">Prénom :</div> 	<div class="col-md-8 info-content">${attribut_personne_connecte.prenom }</div>	</div> 
					<hr align="center" size="1px" width="100%" color="gray"/>
					
					<div class="row info">	<div class="col-md-4 info-header">E-mail :</div> 	<div class="col-md-8 info-content">${attribut_personne_connecte.email }</div>	</div> 
					<hr align="center" size="1px" width="100%" color="gray"/>
					
					<div class="row info">	<div class="col-md-4 info-header">Identifiant :</div> 	<div class="col-md-8 info-content">${attribut_personne_connecte.identifiant }</div>	</div> 
					<hr align="center" size="1px" width="100%" color="gray"/>
					
					<div class="row info">	<div class="col-md-4 info-header">Password :</div> 	<div class="col-md-8 info-content">**********</div>	</div> 
					
					<c:if test="${attribut_personne_connecte.role == 'ROLE_ETUDIANT' }">
						<hr align="center" size="1px" width="100%" color="gray"/>
						<div class="info">	<div class="info-header">Anniversaire</div> 	<div class="info-contetn">${attribut_personne_connecte.dateDeNaissance }</div>	</div>
					</c:if>
					
					
				
					<br/>
					<c:if test="${attribut_personne_connecte.role == 'ROLE_ENSEIGNANT' }">
						<a class="btn btn-info" type="button" style="align:right"
								href="${pageContext.request.contextPath}/gestionEnseignants/form-edit?idPersonne=${attribut_personne_connecte.idPersonne}">
								Modifier
						</a>
					</c:if>
					<c:if test="${attribut_personne_connecte.role == 'ROLE_ETUDIANT' }">
						<a class="btn btn-info" type="button" style="align:right"
								href="${pageContext.request.contextPath}/gestionEtudiants/form-edit?idPersonne=${attribut_personne_connecte.idPersonne}">
								Modifier
						</a>
					</c:if>					
					
				</div><!-- end informations -->
	
				
				<div class="container adresses">
				
					<div class="row adresses-header"> 
						<h2 class="col-md-6">Adresses</h2>	
						<div class="col-md-6"></div> 
					</div>
					<hr align="center" size="1px" width="100%" color="gray"/>
					
					<div class="row">
					
						<div class="col-md-4 add-adress" >
							<a href="${pageContext.request.contextPath}/adresses/editAdresse?idAdresse=0">
								<span class="fa fa-plus fa-2x plus"></span>
								<br/>
								<h4>Ajouter une adresse</h4>
							</a>	
						</div>
					
					<c:forEach items="${attribut_adresses}"  var="adresse">
						
						
						<div class="col-md-4 adresse" >
							<div class="row ">	<div class="numero">${adresse.numero} </div>  <div class="rue"> ${adresse.rue}</div>		</div>
							<div class="row">	<div class="code-postal">${adresse.codePostal} </div>	<div class="ville"> ${adresse.ville}</div>	</div>
							<div class="row gestion">	
								<a href="#">Modifier</a> 
								<span>|</span>	
								<a href="#">Supprimer</a>	
							</div>
						</div>
					
					</c:forEach>
					
					</div>
				</div><!-- end adresses -->
	
	
	
			</div><!-- end container fluid -->

		</div><!-- end content -->
	</div>	<!-- end wrapper -->


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