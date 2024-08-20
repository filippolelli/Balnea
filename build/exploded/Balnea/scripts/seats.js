const container = document.querySelector('.container');
const seats = document.querySelectorAll('.row .seat:not(.occupied)');
const count = document.getElementById('count');
const total = document.getElementById('total');
const movieSelect = document.getElementById('movie');

populateUI();

let ticketPrice = +movieSelect.value;

// Save selected movie index and price
function setMovieData(movieIndex, moviePrice) {
	localStorage.setItem('selectedMovieIndex', movieIndex);
	localStorage.setItem('selectedMoviePrice', moviePrice);
}

// Update total and count
function updateSelectedCount() {
	const selectedSeats = document.querySelectorAll('.row .seat.selected');

	const seatsIndex = [...selectedSeats].map(seat => [...seats].indexOf(seat));

	localStorage.setItem('selectedSeats', JSON.stringify(seatsIndex));

	const selectedSeatsCount = selectedSeats.length;

	count.innerText = selectedSeatsCount;
	total.innerText = selectedSeatsCount * ticketPrice;

	setMovieData(movieSelect.selectedIndex, movieSelect.value);
}

// Get data from localstorage and populate UI
function populateUI() {
	const selectedSeats = JSON.parse(localStorage.getItem('selectedSeats'));

	if (selectedSeats !== null && selectedSeats.length > 0) {
		seats.forEach((seat, index) => {
			if (selectedSeats.indexOf(index) > -1) {
				seat.classList.add('selected');
			}
		});
	}

	const selectedMovieIndex = localStorage.getItem('selectedMovieIndex');

	if (selectedMovieIndex !== null) {
		movieSelect.selectedIndex = selectedMovieIndex;
	}
}

// Movie select event
movieSelect.addEventListener('change', e => {
	ticketPrice = +e.target.value;
	setMovieData(e.target.selectedIndex, e.target.value);
	updateSelectedCount();
});

// Seat click event
container.addEventListener('click', e => {
	if (
		e.target.classList.contains('seat') &&
		!e.target.classList.contains('occupied')
	) {
		e.target.classList.toggle('selected');

		updateSelectedCount();
	}
});

// Initial count and total set
updateSelectedCount();


function removeAllChildNodes(parent) {
    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}

function addFields() {
	// Generate a dynamic number of inputs
	var number = document.getElementById("qtaOmbrelloni").value;
	var err = document.getElementById("errDisp");
	// Get the element where the inputs will be added to
	if (number >= 0) {
		var container = document.getElementById("ombrelloniPosix");
		err.innerHTML = "";
		removeAllChildNodes(container);
		for (i = 0; i < number; i++) {
			
			var row = document.createElement("input");
			row.setAttribute("type", "number");
			row.setAttribute("name", "posizioneRiga" + i);
			row.setAttribute("placeholder", "Riga Ombrellone" + eval(i+1));

			var col = document.createElement("input");
			col.setAttribute("type", "number");
			col.setAttribute("name", "posizioneCol" + i);
			col.setAttribute("placeholder", "Colonna Ombrellone" + eval(i+1));

			container.appendChild(row);
			container.appendChild(col);
		}
		
	}
	else
	{
		
		err.innerHTML = " Numero inserito non valido "
	}
}

function loadDatedd()
{
	var number = document.getElementById("dataDisposizione").value;
	window.location.href = '/gestioneStabilimento.jsp?date='+number;
	alert(1);
}