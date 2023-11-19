<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>

<jsp:include page='components/imports.jsp' />

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>${silos.title}</title>

<link href="webjars/bootstrap/5.2.3/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet" href="/feedSystem/css/general.css">

<link rel="shortcut icon" href="/feedSystem/favicon.ico"
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
				<a id="logoLink" class="nav-link masthead-brand" href="/feedSystem/">
					<h3 class="masthead-brand">Home</h3>
				</a>
				<nav id="centered-nav"
					class="nav nav-masthead justify-content-center">
					<a class="nav-link" href="/feedSystem/silos"><spring:message
							code="silos.title" /></a>
				</nav>
				<nav class="nav nav-masthead nav2-fix">
					<a class="nav-link" href="/feedSystem/contact">Contact</a>
				</nav>
			</div>
		</header>

		<div id="main" role="main" class="inner cover position-absolute p-3">
			<h1 class="cover-heading">
				<spring:message code="silos.title" />
			</h1>
			<p>
				Silos data:

				<c:forEach items="${silosA}" var="s">
    				${s.html} <input type="button" value="Delete Silo" onclick="deleteSilo(${s.id});"/><br>
				</c:forEach>

				<c:if test="${1==1}">
					<h3>WORKS</h3>
				</c:if>
			</p>
			<!-- Form for creating a new silo -->
			<form id="createSiloForm">
				<label for="name">Name:</label> 
				<input type="text" id="name" name="name"><br> 
				
				<label for="capacity">Capacity (kg):</label> 
				<input type="number" id="capacity" name="capacity_kg"><br>
	
				<input type="button" value="Create Silo" onclick="createSilo()">
			</form>
		</div>


		<footer class="mastfoot mt-auto p-3">
			<div class="inner">
				<p>footer</p>
			</div>
		</footer>
	</div>
	<div id="pageBg"></div>
	<div id="pageBgEffect"></div>
	<script type="text/javascript">
	
		function createSilo() {
		    var name = document.getElementById('name').value;
		    var capacity = document.getElementById('capacity').value;
	
		    var xhr = new XMLHttpRequest();
		    xhr.open("POST", "silos/create", true);
		    xhr.setRequestHeader("Content-Type", "application/json");
		    xhr.onreadystatechange = function () {
		        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
		            // Handle success - maybe update the UI with the new silo
		            alert("Silo created successfully!");
		            // Optionally, clear the form fields
		            document.getElementById('name').value = '';
		            document.getElementById('capacity').value = '';
		        }
		    }
		    var data = JSON.stringify({"name": name, "capacity_kg": parseInt(capacity)});
		    xhr.send(data);
		}
	
		function deleteSilo(siloId) {
		    var xhr = new XMLHttpRequest();
		    xhr.open("DELETE", "silos/delete/" + siloId, true);
		    xhr.onreadystatechange = function () {
		        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
		            // Handle success - maybe update the UI to remove the deleted silo
		            alert("Silo deleted successfully!");
		        }
		    }
		    xhr.send();
		}

		
	</script>
</body>
</html>