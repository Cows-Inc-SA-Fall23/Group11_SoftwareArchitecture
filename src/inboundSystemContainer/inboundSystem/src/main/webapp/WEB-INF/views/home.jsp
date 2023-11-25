<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

			<html>

			<head>

				<jsp:include page='components/imports.jsp' />

				<meta charset="UTF-8">
				<meta name="viewport" content="width=device-width, initial-scale=1">

				<title>${title}</title>

				<link href="webjars/bootstrap/5.2.3/css/bootstrap.min.css" rel="stylesheet">

				<link rel="stylesheet" href="/labSystem/css/general.css">

				<link rel="shortcut icon" href="/controlPanel/favicon.ico" type="image/x-icon">


				<script src="webjars/bootstrap/5.2.3/js/bootstrap.min.js"></script>
				<script src="webjars/jquery/3.6.3/jquery.min.js"></script>
				<style>
					.larger-container {
						width: 100%;
						min-height: 600px;
					}

					.blue-box {
						width: 100%;
						height: 100%;
					}
				</style>
			</head>

			<body class="text-center overflow-hidden">
				<div style="width: 100%; max-width: 100%;"
					class="cover-container d-flex w-100 h-100 mx-auto flex-column">
					<header class="masthead mb-auto p-3">
						<div id="navbar" class="inner">
							<nav id="centered-nav" class="nav nav-masthead justify-content-center">
								<a class="nav-link" href="/controlPanel">Home</a>
							</nav>
						</div>
					</header>
					<div class="container-fluid">
						<div class="row">
							<div class="col-12 larger-container">
								<div class="blue-box">
								</div>
							</div>
						</div>
					</div>
					<footer class="mastfoot mt-auto p-3">
						<div class="inner">
							<p>
								<spring:message code="hello" text="**Temp text" />
							</p>
						</div>
					</footer>
				</div>
				<div id="pageBg"></div>
				<div id="pageBgEffect"></div>
				<script type="text/javascript">
					console.log("Hello");
				</script>
			</body>

			</html>