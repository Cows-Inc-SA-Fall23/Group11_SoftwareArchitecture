<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<jsp:include page='components/imports.jsp'>
	<jsp:param name="someParam" value="false" />
</jsp:include>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Control Panel</title>

<link href="webjars/bootstrap/5.2.3/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet" href="/controlPanel/css/general.css">

<link rel="shortcut icon" href="/controlPanel/favicon.ico"
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
				<a id="logoLink" class="nav-link" href="/controlPanel/">
					<h3 class="masthead-brand">Home</h3>
				</a>
				<nav id="centered-nav"
					class="nav nav-masthead justify-content-center">
					<a class="nav-link" href="/controlPanel/delivery">Inbound/Outbound</a>
					<a class="nav-link" href="/controlPanel/lab">Laboratory</a> <a
						class="nav-link" href="/controlPanel/livestock">Livestock</a>
				</nav>
				<nav class="nav nav-masthead nav2-fix">
					<a class="nav-link" href="/controlPanel/contact">Contact</a>
				</nav>
			</div>
		</header>
		<div id="logo">
			<img id="logoImage" src="images/cow.png" alt="Logo">
		</div>
		<div id="main" role="main" class="inner cover position-absolute p-3">
			<h1 class="cover-heading">
				BIOMON COMING <b>SOON&#8482;</b>.
			</h1>
			<p class="lead">Some description&#8482;</p>
			<p class="lead">
				<a href="#" class="btn btn-lg btn-secondary">WISHLIST NOW</a>
			</p>
		</div>
		<footer class="mastfoot mt-auto p-3">
			<div class="inner">
				<p>asdf</p>
				<p><spring:message code="hello"/></p>
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