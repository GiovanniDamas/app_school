<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<!-- +++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
	<!-- ++++++ AFFICHAGE DES MESSAGES D'ERREURS +++++++++++++ -->
	<!-- +++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
	<%-- en cas d'�chec de l'authentification --%>
	<c:if test="${not empty param.error}">
		<font style="color: red; font-style: italic;">
			Erreur d'authentification. Identifiant ou mot de passe invalide. <br/>
			<b>Raison<b/> : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} 			
		</font>
	</c:if>

	<!-- +++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
	<!-- ++++++ FORMULAIRE D'AUTHENTIFICATION ++++++++++++++++ -->
	<!-- +++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
	
	<c:url value="login" var="loginUrl" />

	<!-- formulaire -->
	<form action="${loginUrl}" method="post">
		<table>
			<tr>
				<td>Identifiant :</td>
				<td><input type="text" name="username" /></td>
			</tr>
			<tr>
				<td>Mot de passe :</td>
				<td><input type="text" name="password" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Se connecter" /> |
					<input type="reset" value="Reset" /></td>
			</tr>
		</table>
	</form>

</body>
</html>