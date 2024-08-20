<%@ page session="true"%>
<%@ page import="java.util.List"%>
<%@ page import="Beans.Bagnante"%>
<%@ page import="Beans.Prenotazione"%>


<html lang="it">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.98.0">
<title>Prenotazioni</title>

<link rel="canonical"
	href="https://getbootstrap.com/docs/5.2/examples/carousel/">





<link href="BootStrap/assets/dist/css/bootstrap.min.css"
	rel="stylesheet">

<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}

.b-example-divider {
	height: 3rem;
	background-color: rgba(0, 0, 0, .1);
	border: solid rgba(0, 0, 0, .15);
	border-width: 1px 0;
	box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em
		rgba(0, 0, 0, .15);
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
<link href="styles/prenotazioni.css" rel="stylesheet">

</head>
<body class="body">

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
						<li class="nav-item"><a class="nav-link"
							href="homeBagnante.jsp">Home</a></li>
						<li class="nav-item"><a class="nav-link active"
							aria-current="page">Vedi le prenotazioni</a></li>
					</ul>
				</div>

				<div>
					<%
					Bagnante b = (Bagnante) session.getAttribute("bagnanteAttivo");
					%>
					Benvenuto <b> <%= b.getUsername() %></b>
					<a class="nav-link" href="/Balnea/Login?operation=logout">Logout</a>
				</div>

			</div>
		</nav>
	</header>

	<main>

		<div class="list-group">
			<div class="row">

				<%
				List<Prenotazione> pren = b.getPrenotazioni();
				for (Prenotazione p : pren) {
				%>
				<div class="col">
					<a href="#" class="list-group-item list-group-item-action"
						aria-current="true">
						<div class="justify-content-between">
							<h5 class="mb-1">Prenotazione presso <%=p.getStabilimento().getNome()%></h5>
						</div>
						<p class="mb-1">
							Dal
							<%=p.getDataInizio()%>
							al
							<%=p.getDataFine()%>
							<br> Hai prenotato: <br>
						<ul>
							<li> Ombrellone in posizione : <ul>
								<%
								for (int[] posizione: p.getPosizioniOmbrelloni()) {
								%> <li> <%= posizione[0]+1%>-<%=posizione[1]+1%>
								</li><%
								}
								%>
							</ul></li>
							<li>Lettini : <%=p.getNumLettini()%>
							</li>
							<li>Cabina : <% if(p.isCabina()){%>S&igrave; <%}else{ %> No <%} %>
							</li>
						</ul>
						</p> <small>Per un totale di <%=p.calcolaPreventivo()%> euro </small>
						</a>
				</div>
				<%
				}
				%>
			</div>
		</div>
	</main>


	<script src="BootStrap/assets/dist/js/bootstrap.bundle.min.js"></script>


</body>
</html>
