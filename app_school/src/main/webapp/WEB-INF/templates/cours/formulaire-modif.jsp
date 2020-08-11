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

	<%-- affichage de tous les messages d'erreurs --%>		
	<form:errors path="*" element="div" class="alert alert-dismissible alert-danger"/>				

		<fieldset>
			
			<!--  récup de l'id du cours à modifier dans un champ caché -->
			<form:hidden path="idCours"/>
			
			<div class="form-group">
			  	<form:label class="col-form-label" path="libelle">Libellé</form:label>
  				<form:input type="text" class="form-control" placeholder="Entrer le libellé du cours" path="libelle"/>
  				<form:errors path="libelle" cssStyle="color:red; font-style:italic;"/>
			</div>
			
			<div class="row">
			
			<div class="form-group col-md-4">
			  	<form:label class="col-form-label" path="date">Date</form:label>
  				<form:input class="form-control" path="date" type="date"/>
  				<form:errors path="date" cssStyle="color:red; font-style:italic;"/>
			</div>

			<div class="form-group col-md-4">
			  	<form:label class="col-form-label" path="duree">Durée</form:label>
			  	<div class="input-group mb-3">
  				<form:input type="text" class="form-control" path="duree"/>
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
		
		<input type="submit" class="btn btn-dark" value="Modifier"/>
		
		</form:form>
		
		<br/><br/><br/>
		
</div>
	
</body>
</html>