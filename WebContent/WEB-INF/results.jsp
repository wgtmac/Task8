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

<!-- MetisMenu CSS -->
<link href="resources/bower_components/metisMenu/dist/metisMenu.min.css"
	rel="stylesheet">

<!-- Timeline CSS -->
<link href="resources/dist/css/timeline.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="resources/dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="resources/bower_components/morrisjs/morris.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="resources/bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

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

	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script type="text/javascript">

      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'City');
        data.addColumn('number', 'Count');
        data.addRows([
          ['${ city1sports }',${ city1sportsscore }],
          ['${ city2sports }',${ city2sportsscore }],
        ]);

        // Set chart options
        var options = {'title':'Sport Related Tweets Percentage',
        				is3D : true};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('chart_div_sport'));
        chart.draw(data, options);
      }
    </script>

	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script type="text/javascript">

      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {
    	  
    	  
    	  var data = google.visualization.arrayToDataTable([
    	                                                    ["city", "Number of tweets", { role: "style" } ],
    	                                                    ['${ city1res }', ${ city1resscore }, "#3366CC"],
    	                                                    ['${ city2res }', ${ city2resscore }, "#e0440e"]
    	                                                  ]);

    	                                                  var view = new google.visualization.DataView(data);
    	                                                  view.setColumns([0, 1,
    	                                                                   { calc: "stringify",
    	                                                                     sourceColumn: 1,
    	                                                                     type: "string",
    	                                                                     role: "annotation" },
    	                                                                   2]);

        // Set chart options
        var options = {'title':'',
        				is3D : true};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.BarChart(document.getElementById('chart_div_res'));
        chart.draw(data, options);
      }
    </script>

	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script type="text/javascript">

      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        
        var data = google.visualization.arrayToDataTable([
    	                                                    ["city", "Number of tweets", { role: "style" } ],
    	                                                    ['${ city1cel }', ${ city1celscore }, "#3366CC"],
    	                                                    ['${ city2cel }', ${ city2celscore }, "#e0440e"]
    	                                                  ]);

    	                                                  var view = new google.visualization.DataView(data);
    	                                                  view.setColumns([0, 1,
    	                                                                   { calc: "stringify",
    	                                                                     sourceColumn: 1,
    	                                                                     type: "string",
    	                                                                     role: "annotation" },
    	                                                                   2]);
        
        /* var data = new google.visualization.DataTable();
        data.addColumn('string', 'City');
        data.addColumn('number', 'Count');
        data.addRows([
          ['${ city1job }',${ city1jobscore }],
          ['${ city2job }',${ city2jobscore }],
        ]); */

        // Set chart options
        var options = {'title':'',
        		       is3D : true/* ,
                       'width':400,
                       'height':300 */};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.BarChart(document.getElementById('chart_div_cel'));
        chart.draw(data, options);
      }
    </script>

	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script type="text/javascript">

      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'City');
        data.addColumn('number', 'Count');
        data.addRows([
          ['${ city1job }',${ city1jobscore }],
          ['${ city2job }',${ city2jobscore }],
        ]);

        // Set chart options
        var options = {'title':'Job Related Tweets Percentage',
        		       is3D : true/* ,
                       'width':400,
                       'height':300 */};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('chart_div_job'));
        chart.draw(data, options);
      }
    </script>

	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script type="text/javascript">

      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'City');
        data.addColumn('number', 'Count');
        data.addRows([
          ['${ city1edu }',${ city1eduscore }],
          ['${ city2edu }',${ city2eduscore }],
        ]);

        // Set chart options
        var options = {'title':'Education Related Tweets Percentage',
        		       is3D : true/* ,
                       'width':400,
                       'height':300 */};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('chart_div_edu'));
        chart.draw(data, options);
      }
    </script>

	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script type="text/javascript">

      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        
        var data = google.visualization.arrayToDataTable([
    	                                                    ["city", "Number of tweets", { role: "style" } ],
    	                                                    ['${ city1cri }', ${ city1criscore }, "#3366CC"],
    	                                                    ['${ city2cri }', ${ city2criscore }, "#e0440e"]
    	                                                  ]);

    	                                                  var view = new google.visualization.DataView(data);
    	                                                  view.setColumns([0, 1,
    	                                                                   { calc: "stringify",
    	                                                                     sourceColumn: 1,
    	                                                                     type: "string",
    	                                                                     role: "annotation" },
    	                                                                   2]);
        
        /* var data = new google.visualization.DataTable();
        data.addColumn('string', 'City');
        data.addColumn('number', 'Count');
        data.addRows([
          ['${ city1cri }',${ city1criscore }],
          ['${ city2cri }',${ city2criscore }],
        ]); */

        // Set chart options
        var options = {'title':'',
        		       is3D : true
                       /* 'width':400,
                       'height':300 */};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.BarChart(document.getElementById('chart_div_cri'));
        chart.draw(data, options);
      }
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
				<a class="navbar-brand" href="index.do">There are news for <strong>${ currCity }</strong>!
				</a>
			</div>
			<!-- /.navbar-header -->

			<!-- /.navbar-top-links -->

			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<li><a href="index.do"><i class="fa fa-dashboard fa-fw"></i>
								Start other Match!</a></li>
						<li><a href="about.do"><i class="fa fa-files-o fa-fw"></i>
								How does this work?</a></li>
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
					<h1 class="page-header">We Have a Winner!</h1>
				</div>

				<!-- /.col-lg-12 -->
			</div>
			<div class="panel panel-yellow">
				<div class="panel-heading">
					<h3 align="center">
						<strong>${ city1 }</strong> is Cooler Than <strong>${ city2 }</strong>
					</h3>
				</div>
				<div class="panel-body">
					<div class="row" align="center">
						<div class="col-lg-12" align="center">
							<table align="center">
								<tr>
									<td>
										<h3>${ city1 }
											<font color="green">${ cityScore1 }%</font>
										</h3>
									</td>
									<td width="100px" align="center">
										<h3>
											<font color="#F0AD4E">Versus</font>
										</h3>
									</td>
									<td>
										<h3>
											<font color="red">${ cityScore2 }%</font> ${ city2 }
										</h3>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12" align="center">
							<h4>
								<font color="grey">The People have Spoken:</font>
							</h4>
							<p class="affix">
							<table>

								<c:choose>
									<c:when test="${ (empty crime) }">
									</c:when>
									<c:otherwise>
										<tr>
											<td><h4>
													Crime is a higher concern for people in <font color="red">${ crime }.</font>
													<h4></td>
										</tr>
									</c:otherwise>
								</c:choose>

								<c:choose>
									<c:when test="${ (empty education) }">
									</c:when>
									<c:otherwise>
										<tr>
											<td><h4>
													Residents in <font color="green">${ education }</font> seem
													satisfied with their <font color=""#F0AD4E">education</font>.
												</h4></td>
										</tr>
									</c:otherwise>
								</c:choose>

								<c:choose>
									<c:when test="${ (empty sports) }">
									</c:when>
									<c:otherwise>
										<tr>
											<td><h4>
													<font color=""#F0AD4E">Sports</font> has a greater space in
													<font color="green">${ sports }</font>.
												</h4></td>
										</tr>
									</c:otherwise>
								</c:choose>

								<c:choose>
									<c:when test="${ (empty celebrity) }">
									</c:when>
									<c:otherwise>
										<tr>
											<td><h4>
													<font color=""#F0AD4E">Celebrities</font> and VIPs rarely
													mention <font color="red">${ celebrity }</font> on their
													posts.
												</h4></td>
										</tr>
									</c:otherwise>
								</c:choose>

								<c:choose>
									<c:when test="${ (empty employment) }">
									</c:when>
									<c:otherwise>
										<tr>
											<td><h4>
													People seem to be happier about their <font color=""#F0AD4E">jobs</font>
													in <font color="green">${ employment }</font>.
												</h4></td>
										</tr>
									</c:otherwise>
								</c:choose>

								<c:choose>
									<c:when test="${ (empty restaurants) }">
									</c:when>
									<c:otherwise>
										<tr>
											<td><h4>
													Many more are talking about <font color=""#F0AD4E">eating
														out and partying</font> in <font color="green">${ restaurants }</font>!
												</h4></td>
										</tr>
									</c:otherwise>
								</c:choose>
							</table>
							</p>
							<!-- /.col-lg-6 (nested) -->
							<hr>
						</div>
						<!-- /.row (nested) -->
					</div>
					<!-- /.panel-body -->
				</div>
				<!-- /.panel -->
			</div>

			<!-- Google Graphics begin here -->
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4 align="center">Let's Be More Precise</h4>
				</div>

				<div class="panel-body">
					<div class="row" align="center">

						<div class="col-lg-4 col-md-4">
							<div class="panel panel-warning">
								<div class="panel-heading">
									<h4>Sports Comparison</h4>
								</div>
								
								<c:choose>
									<c:when test="${ (empty sports) }">
									</c:when>
									<c:otherwise>
										<div class="panel-footer">
									<div id="chart_div_sport"></div>
								</div>
									</c:otherwise>
								</c:choose>
								
								
							</div>
						</div>
						<div class="col-lg-4 col-md-4">
							<div class="panel panel-danger">
								<div class="panel-heading">
									<h4>Restaurants and Nightlife Comparison</h4>
								</div>
								
								<c:choose>
									<c:when test="${ (empty restaurants) }">
									</c:when>
									<c:otherwise>
	 								<div class="panel-footer">
									<div id="chart_div_res"></div>
								</div> 
									</c:otherwise>
								</c:choose>

							</div>
						</div>
						<div class="col-lg-4 col-md-4">
							<div class="panel panel-yellow">
								<div class="panel-heading">
									<h4>Jobs Comparison</h4>
								</div>
								
								<c:choose>
									<c:when test="${ (empty employment) }">
									</c:when>
									<c:otherwise>
										<div class="panel-footer">
									<div id="chart_div_job"></div>
								</div>
									</c:otherwise>
								</c:choose>
								
								
							</div>
						</div>
					</div>

					<div class="row" align="center">

						<div class="col-lg-4 col-md-4">
							<div class="panel panel-info">
								<div class="panel-heading">
									<h4>Celebrities Buzz Comparison</h4>
								</div>
								
								<c:choose>
									<c:when test="${ (empty celebrity) }">
									</c:when>
									<c:otherwise>
										<div class="panel-footer">
									<div id="chart_div_cel"></div>
								</div>
									</c:otherwise>
								</c:choose>
								
								
							</div>
						</div>
						<div class="col-lg-4 col-md-4">
							<div class="panel panel-green">
								<div class="panel-heading">
									<h4>Education Comparison</h4>
								</div>
								
								<c:choose>
									<c:when test="${ (empty education) }">
									</c:when>
									<c:otherwise>
										<div class="panel-footer">
									<div id="chart_div_edu"></div>
								</div>
									</c:otherwise>
								</c:choose>
								
								
							</div>
						</div>
						
						
						
						<div class="col-lg-4 col-md-4">
							<div class="panel panel-red">
								<div class="panel-heading">
									<h4>Crime Comparison</h4>
								</div>
								
								<c:choose>
									<c:when test="${ (empty crime) }">
									</c:when>
									<c:otherwise>
										<div class="panel-footer">
									<div id="chart_div_cri"></div>
								</div>
									</c:otherwise>
								</c:choose>
								
								
							</div>
						</div>
					</div>


					<!-- Social dissmisable box begins -->
					<div class="alert alert-info alert-dismissable">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">&times;</button>
						<div class="panel-heading">
							<h4>Social Buzz</h4>
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
												<h4>${ city1 }'s &nbsp; Trends</h4>
											</div>
										</div>
									</div>
									<div class="panel-footer">
										<c:forEach var="hashtag" items="${trend1}">
													#${hashtag}<br>
										</c:forEach>
									</div>
								</div>
							</div>

							<div class="col-lg-2 col-md-2">
								<div class="panel panel-danger">
									<div class="panel-heading">
										<div class="row">
											<div class="col-xs-3">
												<i class="fa fa-comments fa-5x"></i>
											</div>
											<div class="col-xs-9 text-right">
												<h4>${ city2 }'s &nbsp; Trends</h4>
											</div>
										</div>
									</div>
									<div class="panel-footer">
										<c:forEach var="hashtag" items="${trend2}">
													#${hashtag}<br>
										</c:forEach>
									</div>
								</div>
							</div>


							<div class="col-lg-4 col-md-4">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<div class="row">
											<div class="col-xs-3">
												<i class="fa fa-comments fa-5x"></i>
											</div>
											<div class="col-xs-9 text-right">
												<h4>${ city1 }'s &nbsp; Pictures on Flicker</h4>
											</div>
										</div>
									</div>
									<div class="panel-footer">
										Flicker Carousel
									</div>
								</div>
							</div>


							<div class="col-lg-4 col-md-4">
								<div class="panel panel-danger">
									<div class="panel-heading">
										<div class="row">
											<div class="col-xs-3">
												<i class="fa fa-comments fa-5x"></i>
											</div>
											<div class="col-xs-9 text-right">
												<h4>${ city2 }'s &nbsp; Pictures on Flicker</h4>
											</div>
										</div>
									</div>
									<div class="panel-footer">
										Flicker 2
									</div>
								</div>
							</div>


							<!-- /.col-lg-6 -->
							<!-- /.row -->
						</div>
						<!-- /next is dismiss button-->
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
