var socket = new WebSocket("ws://localhost:8080/Template_v0.1/wsEndpoint");
console.log("ws://localhost:8080/Template_v0.1/wsEndpoint");

socket.onmessage =  function (event){
	
	//console.log(event.data); ricevo il nuovo numero o l'esito di fine tombola

	var message = JSON.parse(event.data);
	if (message.fine) {
		//un altro utente ha fatto tombola
		document.getElementById("fine").innerHTML = "<p>Un altro utente ha fatto tombola</p>"
		return;
	}
	if(message.partecipante)
	{
		document.getElementById("partecipanti").innerHTML = "<p>Un altro utente ha lasciato il gioco</p>"
	}
	if (!isNan(message.number)) {
		//ho ricevuto un nuovo numero, controllo la cartella
		var firstLine = document.getElementById("first");
		var secondLine = document.getElementById("second");
		var thirdLine = document.getElementById("third");
		
		updateCartella(firstLine,secondLine,thirdLine,message.number);
		if(checkLinea(firstLine)||checkLinea(secondLine)||checkLinea(thirdLine))
			socket.send("Cinquina");
		
		if(checkLinea(firstLine) && checkLinea(secondLine) ||checkLinea(firstLine)&& checkLinea(thirdLine)||checkLinea(thirdLine) && checkLinea(secondLine))
			socket.send("Decina");
		
		if(checkLinea(firstLine)&& checkLinea(secondLine)&&checkLinea(thirdLine))
		{
			document.getElementById("fine").innerHTML = "<p>TOMBOLA!!</p>"
			socket.send("Tombola!");
		}
		return;
	} else {
		alert("Unhandled Exception");
		return;
	}
	
}

function updateCartella(firstLine,secondLine,thirdLine,number)
{
	for(var i = 0; i < 5; i++)
	{
		if(firstLine[i] == number)
			firstLine[i].style.color = 'green';
		if(secondLine[i] == number)
			secondLine[i].style.color = 'green';
		if(thirdLine[i] == number)
			thirdLine[i].style.color = 'green';	
	}
}

function checkLinea(line)
{
	var cinquina = 1;
	for(var i = 0; i < 5; i++)
	{
		if(cinquina && line[i].style.color !== 'green')
			cinquina = 0;
	}
	return cinquina;
}
 function JSONtoArray(JS_Obj) {
            var obj = eval('(' + JS_Obj + ')');
            var res = [];
              
            for(var i in obj)
                res.push(obj[i]);
                
            return res;
}

socket.onopen = function (event){
	var array = JSONtoArray(event.data);
	if(array.length < 15)
		alert("at least 15 numbers");
		
		
	for(var i in array)
	{
		if(isNan(array[i]))
		alert("error "+array[i]+"is NaN");		
	}
	var firstLine = [];
	var secondLine = [];
	var thirdLine = [];
	
	for(var i = 0; i < 5; i++)
	{
		firstLine.push(array[i]);
	}
	for(var i = 5; i < 10; i++)
	{
		secondLine.push(array[i]);
	}
	for(var i = 10; i < 15; i++)
	{
		thirdLine.push(array[i]);
	}
	document.getElementById("first").innerHTML = firstLine;
	document.getElementById("second").innerHTML = secondLine;
	document.getElementById("third").innerHTML = thirdLine;
}

function timer()
{
	setTimeout(exitFunction,60000*5); //sosituibile con setInterval nell'html
}

function exitFunction()
{
	document.getElementById("hidden").style.display = 'block';
	
}
function exit()
{
	//utente vuole uscire
	socket.send("fineSessione");
	socket.close(0);
		
}