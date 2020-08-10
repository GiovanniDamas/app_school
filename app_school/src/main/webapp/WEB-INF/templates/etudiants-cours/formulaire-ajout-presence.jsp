<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ajouter un étudiant à un cours</title>

<link href="${pageContext.request.contextPath}/resources/styles/bootstrap.css"
    rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/styles/perso.css"
    rel="stylesheet">  
</head>
<body>

	<jsp:include page="/WEB-INF/generic/header.jsp"></jsp:include>
	
	<%-- ====================================================== --%>
	<%-- = FORMULAIRE POUR AJOUTER UN ETUDIANT A COURS ======== --%>
	<%-- ====================================================== --%>
	<br/>
<div class="content">
	
	<h3>Ajout de l'absence d'un étudiant à un cours</h3>
	
	<br/>	
	
	<%-- à la soumission envoi d'une requête HTTP en POST vers 'GestionEtudiantCoursController' et sa méthode ajouterEtudiantCoursBdd--%>
	
	<form:form modelAttribute="attribut_etudiant_cours" method="POST" action="${pageContext.request.contextPath}/etudiants-cours/ajouter">
	
		<table>
	 		<tr>
	 			<td>Absence : </td>
	 			<td><form:checkbox path="absence"/></td>
	 		</tr>
	 		<tr>
	 			<td>Motif : </td>
	 			<td><form:input path="motif"/></td>
	 		</tr> 		
	 		<tr>
	 			<td>Id Etudiant : </td>
	 			<td><form:input path="etudiant"/></td>
	 		</tr>
	 		<tr>
	 			<td>Cours : </td>
	 			<td>
	 				<form:select path="cours.idCours">
              			<form:option value="" label="--Sélectionner"/>
            			<form:options items="${attribut_cours}" itemValue="idCours" itemLabel="libelle"/>
         			</form:select>
         		</td>
	 		</tr>	
			<tr>
				<td colspan="2"> <input type="submit" value="Ajouter"/></td>
			</tr>
		</table>
	</form:form>
</div>
</body>
</html>