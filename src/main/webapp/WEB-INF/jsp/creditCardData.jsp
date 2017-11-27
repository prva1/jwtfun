<html lang='en' ng-app="FwtFunApp"

      xmlns="http://www.w3.org/1999/xhtml"
	  xmlns:th="http://www.thymeleaf.org"
	  xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">

<head>

	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-social/4.10.1/bootstrap-social.css" rel="stylesheet">
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script> 
	

<footer class="navbar navbar-inverse navbar-fixed-bottom">
  <div class="container btn-lg text-center">
    <a href="#"><i class="fa fa-facebook"></i></a>
    <a href="#"><i class="fa fa-twitter"></i></a>
    <a href="#"><i class="fa fa-linkedin"></i></a>
    <a href="#"><i class="fa fa-google-plus"></i></a>
    <a href="#"><i class="fa fa-skype"></i></a>
  </div>

</footer>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
</head>




<body class="grey">

<nav class='navbar navbar-default' role='navigation'>

 <div class="container-fluid">
		<div class='navbar-header'>
			 
			<button  type='button'  class='navbar-toggle' data-toggle='collapse' data-target='#navbar-toggle'> 

				<span class='icon-bar'></span>
				<span class='icon-bar'></span>
				<span class='icon-bar'></span>
				<span class='icon-bar'></span>

			</button>

			<a class='navbar-brand' ng-href="#signout"  ng-click="clickHandler()">JWT FUN </a>

		</div>

		<div class='collapse navbar-collapse' id='nav-toggle'>
			
				<ul class='nav navbar-nav'>
					<li><a href="index.html"> <span class="glyphicon glyphicon-home"></span> Home </a></li>
					<li><a href=''>Product</a></li> 
					<li><a href=''>Promotions</a></li>
					<li><a href=''>Customer Service</a></li>
					<li><a href=''>About Us</a></li>
					<li><a href=''>Contact Us</a></li>		
					
          <div ng-hide='!isHidden'>

                <li role="presentation" class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true">
                  Credit Card <span class="caret"></span></a>

              <ul class="dropdown-menu">
                       <li><a href='/index.html'>Add Credit Card</a></li> 
                     <li><a href='/index.html'>Search Credit Card</a></li>
                   </ul>

                </li> 
          </div>

				</ul>
					
				   

				</ul>


				<ul class='nav navbar-right'>

					<li><a class='text-center' href='login.jsp'>Login</a></li>

					Login as ${userName} | <a href="<%=request.getContextPath()%>/logOut">Logout</a>

	      			<li><a href="#" id="cart"><i class="fa fa-shopping-cart"></i> Cart <span class="badge">0</span></a></li>

				</ul>

				<div ng-controller='ContactController'>

				<form class='navbar-form navbar-right' role='search'>
				<input type='text' class='form-control' placeholder='Search Product' ng-model='search'>
				<button ng-click='clickHandlerHiden()' type='submit' class='btn btn-default'>  <i class="glyphicon glyphicon-search"></i></button>
                        
					
				</form>

				</div>

		</div>
</div>
</nav>


<div class="container">
<div class="page-header">
	<h2><p align="center"> JWT FUN - CREDIT CARD DATA </p></h2>
</div>

<div class="jumbotron clearfix">

	<form action="creditCard" method="POST">
	<h1>
		<div class="form-group row mx-sm-3">
			<h5><label class="col-sm-1 col-form-label">Name: </label></h5>
			<div class="col-sm-3">
				<select name="nameCardType">
					<option class="col-sm-3" value='--'>- - - - - -</option>
					<c:forEach items="${databaseList}" var="databaseValue">
						<option value="${databaseValue.id}">
							${databaseValue.nameType}</option>
					</c:forEach>
				</select>
			</div>
		</div>

			<h5><label class="col-sm-1 col-form-label">Number: </label></h5>

			<div class="col-sm-3">
				<input type="text" class="form-control" name="number" placeholder='Credit Card Number'>
			</div>


		<h5><label class="col-sm-2 col-form-label">Card Expiration: </label></h5>
		
		<div class="col-sm-3">
			<select name='expireYY' id='expireYY'>
				<option value='00'>Year</option>
				<option value='17'>2017</option>
				<option value='18'>2018</option>
				<option value='19'>2019</option>
				<option value='20'>2020</option>
				<option value='21'>2021</option>
				<option value='22'>2022</option>
			</select>

			<select name='expireMM' id='expireMM'>
				<option value='00'>Month</option>
				<option value='01'>January</option>
				<option value='02'>February</option>
				<option value='03'>March</option>
				<option value='04'>April</option>
				<option value='05'>May</option>
				<option value='06'>June</option>
				<option value='07'>July</option>
				<option value='08'>August</option>
				<option value='09'>September</option>
				<option value='10'>October</option>
				<option value='11'>November</option>
				<option value='12'>December</option>
			</select> 
		</div>

	    <input type="submit" class="btn btn-primary">

	</form>

		</h1>
		<pre>
			<p align="center">
				<b>${status}</b>
			</p>
		</pre>
		
		
	</h1>
</div>
</div>
</body>

</html>