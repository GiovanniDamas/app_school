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
<script src="https://kit.fontawesome.com/9dde17f0e3.js" crossorigin="anonymous"></script>

</head>

<body>

<div id="divhaute" class="container-fluid col-lg-12">
<h1 id="titre"> SchoolApp </h1>
<a href="#" id="connexion" type="button" class="btn btn-secondary">
	<i class="fas fa-user-circle"></i>
	Connexion
</a>
</div>

<aside class="col-md-2 p-0 bg-light" id="sidebar">

<div id="navig">

<nav class="navbar navbar-expand navbar-light flex-md-column flex-row align-items-start">
  <a class="navbar-brand" href="#" id="accueil">
  	<i class="fas fa-home"></i>Accueil</a>

  <div class="collapse navbar-collapse" id="navbarColor01">
    <ul class="navbar-nav mr-auto flex-md-column flex-row navbar-nav w-100 justify-content-between" id="navul">
      <li class="nav-item navli">
        <a class="nav-link" href="#">Etudiant</a>
      </li>
      <li class="nav-item navli">
        <a class="nav-link" href="#">Enseignant</a>
      </li>
      <li class="nav-item navli">
        <a class="nav-link" href="#">Promotion</a>
      </li>
      <li class="nav-item navli">
        <a class="nav-link" href="#">Matière</a>
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