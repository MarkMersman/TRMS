
function login(){
   
    let un = document.getElementById("username").value;
    let pw = document.getElementById("password").value;

    
   // console.log(usernameInput);
   // console.log(passwordInput);
    let url = 'http://localhost:8080/TRMS/login'

    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = tryLogin;

    xhttp.open('POST', url + "?un="+un +"&pw="+pw, true);

    //JSON.stringify(emp)
    xhttp.send();

    function tryLogin(){
        if(xhttp.readyState == 4){
            if(xhttp.status == 200){
               
                let res = xhttp.responseText;
                
                if(res =="fail"){
                    let fail = document.createElement('p');
                    fail.innerHTML ="Login Failed!";
                    let div = document.getElementById("response");
                    div.appendChild(fail);                    
                }
                else{
                    //window.location.href= "D:\\Users\\markm\\Revature_Training\\TRMS\\TRMS\\TRMS\\src\\main\\webapp\\main.html";
                   loadMain(res);
                   
                }
               // response = JSON.parse(response);
               // console.log(response);
                //if login doesn't wor create an error message if it does it redirects.

            }
    
        }
    }

    function loadMain(response){
       
       // let emp = JSON.parse(response);
        localStorage["loggedUser"] = response;       
        window.location.href= "D:\\Users\\markm\\Revature_Training\\TRMS\\TRMS\\TRMS\\src\\main\\webapp\\main.html";

       console.log(emp);
       
    }

}