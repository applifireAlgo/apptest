/**
 * THIS FILE IS USED TO LOAD EXTERNAL API'S 
 */

function loadExternalApi() {
	var googleScript = document.createElement('script');
    googleScript.setAttribute("type","text/javascript");
    googleScript.setAttribute("src", "https://maps.googleapis.com/maps/api/js?v=3.18");
    document.body.appendChild(googleScript);
}