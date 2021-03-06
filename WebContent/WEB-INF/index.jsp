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

	<div id="fb-root"></div>
	<div id="fb-root"></div>
	<script>
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.0";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>

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
					are in <font color="black"> <c:choose>
							<c:when test="${ (empty currCity) }">
					     a cool unknown place
					 </c:when>
							<c:otherwise>
					      ${currCity}
                     </c:otherwise>
						</c:choose>
				</font> <script type="text/javascript">
					var city = geoip_city() + ", " + geoip_region();
					if (city.length <= 2) {
						change = "an unknown cool place";
						city = "Pittsburgh";
					}
				</script>
				</a>
			</div>

			<!-- /.hidden form that auto submits -->
			<div style="display: none;">
				<iframe id="hidden_frame" name="hidden_frame"></iframe>

				<form id="hidden_form" method="POST" action="index.do"
					target="hidden_frame">
					<input type="text" name="hidden_city" />
					<script type="text/javascript">
						//<![CDATA[
						{
							document.forms[0].elements['hidden_city'].value = city;
						}
						//]]>
					</script>

					<c:if test="${empty currCity}">
						<script type="text/javascript">
							setInterval(
									function() {
										document.getElementById("hidden_form")
												.submit();
									}, 300)
						</script>
					</c:if>


				</form>
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

			<c:if test="${empty currCityTrend}">
				<script>
					setInterval(function() {
						location.reload();
					}, 500)
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
									<option value="Anaheim, CA">
									<option value="Boston, MA">
									<option value="Chicago, IL">
									<option value="Detroit, MI">
									<option value="Houston, TX">
									<option value="Las Vegas, NV">
									<option value="Los Angeles, CA">
									<option value="Miami, FL">
									<option value="New York, NY">
									<option value="Orlando, FL">
									<option value="Portland, OR">
									<option value="Philadelphia, PA">
									<option value="Pittsburgh, PA">
									<option value="San Diego, CA">
									<option value="San Francisco, CA">
									<option value="San Jose, CA">
									<option value="Seattle, WA">
									<option value="Tucson, TX">
									<option value="Washington, DC">																	
								</datalist>
								<table align="center">
									<tr>
										<td width="300px" align="center">
											<div class="form-group">
												<h4>Choose City 1</h4>
												<input name="cities1" required class="form-control"
													list="cities" value="${ currCity }">
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
								<td>See what's going on in ${ currCity }! To change the
									city, type it here: &nbsp;</td>
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
										Latest trends in <br> <strong>${ currCity }</strong>
									</div>
								</div>
							</div>
						</div>
						<div class="panel-footer">
							<c:forEach var="hashtag" items="${currCityTrend}">
										#${hashtag}<br>
							</c:forEach>
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
										Ask and anwswer questions about <strong>${ currCity }</strong>.
										<br>This forum is displayed for ${ currCity } only.
									</div>
								</div>
							</div>
						</div>
						<!-- Begining of main pannel of Forum -->
						<div class="panel-footer">
							<div class="fb-comments"
								data-href="http://www.teamhex.tk/${currCity.hashCode()}/comments "
								data-width="100%" data-numposts="5" data-colorscheme="light"></div>
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
										Flicker pics from or about<br> <strong>${ currCity }</strong>
									</div>
								</div>
							</div>
						</div>
						<div class="panel-footer" align="center">
							<table>
								<tr height="400px" valign="top">
									<td align="center" valign="top">
										<div id="myCarousel" class="carousel slide">
											<ol class="carousel-indicators">
												<li data-target="#myCarousel" data-slide-to="0"
													class="active"></li>
												<li data-target="#myCarousel" data-slide-to="1"></li>
												<li data-target="#myCarousel" data-slide-to="2"></li>
												<li data-target="#myCarousel" data-slide-to="3"></li>
												<li data-target="#myCarousel" data-slide-to="4"></li>
											</ol>
											<div class="carousel-inner">
												<div class="item active">
													<img src="${currCityPhoto.get(0)}" alt="${city1} Panoramic">
												</div>
												<div class="item">
													<img src="${currCityPhoto.get(1)}" alt="${city1} Panoramic">
												</div>
												<div class="item">
													<img src="${currCityPhoto.get(2)}" alt="${city1} Panoramic">
												</div>
												<div class="item">
													<img src="${currCityPhoto.get(3)}" alt="${city1} Panoramic">
												</div>
												<div class="item">
													<img src="${currCityPhoto.get(4)}" alt="${city1} Panoramic">
												</div>
											</div>
											<a class="left carousel-control" href="#myCarousel"
												data-slide="prev">&lsaquo;</a> <a
												class="right carousel-control" href="#myCarousel"
												data-slide="next">&rsaquo;</a>
										</div>
									</td>
								</tr>
							</table>
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