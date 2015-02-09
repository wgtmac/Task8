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
        <h1 class="page-header">We Have a Winner!</h1>
      </div>

      <!-- /.col-lg-12 --> 
    </div>
    <div class="panel panel-yellow">
      <div class="panel-heading">
        <h3 align="center">Pittsburgh is Cooler Than Detroit</h3>
      </div>
      <div class="panel-body">
        <div class="row" align="center">
          <div class="col-lg-12" align="center">
            <table align="center"><tr><td>
            <h3>Pitsburgh <font color="green">63%</font></h3>
            </td>
            <td width="100px" align="center">
            <h3><font color="#F0AD4E">Versus</font></h3>
            </td>
            <td>
            <h3><font color="red">37%</font> Detroit</h3>
            </td>
            </tr></table>
            </div>
            </div>
           <div class="row">
          <div class="col-lg-12" align="center">
          <h4>Based on the following findings:</h4>
            <p class="affix">
            <table>
			<tr>
            <td>Crime is a higher concern for people in <font color="red">Detroit</font>.</td>   
			</tr>
            <tr>
            <td>Residents in <font color="green">Pittsburgh</font> seem satisfied with their education.</td>  
			</tr>
            <tr>
            <td>Sports has a greater space in <font color="green">Pittsburgh</font>.</td>
			</tr>
            <tr>
            <td>Celebrities and VIP talk less about <font color="red">Detroit</font>.</td>
			</tr>
             <tr>
            <td>People seem to be happier about their jobs in <font color="green">Pittsburgh</font>.</td>    
			</tr>
          	 <tr>
            <td>Everyone is talking about eating out and partying in <font color="green">Pittsburgh</font>!</td>    
			</tr>
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
            <div class="row">
            <strong>Graphics</strong>
            </div>
            
            </div>
            graphics
            </div>
        
        
        
        
     <!-- Social dissmisable box begins -->   
        <div class="alert alert-info alert-dismissable">
         <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>               
        <div class="row">                       
		<h4>&nbsp; &nbsp; Social Buzz</h4> 
        
        <div class="col-lg-3 col-md-3">
        <div class="panel panel-green">
          <div class="panel-heading">
            <div class="row">
              <div class="col-xs-3"> <i class="fa fa-comments fa-5x"></i> </div>
              <div class="col-xs-9 text-right">
                <h4>Pittsburgh Tweet's</h4>
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
            </table>
          </div>
        </div>
      </div>

      <div class="col-lg-6 col-md-6">
        <div class="panel panel-primary">
          <div class="panel-heading">
            <div class="row">
              <div class="col-xs-3"> <i class="fa fa-users fa-5x"></i> </div>
              <div class="col-xs-9 text-right">
                <h4>What do you think about the results?</h4>
                <div>Comments are displayed for each set of results</div>
              </div>
            </div>
          </div>
          <div class="panel-footer">
            FACEBOOK APP
          </div>
        </div>
      </div>
      
              <div class="col-lg-3 col-md-3">
        <div class="panel panel-danger">
          <div class="panel-heading">
            <div class="row">
              <div class="col-xs-3"> <i class="fa fa-comments fa-5x"></i> </div>
              <div class="col-xs-9 text-right">
                <h4>Detroit Tweet's</h4>
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
            </table>
          </div>
        </div>
      </div>
      <!-- /.col-lg-6 --> 
      <!-- /.row --> 
      </div>
      <!-- /dismiss button-->
      </div>
      
      <!-- Flicker dissmisable box begins -->   
        <div class="alert alert-primary alert-dismissable">
         <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>               
        <div class="row">                       
		<h4>&nbsp; &nbsp; See it for yourself</h4> 
        
        <div class="col-lg-6 col-md-6">
        <div class="panel panel-green">
          <div class="panel-heading">
            <div class="row">
              <div class="col-xs-3"> <i class="fa fa-image fa-5x"></i> </div>
              <div class="col-xs-9 text-right">
                <h4>Pittsburgh's Pics</h4>
                <div>Flicker's trends related to Pittsburgh</div>
              </div>
            </div>
          </div>
          <div class="panel-footer">
FLICKER PICS
          </div>
        </div>
      </div>

      <div class="col-lg-6 col-md-6">
        <div class="panel panel-danger">
          <div class="panel-heading">
            <div class="row">
              <div class="col-xs-3"> <i class="fa fa-image fa-5x"></i> </div>
              <div class="col-xs-9 text-right">
                <h4>Detroit's Pics</h4>
                <div>Flicker's trends related to Detroit</div>
              </div>
            </div>
          </div>
          <div class="panel-footer">
            FLICKER PICS
          </div>
        </div>
      </div>
      
      <!-- /.col-lg-6 --> 
      <!-- /.row --> 
      </div>
      <!-- /dismiss button-->
       
      
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
