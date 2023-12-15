<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>

<jsp:include page='components/imports.jsp' />

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>${title}</title>

<link href="webjars/bootstrap/5.2.3/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
	rel="stylesheet">

<link rel="stylesheet" href="/sensorManager/css/general.css">

<link rel="shortcut icon" href="/sensorManager/favicon.ico"
	type="image/x-icon">


<script src="webjars/bootstrap/5.2.3/js/bootstrap.min.js"></script>
<script src="webjars/jquery/3.6.3/jquery.min.js"></script>
<!-- Custom styles for this template -->
</head>
<body class="text-center overflow-hidden">
	<div style="width: 100%; max-width: 100%;"
		class="cover-container d-flex w-100 h-100 mx-auto flex-column">
		<header class="masthead mb-auto p-3">
			<div id="navbar" class="inner">
				<a style="z-index:999;" id="logoLink" class="nav-link" href="/sensorManager/">
					<h3 class="masthead-brand">Home</h3>
				</a>
				<nav id="centered-nav"
					class="nav nav-masthead justify-content-center">
					<a class="nav-link" href="/sensorManager/debug">Debug and
						testing</a>
					<a class="nav-link" href="/sensorManager/sensorDataList">Sensor data list</a>
					<a class="nav-link" href="/sensorManager/sensors">Sensors</a>
					<a class="nav-link" href="/sensorManager/contact">Contact</a>
				</nav>
			</div>
		</header>
		<div id="logo">
			<img id="logoImage" src="images/cowsensor.png" alt="Logo">
		</div>
		<div id="main" role="main" class="inner cover position-absolute p-3">
			<div class="container mt-4">
				<div class="row">
					<div class="col">
						<input type="text" id="valueName" value="temp[C]"
							placeholder="Value Name" class="form-control mb-2">
					</div>
					<div class="col">
						<input type="text" id="value" value="27" placeholder="Value"
							class="form-control mb-2">
					</div>
					<div class="col">
						<input type="text" id="sensorMac" value="1a"
							placeholder="Sensor MAC" class="form-control mb-2">
					</div>
					<div class="col">
						<button id="sendDataBtn" class="btn btn-primary">Send
							Random Sensor Data</button>
						<i id="statusIcon" style="margin-left: 10px;"></i>
					</div>
				</div>
			</div>
		</div>
		<footer class="mastfoot mt-auto p-3"> </footer>
	</div>
	<div id="pageBg"></div>
	<div id="pageBgEffect"></div>
	<script>
		$(document).ready(function() {
			$('#sendDataBtn').click(
			function() {
				$.ajax({
				url : 'sendRandomSensorData',
				type : 'POST',
				data : {
					valueName : $(
							'#valueName')
							.val(),
					value : $(
							'#value')
							.val(),
					sensorMac : $(
							'#sensorMac')
							.val()
				},
				success : function(
						response) {
					console
							.log('Data sent successfullyy');
					$('#statusIcon')
							.removeClass()
							.addClass(
									'fas fa-check text-success');
				},
				error : function(error) {console.error('Error sending data',error);
					$('#statusIcon').removeClass().addClass('fas fa-times text-danger');
				}
			});
		});
		});
	</script>

</body>
</html>