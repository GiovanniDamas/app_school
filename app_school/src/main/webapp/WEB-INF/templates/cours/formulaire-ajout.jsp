<%@ page language="java" contentType="text/html; charset=UTF-8"
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

	<!-- =========================================================== -->
	<!-- ======== Header (navabar) ================================= -->
	<!-- =========================================================== -->
	
	<jsp:include page="/WEB-INF/generic/header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/generic/sidebar.jsp"></jsp:include>

	<%-- ====================================================== --%>
	<%-- ========= FORMULAIRE POUR AJOUTER UN COURS =========== --%>
	<%-- ====================================================== --%>
	<br/>
	
	<%-- à la soumission envoi d'une requête HTTP en POST vers 'GestionCoursController' et sa méthode ajouterCoursBdd--%>
	<div class="content">
	<h3>Ajout d'un nouveau cours</h3>
	
	<br/>
	
	<form:form modelAttribute="attribut_cours" method="POST" action="${pageContext.request.contextPath}/cours/ajouter">
	
	<%-- affichage de tous les messages d'erreurs --%>		
	<form:errors path="*" element="div" class="alert alert-dismissible alert-danger"/>	
		
		<fieldset>
			
			<div class="form-group">
			  	<form:label class="col-form-label" path="libelle">Libellé</form:label>
  				<form:input type="text" class="form-control" placeholder="Entrer le libellé du cours" path="libelle"/>
  				<form:errors path="libelle" cssStyle="color:red; font-style:italic;"/>
			</div>
			
			<div class="row">
			
			<div class="form-group col-md-4">
			  	<form:label class="col-form-label" path="date">Date</form:label>
  				<form:input type="date" class="form-control" path="date"/>
  				<form:errors path="date" cssStyle="color:red; font-style:italic;"/>
			</div>

			<div class="form-group col-md-4">
			  	<form:label class="col-form-label" path="duree">Durée</form:label>
			  	<div class="input-group mb-3">
  				<form:input type="number" class="form-control" path="duree"/>
  				<div class="input-group-append">
        			<span class="input-group-text">min</span>
      			</div>
      			</div>
  				<form:errors path="duree" cssStyle="color:red; font-style:italic;"/>
			</div>
			
			</div>		

			<div class="form-group">
			  	<form:label class="col-form-label" path="description">Description</form:label>
  				<form:textarea class="form-control" placeholder="Entrer la description du cours" path="description"/>
  				<form:errors path="description" cssStyle="color:red; font-style:italic;"/>
			</div>
			
			<div class="form-group">
			  	<form:label path="matieres.idMatiere">Matière</form:label>
				<form:select path="matieres.idMatiere" class="form-control">
              			<form:option value="" label="--Sélectionner la matière"/>
            			<form:options items="${attribut_matieres}" itemValue="idMatiere" itemLabel="libelle"/>
         		</form:select>  				
         		<form:errors path="matieres.idMatiere" cssStyle="color:red; font-style:italic;"/>
			</div>
			
			<div class="form-group">
			  	<form:label path="promotions.idPromotion">Promotion</form:label>
				<form:select path="promotions.idPromotion" class="form-control">
              			<form:option value="" label="--Sélectionner la promotion"/>
            			<form:options items="${attribut_promotions}" itemValue="idPromotion" itemLabel="libelle"/>
         		</form:select>  				
         		<form:errors path="promotions.idPromotion" cssStyle="color:red; font-style:italic;"/>
			</div>
			
		</fieldset>
		
		<br/>
		
		<input type="submit" class="btn btn-dark" value="Ajouter"/>
	
	</form:form>
	
	<br/><br/><br/>
	
	</div>
	
	<!-- =========================================================== -->
	<!-- ======== FOOTER  ========================================== -->
	<!-- =========================================================== -->
	
	<%@include file="/WEB-INF/generic/footer.jsp" %>
</body>
</html>