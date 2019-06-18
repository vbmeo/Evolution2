<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>List</title>
</head>
<body>
	<table>
		<tr>
			<td>Email address</td>
			<td>First name</td>
			<td>Last name</td>
			<td>Username</td>
			<td>delete</td>
			<td>edit</td>
		</tr>
		<c:if test="${!empty macros}">
			<c:forEach items="${macros}" var="macro">
				<tr>
					<td>${macro.data}</td>
					<td>${macro.calorie_sett }</td>
					<td>${macro.carboidrati_sett }</td>
					<td>${macro.proteine_sett }</td>
					<td><a href="<c:url value="delete/${macro.id}"/>">Delete</a></td>
					<td><a href="<c:url value="edit/${macro.id}"/>">Edit</a></td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
	<a href="<c:url value="/"/>">Aggiungi</a>
</body>