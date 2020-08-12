<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Aide</title>

	<link href="${pageContext.request.contextPath}/resources/styles/bootstrap.css"
	rel="stylesheet">

</head>

<body>


	<jsp:include page="/WEB-INF/generic/header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/generic/sidebar.jsp"></jsp:include> 
	
	<h2> Bienvenue dans l'aide ! </h2>

	<div style="width: 70%; float: right; margin-right: 10%">

		<table class="table table-bordered">

			<h3 align="center">Gestion de l'aide</h3>
			<thead style="background-color: black; color: white">
				<tr>
					<th scope="col">#</th>
					<th scope="col">Page</th>
					<th scope="col" colspan="4" >Contenu</th>
				
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${attribut_liste_aide}" var="aides">
					<tr>
						<th scope="row">${aides.idAide}</th>
						<th>${aides.urlPage}</th>
						<th colspan="4">${aides.contenu}</th>
					
						<td><a	href="${pageContext.request.contextPath}/aide/editAide?idAide=${aides.idAide}"
								aria-pressed="true">
								<i class="fas fa-pencil-alt fa-3x">Modifier</i>
							</a>
						</td>

						<!-- colonne pour la suppression de l'emploe -->
						<td><a
							href="${pageContext.request.contextPath}/aide/delete?idAide=${aides.idAide}">Supprimer</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<br/><br/>
		<a	href="${pageContext.request.contextPath}/aide/editAide?idAide=0"
			class="btn btn-primary btn-md active" role="button"
			aria-pressed="true" style="align-content: left"> 
			Ajout d'une fiche d'aide
		</a>

	</div>



	<div style="float: none; margin-top: 50%">
		<a href="${pageContext.request.contextPath}/index.jsp">lien vers
			accueil</a>

	</div>

</body>
</html>