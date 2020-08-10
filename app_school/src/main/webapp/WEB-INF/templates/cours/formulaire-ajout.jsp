<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulaire d'ajout d'un cours</title>

<link href="${pageContext.request.contextPath}/resources/styles/bootstrap.css"
    rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/styles/perso.css"
    rel="stylesheet">
</head>
<body>

	<jsp:include page="/WEB-INF/generic/header.jsp"></jsp:include>

	<%-- ====================================================== --%>
	<%-- ========= FORMULAIRE POUR AJOUTER UN COURS =========== --%>
	<%-- ====================================================== --%>
	<br/>
	
	<%-- � la soumission envoi d'une requ�te HTTP en POST vers 'GestionCoursController' et sa m�thode ajouterCoursBdd--%>
	<div class="content">
	<h3>Ajout d'un nouveau cours</h3>
	
	<br/>
	
	<form:form modelAttribute="attribut_cours" method="POST" action="${pageContext.request.contextPath}/cours/ajouter">
	
	<%-- affichage de tous les messages d'erreurs --%>		
	<form:errors path="*" cssClass="erreurs_validation" element="div"/>	

		<table>
	 		<tr>
	 			<td><form:label path="libelle">Libell� : </form:label></td>
	 			<td><form:input path="libelle"/></td>
				<td> <form:errors path="libelle" cssStyle="color:red; font-style:italic;"/> </td>
	 		</tr>
	 		<tr>
	 			<td><form:label path="date">Date : </form:label></td>
	 			<td><form:input path="date" type="date"/></td>
	 			<td> <form:errors path="date" cssStyle="color:red; font-style:italic;"/> </td>
	 		</tr> 		
	 		<tr>
	 			<td><form:label path="duree">Dur�e : </form:label></td>
	 			<td><form:input path="duree"/></td>
				<td> <form:errors path="duree" cssStyle="color:red; font-style:italic;"/> </td>
	 		</tr>
	 		<tr>
	 			<td><form:label path="description">Description : </form:label></td>
	 			<td><form:textarea rows="3" cols="20" path="description"/></td>
				<td> <form:errors path="description" cssStyle="color:red; font-style:italic;"/> </td>
	 		</tr>	
	 		<tr>
	 			<td><form:label path="matieres.idMatiere">Id mati�re : </form:label></td>
	 			<td>
	 				<form:select path="matieres.idMatiere">
              			<form:option value="" label="--S�lectionner"/>
            			<form:options items="${attribut_matieres}" itemValue="idMatiere" itemLabel="libelle"/>
         			</form:select>
	 			</td>
				<td> <form:errors path="matieres" cssStyle="color:red; font-style:italic;"/> </td>
	 		</tr>
	 		<tr>
	 			<td><form:label path="promotions.idPromotion">Id promotion : </form:label></td>
	 			<td>
	 				<form:select path="promotions.idPromotion">
              			<form:option value="" label="--S�lectionner"/>
              			<form:options items="${attribut_promotions}" itemValue="idPromotion" itemLabel="libelle"/>
         			</form:select>
	 			</td>
				<td> <form:errors path="promotions" cssStyle="color:red; font-style:italic;"/> </td>
	 		</tr>	

		</table>
		
		<br/><br/>
		
		<input type="submit" class="btn btn-dark" value="Ajouter"/>
	
	</form:form>
	</div>
</body>
</html>