<%@ page session="true"%>
<%@ page import="java.util.Set"%>
<%@ page import="Beans.Proprietario"%>
<%@ page import="Beans.Stabilimento"%>
<%@ page import="Beans.Locazione"%>
<%@ page import="Beans.Informazioni"%>
<%@ page import="Beans.Inventario"%>
<html lang="it">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.98.0">

<title>Homepage Proprietario</title>

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
							aria-current="page">Home</a></li>
						<li class="nav-item"><a class="nav-link"
							href="registraStabilimento.jsp">Registra un nuovo stabilimento</a></li>
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

		<div class="list-group">
		<div class="row g-3">
		
		<% if(p!= null) { 
			Set<Stabilimento> stab = p.getStabilimenti();
			for(Stabilimento s : stab)
			{
				System.out.print(s.getInformazioni().getFoto().get(0).getFile());
		%>
			<div class="col">
			<a href="gestioneStabilimento.jsp?idStabilimento=<%=s.getId()%>" class="list-group-item list-group-item-action" >
			<img id="pino" width="325px" height="220px" src=<%= s.getInformazioni().getFoto().get(0).getFile() %> >
				<div class="justify-content-between">
					<h5 class="mb-1"> Stabilimento balneare <%= s.getNome() %></h5>
				</div>
				<p class="mb-1">
					Aperto dal <%= s.getInformazioni().getDataInizio() %> al <%= s.getInformazioni().getDataFine() %>
					<br>
					Locazione: <%= s.getLocazione().getIndirizzo() %>, <%= s.getLocazione().getCittà() %>, <%= s.getLocazione().getStato() %>
					<br>
					Tariffe:
					<ul>
						<li>
						Ombrelloni: <%= s.getInventario().getOmbrelloni().getTariffa() %> Euro
						</li>
						<li>
						Lettini: <%= s.getInventario().getLettini().getTariffa() %> Euro
						</li>
						<li>
						Cabine: <%= s.getInventario().getCabine().getTariffa() %> Euro
						</li>
					</ul>
				</p> 
				<small>Contatti: <%= s.getInformazioni().getEmail() %> <%= s.getInformazioni().getTelefono() %> </small>
			</a> 
			</div>
		<% 		}
			} %>	
			
		</div>
		</div>


	<script src="BootStrap/assets/dist/js/bootstrap.bundle.min.js"></script>


</body>
</html>
