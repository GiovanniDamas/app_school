<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Page gestion des Administrateurs</title>

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

	<style>
	

	</style>

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
  <div id="content" style="width:100%">
     <button type="button" id="sidebarCollapse" class="navbar-btn">
         <span></span>
         <span></span>
         <span></span>
     </button>
   <!--Put some content here	
	<h2 style="text-align: center;"> Bienvenue dans l'aide ! </h2>
	-->
	<br/><br/>


		<table class="table table-bordered" style="width:90%;margin-left:5%">

			<h3 align="center">Liste des Administrateurs</h3>
			<thead style="background-color: black; color: white">
				<tr>
					<th scope="col">#</th>
					<th scope="col">Nom</th>
					<th scope="col">Prénom</th>
					<th scope="col">E-mail</th>
					<th scope="col">Identifiant</th>
					<th scope="col">Mot de passe</th>
					<th scope="col">Modifier</th>
					<th scope="col">Supprimer</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${attribut_liste_admin}" var="admin">
					<tr>
						<th scope="row">${admin.idPersonne}</th>
						<th>${admin.nom}</th>
						<th>${admin.prenom}</th>
						<td>${admin.email}</td>
						<td>${admin.identifiant}</td>
						<td>${admin.motDePasse}</td>
						<td><a
							href="${pageContext.request.contextPath}/gestionAdmin/form-edit?idPersonne=${admin.idPersonne}"
							aria-pressed="true"><i class="fas fa-pencil-alt fa-3x"></i>Modifier
						</a></td>

						<!-- colonne pour la suppression de l'emploe -->
						<td><a
							href="${pageContext.request.contextPath}/gestionAdmin/delete?idPersonne=${admin.idPersonne}">Supprimer</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<a	href="${pageContext.request.contextPath}/gestionAdmin/form-edit?idPersonne=0"
			class="btn btn-primary btn-md active" role="button"
			aria-pressed="true" style="align-content: left;margin-left:5%" >
			Ajout d'un	Administrateur
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

 	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/jquery-3.4.1.min.js"></script>	
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/bootstrap.js"></script>	

	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/sidebar.js"></script>	
    

</body>
</html>