<%@ page session="true"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.Date"%>
<%@ page import="Beans.Proprietario"%>
<%@ page import="Beans.Stabilimento"%>
<%@ page import="java.time.LocalDate" %>
<html lang="it">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.98.0">
<title>Gestisci lo stabilimento</title>

<link rel="canonical"
	href="https://getbootstrap.com/docs/5.2/examples/carousel/">
<link href="BootStrap/assets/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="styles/ricerca.css" rel="stylesheet">
<link rel="stylesheet" href="styles/seats.css" />

</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-blue fixed-top bg-blue">
			<div class="container-fluid">
				<a class="navbar-brand" href="#">Balnea</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarCollapse"
					aria-controls="navbarCollapse" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarCollapse">
					<ul class="navbar-nav me-auto mb-2 mb-md-0">
						<li class="nav-item"><a class="nav-link active"
							href="homeProprietario.jsp">Home</a></li>
						<li class="nav-item"><a class="nav-link"
							href="registraStabilimento.jsp">Registra il tuo stabilimento</a></li>
					</ul>

				</div>

				<div>
					<% Proprietario p = (Proprietario) session.getAttribute("proprietarioAttivo");%>
					Benvenuto <b> <%=p.getUsername()%></b> 
					<a class="nav-link" href="/Balnea/Login?operation=logout">Logout</a>
				</div>

			</div>
		</nav>
	</header>

	<%
		String stabId = request.getParameter("idStabilimento");
		Set<Stabilimento> stabProprietario =  p.getStabilimenti();
		Stabilimento current = (Stabilimento) session.getAttribute("stabilimentoGestito");
		if(current == null){
		for(Stabilimento s: stabProprietario)
		{
			if(s.getId().equals(stabId))
			{
				current = s;
				break;
			}
		}}
		
		if(current == null)
		{
			//non dovrebbe mai succedere
			session.setAttribute("errGestione","Stabilimento non trovato, errore");
		}
		session.setAttribute("stabilimentoGestito",current);
	%>


	<div class="container">
		<h2>Stabilimento
		<%= current.getNome()%>
		in
		<%=current.getLocazione().getIndirizzo()%>,
		<%=current.getLocazione().getCittà()%>,
		<%=current.getLocazione().getStato()%>
		</h2>
		
		<h4>Stato delle prenotazioni: </h4>

		<ul class="showcase">
			<li>
				<div class="seat"></div> <small>Disponibile</small>
			</li>
			<li>
				<div class="seat occupied"></div> <small>Occupato</small>
			</li>
		</ul>

		<div>
			Inserisci la data di interesse (default: Data Odierna): 
			<input type="date" name="dataDisposizione" id="dataDisposizione" onchange = "loadDatedd()">
		</div>

		<%
		LocalDate ld = null;
		if(request.getParameter("date")== null || request.getParameter("date").isEmpty())
		{
			ld = LocalDate.now();
		}
		else
		{
			ld = LocalDate.of(2022,7,9);
		}
		
		boolean[][] ombrelloni = current.getOmbrelloniDisponibiliByDate(ld);
		for(int i = 0; i < current.getInventario().getOmbrelloni().getRighe();i++)
		{
			 %>Fila:<%=i+1%><div class="row"><br><%
			for(int j = 0; j < current.getInventario().getOmbrelloni().getColonne();j++)
			{
				if(ombrelloni[i][j]==true)
				{
					%> <div class="seat"><%=j+1%></div> <% 
				} 
				else 
				{
					%> <div class="seat occupied"><%=j+1%></div> <%
				}
			}
			%></div><% 
		}
		
		
		%>
		

		<br>

		<form action="Gestisci" method="post">
			<div class="row mb-4">
				<h4>Modifica Informazioni dello stabilimento</h4>
				<div class="col">
					<div class="form-outline">
						<input type="email" id="typeEmail" name="email"
							class="form-control input-custom" /> <label class="form-label"
							for="typeEmail">Email</label>
					</div>
				</div>

				<div class="col">
					<div class="form-outline">
						<input type="text" id="typeEmail" name="telefono"
							class="form-control input-custom" /> <label class="form-label"
							for="typeEmail">Telefono</label>
					</div>
				</div>

				<div class="col">
					<div class="form-outline">
						<input type="date" name="dataInizio" id="form9Example1"
							class="form-control input-custom" /> <label class="form-label"
							for="form9Example1">Data inizio stagione</label>
					</div>
				</div>
				<div class="col">
					<div class="form-outline">
						<input type="date" name="dataFine" id="typeEmail"
							class="form-control input-custom" /> <label class="form-label"
							for="typeEmail">Data fine stagione</label>
					</div>
				</div>

				<textarea id="desc" name="descrizione" rows="5" cols="40"> </textarea>
				<label for="desc">Descrizione</label>
			</div>
			<div class="float-end ">
				<!-- Submit button -->
				<button type="submit" class="btn btn-primary btn-rounded" name="submitButton" value="ModificaInformazioni"
					style="background-color: #0062CC;">Modifica informazioni</button>
			</div>
		</form>
		
		<br>
		
		<form action="Gestisci" method="post">
			<div class="row mb-4">
				<h4>Modifica Inventario dello stabilimento</h4>
				<div class="col">
					<div class="form-outline">
						<input type="number" id="form9Example1" name="qtaOmbrelloni"
							class="form-control input-custom" /> <label class="form-label"
							for="form9Example1">Numero di ombrelloni</label>
					</div>
				</div>
				<div class="col">
					<div class="form-outline">
						<input type="number" id="typeEmail" name="qtaLettini"
							class="form-control input-custom" /> <label class="form-label"
							for="typeEmail">Numero di lettini</label>
					</div>
				</div>

				<div class="col">
					<div class="form-outline">
						<input type="number" id="typeEmail" name="qtaCabine"
							class="form-control input-custom" /> <label class="form-label"
							for="typeEmail">Numero di cabine</label>
					</div>
				</div>

				<div class="col">
					<div class="form-outline">
						<input type="number" name="dispRighe" id="typeEmail"
							class="form-control input-custom" placeholder="Righe" /> <input
							type="number" name="dispCol" id="typeEmail"
							class="form-control input-custom" placeholder="Colonne" /> <label
							class="form-label" for="typeEmail">Disposizione degli
							ombrelloni</label>
					</div>
				</div>

				<div class="row mb-4">
					<div class="col">
						<div class="form-outline">
							<input type="text" id="form10Example1" name="tarOmbrelloni"
								class="form-control input-custom" /> <label class="form-label"
								for="form10Example1">Tariffa ombrellone</label>
						</div>
					</div>
					<div class="col">
						<div class="form-outline">
							<input type="text" id="tarLet" name="tarLettini"
								class="form-control input-custom" /> <label class="form-label"
								for="tarLet">Tariffa lettino</label>
						</div>
					</div>

					<div class="col">
						<div class="form-outline">
							<input type="text" id="tarCab" name="tarCabine"
								class="form-control input-custom" /> <label class="form-label"
								for="tarCab">Tariffa cabina</label>
						</div>
					</div>
				</div>

			</div>
			<div class="float-end ">
				<!-- Submit button -->
				<button type="submit" class="btn btn-primary btn-rounded" name="submitButton" value="modificaInventario"
					style="background-color: #0062CC;">Modifica Inventario</button>
			</div>
		</form>
	</div>

	<div>
		<%
		String err = (String) session.getAttribute("errGestione");
		if (err != null && !err.isEmpty()) {
		%>
		<p>
			<b> Errore: <%=err%></b>
		</p>
		<%
			}
			%>
	</div>

	<script src="BootStrap/assets/dist/js/bootstrap.bundle.min.js"></script>
	<script src="scripts/seats.js"></script>

</body>
</html>
