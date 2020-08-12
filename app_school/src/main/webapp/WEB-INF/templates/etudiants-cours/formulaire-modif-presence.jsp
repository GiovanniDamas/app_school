<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulaire de modification de la pr�sence des �tudiants</title>

<link href="${pageContext.request.contextPath}/resources/styles/bootstrap.css"
    rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/styles/perso.css"
    rel="stylesheet">  
</head>
<body>

	<!-- =========================================================== -->
	<!-- ======== Header (navabar) ================================= -->
	<!-- =========================================================== -->
	
	<jsp:include page="/WEB-INF/generic/header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/generic/sidebar.jsp"></jsp:include>
	
	<%-- ====================================================== --%>
	<%-- FORMULAIRE POUR MODIFIER LA PRESENCE D UN ETUDIANT A COURS --%>
	<%-- ====================================================== --%>
	
	<%-- � la soumission envoi d'une requ�te HTTP en POST vers 'GestionEtudiantCoursController' et sa m�thode modifierEtudiantCoursBdd--%>
<div class="content">

	<h3>Modification de la pr�sence d'un �tudiant</h3>
	
	<br/>
	
	<form:form modelAttribute="attribut_etudiant_cours" method="POST" action="${pageContext.request.contextPath}/etudiants-cours/modifier">

	<%-- affichage de tous les messages d'erreurs --%>		
	<form:errors path="*" element="div" class="alert alert-dismissible alert-danger" />	
	
		<fieldset>
		
		<!--  r�cup de l'id de l�tudiant cours � modifier dans un champ cach� -->
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
			  	<form:label path="etudiant.idPersonne">Etudiant</form:label>
         		<form:select path="etudiant.idPersonne" class="form-control">
    				<option value="">--S�lectionner l'�tudiant</option>
    					<c:forEach var="etudiant" items="${attribut_etudiants}">
        					<form:option value="${etudiant.idPersonne}"><c:out value="${etudiant.nom} ${etudiant.prenom}"/></form:option>
    					</c:forEach>
				</form:select>			
         		<form:errors path="etudiant.idPersonne" cssStyle="color:red; font-style:italic;"/>
			</div>
			
			<div class="form-group">
			  	<form:label path="cours.idCours">Cours</form:label>
				<form:select path="cours.idCours" class="form-control">
              			<form:option value="" label="--S�lectionner le cours"/>
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

	<!-- =========================================================== -->
	<!-- ======== FOOTER  ========================================== -->
	<!-- =========================================================== -->
	
	<%@include file="/WEB-INF/generic/footer.jsp" %>
</body>
</html>