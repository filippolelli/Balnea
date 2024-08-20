<%@ page session="true"%>
<%@ page import="java.util.List"%>
<%@ page import="Beans.Bagnante"%>
<%@ page import="Beans.Stabilimento"%>
<html lang="it">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.98.0">
<title>Homepage Bagnante</title>


<!-- Custom styles for this template -->
<link href="styles/ricerca.css" rel="stylesheet">

<link href="BootStrap/assets/dist/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-blue fixed-top bg-blue">
			<div class="container-fluid">
				<span class="navbar-brand">Balnea</span>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarCollapse"
					aria-controls="navbarCollapse" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarCollapse">
					<ul class="navbar-nav me-auto mb-2 mb-md-0">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page">Home</a></li>
						<li class="nav-item"><a class="nav-link"
							href="prenotazioni.jsp">Vedi le prenotazioni</a></li>
					</ul>

				</div>

				<div>
					<%
					Bagnante b = (Bagnante) session.getAttribute("bagnanteAttivo");
					%>
					Benvenuto <b> <%=b.getUsername()%></b> 
					<a class="nav-link" href="/Balnea/Login?operation=logout">Logout</a>
				</div>

			</div>
		</nav>
	</header>
	
	<div class="container">
			<form action="Ricerca" method="post">
				<div class="search">
					<div class="row">
						<div class="col-md-6">
							<div class="search-1">
								<input name="nomeStab" type="text"
									placeholder="Nome dello stabilimento">
							</div>
						</div>
						<div class="col-md-6">
							<div class="search-1">
								<input name="indirizzo" type="text" placeholder="Indirizzo">
							</div>
						</div>
						<div class="col-md-6">
							<div class="search-1">
								<input name="città" type="text" placeholder="Città">
							</div>
						</div>

						<div class="col-md-6">
							<div class="search-1">
								<input name="stato" type="text" placeholder="Stato">
							</div>
						</div>

						<div class="col-md-6">
							<div class="search-1">
								Data Inizio: <input name="dataInizio" type="date"
									id="dataInizio" name="dataInizio">

							</div>
						</div>

						<div class="col-md-6">
							<div class="search-1">
								Data fine: <input name="dataFine" type="date" id="dataFine"
									name="dataFine">
							</div>
						</div>

					</div>

				</div>
				<div class="search-2" text-align="center">
					<button type="submit" top="50%">Ricerca</button>
				</div>
			</form>

		<div>
			<%
			String err = (String) session.getAttribute("errRicerca");
			if (err != null && !err.isEmpty()) {
			%>
			<p>
				<b> Errore: <%=err%></b>
			</p>
			<%
			}
			%>
		</div>

		<br> <br>

		<div class="list-group">
			<div class="row">
				<%
				if (err != null && err.isEmpty()) {
					List<Stabilimento> stab = (List<Stabilimento>) session.getAttribute("esitoRicercaStab");
					if (stab == null || stab.size() < 1) {
				%>
				<p>
					<b>Nessuno stabilimento è stato trovato</b>
				</p>

				<%
				} else {
				for (Stabilimento s : stab) {
				%>
				<div class="col">
					<a href="prenotaStabilimento.jsp?idStab=<%=s.getId()%>"
						class="list-group-item list-group-item-action"> 
						<img width="325px" height="220px" src=<%=s.getInformazioni().getFoto().get(0).getFile()%>>
						<div class="justify-content-between">
							<h5 class="mb-1">
								Stabilimento balneare
								<%=s.getNome()%></h5>
						</div>
						<p>
							Aperto dal
							<%=s.getInformazioni().getDataInizio()%>
							al
							<%=s.getInformazioni().getDataFine()%>
							<br> Locazione:
							<%=s.getLocazione().getIndirizzo()%>,
							<%=s.getLocazione().getCittà()%>,
							<%=s.getLocazione().getStato()%>
							<br> Tariffe:
						<ul>
							<li>Ombrelloni: <%=s.getInventario().getOmbrelloni().getTariffa()%>
								Euro
							</li>
							<li>Lettini: <%=s.getInventario().getLettini().getTariffa()%>
								Euro
							</li>
							<li>Cabine: <%=s.getInventario().getCabine().getTariffa()%>
								Euro
							</li>
						</ul>
						</p> <small>Contatti: <%=s.getInformazioni().getEmail()%> <%=s.getInformazioni().getTelefono()%>
					</small>
					</a>
				</div>
				<%
				}
				}
				}
				%>
			</div>
		</div>
	</div>

	<script src="BootStrap/assets/dist/js/bootstrap.bundle.min.js"></script>


</body>
</html>
