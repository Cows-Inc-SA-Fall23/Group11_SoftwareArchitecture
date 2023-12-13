<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<jsp:include page='components/imports.jsp' />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${title}</title>
<link href="webjars/bootstrap/5.2.3/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="/sensorManager/css/general.css">
<link rel="shortcut icon" href="/sensorManager/favicon.ico"
	type="image/x-icon">
<script src="webjars/bootstrap/5.2.3/js/bootstrap.min.js"></script>
<script src="webjars/jquery/3.6.3/jquery.min.js"></script>
</head>
<body class="text-center overflow-hidden">
	<div style="width: 100%; max-width: 100%;"
		class="cover-container d-flex w-100 h-100 mx-auto flex-column">
		<header class="masthead mb-auto p-3">
			<div id="navbar" class="inner">
				<a style="z-index: 999;" id="logoLink" class="nav-link"
					href="/sensorManager/">
					<h3 class="masthead-brand">Home</h3>
				</a>
				<nav id="centered-nav"
					class="nav nav-masthead justify-content-center">
					<a class="nav-link" href="/sensorManager/debug">Debug and
						testing</a> <a class="nav-link" href="/sensorManager/sensorDataList">Sensor
						data list</a> <a class="nav-link" href="/sensorManager/sensors">Sensors</a>
					<a class="nav-link" href="/sensorManager/contact">Contact</a>
				</nav>
			</div>
		</header>
		<div id="logo">
			<img id="logoImage" src="images/cowsensor.png" alt="Logo">
		</div>
		<div class="container mt-4">
			<h2>Sensor Data</h2>
			<div class="scrollable-table-container">
				<table class="table white-text-table">
					<thead>
						<tr>
							<th>ID</th>
							<th>Timestamp</th>
							<th>Value Name</th>
							<th>Value</th>
							<th>Sensor MAC</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${sensorDataList}" var="sensorData">
							<tr>
								<td>${sensorData.id}</td>
								<td>${sensorData.timestamp}</td>
								<td>${sensorData.valueName}</td>
								<td>${sensorData.value}</td>
								<td>${sensorData.sensor.mac}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<!-- Footer and other content -->
		</div>
</body>
</html>
