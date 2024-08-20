<%@ page session="true"%>

<html lang="it">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.98.0">
    <title>Login</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/sign-in/">

    

    

<link href="BootStrap/assets/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      .b-example-divider {
        height: 3rem;
        background-color: rgba(0, 0, 0, .1);
        border: solid rgba(0, 0, 0, .15);
        border-width: 1px 0;
        box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
      }

      .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
      }

      .bi {
        vertical-align: -.125em;
        fill: currentColor;
      }

      .nav-scroller {
        position: relative;
        z-index: 2;
        height: 2.75rem;
        overflow-y: hidden;
      }

      .nav-scroller .nav {
        display: flex;
        flex-wrap: nowrap;
        padding-bottom: 1rem;
        margin-top: -1px;
        overflow-x: auto;
        text-align: center;
        white-space: nowrap;
        -webkit-overflow-scrolling: touch;
      }
    </style>
	
    
    <!-- Custom styles for this template -->
    <link href="styles/signin.css" rel="stylesheet">
  </head>
  <body class="text-center">
    
<main class="form-signin w-100 m-auto">

	<%
		
	
	
	
	
	
	
	
	
	%>

	
  <form  action="Login" method="post" >
    <img class="mb-4" src="images/logo.png" alt="" width="180" height="120">
    <h3 class="h3 mb-3 fw-normal">Inserisci qui sotto le credenziali</h3>

    <div class="form-floating">
      <input name="username" class="form-control" id="floatingInput" placeholder="Username">
      <label for="floatingInput">Username</label>
    </div>
    
    <div class="form-floating">
      <input type="password" name="password" class="form-control" id="floatingPw" placeholder="Password">
       <label for="floatingPw">Password</label>
    </div>
    
    <div>
	  	<%String err = (String)session.getAttribute("errMessaggio");
	  	  if(err!=null && !err.isEmpty())
	  	  { %>
	  			  	<p><b> Errore: <%=err%></b></p>
	  	<% } %>
	  </div>

    <button class="w-100 btn btn-lg btn-primary"  type="submit" value="Login">Login</button>
    <p></p>
    <div>Se non sei ancora registrato:
    <a href="registration.jsp"> Registrati </a>
    </div>
    </form>
</main>


    
  </body>
</html>
