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
  	<li><a href="/courseop/" data-toggle="tab">Programs</a></li>
  	<li class="active"><a href="#tab1" data-toggle="tab">Courses</a></li>
    <li><a href="/courseop/homefaculty" data-toggle="tab">Faculty</a></li>
  </ul>
  <div class="tab-content">
  <table class="table">
  <tbody>
   <tr>
      <td align="center"><a href="/courseop/courses">List of Courses</a></td>
    </tr>
    <tr>
      <td align="center"  class="active"><a href="/courseop/courseTimings" data-toggle="tab" class="active">Timings</a></td>
    </tr>
  </tbody>
</table>
  </div>
  </div>
<label class="radio-inline"><input type="radio" name="optradio" checked>Spring</label>
<label class="radio-inline"><input type="radio" name="optradio">Summer</label>
<label class="radio-inline"><input type="radio" name="optradio">Fall</label>
<br>
<br>
<table class="table table-bordered">
  <thead class="thead-light">
    <tr>
      <th scope="col">Part of the Day</th>
      <th scope="col">Timings</th>
      <th scope="col">Day</th>
      <th scope="col">CourseNumber</th>
    </tr>
  </thead>
  <tbody>
<%--   <c:forEach items="${courses}" var="course">
    <tr>
      <td>${course.courseNumber}</td>
      <td>${course.courseName}</td>
    </tr>
   </c:forEach> --%>
    <tr>
      <td>Morning</td>
      <td><ul>
 			<li>8:00-9:15 am</li>
  		 	<li>9:25-10:40 am</li>
  		 	<li>10:50-12:05 am</li>
		</ul>
	 </td>
      <td>Mon,Tue,Wed</td>
      <td>
      	<ul>
 			<li><a href="#">501</a></li>
  		 	<li><a href="#">502</a></li>
		</ul>
	  </td>
    </tr>
    <tr>
      <td>Noon</td>
      <td><ul>
 			<li>12:15-01:30 pm</li>
  		 	<li>1:40-02:55 pm</li>
  		 	<li>3:05-04:20 pm</li>
		</ul>
	 </td>
      <td>Mon,Tue,Wed</td>
      <td>
      	<ul>
 			<li><a href="#">501</a></li>
  		 	<li><a href="#">502</a></li>
		</ul>
	  </td>
    </tr>
    <tr>
      <td>Evening</td>
      <td><ul>
 			<li>4:30-05:45 pm</li>
  		 	<li>5:55-07:10 pm</li>
  		 	<li>7:20-08:35 pm</li>
  		 	<li>8:45-10:00 pm</li>
		</ul>
	 </td>
      <td>Wed,Thu</td>
      <td>
      	<ul>
 			<li><a href="#">501</a></li>
  		 	<li><a href="#">502</a></li>
		</ul>
	  </td>
    </tr>
  </tbody>
</table>
</div>

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
