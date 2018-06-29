let url = "http://localhost:8082/WorkHard-PlayHard/users/4"

	
//var userid = `${userid}`
function sendAjaxGet(url, func) {
    let xhr = (new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest"));
    xhr.onreadystatechange = function () {
        //console.log(xhr.readyState);
        if (this.status == 200 && this.readyState == 4) {
            func(this);
        }
    }
    xhr.open("GET", url);
    xhr.send();
}

sendAjaxGet(url, displayUser);

function displayUser(xhr){
	let response = xhr.response;
    let user = JSON.parse(response);
    let temp = document.getElementById("para");
    temp.innerHTML = "hello";	
    //temp.innerHTML = "Userid is: " + user.userid + " and email is: " + user.email;
}