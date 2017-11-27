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
					<li class="active"><a class='text-center' href='login.jsp'>Login</a></li>	
					
				   

				</ul>


				<ul class='nav navbar-right'>
					

					<!-- <li><a class='text-primary' href='/login'>Logout</a></li> -->

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
	<h2><p align="center"> JWT FUN - LOGIN </p></h2>
</div>

<div class="jumbotron clearfix" >

	<form action="login" method="POST">
	<h1>
		
		<div class="form-group row mx-sm-3">
			<h5><label class="col-sm-1 col-form-label">Email: </label></h5>
			<div class="col-sm-3">
				<input type="email" class="form-control" name="email">
			</div>
		</div>

		<div class="form-group row mx-sm-3">
 			<h5><label class="col-sm-1 col-form-label">Password: </label></h5>
			<div class="col-sm-3">
				 <input type="password"  class="form-control" name="password">
			</div>
		</div>
		
	    <input type="submit" class="btn btn-primary">

		 <div class="form-group row mx-sm-3">
	     	<div jumbotext"><a class='text-center' href='#'><h5>Sing Up</h5></a></div>
         	<div jumbotext"><a class='text-center' href='#'><h5>Forget your Password?</h5></a></div>
		 </div>

		<!-- <pre>
			<p align="center">
				<b>${status}</b>
			</p>
		</pre> -->
		
		
		</h1>
	</form>
</div>
</div>


</body>
</html>