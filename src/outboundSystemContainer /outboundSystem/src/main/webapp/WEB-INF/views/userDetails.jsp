<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<jsp:include page='components/imports.jsp'>
	<jsp:param name="someParam" value="false" />
</jsp:include>

<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}

</style>
<!-- Custom styles for this template -->
</head>
<body class="text-center">
	<div>
		<table>
		<tr>
			<th>id</th>
			<th>name</th>
			<th>email</th>
		</tr>
		<tr>
			<td>${savedUser.id}</td>
			<td>${savedUser.name}</td>
			<td>${savedUser.email}</td>
		</tr>
		</table>
	</div>
</body>
</html>