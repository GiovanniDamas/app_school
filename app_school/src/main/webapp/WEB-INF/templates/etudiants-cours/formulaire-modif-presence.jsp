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
<link href="${pageContext.request.contextPath}/resources/styles/perso.css"
    rel="stylesheet">  
</head>
<body>

	<jsp:include page="/WEB-INF/generic/header.jsp"></jsp:include>

	<%-- ====================================================== --%>
	<%-- FORMULAIRE POUR MODIFIER LA PRESENCE D UN ETUDIANT A COURS --%>
	<%-- ====================================================== --%>
	
	<%-- à la soumission envoi d'une requête HTTP en POST vers 'GestionEtudiantCoursController' et sa méthode modifierEtudiantCoursBdd--%>
<div class="content">

	<h3>Modification de la présence d'un étudiant</h3>
	
	<br/>
	
	<form:form modelAttribute="attribut_etudiant_cours" method="POST" action="${pageContext.request.contextPath}/etudiants-cours/modifier">

	<%-- affichage de tous les messages d'erreurs --%>		
	<form:errors path="*" element="div" class="alert alert-dismissible alert-danger" />	
	
		<fieldset>
		
		<!--  récup de l'id de létudiant cours à modifier dans un champ caché -->
			<form:hidden path="idEtudiantCours"/> 
			
			<div>
				<legend style="font-size: 12pt;">Absence</legend>
  				<form:checkbox path="absence"/>
  				<form:errors path="absence" cssStyle="color:red; font-style:italic;"/>
			</div>	
		
		<br/>
		
			<div class="form-group">
			  	<form:label class="col-form-label" path="motif">Motif</form:label>
  				<form:textarea class="form-control" placeholder="Entrer la description du cours" path="motif"/>
  				<form:errors path="motif" cssStyle="color:red; font-style:italic;"/>
			</div>
			
			<div class="form-group">
			  	<form:label path="etudiant.idEtudiant">Etudiant</form:label>
         		<form:select path="etudiant.idEtudiant" class="form-control">
    				<option value="">--Sélectionner l'étudiant</option>
    					<c:forEach var="etudiant" items="${attribut_etudiants}">
        					<form:option value="${etudiant.idEtudiant}"><c:out value="${etudiant.nom} ${etudiant.prenom}"/></form:option>
    					</c:forEach>
				</form:select>			
         		<form:errors path="etudiant.idEtudiant" cssStyle="color:red; font-style:italic;"/>
			</div>
			
			<div class="form-group">
			  	<form:label path="cours.idCours">Cours</form:label>
				<form:select path="cours.idCours" class="form-control">
              			<form:option value="" label="--Sélectionner le cours"/>
            			<form:options items="${attribut_cours}" itemValue="idCours" itemLabel="libelle"/>
         		</form:select>  				
         		<form:errors path="cours.idCours" cssStyle="color:red; font-style:italic;"/>
			</div>
			
		</fieldset>

		<br/>
		
		<input type="submit" class="btn btn-dark" value="Modifier"/>
		
		</form:form>
		
		<br/><br/><br/>
		
</div>	
</body>
</html>