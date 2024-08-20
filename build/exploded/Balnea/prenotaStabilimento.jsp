<%@ page session="true"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.Date"%>
<%@ page import="Beans.Bagnante"%>
<%@ page import="Beans.Stabilimento"%>
<%@ page import="java.time.LocalDate"%>
<html lang="it">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.98.0">
<title>Prenota la vacanza</title>

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
							href="homeBagnante.jsp">Home</a></li>
						<li class="nav-item"><a class="nav-link"
							href="prenotazioni.jsp">Vedi le prenotazioni</a></li>
					</ul>

				</div>

				<div>
					<%
					Bagnante b = (Bagnante) session.getAttribute("bagnanteAttivo");
					%>
					Benvenuto <b> <%=b.getUsername()%></b> <a class="nav-link"
						href="/Balnea/Login?operation=logout">Logout</a>
				</div>

			</div>
		</nav>
	</header>

	<%
			String stabId = request.getParameter("idStab");
			Set<Stabilimento> stabSalvati = (Set<Stabilimento>) application.getAttribute("stabilimenti");
			Stabilimento current = (Stabilimento) session.getAttribute("stabilimentoCorrente");
			
			if (current == null) {
				for (Stabilimento s : stabSalvati) {
					if (s.getId().equals(stabId)) {
						current = s;
						break;
					}
				}
			}

			if (current == null) {
				//non dovrebbe mai succedere
				session.setAttribute("errPrenotazione", "Stabilimento non trovato, errore");
			}
			session.setAttribute("stabilimentoCorrente", current);
			LocalDate dataInizio = (LocalDate) session.getAttribute("dataInizio");
			LocalDate dataFine = (LocalDate) session.getAttribute("dataFine");
	%>


	<div class="container">
		<h2>Hai selezionato Stabilimento <%= current.getNome()%> in <%=current.getLocazione().getIndirizzo()%>,
			<%=current.getLocazione().getCittà()%>, <%=current.getLocazione().getStato()%>
			Dal <%= dataInizio %> a <%= dataFine %>
		</h2> Immetti nei campi sottostanti ombrelloni, lettini e eventualmente la
		cabina.

		<ul class="showcase">
			<li>
				<div class="seat"></div> <small>Disponibile</small>
			</li>
			<li>
				<div class="seat occupied"></div> <small>Occupato</small>
			</li>
		</ul>

		<%
		boolean[][] ombrelloni = current.getOmbrelloniDisponibiliByPeriod(dataInizio, dataFine);
		for(int i = 0; i < current.getInventario().getOmbrelloni().getRighe();i++)
		{
			 %>Fila:<%=i+1%><div class="row">
			<br>
			<%
			for(int j = 0; j < current.getInventario().getOmbrelloni().getColonne();j++)
			{
				if(ombrelloni[i][j]==true)
				{
					%><div class="seat">
				<span><%=j+1%></span>
			</div>

			<%
				} 
				else 
				{
					%><div class="seat occupied"><%=j+1%></div>
			<%
				}
			}
			%>
		</div>
		<% 
		}
		
		
		%>

		<br>
		<form action="Prenota" method="post">
			<div class="row mb-4">
				<div class="col">
					<div class="form-outline">
						<input type="number" id="qtaOmbrelloni" name="qtaOmbrelloni"
							class="form-control input-custom"  oninput= "addFields()"/> <label class="form-label"
							for="form9Example1">Numero di ombrelloni</label>
					</div>
				</div>
				
				<div class="col">
					<div class="form-outline">
						<span id="errDisp">Disposizione: </span>
						<div id="ombrelloniPosix"></div>
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
						Cabina:
						<div>
							<input type="radio" id="yes" name="radio" value="Sì" checked>
							<label for="yes">Sì</label>
						</div>
						<div>
							<input type="radio" id="no" name="radio" value="No">
							<label for="no">No</label>
						</div>
						
					</div>
				</div>
			</div>

			<div class="float-end ">
				<button type="submit" value="Calcola" name="submitButton"
					class="btn btn-primary btn-rounded"
					style="background-color: #0062CC;">Calcola preventivo</button>
				<button type="submit" value="Prenota"  name="submitButton"
					class="btn btn-primary btn-rounded"
					style="background-color: #0062CC;">Prenota</button>
			</div>
		</form>
	</div>
	
	<div>
		<%
			Double prev = (Double) session.getAttribute("preventivo");
			if (prev != null && prev > 0) {
			%>
		<p>
			<b> Preventivo della prenotazione: <%=prev%> Euro</b>
		</p>
		<%
			}
			%>
	</div>
	

	<div>
		<%
			String err = (String) session.getAttribute("errPrenotazione");
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
