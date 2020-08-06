<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<link href="${pageContext.request.contextPath}/resources/styles/bootstrap.css"
    rel="stylesheet">

<title>Insert title here</title>
</head>
<body>



	<h1>
		<u>Liste des matières:</u>
	</h1>

	<table border="1" width="70%">

		<tr>
		
			<td colspan="5" align="right"><a
				href="${pageContext.request.contextPath}/employe/add-employe-form">
					Ajout d'une matière </a></td>
		</tr>

		<!-- en tête de la table -->
		<tr>
			<th>Libelle</th>
		
			<th>Modifier</th>
			<th>Supprimer</th>
		</tr>

		<!-- données de la table -->
		<c:forEach items="${attribut_liste_matieres }" var="mat">
			<tr>
				<td>${mat.libelle}</td>
				

			
				<td>
				
					<a
					href="${pageContext.request.contextPath}/employe/update-employe-form?idemploye=${employe.idEmploye}">
						Modifier</a>
				</td>
				

			
				<td>
				
					<a
					href="${pageContext.request.contextPath}/employe/delete/${employe.idEmploye}">
						Supprimer</a>
				</td>

			</tr>

		</c:forEach>
	</table>
	
	<button type="button" class="btn btn-danger">Hello</button>


</body>
</html>