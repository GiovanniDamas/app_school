<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Promotion - formulaire d'ajout/édition</title>


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
			<a href="#"><span class="fa fa-home" style="margin-right: 5px;"></span>Accueil</a>
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
		<div>
			<h2 style="margin-left: 20px">
				<u>Formulaire d'ajout/édition d'une promotion :</u>
			</h2>

			<div class="form-group">
				<form:form modelAttribute="linkCommand" method="POST"
					action="${pageContext.request.contextPath}/promotion/add">

					<%-- affichage de tous les messages d'erreurs --%>
					<form:errors path="*" cssClass="erreur_validation" element="div" />


					<table width="60%">
						<tr>

							<td><form:hidden path="promotion.idPromotion" /></td>

						</tr>


						<tr>
							<td><form:label class="col-form-label"
									path="promotion.libelle"> Libellé : </form:label></td>
							<td><form:input class="form-control"
									path="promotion.libelle" /></td>
							<td><form:errors path="promotion.libelle"
									cssStyle="color:red; font-style:italic;" /></td>
						</tr>


						<tr>
							<td><form:label class="col-form-label"
									path="enseignant.idPersonne"> Enseignants: </form:label></td>
							<td><form:select class="form-control" multiple="true"
									path="enseignant.idPersonne">
									<c:forEach items="${attribut_liste_enseignants}" var="ens">
										<form:option value="${ens.idPersonne}">
											<c:out value="${ens.nom} ${ens.prenom}" />
										</form:option>
									</c:forEach>
								</form:select></td>
							<td></td>
						</tr>


						<c:if test="${promotion.idPromotion == null}">
							<tr>
								<td><input class="btn btn-primary" type="submit"
									value="Ajouter" /></td>
							</tr>
						</c:if>

						<c:if test="${not empty promotion.idPromotion}">
							<tr>
								<td><input class="btn btn-primary" type="submit"
									value="Modifier" /></td>
							</tr>
						</c:if>


					</table>

				</form:form>
			</div>
		</div>
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