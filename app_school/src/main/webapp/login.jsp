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
	<!-- ++++++ FORMULAIRE D'AUTHENTIFICATION ++++++++++++++++ -->
	<!-- +++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
	
	<c:url value="login" var="loginUrl" />

	<!-- formulaire -->
	<form action="${loginUrl}" method="post">
		<table>
			<tr>
				<td>Identifiant :</td>
				<td><input type="text" name="identifiant" /></td>
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