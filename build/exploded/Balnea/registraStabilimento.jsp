<%@ page session="true"%>
<html lang="it">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.98.0">
<title>Registra Stabilimento</title>

<link href="BootStrap/assets/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="styles/stab.css" rel="stylesheet">
</head>


<body>
	<div class="row mt-3 mx-3" style="margin-top: 25px;">
		<div class="col-md-3">
			<div style="margin-top: 50px; margin-left: 10px;" class="text-center">
				<h3 class="mt-3">Registra il tuo stabilimento su</h3>
				<br> <a href="./homeProprietario.jsp"><img class="mb-4" src="images/logo.png" alt="" width="200"
					height="160"></a>
			</div>

		</div>
		<div class="col-md-9 justify-content-center">
			<div class="card card-custom pb-4">
				<div class="card-body mt-0 mx-5">


					<div>
						<%
						String err = (String) session.getAttribute("errRegistraStab");
						if (err != null && !err.isEmpty()) {
						%>
							<p><b> Errore: <%=err%></b></p>
						<%
						}
						%>
					</div>
					<div class="text-center mb-3 pb-2 mt-3">
						<h4 style="color: #495057;">Dettagli e informazioni dello
							stabilimento</h4>
					</div>

					<form class="mb-0" action="RegistraBagno" method="post" enctype="multipart/form-data">

						<div class="row mb-4">
							<div class="col">
								<div class="form-outline">
									<input type="text" id="form9Example1" name="nomeStab"
										class="form-control input-custom" /> <label
										class="form-label" for="form9Example1">Nome dello
										stabilimento</label>
								</div>
							</div>
							<div class="col">
								<div class="form-outline">
									<input type="email" id="typeEmail" name="email"
										class="form-control input-custom" /> <label
										class="form-label" for="typeEmail">Email</label>
								</div>
							</div>

							<div class="col">
								<div class="form-outline">
									<input type="text" id="typeEmail" name="telefono"
										class="form-control input-custom" /> <label
										class="form-label" for="typeEmail">Telefono</label>
								</div>
							</div>
						</div>

						<div class="row mb-4">
							<div class="col">
								<div class="form-outline">
									<input type="text" id="form9Example1" name="indirizzo"
										class="form-control input-custom" /> <label
										class="form-label" for="form9Example1">Indirizzo</label>
								</div>
							</div>
							<div class="col">
								<div class="form-outline">
									<input type="text" id="typeEmail" name="città"
										class="form-control input-custom" /> <label
										class="form-label" for="typeEmail">Citt&agrave; </label>
								</div>
							</div>
							<div class="col">
								<div class="form-outline">
									<input type="text" id="form9Example1" name="stato"
										class="form-control input-custom" /> <label
										class="form-label" for="form9Example1">Stato</label>
								</div>
							</div>
						</div>

						<div class="row mb-4">
							<div class="col">
								<div class="form-outline">
									<input type="number" id="form9Example1" name="qtaOmbrelloni"
										class="form-control input-custom" /> <label
										class="form-label" for="form9Example1">Numero di
										ombrelloni</label>
								</div>
							</div>
							<div class="col">
								<div class="form-outline">
									<input type="number" id="typeEmail" name="qtaLettini"
										class="form-control input-custom" /> <label
										class="form-label" for="typeEmail">Numero di lettini</label>
								</div>
							</div>

							<div class="col">
								<div class="form-outline">
									<input type="number" id="typeEmail" name="qtaCabine"
										class="form-control input-custom" /> <label
										class="form-label" for="typeEmail">Numero di cabine</label>
								</div>
							</div>

							<div class="col">
								<div class="form-outline">
									<input type="number" name="dispRighe" id="typeEmail"
										class="form-control input-custom" placeholder="Righe" /> 
									<input type="number" name="dispCol" id="typeEmail"
										class="form-control input-custom" placeholder="Colonne" /> <label
										class="form-label" for="typeEmail">Disposizione degli
										ombrelloni</label>
								</div>
							</div>
						</div>
						
						<div class="row mb-4">
							<div class="col">
								<div class="form-outline">
									<input type="text" id="form10Example1" name="tarOmbrelloni"
										class="form-control input-custom" /> <label
										class="form-label" for="form10Example1">Tariffa ombrellone</label>
								</div>
							</div>
							<div class="col">
								<div class="form-outline">
									<input type="text" id="tarLet" name="tarLettini"
										class="form-control input-custom" /> <label
										class="form-label" for="tarLet">Tariffa lettino</label>
								</div>
							</div>

							<div class="col">
								<div class="form-outline">
									<input type="text" id="tarCab" name="tarCabine"
										class="form-control input-custom" /> <label
										class="form-label" for="tarCab">Tariffa cabina</label>
								</div>
							</div>
						</div>

						<div class="row mb-4">
							<div class="col">
								<div class="form-outline">
									<input type="date" name="dataInizio" id="form9Example1"
										class="form-control input-custom" /> <label
										class="form-label" for="form9Example1">Data inizio
										stagione</label>
								</div>
							</div>
							<div class="col">
								<div class="form-outline">
									<input type="date" name="dataFine" id="typeEmail"
										class="form-control input-custom" /> <label
										class="form-label" for="typeEmail">Data fine stagione</label>
								</div>
							</div>
							
							<textarea id="desc" name="descrizione" rows="5" cols="50">
								</textarea>
								<label for="desc">Descrizione</label>


							<div class="d-flex flex-row align-items-center mb-4">
								<i class="fas fa-key fa-lg me-3 fa-fw"></i> 
								
								<div class="form-outline flex-fill mb-0">
									<label class="form-label" for="form3Example4cd">Carica
										foto </label> <input name="fotoFile"
										class="form-control form-control-lg" id="formFileLg"
										type="file" />
								</div>
								<i class="fas fa-key fa-lg me-3 fa-fw"></i>
								<div class="form-outline flex-fill mb-0">
									<label class="form-label" for="form3Example4cd">Carica
										documento di propriet&agrave; </label> <input name="file"
										class="form-control form-control-lg" id="formFileLg"
										type="file" />
								</div>
							</div>
						</div>


						<div class="float-end ">
							<!-- Submit button -->
							<button type="submit" class="btn btn-primary btn-rounded"
								style="background-color: #0062CC;">Registra stabilimento</button>
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>