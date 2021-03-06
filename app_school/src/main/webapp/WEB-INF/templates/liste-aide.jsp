<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Aide</title>

	<!-- Lien vers feuille de style de Bootstrap -->
	<link href="${pageContext.request.contextPath}/resources/styles/bootstrap.css"
		rel="stylesheet">

	<!-- Lien vers feuille de style perso de index -->
	<link href="${pageContext.request.contextPath}/resources/styles/index.css"
	    rel="stylesheet">

    <!-- Lien vers font awesome 4.7.0-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
    <script src="https://use.fontawesome.com/releases/v5.14.0/js/all.js" data-auto-a11y="true"></script>

	<!-- Lien vers la font de la sidebar -->
    <link href="https://fonts.googleapis.com/css2?family=Cookie&display=swap" rel="stylesheet">

	<link href="https://fonts.googleapis.com/css2?family=Fredericka+the+Great&display=swap" rel="stylesheet"> <!-- 'Fredericka the Great' -->
 	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/jquery-3.4.1.min.js"></script>	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/bootstrap.bundle.min.js"></script>	
	<style>
	

	</style>

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


     <!--Put some content here	-->
	<br/><br/>

		<table class="table table-bordered rounded"  style="width:90%;margin-left:5%">

			<h2 align="center" style="margin-bottom:20px;">Gestion de l'aide</h2>
			
			<thead class="thead-dark">
				<tr>
					<th scope="col"> #	</th>
					<th scope="col"> Page	</th>
					<th scope="col" colspan="3" > Contenu	</th>
					<th scope="col" colspan="2" > Gestion	</th>
				</tr>
			</thead>
			
			<tbody >
				<c:forEach items="${attribut_liste_aide}" var="aides">
					<tr>
						<td scope="row">	${aides.idAide}	</td>
						<td>	${aides.urlPage}	</td>
						<td colspan="3">	${aides.contenu}	</td>
					
						<td>
							<a	href="${pageContext.request.contextPath}/aide/editAide?idAide=${aides.idAide}" >
								<span class="fa fa-pencil-square-o fa-1x" ></span><i > Modifier</i>
							</a>
						</td>

						<!-- colonne pour la suppression de l'aide -->
						<td>
							<a	href="${pageContext.request.contextPath}/aide/delete?idAide=${aides.idAide}">
								<span class="fa fa-trash-o fa-1x" ></span><i> Supprimer</i>
							</a>
						</td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<br/><br/>
		<a	href="${pageContext.request.contextPath}/aide/editAide?idAide=0"
			class="btn btn-primary btn-md active" role="button"
			aria-pressed="true" style="align-content: left;margin-left:5%" > 
			Ajout d'une fiche d'aide
		</a>


	
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