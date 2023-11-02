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
	<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
		<header class="masthead mb-auto">
			<div class="inner">
				<h3 class="masthead-brand">Vee's gaming brand</h3>
				<nav class="nav nav-masthead justify-content-center">
					<a class="nav-link" href="/ddd/">Home</a> <a
						class="nav-link active" href="/ddd/demo">DEMO</a>
				</nav>
			</div>
		</header>

		<main role="main" class="inner cover">
			<h1 class="cover-heading">Coomer hotel demo.</h1>
			<p class="lead">
				Use arrow keys to move, x to interact.
			</p>
			<p class="lead">
				<a href="#" class="btn btn-lg btn-secondary">Learn more</a>
			</p>
		</main>

		<jsp:include page='components/footer.jsp' />
	</div>
	
<!-- 	<div style="aspect-ratio: auto 800/600; background: 0% 0%/contain rgb(20, 21, 31);"></div> -->
<!-- 	<div style="aspect-ratio: auto 800/600; background: 0% 0%/contain rgb(20, 21, 31);"></div> -->
	<canvas id="playArea" style="aspect-ratio: auto 800/600; background: 0% 0%/contain rgb(20, 21, 31);"></canvas>
	
	<script type="text/javascript" src="matter.min.js"></script>
	<script type="text/javascript" src="/ddd/doublePendulum.js"></script>
	<script type="text/javascript">
		$(()=>{
			Example.doublePendulum();
// 			console.log("Ready");
// 			setup();
// 			draw();
		});
	</script>
</body>
</html>