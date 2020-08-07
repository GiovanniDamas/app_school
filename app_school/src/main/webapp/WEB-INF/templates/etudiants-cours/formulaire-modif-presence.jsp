<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulaire de modification de la présence des étudiants</title>

<link href="${pageContext.request.contextPath}/resources/styles/bootstrap.css"
    rel="stylesheet">

</head>
<body>

	<jsp:include page="/WEB-INF/generic/header.jsp"></jsp:include>

	<%-- ====================================================== --%>
	<%-- FORMULAIRE POUR MODIFIER LA PRESENCE D UN ETUDIANT A COURS --%>
	<%-- ====================================================== --%>
	<br/>
	
	<%-- à la soumission envoi d'une requête HTTP en POST vers 'GestionEtudiantCoursController' et sa méthode modifierEtudiantCoursBdd--%>
	
	<form:form modelAttribute="attribut_etudiant_cours" method="POST" action="${pageContext.request.contextPath}/etudiant-cours/modifier">
	
		<table>
		
			<!--  récup de l'id de létudiant cours à modifier dans un champ caché -->

			<tr>
				<td> <form:hidden path="idEtudiantCours"/> </td>
			</tr>

	 		<tr>
	 			<td> <form:label path="absence">Absence : </form:label> </td>
				<td> <form:checkbox path="absence"/> </td>
	 		</tr>
	 		<tr>
	 			<td> <form:label path="motif">Motif : </form:label> </td>
	 			<td> <form:input path="motif"/></td>
	 		</tr> 		
	 		<tr>
	 			<td> <form:label path="etudiant">Id Etudiant : </form:label></td>
	 			<td><form:input path="etudiant"/></td>
	 		</tr>
	 		<tr>
	 			<td><form:label path="cours">Id Cours : </form:label></td>
	 			<td><form:input path="cours"/></td>
	 		</tr>	
			<tr>
				<td colspan="2"> <input type="submit" value="Modifier"/></td>
			</tr>
		</table>
	</form:form>
</body>
</html>