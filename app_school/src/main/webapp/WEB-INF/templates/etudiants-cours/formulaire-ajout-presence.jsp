<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ajouter un étudiant à un cours</title>
</head>
<body>
	
	<%-- ====================================================== --%>
	<%-- = FORMULAIRE POUR AJOUTER UN ETUDIANT A COURS ======== --%>
	<%-- ====================================================== --%>
	<br/>
	
	<%-- à la soumission envoi d'une requête HTTP en POST vers 'GestionEtudiantCoursController' et sa méthode ajouterEtudiantCoursBdd--%>
	
	<form:form modelAttribute="attribut_etudiant_cours" method="POST" action="${pageContext.request.contextPath}/etudiant-cours/ajouter">
	
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
	 			<td>Id Cours : </td>
	 			<td><form:input path="cours"/></td>
	 		</tr>	
			<tr>
				<td colspan="2"> <input type="submit" value="Ajouter"/></td>
			</tr>
		</table>
	</form:form>
	
</body>
</html>