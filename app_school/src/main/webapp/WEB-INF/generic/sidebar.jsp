<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
--%>
<title>Insert title here</title>

	<link href="${pageContext.request.contextPath}/resources/styles/sidebar.css"
    	rel="stylesheet">
    
	<link href="${pageContext.request.contextPath}/resources/styles/bootstrap.css"
    	rel="stylesheet">

    <!-- Lien vers font awesome 4.7.0-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">


</head>
<body>


    <nav class="sidebar" id="sidebar">
    
      <!--
        <button type="button" class="toggler" id="sidebarCollapse" > <span class="fa fa-arrow-left fa-2x"></span></button>
        -->


		<div class="sidebar-header">
			<a   href="#" ><span class="fa fa-home" style="margin-right: 5px;"></span>Accueil</a>
		</div>
		
        <ul class="sidebar-links">

            <li class="nav-item navli">
                <a class="nav-link" href="${pageContext.request.contextPath}/gestionEtudiants/listeEtudiants">Etudiant</a>
              </li>
              <li class="nav-item navli">
                <a class="nav-link" href="${pageContext.request.contextPath}/gestionEnseignants/listeEnseignants">Enseignant</a>
              </li>
              <li class="nav-item navli">
                <a class="nav-link" href="${pageContext.request.contextPath}/promotion/liste-promotion">Promotion</a>
              </li>
              <li class="nav-item navli">
                <a class="nav-link" href="${pageContext.request.contextPath}/matiere/liste-matiere">Matière(s)</a>
              </li>
              <li class="nav-item navli">
                <a class="nav-link" href="${pageContext.request.contextPath}/cours/liste">Cours</a>
              </li>
              <li class="nav-item navli">
                <a class="nav-link" href="${pageContext.request.contextPath}/etudiants-cours/liste">Absence</a>
              </li>
              <li class="nav-item navli">
                <a class="nav-link" href="${pageContext.request.contextPath}/aide/listeAide">Aide</a>
              </li>  
              <li id="copyright">
                 <p>© Copyright</p>
              </li>  


        </ul>
        

	</nav>
	
	<div id="content">
	     <button type="button" id="sidebarCollapse" class="navbar-btn">
	         <span></span>
	         <span></span>
	         <span></span>
	         
	       
	   </button>
	</div>
	
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/jquery-3.4.1.min.js"></script>	
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/bootstrap.js"></script>	

	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/sidebar.js"></script>	


</body>
</html>