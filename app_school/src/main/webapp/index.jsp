<%@page import="com.intiformation.appschool.modeles.Enseignants"%>
<%@page import="com.intiformation.appschool.service.EnseignantsServiceImpl"%>
<%@page import="com.intiformation.appschool.service.IEnseignantsService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="s" uri="http://www.springframework.org/security/tags"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Accueil</title>

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


</head>
<body>


	<%--	UNCOMMENT IF U WANT TO USE INCLUDE (PB: FOOTER ) 
	<jsp:include page="/WEB-INF/generic/header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/generic/sidebar.jsp"></jsp:include>


	<a href="${pageContext.request.contextPath}/matiere/liste-matiere"">lien
		test liste matières</a>

	<a
		href="${pageContext.request.contextPath}/gestionEtudiants/listeEtudiants">lien
		test liste etudiants</a>

	<div>


		<h1>Hello world !!</h1>
 	</div>
 	
 <jsp:include page="/WEB-INF/generic/footer.jsp"></jsp:include>		--%>


	<!-- ===================================================== -->
	<!-- =============== HEADER ============================= -->
	<!-- ===================================================== -->


	<div id="divhaute" class="container-fluid col-lg-12">
		<h1 id="titre">SchoolApp</h1>
		
		<div id="connexion">
		<s:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_ENSEIGNANT', 'ROLE_ETUDIANT')">
			<h5>
			Bienvenue, <s:authentication property="name"/> <s:authentication property="authorities"/>
			</h5>
		</s:authorize>
		
		<br/>
		
		<s:authorize
			access="hasAnyRole('ROLE_ETUDIANT', 'ROLE_ADMIN', 'ROLE_ENSEIGNANT')">
				<a href="${pageContext.request.contextPath}/logout" id="deconnexion"
				type="button" class="btn btn-dark" style="align-content: right"> <span class="fa fa-user-circle" ></span> Déconnexion</a>
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
			<li><a
				href="${pageContext.request.contextPath}/gestionEtudiants/listeEtudiants">Etudiant</a>
			</li>
			<li><a
				href="${pageContext.request.contextPath}/gestionEnseignants/listeEnseignants">Enseignant</a>
			</li>
			<li><a href="${pageContext.request.contextPath}/promotion/liste-promotion">Promotion</a></li>
			<li><a href="${pageContext.request.contextPath}/matiere/liste-matiere">Matière(s)</a>
			</li>
			<li><a href="${pageContext.request.contextPath}/cours/liste">Cours</a>
			</li>
			<li><a
				href="${pageContext.request.contextPath}/etudiants-cours/liste">Absence</a>
			</li>
			<li><a href="${pageContext.request.contextPath}/aide/listeAide">Aide</a>
			</li>
		</ul>

		</nav>


		<!-- ===================================================== -->
		<!-- =============== CONTENT ============================= -->
		<!-- ===================================================== -->
		<div id="content">
							
			<button type="button" id="sidebarCollapse" class="navbar-btn">
				<span></span> <span></span> <span></span>
			</button>

		<!-- Affichage d'un message lors de la déconnexion -->
	    <div style="padding: 20px;"> 
			<c:if test="${not empty param.logout_message}">
				<div class="alert alert-dismissible alert-success col-12" style="float: right;">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<font> Déconnexion avec succès </font>
				</div>
			</c:if>
		</div>
	
			<br />

			<!--Put some content here-->
			<h1>Bienvenue sur le site de l'école Sainte Thérèse de Saint
				Valaougue.</h1>
			<p>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ea,
				ad voluptas temporibus, et iusto officia adipisci nisi dolorum
				perspiciatis sed libero! Adipisci officiis, ea labore vel explicabo
				magnam illo eius!</p>
			<p>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ea,
				ad voluptas temporibus, et iusto officia adipisci nisi dolorum
				perspiciatis sed libero! Adipisci officiis, ea labore vel explicabo
				magnam illo eius!</p>

		
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
 
 	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/jquery-3.4.1.min.js"></script>	
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/bootstrap.js"></script>	

	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/sidebar.js"></script>	
    
</body>
</html>