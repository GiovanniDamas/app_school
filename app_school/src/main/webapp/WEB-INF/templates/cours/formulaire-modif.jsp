<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulaire de modification d'un cours</title>

<link href="${pageContext.request.contextPath}/resources/styles/bootstrap.css"
    rel="stylesheet">
    
</head>
<body>

	<jsp:include page="/WEB-INF/generic/header.jsp"></jsp:include>

<div class="container-fluid">
		<form:form modelAttribute="attribut_cours" 
					method="POST" 
					action="${pageContext.request.contextPath}/cours/modifier">
					
			<table width=60%>
			
				<!--  récup de l'id du cours à modifier dans un champ caché -->

				<tr>
					<td> <form:hidden path="idCours"/> </td>
				</tr>
			
				<tr>
					<td> <form:label path="libelle">Libellé :</form:label> </td>
					<td> <form:input path="libelle"/> </td>
				</tr>
				
				<tr>
					<td> <form:label path="date">Date :</form:label> </td>
					<td> <form:input path="date"/> </td>
				</tr>
				
				<tr>
					<td> <form:label path="duree">Durée :</form:label> </td>
					<td> <form:input path="duree"/> </td>
				</tr>
				
				<tr>
					<td> <form:label path="description">Description :</form:label> </td>
					<td> <form:input path="description"/> </td>
				</tr>
				<tr>
					<td> <form:label path="matieres">Id Matière :</form:label> </td>
					<td> <form:input path="matieres"/> </td>
				</tr>								
				<tr>
					<td> <form:label path="promotions">Id Promotion :</form:label> </td>
					<td> <form:input path="promotions"/> </td>
				</tr>						
				<tr>
					<td colspan="2">
						<input type="submit" value="Modifier">
					</td>
				</tr>
							
			</table>
		
		</form:form>
</div>
	
</body>
</html>