function getEmp(){
    let emp = localStorage["loggedUser"];
    emp = JSON.parse(emp);
    return emp.id;
}

function Back(){
    window.location.href = "D:\\Users\\markm\\Revature_Training\\TRMS\\TRMS\\TRMS\\src\\main\\webapp\\main.html";
}
function Submit(){

    let eType = document.getElementById('evType').value;
    let eDesc = document.getElementById('evDesc').value;
    let eLoc = document.getElementById('evLoc').value;
    let eTime = document.getElementById('evTime').value;
    let sDate = document.getElementById('sDate').value;
    let gFor = document.getElementById('gFormat').value;
    let eCost = document.getElementById('eCost').value;
    let tMissed = document.getElementById('tMissed').value;
    let pGrade = document.getElementById('pGrade').value;
    let empId = getEmp();
    let today = new Date();
    today.setDate(today.getDate()+ 8);
    let start = new Date(sDate);

    if ((today > start)){
        console.log("start within a week");
        let ww = document.getElementById('withinWeek');
        let mess = document.createElement('p');
        mess.innerHTML= "Sorry, you can't submit a request within a week of the start date of the event";
        

        ww.appendChild(mess);
    }
    else{
        
        let url = 'http://localhost:8080/TRMS/newreq'

        let xhttp = new XMLHttpRequest();

        //xhttp.onreadystatechange = confirm;

         xhttp.open('POST', url +"?id="+ empId +"&type=" + eType +"&desc=" + eDesc + "&loc=" + eLoc + "&time=" + eTime
                + "&date=" + sDate +"&form=" + gFor + "&cost=" +eCost + "&missed=" + tMissed + "&pGrade=" +pGrade, true);


        //+"?id="+ editObject.id
        //xhttp.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
        xhttp.send();

         window.location.href = "D:\\Users\\markm\\Revature_Training\\TRMS\\TRMS\\TRMS\\src\\main\\webapp\\main.html";
    }
}