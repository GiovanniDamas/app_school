<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulaire d'ajout d'un cours</title>

<link href="${pageContext.request.contextPath}/resources/styles/bootstrap.css"
    rel="stylesheet">
    
</head>
<body>

	<jsp:include page="/WEB-INF/generic/header.jsp"></jsp:include>

	<%-- ====================================================== --%>
	<%-- ========= FORMULAIRE POUR AJOUTER UN COURS =========== --%>
	<%-- ====================================================== --%>
	<br/>
	
	<%-- � la soumission envoi d'une requ�te HTTP en POST vers 'GestionCoursController' et sa m�thode ajouterCoursBdd--%>
	
	<form:form modelAttribute="attribut_cours" method="POST" action="${pageContext.request.contextPath}/cours/ajouter">
	
		<table>
	 		<tr>
	 			<td>Libell� : </td>
	 			<td><form:input path="libelle"/></td>
	 		</tr>
	 		<tr>
	 			<td>Date : </td>
	 			<td><form:input path="date"/></td>
	 		</tr> 		
	 		<tr>
	 			<td>Dur�e : </td>
	 			<td><form:input path="duree"/></td>
	 		</tr>
	 		<tr>
	 			<td>Description : </td>
	 			<td><form:textarea path="description"/></td>
	 		</tr>	
	 		<tr>
	 			<td>Id mati�re : </td>
	 			<td><form:input path="matieres"/></td>
	 		</tr>
	 		<tr>
	 			<td>Id promotion : </td>
	 			<td><form:input path="promotions"/></td>
	 		</tr>	
			<tr>
				<td colspan="2"> <input type="submit" value="Ajouter"/></td>
			</tr>
		</table>
	
	</form:form>
</body>
</html>