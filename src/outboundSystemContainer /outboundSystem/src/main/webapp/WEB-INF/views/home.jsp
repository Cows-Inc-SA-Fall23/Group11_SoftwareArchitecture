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
					.modal-dialog {
						max-width: 800px;
					}

					.black-text {
						color: black;
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
					<div class="container">
						<div class="row">
							<div class="col-12">
								<div class="blue-box">
									<div class="top-left-box">Product Batches</div>
									<div class="batch" id="batch1">Batch #1 - Pass</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="blue-box">
									<div class="top-left-box">Product Details</div>
									<div>Product #1 - ...</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="blue-box">
									<div class="top-left-box">Reports</div>
									<div class="button-container d-grid gap-2 col-4 mx-auto">
										<div class="report-container">
											<%-- <c:forEach items="${reports}" var="report">
												<a href="#" class="report-link" data-bs-toggle="modal"
													data-bs-target="#reportDetailsModal"
													data-report-id="${report.id}">Report #${report.id}</a>
												</c:forEach>
												--%>
										</div>
										<button class="btn btn-outline-secondary btn-sm" type="button"
											data-bs-toggle="modal" data-bs-target="#reportModal">Generate
											Report</button>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-12">
								<div class="blue-box">
									<div class="top-left-box">Monitoring</div>
									<div class="container mt-3">
										<div class="mt-4"></div>
										<div class="row">
											<div class="col-11">
												<div class="progress" style="height: 20px;">
													<div class="monitoring-box progress-bar" role="progressbar"
														style="width: 50%; background-color: #0E0E2C" aria-valuenow="50"
														aria-valuemin="0" aria-valuemax="100">50% Humidity</div>
												</div>
												<div class="mt-2"></div>
												<div class="progress" style="height: 20px;">
													<div class="monitoring-box progress-bar" role="progressbar"
														style="width: 70%; background-color: #0E0E2C" aria-valuenow="50"
														aria-valuemin="0" aria-valuemax="100">70% Feed Capacity </div>
												</div>
											</div>
										</div>
										<div class="col-sm-12 d-flex flex-column">
											<button class="btn btn-outline-secondary btn-sm align-self-end"
												type="button">Next</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="blue-box">
									<div class="top-left-box">Alerts</div>
									<div>Alert ID#1 - ...</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="blue-box">
									<div class="top-left-box">Standards</div>
									<div>Standard #1 - ...</div>
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
				<div class="modal fade" id="reportModal" tabindex="-1" aria-labelledby="reportModalLabel"
					aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title black-text" id="reportModalLabel">New Report</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<form id="reportForm" action="/generateReport" method="post">
									<input type="hidden" name="batchId" value="${batchId}" />
									<div class="mb-3">
										<label for="reportText" class="form-label black-text">Report Content</label>
										<textarea class="form-control" id="reportText" name="reportText"
											required></textarea>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Close</button>
										<button type="submit" class="btn btn-outline-secondary">Save Report</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="modal fade" id="reportDetailsModal" tabindex="-1" role="dialog"
					aria-labelledby="reportDetailsModalLabel" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="reportDetailsModalLabel">Report Details</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
							</div>
						</div>
					</div>
				</div>
				<script>
					document.addEventListener('DOMContentLoaded', function () {
						const reportLinks = document.querySelectorAll('.report-link');
						reportLinks.forEach(link => {
							link.addEventListener('click', function (event) {
								event.preventDefault();
								const reportId = this.getAttribute('data-report-id');
								fetchReportDetails(reportId);
							});
						});
					});

					function fetchReportDetails(reportId) {
						fetch('/api/reports/' + reportId)
							.then(response => response.json())
							.then(data => {
								const modalBody = document.querySelector('#reportDetailsModal .modal-body');
								modalBody.innerHTML = '<p>ID: ' + data.id + '</p>'
									+ '<p>Content: ' + data.report_text + '</p>'
									+ '<p>Date: ' + data.date + '</p>';
							})
							.catch(error => console.error('Error fetching report details:', error));
					}
				</script>
			</body>

			</html>