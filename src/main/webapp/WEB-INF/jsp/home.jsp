<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />

</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">CourseOp</a>
			</div>
		</div>
	</nav>

<div class="container">
	<div class="tabbable tabs-left">
	  <ul class="nav nav-tabs">
	   <li class="active"><a href="#tab2" data-toggle="tab">Programs</a></li>
	  	<li><a href="/courseop/homecourses" data-toggle="tab">Courses</a></li>
	     <li><a href="/courseop/homefaculty" data-toggle="tab">Faculty</a></li>
	  </ul>
	</div>
	<div class="container-fluid" style="border:2px solid #cecece;">
   	 	<h4 class="card-title">
       		 <span class="float-right">CS</span>
		</h4>
		<p class="text-secondary">Sample description for CS</p>
		<ul>
			<li><a href="/courseop/programCourses" data-toggle="tab">Graduate courses</a></li> 
			<li><a href="/courseop/programCourses" data-toggle="tab">Undergraduate courses</a></li>
		</ul>
	</div>   
    <div class="container-fluid" style="border:2px solid #cecece;">
	   <h4 class="card-title">
       		 <span class="float-right">CIT</span>
		</h4>    
		<p class="text-secondary">Sample description for CIT</p>
		<ul>
			<li><a href="/courseop/programCourses" data-toggle="tab">Graduate courses</a></li> 
			<li><a href="/courseop/programCourses" data-toggle="tab">Undergraduate courses</a></li>
		</ul>                                            
	</div> 
</div>
	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
