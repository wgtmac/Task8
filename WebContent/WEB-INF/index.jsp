<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Team Hex Web Service - Welcome</title>

<!-- Bootstrap Core CSS -->
<link
	href="resources/bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="resources/bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="resources/bower_components/metisMenu/dist/metisMenu.min.css"
	rel="stylesheet">
<link href="resources/bower_components/metisMenu/dist/metisMenu.min.css"
	rel="stylesheet">

<!-- Timeline CSS -->
<link href="resources/dist/css/timeline.css" rel="stylesheet">
<link href="resources/dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="resources/bower_components/morrisjs/morris.css"
	rel="stylesheet">
<link href="resources/bower_components/morrisjs/morris.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="resources/bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href="resources/bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<script
	src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<script type="text/javascript" src="//js.maxmind.com/js/geoip.js">
	
</script>

</head>

<body>
	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.do">Hello, we believe you
					are in <font color="black"> <script type="text/javascript">
						var city = geoip_city() + ", " + geoip_region();
						if (city.length <= 2) {
							change = "an unknown cool place";
							city = "";
						}
						if (typeof change === 'undefined') {
							document.write(city);
						} else {
							document.write(change);
						}
					</script></font>
				</a>
			</div>

			<!-- /.hidden form that auto submits -->
			<div style="display: none;">
				<iframe id="hidden_frame" name="hidden_frame"></iframe>

				<form id="hidden_form" method="POST" action="toBeEdited"
					target="hidden_frame">
					<input type="text" name="hidden_city" />
					<script type="text/javascript">
						//<![CDATA[
						{
							document.forms[0].elements['hidden_city'].value = "oklahoma";
						}
						//]]>
					</script>
				</form>
				<script type="text/javascript">
					document.getElementById("hidden_form").submit();
				</script>
			</div>

			<!-- /.navbar-header -->
			<!-- /.navbar-top-links -->

			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<li><a href="index.do"><i class="fa fa-dashboard fa-fw"></i>
								Cool-O-Meter Home</a></li>
						<li><a href="about.do"><i class="fa fa-files-o fa-fw"></i>
								What is Cool-O-Meter?</a></li>
					</ul>
					<br> <img src="resources/img/banner.png"
						alt="social media banner" />
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Cool-O-Meter</h1>
					<div class="alert alert-success alert-dismissable">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">&times;</button>
						Use our meter to find out how "cool" is your city compared to
						another one. To learn about the rationale used <a href="about.do"
							class="alert-link">click here</a>.
					</div>
				</div>
			</div>

			<c:choose>
				<c:when test="${ (empty msg) }">
				</c:when>
				<c:otherwise>
					<h3 style="color: blue">${msg}</h3>
				</c:otherwise>
			</c:choose>

			<c:forEach var="error" items="${errors}">
				<h3 style="color: red">${error}</h3>
			</c:forEach>

			<c:if test="${empty token}">
				<script>
					var w = window.open("${authUrl}");
					function checkFunction() {
						if (w.closed) {
							location.reload();
						}
					}
					var time = self.setInterval("checkFunction()", 1000)
				</script>
			</c:if>


			<div class="panel panel-red">
				<div class="panel-heading">
					<h3 align="center">Set Up a Match!</h3>
				</div>
				<div class="panel-body">
					<div class="row" align="center">
						<div class="col-lg-12" align="center">
							<form role="form" method="post">
								<datalist id="cities">
									<c:forEach items="${cityList}" var="listValue">
										<option value="${listValue}">
									</c:forEach>
								</datalist>
								<table align="center">
									<tr>
										<td width="300px" align="center">
											<div class="form-group">
												<h4>Choose City 1</h4>
												<input name="cities1" required class="form-control"
													list="cities" placeholder = "City, ST">
												<script type="text/javascript">
													//<![CDATA[
													{
															document.forms[1].elements['cities1'].value = city;
													}
													//]]>
												</script>
											</div>
										</td>
										<td width="200px" align="center">
											<h3>
												<font color="#F0AD4E">Versus</font>
											</h3>
										</td>
										<td width="300px" align="center">
											<div class="form-group">
												<h4>Choose City 2</h4>
												<input name="cities2" required class="form-control"
													list="cities" placeholder="City, ST">
										</td>
									</tr>
								</table>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12" align="center">
						<h4>
							<font color="#F0AD4E">Parameters to Compare</font>
						</h4>
						<div class="form-group">
							<table>
								<tr>
									<td width="200"><label> <input type="checkbox"
											name="sports" id="Parameters_1" value="sports" checked>
											Sports
									</label></td>
									<td><label> <input type="checkbox"
											name="restaurants" value="restaurants" id="Parameters_0"
											checked> Restaurants & Nightlife
									</label></td>
								</tr>
								<tr>
									<td><label> <input type="checkbox"
											name="employment" checked value="employment"
											id="Parameters_2"> Jobs
									</label></td>
									<td><label> <input type="checkbox"
											name="celebrity" checked value="celebrity" id="Parameters_3">
											Celebrity Buzz
									</label></td>
								</tr>
								<tr>
									<td><label> <input type="checkbox"
											name="education" checked value="education" id="Parameters_4">
											Education
									</label></td>
									<td><label> <input type="checkbox" name="crime"
											checked value="crime" id="Parameters_5"> Crime
									</label></td>
								</tr>
							</table>
						</div>
						<!-- /.col-lg-6 (nested) -->
						<hr>
						<div class="row">
							<div class="col-lg-12" align="center">
								<div class="form-group">
									<button type="submit" name="action" value="compare"
										class="btn btn-danger">
										<h1>GO!</h1>
									</button>
									</form>
								</div>
							</div>
						</div>

					</div>
					<!-- /.row (nested) -->
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->

			<div class="row">
				<div class="col-lg-12 col-md-12">
					<div class="alert alert-warning alert-dismissable">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">&times;</button>
						<table>
							<tr>
								<td>See what's going on in ${ currCity }! To change the city, type it here: &nbsp;</td>
								<td>
									<div class="input-group custom-search-form">
										<form method="post">
											<span class="input-group-btn"> <input type="text"
												id="cityChange" list="cities" class="form-control"
												placeholder="City, ST" name="local">
												<button class="btn btn-default" type="submit" name="action"
													value="change">
													<i class="fa fa-search"></i>
												</button>
											</span>

										</form>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-2 col-md-2">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-comments fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div class="huge">@ # !</div>
									<div>
										Latest trends in <br><strong>${ currCity }</strong>
										<script type="text/javascript">
											// 											if (typeof change === 'undefined') {
											// 												document.write(city);
											// 											} else {
											// 												document.write(change);
											// 											}
										</script>
									</div>
								</div>
							</div>
						</div>
						<div class="panel-footer">
							<table>
								<c:forEach var="hashtag" items="${currCityTrend}">
									<tr>
										<td>#${hashtag}</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>

				<div class="col-lg-5 col-md-5">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-users fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div class="huge">Community Forum</div>
									<div>
										Ask and anwswer questions about<br><strong>${ currCity }</strong>
										<script type="text/javascript">
											// 											if (typeof change === 'undefined') {
											// 												document.write(city);
											// 											} else {
											// 												document.write(change);
											// 											}
										</script>
									</div>
								</div>
							</div>
						</div>
						<!-- Begining of main pannel of Forum -->
						<div class="panel-footer">
							<table>
								<!-- Method API to display Questions -->
								
									
									<c:forEach var="obj1" items="${topics}">
									<tr>
										<td>${obj1}</td>
									</tr>
									
									<tr>
									    <c:forEach var="obj" items="${replies}">

									    <tr>
										<td>${obj}</td>
									</tr>
									     </c:forEach>
									</tr>
								</c:forEach>
										
							</table>
						</div>
						<!-- End of main pannel of Forums -->

					</div>
				</div>
				<!-- /.col-lg-6 -->

				<div class="col-lg-5 col-md-5">
					<div class="panel panel-green">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-image fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div class="huge">Take a look!</div>
									<div>
										Flicker pics from or about<br><strong>${ currCity }</strong>
										<script type="text/javascript">
											// 											if (typeof change === 'undefined') {
											// 												document.write(city);
											// 											} else {
											// 												document.write(change);
											// 											}
										</script>
									</div>
								</div>
							</div>
						</div>
						<div class="panel-footer">
							<iframe
								src="https://www.flickr.com/photos/52209513@N03/15944683727/in/photolist-qhYEY8-q5U7WE-pCoGKj-q6K8n9-p8KbWR-qjz5cJ-pLnoTa-pRsbh6-pQgy99-qCDCnP-pebDR2-qjz29Q-pnReTV-oxDo45-ow18e4-pU1UpD-pQTfiY-q34S8o-ouUnaU-qjshUv-pTwC2g-qojoEt-qN3bjC-pDMt4w-qY1yhZ-qNxHFn-qbY7Dz-q6CFSW-q291MA-puLCWj-q7y2SD-pXL7eo-pnChbK-qrGsLW-pFciwv-qfpyNs-qFHCVz-pYFmg6-peGY8Q-r1fKoS-r5gYar-oRR46A-ofpEMw-pC8ax9-pUdYLN-qDQgZ3-qpsdCj-pdS9h5-qkvdDn-pZpGHL/player/"
								name="frame2" id="frame2" frameborder="0" marginwidth="0"
								marginheight="0" scrolling="no" onload=""
								allowtransparency="false" width="100%" height="300px">

							</iframe>
						</div>
					</div>
				</div>


				<!-- /.row -->
			</div>
			<!-- /.following closes wrapper -->
		</div>
		<hr>
		<p align="center">
			Team Hex | All Rights Reserved | Carnegie Mellon University &copy;
			2015<br> Site developed by for educational purposes only
		</p>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->
	</div>
	<!-- jQuery -->
	<script src="resources/bower_components/jquery/dist/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script
		src="resources/bower_components/metisMenu/dist/metisMenu.min.js"></script>

	<!-- Morris Charts JavaScript -->
	<script src="resources/bower_components/raphael/raphael-min.js"></script>
	<script src="resources/bower_components/morrisjs/morris.min.js"></script>
	<script src="resources/js/morris-data.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="resources/dist/js/sb-admin-2.js"></script>
</body>
</html>