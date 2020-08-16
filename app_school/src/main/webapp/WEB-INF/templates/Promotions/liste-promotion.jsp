<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listes des promotions</title>


<!-- Lien vers feuille de style de Bootstrap -->
<link
	href="${pageContext.request.contextPath}/resources/styles/bootstrap.css"
	rel="stylesheet">

<!-- Lien vers feuille de style perso de index -->
<link
	href="${pageContext.request.contextPath}/resources/styles/index.css"
	rel="stylesheet">

<!-- Lien vers font awesome 4.7.0-->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">

<!-- Lien vers la font de la sidebar -->
<link
	href="https://fonts.googleapis.com/css2?family=Cookie&display=swap"
	rel="stylesheet">


</head>
<body>
	<!-- ===================================================== -->
	<!-- =============== HEADER ============================= -->
	<!-- ===================================================== -->
	<div id="divhaute" class="container-fluid col-lg-12">
		<h1 id="titre">SchoolApp</h1>
		<a href="${pageContext.request.contextPath}/login.jsp" id="connexion"
			type="button" class="btn btn-secondary"> <span
			class="fa fa-user-circle"></span> Connexion
		</a>
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
			<li><a
				href="${pageContext.request.contextPath}/promotion/liste-promotion">Promotion</a>
			</li>
			<li><a
				href="${pageContext.request.contextPath}/matiere/liste-matiere">Matière(s)</a>
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

		<!-- =========================================================== -->
		<!-- ======== Content ========================================== -->
		<!-- =========================================================== -->


		<h1 style="margin-left: 20px">
			<u>Liste des Promotions:</u>
		</h1>

		<table class="table table-hover" style="width: 70%;">

			<tr>

				<td colspan="5" align="right"><a
					href="${pageContext.request.contextPath}/promotion/edit-promotion-form?idPromotion=0">
						Ajout d'une Promotion </a></td>
			</tr>
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Libelle</th>
					<th scope="col">Modifier</th>
					<th scope="col">Supprimer</th>


				</tr>
			</thead>
			<tbody>

				<c:forEach items="${attribut_liste_promotions }" var="prom">
					<tr>
						<td>${prom.idPromotion}</td>
						<td>${prom.libelle}</td>
						<td><a
							href="${pageContext.request.contextPath}/promotion/edit-promotion-form?idPromotion=${prom.idPromotion}">
								Modifier</a></td>
						<td><a
							href="${pageContext.request.contextPath}/promotion/delete?idPromotion=${prom.idPromotion}">
								Supprimer</a></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<!-- ===================================================== -->
	<!-- =============== FOOTER ============================== -->
	<!-- ===================================================== -->
	<div class="clear" style="clear: both"></div>

	<footer class="footer">

	<p>2020 Copyright © Groupe2 : Gio, Hannah, Marlène &#x26; Gab</p>

	</footer>


	<!-- ===================================================================== -->
	<!-- ==================  SCRIPTS  ======================================== -->
	<!-- ===================================================================== -->

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/scripts/jquery-3.4.1.min.js"></script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/scripts/bootstrap.js"></script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/scripts/sidebar.js"></script>

</body>
</html>