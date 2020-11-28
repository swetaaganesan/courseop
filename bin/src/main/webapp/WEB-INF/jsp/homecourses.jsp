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
      <td align="center"><a href="/courseop/courses" data-toggle="tab">List of Courses</a></td>
    </tr>
    <tr>
      <td align="center"><a href="/courseop/courseTimings" data-toggle="tab">Timings</a></td>
    </tr>
  </tbody>
</table>
  </div>
</div>

	</div>

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
