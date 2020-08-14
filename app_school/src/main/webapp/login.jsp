<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Authentification</title>

   
	<!-- Lien vers feuille de style de Bootstrap -->
	<link href="${pageContext.request.contextPath}/resources/styles/bootstrap.css"
		rel="stylesheet">

	<!-- Lien vers feuille de style perso de signin -->
	<link href="${pageContext.request.contextPath}/resources/styles/signin.css"
	    rel="stylesheet">

    <!-- Lien vers font awesome 4.7.0-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">

	<link href="https://fonts.googleapis.com/css2?family=Homemade+Apple&display=swap" rel="stylesheet">
	
	
	<link href="https://fonts.googleapis.com/css2?family=Cabin+Sketch&display=swap" rel="stylesheet"> <!-- 'Cabin Sketch' -->
	<link href="https://fonts.googleapis.com/css2?family=Fredericka+the+Great&display=swap" rel="stylesheet"> <!-- 'Fredericka the Great' -->
	   <link href="https://fonts.googleapis.com/css2?family=Miltonian&display=swap" rel="stylesheet"> <!-- 'Miltonian' -->
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/jquery-3.4.1.min.js"></script>	
	<script src="${pageContext.request.contextPath}/resources/scripts/bootstrap.min.js"></script>
	
	 
</head>
<body>
		
	
	<a class="brand" href="index.jsp"  >SchoolApp</a>
	

	<!-- +++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
	<!-- ++++++ AFFICHAGE DES MESSAGES D'ERREURS +++++++++++++ -->
	<!-- +++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
	<%-- en cas d'échec de l'authentification --%>
	<c:if test="${not empty param.error}">
		<div class="alert alert-danger alert-dismissible fade show" role="alert" style="position:absolute;top:10px;right:0">

			<font style="color: red; font-style: italic;">
				Erreur d'authentification. Identifiant ou mot de passe invalide. <br/>
				<b>Raison<b/> : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} 			
			</font>
		
		  	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    	<span aria-hidden="true">&times;</span>
		  	</button>	
		</div>
	</c:if>	
	
	
	
	<!-- +++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
	<!-- ++++++ FORMULAIRE D'AUTHENTIFICATION ++++++++++++++++ -->
	<!-- +++++++++++++++++++++++++++++++++++++++++++++++++++++ -->      
	
	<c:url value="login" var="loginUrl" />

	<!-- formulaire -->
	<form action="${loginUrl}" method="post" class="form-signin">
	
		<!-- HEADER -->
		<h1 class="fa fa-user-o fa-3x" style="align-content: center;color:white; font-size:8em; margin-bottom:50px"></h1>
      
       	<h2 class="h3 mb-3 font-weight-normal" style="font-family: 'Homemade Apple', cursive;color:white;margin-bottom:50px">
       		Veuillez vous connecter
       	</h2>
      
      
      	<!-- LABELS -->
      	<label for="inputId" class="sr-only" >Identifiant</label>
      	<input type="text" id="inputId" name="username" class="form-control" placeholder="Identifiant" >
      
     	<label for="inputPassword" class="sr-only">Mot de passe</label>
      	<input type="password" id="inputPassword" name="password" class="form-control" placeholder="Mot de passe" >		
	
	
		<!-- BUTTONS -->
		<div class="container-fluid" style="margin-top:20px" >
			<div class="row" >
			  <button type="submit" value="Se connecter" class="btn btn-lg btn-info btn-block" style="margin-left:0">Se connecter </button> 
			  <p style="color:darkgray;margin-right:33px;margin-left:33px; width:4px;font-size: 22px" >|</p>
			  <button type="reset" value="Reset" class="btn btn-lg btn-info btn-block" style="margin-right:0" >Reset </button>
	  		</div>
		</div>
		
		
	</form>

	<!-- ===================================================== -->
	<!-- =============== FOOTER ============================== -->
	<!-- ===================================================== -->
  <div class="clear" style="clear:both"></div>
     
	<footer class="footer" style="color:lightgray;position:absolute;bottom:0" >
  
        <p>2020 Copyright © Groupe2 : Gio, Hannah, Marlène &#x26; Gab  </p>
         
  </footer>




</body>
</html>