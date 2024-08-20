/*function myFunction()
{
	
	var text = document.getElementById("myText");
	var fileName = document.getElementById("fileName");
	
	
	
	if(text.value.length > 0 && fileName.value.length > 0)
		{
		
		// assegnazione oggetto XMLHttpRequest
			var	xhr = myGetXmlHttpRequest();
			var xhr2 = myGetXmlHttpRequest();
			if ( xhr ) 
				countingAJAX(xhr, xhr2, fileName.value, text.value); 
			else 
				countingIframe(); 
		}else
			{
				alert("the inputs text cannot be empty!");		
			}
	
		

}*/



function counter( jsonText ) {
   
	// variabili di funzione
	
	
		var conteggio = JSON.parse(jsonText);
		var i;
		// Ottengo la lista delle sezioni del docuemento
		
		if(conteggio == "termina")
		{
			document.getElementById("invalidate").innerHTML = "<b>Sessione invalidata dal server</b>";
		}
		
		for(i in conteggio)
		{
			document.getElementById("root").innerHTML = "<p>Titolo:"+i.titolo+"<br>Descrizione"+i.descrizione +"</p>" + document.getElementById("root").innerHTML
		}
		

}// parsificaJSON()





/*
 * Funzione di callback
 */
function callback( theXhr ) {

	
	if ( theXhr.readyState === 2 ) {
	    	//theElement.innerHTML = "Richiesta inviata...";
	}// if 2
	else if ( theXhr.readyState === 3 ) {
    	//	theElement.innerHTML = "Ricezione della risposta...";
	}// if 3
	else if ( theXhr.readyState === 4 ) {
		// verifica della risposta da parte del server
		if ( theXhr.status === 200 ) {
			// operazione avvenuta con successo
			counter(theXhr.responseText);
			
			//location.reload();
		}// if 200

		 else {
	        	// errore di caricamento
			 alert("Impossibile effettuare l'operazione richiesta.");
//	        	theElement.innerHTML = "Impossibile effettuare l'operazione richiesta.<br />";
//	        	theElement.innerHTML += "Errore riscontrato: " + theXhr.statusText;
	        }// else (if ! 200)
	}// if 4

} // callback();








function countingIframe() {
	// qui faccio scaricare al browser direttamente il contenuto del feed come src dell'iframe.
	alert("Impossibile effettuare l'operazione, il tuo browser è troppo vecchio")
	
	// non riesco tuttavia a intervenire per parsificarlo! è il browser che renderizza il src del iframe!
}// caricaFeedIframe()



function countingAJAX( theXhr, i) {
    
	// impostazione controllo e stato della richiesta
	theXhr.onreadystatechange = function() { callback(theXhr); };
	try {
		theXhr.open("post", "PushServlet", true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}

	// rimozione dell'header "connection" come "keep alive"
	theXhr.setRequestHeader("connection", "close"); //???
	
	var argument = "init="+i;
	// invio richiesta
	theXhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	theXhr.send(argument);

} // caricaFeedAJAX()

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}


function init()
{
	var i = 0;
	while (true) {
		var xhr = myGetXmlHttpRequest();
		if (xhr)
			countingAJAX(xhr, i);
		else
			countingIframe();
		i++;
		await sleep(60000);
	}
}