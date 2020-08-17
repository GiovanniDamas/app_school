<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form modelAttribute="linkCommand" method="POST"
		action="${pageContext.request.contextPath}/matiere/lier">

		<%-- affichage de tous les messages d'erreurs --%>
		<form:errors path="*" cssClass="erreur_validation" element="div" />

		<table width="60%">


			<tr>

				<td><form:hidden path="matiere.idMatiere" /></td>
				<td><form:label class="col-form-label"
						path="matiere.idMatiere"> Promotions: </form:label></td>
						<form:input disabled="true" path="matiere.idMatiere"/>

			</tr>

			<tr>
				<td><form:label class="col-form-label"
						path="promotion.idPromotion"> Promotions: </form:label></td>
				<td><form:select class="form-control" multiple="true"
						path="promotion.idPromotion">
						<c:forEach items="${attribut_liste_promotions}" var="prom">
							<form:option value="${prom.idPromotion}">
								<c:out value="${prom.libelle}" />
							</form:option>
						</c:forEach>
					</form:select></td>
				<td></td>
			</tr>




			<c:if test="${matier.idMatiere == null}">
				<tr>
					<td><input type="submit" value="Ajouter" /></td>
				</tr>
			</c:if>

			<c:if test="${matiere.idMatiere != null}">
				<tr>
					<td><input type="submit" value="Modifier" /></td>
				</tr>
			</c:if>
		</table>
	</form:form>
</body>
</html>