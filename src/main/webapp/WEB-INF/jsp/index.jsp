<html lang='en' ng-app="FwtFunApp">

<head>

<meta charset="utf-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/animate.min.css" />
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-social/4.10.1/bootstrap-social.css" rel="stylesheet" >

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

<nav class='navbar navbar-default' role='navigation' >
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
					<li class="active">
                      <a href="#"> <span class="glyphicon glyphicon-home"></span> Home </a>
                    </li>
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
  <h2><p align="center"> JWT FUN - HOME </p></h2>
</div>

<div class="container-fluid">
			<div class="jumbotron">							
					<div class="clock-header text-center">
						<h1>Our Website is Under Construction</h1>
						<br/>						
					</div>
					<div class="clock" id="countdown">&nbsp;</div>				
			</div>
				
</div>

</body>

</html>