<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<jsp:include page='components/imports.jsp' />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Sensors Data List</title>
<link href="webjars/bootstrap/5.2.3/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="/sensorManager/css/general.css">
<link rel="shortcut icon" href="/sensorManager/favicon.ico"
	type="image/x-icon">
<script src="webjars/bootstrap/5.2.3/js/bootstrap.min.js"></script>
<script src="webjars/jquery/3.6.3/jquery.min.js"></script>
<!-- Additional script for handling future livestock selection functionality -->
</head>
<body class="text-center overflow-hidden">
	<div class="cover-container d-flex w-100 h-100 mx-auto flex-column">
		<header class="masthead mb-auto p-3">
			<!-- Navigation bar -->
		</header>
		<main role="main" class="inner cover">
			<form action="saveSensorLivestockAssignments" method="post">
				<h2 class="cover-heading">Sensor Data</h2>
				<div class="scrollable-table-container">
					<table class="table white-text-table">
						<!-- ... existing table headers ... -->
						<tbody>
							<c:forEach items="${sensorList}" var="sensor">
								<tr>
									<td>${sensor.mac}</td>
									<td><select name="livestock_${sensor.mac}"
										class="form-control">
											<option value="-1"/>
											<c:forEach items="${livestockList}" var="livestock">
												<option value="${livestock.id}"
													${livestock.id == sensor.livestock ? 'selected' : ''}>${livestock.toString}</option>
											</c:forEach>
									</select></td>
									<td>${sensor.dataEntriesNumber}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<button type="submit" class="btn btn-primary">Save Changes</button>
			</form>
		</main>
		<footer class="mastfoot mt-auto"> </footer>
	</div>
</body>
</html>
