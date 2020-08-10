<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/styles/header.css"
    rel="stylesheet">
</head>

<body>


<div id="divhaute" class="container-fluid col-lg-12">
<h1 id="titre"> SchoolApp </h1>
	<a href="#" id="connexion" type="button" class="btn btn-secondary">
		<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
	  		<path d="M13.468 12.37C12.758 11.226 11.195 10 8 10s-4.757 1.225-5.468 2.37A6.987 6.987 0 0 0 8 15a6.987 6.987 0 0 0 5.468-2.63z"/>
	  		<path fill-rule="evenodd" d="M8 9a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
	  		<path fill-rule="evenodd" d="M8 1a7 7 0 1 0 0 14A7 7 0 0 0 8 1zM0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8z"/>
		</svg>
		Connexion
	</a>
</div>

<aside class="col-12 col-md-2 p-0 bg-light" id="sidebar">

	<div id="navig" class="col-lg-3">
	
	<nav class="navbar navbar-expand navbar-light flex-md-column flex-row align-items-start">
	  <a class="navbar-brand" href="#" id="accueil">
	  	<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-house navicon" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
	  		<path fill-rule="evenodd" d="M2 13.5V7h1v6.5a.5.5 0 0 0 .5.5h9a.5.5 0 0 0 .5-.5V7h1v6.5a1.5 1.5 0 0 1-1.5 1.5h-9A1.5 1.5 0 0 1 2 13.5zm11-11V6l-2-2V2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5z"/>
	  		<path fill-rule="evenodd" d="M7.293 1.5a1 1 0 0 1 1.414 0l6.647 6.646a.5.5 0 0 1-.708.708L8 2.207 1.354 8.854a.5.5 0 1 1-.708-.708L7.293 1.5z"/>
		</svg>Accueil</a>
	
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary" style="margin-bottom:50px">
	  <!-- <a class="navbar-brand" href="#">Navbar</a>-->
	
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	
	  <div class="collapse navbar-collapse" id="navbarColor01">
	    <ul class="navbar-nav mr-auto flex-md-column flex-row navbar-nav w-100 justify-content-between" id="navul">
	      <li class="nav-item navli">
	        <a class="nav-link" href="${pageContext.request.contextPath}/gestionEtudiants/listeEtudiants">Etudiant</a>
	      </li>
	      <li class="nav-item navli">
	        <a class="nav-link" href="${pageContext.request.contextPath}/gestionEnseignants/listeEnseignants">Enseignant</a>
	      </li>
	      <li class="nav-item navli">
	        <a class="nav-link" href="">Promotion</a>
	      </li>
	      <li class="nav-item navli">
	        <a class="nav-link" href="${pageContext.request.contextPath}/matiere/liste-matiere">Matière</a>
	      </li>
	      <li class="nav-item navli">
	        <a class="nav-link" href="${pageContext.request.contextPath}/cours/liste">Cours</a>
	      </li>
	      <li class="nav-item navli">
	        <a class="nav-link" href="${pageContext.request.contextPath}/etudiants-cours/liste">Absence</a>
	      </li>
	      <li class="nav-item navli">
	        <a class="nav-link" href="#">Aide</a>
	      </li>  
	      <li id="copyright">
	         <p>© Copyright</p>
	      </li>  
	    </ul>
	   
	  </div>
	</nav>

	</div>

 </aside>


</body>
</html>