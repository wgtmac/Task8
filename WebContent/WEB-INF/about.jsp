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
<title>Team Hex Web Service - About Cool-O-Meter</title>

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
				<a class="navbar-brand" href="#">Learn about Cool-O-Meter</a>
			</div>
			<!-- /.navbar-header -->

			<!-- /.navbar-top-links -->

			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<li><a href="index.do"><i class="fa fa-dashboard fa-fw"></i>
								Back to Homepage</a></li>
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
					<h1 class="page-header">How does Cool-O-Meter work?</h1>
				</div>

				<div class="col-lg-9">
					<div class="alert alert-success alert-dismissable">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">&times;</button>
						Our tool is based on Web Service APIs from Twitter, Flicker and
						Facebook. All you need to know is that these companies have built
						methods of communication (APIs) so that they can make their own
						capabilities available to a wider audience through the Internet.
						Our tool requests data to these companies using those APIs and
						performs an analysis of the data based on the following criteria:
						&#8226; Our tool is based on Web Service APIs from Twitter,
						Flicker and Facebook. <br> &#8226; All you need to know is
						that these companies have built methods of communication (APIs) so
						that other web services (us) can make use of their capabilities.<br>
						&#8226; Our tool requests data to them and performs an analysis of
						the social trends received from twitter.<br> &#8226; Please
						note that the analytics presented are exclusively based on the
						latest "buzz" only. <br>
					</div>
				</div>
				</div>
			<!-- /.row -->

			<div class="row">
				<div class="col-lg-9">
					<div class="panel panel-warning">
						<div class="panel-heading">
							<h4>Criteria for the Parameters Compared</h4>
						</div>
						<!-- .panel-heading -->
						<div class="panel-body">
							<div class="panel-group" id="accordion">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a data-toggle="collapse" data-parent="#accordion"
												href="#collapseOne">Sports</a>
										</h4>
									</div>
									<div id="collapseOne" class="panel-collapse collapse in">
										<div class="panel-body">Sports are compared by looking at the number of tweets from ESPN OR FOXSports related to hashtag #Sports in your city.</div>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a data-toggle="collapse" data-parent="#accordion"
												href="#collapseTwo">Jobs</a>
										</h4>
									</div>
									<div id="collapseTwo" class="panel-collapse collapse">
										<div class="panel-body">Jobs are compared by looking at the number of positive tweets related to hashtag #jobs OR #employment in your city.</div>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a data-toggle="collapse" data-parent="#accordion"
												href="#collapseThree">Education</a>
										</h4>
									</div>
									<div id="collapseThree" class="panel-collapse collapse">
										<div class="panel-body">Education is compared by looking at the number of positive tweets related to hashtag #graduate OR #university in your city.</div>
									</div>
								</div>
																<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a data-toggle="collapse" data-parent="#accordion"
												href="#collapseFour">Restaurants & Nightlife</a>
										</h4>
									</div>
									<div id="collapseFour" class="panel-collapse collapse">
										<div class="panel-body">Restaurants & Nightlife is compared by looking at the number of positive tweets related to the hashtag #restaurant OR #pubs OR #nightclubs OR #food in your city.</div>
									</div>
								</div>
																<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a data-toggle="collapse" data-parent="#accordion"
												href="#collapseFive">Celebrities and VIP Buzz</a>
										</h4>
									</div>
									<div id="collapseFive" class="panel-collapse collapse">
										<div class="panel-body">Celebrities and VIP Buzz is compared by looking at the number of  tweets from mtv OR ftvdotcom OR tmz OR eNews OR bravomagazin OR thr OR deadline about #celebrities in your city.</div>
									</div>
								</div>
																<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a data-toggle="collapse" data-parent="#accordion"
												href="#collapseSix">Crime</a>
										</h4>
									</div>
									<div id="collapseSix" class="panel-collapse collapse">
										<div class="panel-body">Crime is compared by looking at the number of tweets related to the hashtag #crime in your city.</div>
									</div>
								</div>
							</div>
						</div>
						<!-- .panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.following closes page wrapper -->
	</div>
	<hr>
	<p align="center">
		Team Hex | All Rights Reserved | Carnegie Mellon University &copy;
		2015<br> Site developed by for educational purposes only
	</p>

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