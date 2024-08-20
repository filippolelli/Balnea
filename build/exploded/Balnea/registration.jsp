<%@ page session="true"%>
<html lang="it">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.98.0">
    <title>Registrati</title>

	<link href="BootStrap/assets/dist/css/bootstrap.min.css" rel="stylesheet">
</head>


<body>
<section class="vh-100" style="background-color: #ffffff;">
  <div class="container h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-lg-12 col-xl-11">
        <div class="card text-black" style="border-radius: 25px;">
          <div class="card-body p-md-5">
            <div class="row justify-content-center">
              <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

                <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Registrati</p>

                <form class="mx-1 mx-md-4" action="Registration" method="post" enctype="multipart/form-data">

                  <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                      <input type="text" id="form3Example1c" name="nome" class="form-control" />
                      <label class="form-label" for="form3Example1c">Nome</label>
                    </div>
                  </div>

                  <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                      <input type="text" id="form3Example3c" name="cognome" class="form-control" />
                      <label class="form-label" for="form3Example3c">Cognome</label>
                    </div>
                  </div>

				  <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                      <input type="text" name="username" id="form3Example3c" class="form-control" />
                      <label class="form-label" for="form3Example3c">Username</label>
                    </div>
                  </div>
                  
                   <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                      <input type="email" name="email" id="form3Example3c" class="form-control" />
                      <label class="form-label" for="form3Example3c">Email</label>
                    </div>
                  </div>

                  <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                      <input name="password" type="password" id="form3Example4c" class="form-control" />
                      <label class="form-label" for="form3Example4c">Password</label>
                    </div>
                  </div>

                  <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                      <input name="reptPassword" type="password" id="form3Example4cd" class="form-control" />
                      <label class="form-label" for="form3Example4cd">Ripeti la password</label>
                    </div>
                  </div>


				  <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                      <h6 class="form-label">Sei un bagnante o un proprietario?</h6>

                  <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="bagnante"
                      value="bagnante" checked />
                    <label class="form-check-label" for="bagnante">Bagnante</label>
                  </div>

                  <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="proprietario"
                      value="proprietario" />
                    <label class="form-check-label" for="proprietario">Proprietario</label>
                  </div>

                    </div>
                  </div>
					
				 <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-key fa-lg me-3 fa-fw"></i>
					<div class="form-outline flex-fill mb-0">
                    <h6 class="form-label" for="form3Example4cd">Se sei un proprietario carica qui sotto il tuo documento di identit&aacute; </h6>
                    <input class="form-control form-control-lg" id="formFileLg" type="file" name="file"/>
                    </div>
                  </div>

                  <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                    <button type="submit" class="btn btn-primary btn-lg" value="Registration">Registrati</button>
                  </div>

                </form>

              </div>
              <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">

                <img src="images/logo.png" class="img-fluid" alt="Sample image">

              </div>
              
               
    		<div>
	  			<%String err = (String)session.getAttribute("errRegistration");
	  			  if(err!=null && !err.isEmpty())
	  			  { %>
	  					  	<h2><b> Errore: <%=err%></b></h2>
			  	<% } %>
			  </div>
              
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>