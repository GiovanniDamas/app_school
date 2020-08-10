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
<link href="${pageContext.request.contextPath}/resources/styles/perso.css"
    rel="stylesheet">    
</head>
<body>

	<jsp:include page="/WEB-INF/generic/header.jsp"></jsp:include>

<div class="content">
	<h3>Modification d'un cours</h3>

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
					<td> <form:errors path="libelle" cssStyle="color:red; font-style:italic;"/> </td>
				</tr>
				
				<tr>
					<td> <form:label path="date">Date :</form:label> </td>
					<td> <form:input path="date"/> </td>
					<td> <form:errors path="date" cssStyle="color:red; font-style:italic;"/> </td>
				</tr>
				
				<tr>
					<td> <form:label path="duree">Durée :</form:label> </td>
					<td> <form:input path="duree"/> </td>
					<td> <form:errors path="duree" cssStyle="color:red; font-style:italic;"/> </td>
				</tr>
				
				<tr>
					<td> <form:label path="description">Description :</form:label> </td>
					<td> <form:input path="description"/> </td>
					<td> <form:errors path="description" cssStyle="color:red; font-style:italic;"/> </td>
				</tr>
				<tr>
					<td> <form:label path="matieres">Id Matière :</form:label> </td>
					<td> 
						<form:select path="matieres">
              					<form:option value="" label="--Sélectionner"/>
              					<form:options items="${attribut_matieres}" itemValue="idMatiere" itemLabel="libelle"/>
         				</form:select>
         			</td>
					<td> <form:errors path="matieres" cssStyle="color:red; font-style:italic;"/> </td>
				</tr>								
				<tr>
					<td> <form:label path="promotions">Id Promotion :</form:label> </td>
					<td> 
						<form:select path="promotions">
              					<form:option value="" label="--Sélectionner"/>
              					<form:options items="${attribut_promotions}" itemValue="idPromotion" itemLabel="libelle"/>
         				</form:select>
         			</td>					
         			<td> <form:errors path="promotions" cssStyle="color:red; font-style:italic;"/> </td>
				</tr>						
							
			</table>
				
		<br/><br/>
		
		<input type="submit" class="btn btn-dark" value="Modifier"/>
		
		</form:form>
</div>
	
</body>
</html>