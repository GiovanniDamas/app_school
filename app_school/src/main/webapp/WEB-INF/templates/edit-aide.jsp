<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="s" uri="http://www.springframework.org/security/tags"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Ajout/modif Aide</title>

	<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">	
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>		
	
	
</head>

<body>


	<div id="divhaute" class="container-fluid col-lg-12">
		<h1 id="titre">SchoolApp</h1>
		
		<div id="connexion">
		<s:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_ENSEIGNANT', 'ROLE_ETUDIANT')">
			<h5>
			Bienvenue, ${attribut_personne_connecte.prenom} ${attribut_personne_connecte.nom}
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

	<container style="height:100%;position:relative;top:0">

	<div align="center" style="margin-top:0;background: rgb(192,192,192);background: linear-gradient(180deg, rgba(192,192,192,1) 0%, rgba(255,255,255,1) 56%);min-width:100%; ">
		<h1>Edition de l'aide</h1>
	</div>

	<br/>
	
	<div align="center">

		<form:form modelAttribute="attribut_aide" method="POST"
			action="${pageContext.request.contextPath}/aide/edit">

			<%-- affichage de tous les messages d'erreurs --%>
			<form:errors path="*" cssClass="erreur_validation" element="div" />

			<tr>
				<c:if test="${idAide} != 0"> </c:if>
				<td><form:hidden path="idAide"/></td>
				
				
			</tr>

			<table width="60%">
				<tr>
					<td><form:label path="urlPage">Page :</form:label></td>
					<td><form:input path="urlPage" /></td>
					<td><form:errors path="urlPage"
							cssStyle="color:red; font-style:italic;" /></td>
				</tr>
				
				
				<tr>
					<td><form:label path="contenu">Contenu :</form:label></td>
					<td><form:textarea  path="contenu" class="helpcontent" rows="10" /></td>
					<td><form:errors path="contenu"
							cssStyle="color:red; font-style:italic;" /></td>
				</tr>
				

				<tr>
					
					<td><input type="submit" value="Ajouter" /></td>
				</tr>
				<tr>
					<c:if test="${idAide} != 0"></c:if>
					<td><input type="submit" value="Modifier"/></td>
				</tr>
			</table>

		</form:form>
	</div>
	</container>

	<!-- ===== SCIRPTS ===== -->	 
	<script>
	    $(document).ready(function() {
	        $('.helpcontent').summernote();
	    });
	</script>
	
</body>
</html>