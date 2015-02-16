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
<link href="resources/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="resources/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

<!-- Timeline CSS -->
<link href="resources/dist/css/timeline.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="resources/dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="resources/bower_components/morrisjs/morris.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="resources/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
<div id="wrapper"> 
  
  <!-- Navigation -->
  <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      <a class="navbar-brand" href="index.html">Hi, we believe you are in <strong>{City}</strong></a> </div>
    <!-- /.navbar-header --> 
    
    <!-- /.navbar-top-links -->
    
    <div class="navbar-default sidebar" role="navigation">
      <div class="sidebar-nav navbar-collapse">
        <ul class="nav" id="side-menu">
          <li> <a href="index.do"><i class="fa fa-dashboard fa-fw"></i> Cool-O-Meter Home</a> </li>
          <li> <a href="#"><i class="fa fa-files-o fa-fw"></i> What is Cool-O-Meter?</a> </li>
        </ul>
        <br>
      <img src="resources/img/banner.png" alt="social media banner"/> </div>
      <!-- /.sidebar-collapse --> 
    </div>
    <!-- /.navbar-static-side --> 
  </nav>
  <div id="page-wrapper">
    <div class="row">
      <div class="col-lg-12">
        <h1 class="page-header">Cool-O-Meter</h1>
        <div class="alert alert-success alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                Use our meter to find out how "cool" is your city compared to another one. To learn about the rationale used <a href="about.do" class="alert-link">click here</a>.
                            </div>
      </div>

      <!-- /.col-lg-12 --> 
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

			<div class="panel panel-red">
      <div class="panel-heading">
        <h3 align="center">Set Up a Match!</h3>
      </div>
      <div class="panel-body">
        <div class="row" align="center">
          <div class="col-lg-12" align="center">
          <form role="form" method="post">
            <datalist id="cities">
              <option value="Pittsburgh, PA">
              <option value="Pittsburgh, CA">
              <option value="San Francisco, CA">
              <option value="San Diego, CA",>
              <option value="Ankorage, AL">
            </datalist>
            <table align="center"><tr><td width="300px" align="center">
           <div class="form-group">
              <h4>Choose City 1</h4>
              <input name="cities1" required class="form-control" list="cities" value="">
            </div>
            </td>
            <td width="200px" align="center">
            <h3><font color="#F0AD4E">Versus</font></h3>
            </td>
            <td width="300px" align="center">
            <div class="form-group">
              <h4>Choose City 2</h4>
              <input name="cities2" required class="form-control" list="cities" >
            </td>
            </tr></table>
            </div>
            </div>
            </div>
           <div class="row">
          <div class="col-lg-12" align="center">
          <h4><font color="#F0AD4E">Parameters to Compare</font></h4>
            <div class="form-group">
			  <table>
			    <tr>
			      <td width="200"><label>
                  <input type="checkbox" name="sports" id="Parameters_1" value="sports" checked>
			        Sports
			        </label></td>
			      <td><label>
			        <input type="checkbox" name="restaurants" value="restaurants" id="Parameters_0" checked>
			        Restaurants & Nightlife</label></td>
			      </tr>
			    <tr>
			      <td><label>
			        <input type="checkbox" name="employment" checked value="employment" id="Parameters_2">
			        Jobs</label></td>
			      <td><label>
			        <input type="checkbox" name="celebrity" checked value="celebrity" id="Parameters_3">
			        Celebrity Buzz</label></td>
			      </tr>
			    <tr>
			      <td><label>
			        <input type="checkbox" name="education" checked value="education" id="Parameters_4">
			        Education</label></td>
			      <td><label>
			        <input type="checkbox" name="crime" checked value="crime" id="Parameters_5">
			        Crime</label></td>
			      </tr>
			    </table>
            </div>
            <!-- /.col-lg-6 (nested) -->
            <hr>
            <div class="row">
         	 <div class="col-lg-12" align="center">
            <div class="form-group">
            <button type="submit"  name="action" value="compare" class="btn btn-danger">
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
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <table><tr><td>
            What's up in {city}? If this is not the city you are looking for, you can change it here: &nbsp;</td>
                            <td>
                            <div class="input-group custom-search-form">
                            <form method="post">
                                <input type="text" name="local" list="cities" class="form-control" >
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="submit" name="action" value="change">
                                    <i class="fa fa-search"></i>
                                </button></span>
                                </form></div></td></tr></table></div></div></div>
                               
        <div class="row">                       
        <div class="col-lg-6 col-md-6">
        <div class="panel panel-primary">
          <div class="panel-heading">
            <div class="row">
              <div class="col-xs-3"> <i class="fa fa-comments fa-5x"></i> </div>
              <div class="col-xs-9 text-right">
                <div class="huge">What's buzzing?</div>
                <div>The most recent tweets about {City}</div>
              </div>
            </div>
          </div>
          <div class="panel-footer">
            <table>
              <tr>
                <td>Tweeter Tweets Go Here</td>
              </tr>
              <tr>
                <td>Tweeter Tweets Go Here</td>
              </tr>
              <tr>
                <td>Tweeter Tweets Go Here</td>
              </tr>
              <tr>
                <td>Tweeter Tweets Go Here</td>
              </tr>
              <tr>
                <td>Tweeter Tweets Go Here</td>
              </tr>
            </table>
          </div>
        </div>
      </div>
      <div class="col-lg-6 col-md-6">
        <div class="panel panel-green">
          <div class="panel-heading">
            <div class="row">
              <div class="col-xs-3"> <i class="fa fa-image fa-5x"></i> </div>
              <div class="col-xs-9 text-right">
                <div class="huge">Take a look!</div>
                <div>Latest pictures on Flicker related to {City}</div>
              </div>
            </div>
          </div>
          <div class="panel-footer">
            <table>
              <tr>
                <td><a href="https://www.flickr.com/photos/mpk6070/15807997594" title="Pittsburgh Panoramic by Mike Keller, on Flickr" target="new"><img src="https://farm8.staticflickr.com/7346/15807997594_3cabd7e1d7.jpg" alt="Pittsburgh Panoramic"></a><br>
                  <br></td>
              </tr>
              <tr>
                <td><a href="https://www.flickr.com/photos/artisticpursuits/15508029056" title="Q2760 by Robert Strovers, on Flickr"><img src="https://farm4.staticflickr.com/3942/15508029056_16c06ff666.jpg" alt="Q2760"></a><br>
                  <br></td>
              </tr>
              <tr>
                <td><a href="https://www.flickr.com/photos/baskervilleh/2899340423" title="Franco, Franco ... by baskervilleh, on Flickr"><img src="https://farm4.staticflickr.com/3010/2899340423_cb65516fce.jpg" alt="Franco, Franco ..."></a></td>
              </tr>
            </table>
          </div>
        </div>
      </div>
      <!-- /.col-lg-6 --> 
      <!-- /.row --> 
      </div>
    </div>
    <hr>
    <p align="center">Team Hex | All Rights Reserved | Carnegie Mellon University © 2015<br>
      Site developed by for educational purposes only</p>
    <!-- /#page-wrapper --> 
  </div>
  <!-- /#wrapper --> 
</div>
<!-- jQuery --> 
<script src="resources/bower_components/jquery/dist/jquery.min.js"></script> 

<!-- Bootstrap Core JavaScript --> 
<script src="resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script> 

<!-- Metis Menu Plugin JavaScript --> 
<script src="resources/bower_components/metisMenu/dist/metisMenu.min.js"></script> 

<!-- Morris Charts JavaScript --> 
<script src="resources/bower_components/raphael/raphael-min.js"></script> 
<script src="resources/bower_components/morrisjs/morris.min.js"></script> 
<script src="resources/js/morris-data.js"></script> 

<!-- Custom Theme JavaScript --> 
<script src="resources/dist/js/sb-admin-2.js"></script>
</body>
</html>
