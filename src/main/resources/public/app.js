document.getElementById('movieForm-get').addEventListener('submit', function(event) {
    event.preventDefault();
    loadGetMsg();
});
document.getElementById('movieForm-post').addEventListener('submit', function(event) {
    event.preventDefault();
    loadPostMsg();
});
function loadGetMsg() {
    let nameVar = document.getElementById("name-get").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        document.getElementById("getrespmsg").innerHTML = this.responseText;
    };
    xhttp.open("GET", "/movie?name="+nameVar);
    xhttp.send();
}

function loadPostMsg() {
    let nameVar = document.getElementById("name-post").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        document.getElementById("postrespmsg").innerHTML = this.responseText;
    };
    xhttp.open("POST", "/movie");

    xhttp.send(JSON.stringify({name: nameVar}));
}