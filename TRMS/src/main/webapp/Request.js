function getInfo(){
    let request = localStorage["Req"];

    let event = localStorage["Event"];

    let reqEmp = localStorage["ReqEmp"];

    let reim = localStorage["Reimbursement"];

    let logged = localStorage['loggedUser'];

    console.log(request);
    console.log(event);
    console.log(reqEmp);
    console.log(reim);
    console.log(logged);
    generatePage();
}

function generatePage(){
    let request = localStorage["Req"];
    request = JSON.parse(request);
    let event = localStorage["Event"];
    event = JSON.parse(event);
    let reqEmp = localStorage["ReqEmp"];
    reqEmp = JSON.parse(reqEmp);
    let reimId = localStorage["Reimbursement"];
    reimId = JSON.parse(reimId);
    console.log(reimId);
    let logged = localStorage['loggedUser'];
    logged = JSON.parse(logged);

    let empName = document.getElementById("EmpName");
    empName.innerHTML = "Employee Name: " + reqEmp.firstName + " " + reqEmp.lastName;
    let reqDate = document.getElementById("reqDate");
    reqDate.innerHTML = "Date of Request: " + request.requestDate;
    let evType = document.getElementById("evType");
    evType.innerHTML = "Event Type: " + event.eventType;
    let evDesc =  document.getElementById("evDesc");
    evDesc.innerHTML = "Event Desription: " + event.description;
    let evLoc = document.getElementById("evLoc");
    evLoc.innerHTML = "Event Location:" + event.location;
    let sDate = document.getElementById("sDate");
    sDate.innerHTML = "Start Date:" + event.startDate;
    let mTime = document.getElementById("evMissedTime");
    mTime.innerHTML = "Time Missed:" + event.timeMissed;
    let evCost = document.getElementById("evCost");
    evCost.innerHTML = "Event Cost:" + event.cost;
    let evGradeForm = document.getElementById("evGradeFormat");
    evGradeFormat.innerHTML = "Grade Format:" + getGF(event.gradeFormat);
    let passGrade = document.getElementById("evPassingGrade");
    passGrade.innerHTML = "Passing Grade:" + event.pGrade;
    let sApp = document.getElementById("supApp");
    sApp.innerHTML = "Supervisor Approval: " + request.supApproval;
    let dhApp = document.getElementById("dhApp");
    dhApp.innerHTML = "Dept. Head Approval: " + request.deptHeadApproval;
    let bcApp = document.getElementById("bcApp");
    bcApp.innerHTML = "BenCo Approval: " + request.benCoApproval;
    let reqStatus = document.getElementById("reqStatus");
    reqStatus.innerHTML = " Request Status: " + request.status;
    let reqInfo = document.getElementById("reqInfo");
    reqInfo.innerHTML = "Request Notes: " + request.denialReason;

    //Reimbursement details
    let rID = document.getElementById("id");
    rID.innerHTML = "Reimbursement ID: " + reimId.id;
    let req = document.getElementById("req");
    req.innerHTML = "Request ID: " + reimId.requestId;
    let amt = document.getElementById("amt");    
    amt.innerHTML = "Amount: " + reimId.amount;
    let amtEx = document.getElementById("amtEx");
    amtEx.innerHTML = "Amount Exeeded: " + reimId.amountExceeded;
    let exReason = document.getElementById("exReason");
    exReason.innerHTML = "Exceeded Reason: " + reimId.exceededReason;
    let reimStatus = document.getElementById("reimStatus");
    reimStatus.innerHTML = "Status: " + reimId.status;
    let grade = document.getElementById("grade");   
    
    if (logged.position == 4){
        grade.innerHTML = "Grade: " + reimId.eventGrade;
        let editBtn = document.createElement('button');
        editBtn.innerHTML = "Edit Reimbursement Amount";
        editBtn.addEventListener('click', editReim);
        let btnDiv = document.getElementById('buttons');
        btnDiv.appendChild(editBtn);
        if(reimId.status == 'Pending Grade Approval'){
            let addAppBtn = document.getElementById('gradeApprove');
            let apprBtn = document.createElement('button');
            let denyBtn = document.createElement('button');
            apprBtn.innerHTML = "Approve";
            denyBtn.innerHTML = "Deny";
            apprBtn.addEventListener('click', appGrade);
            denyBtn.addEventListener('click', denyGrade);

            addAppBtn.appendChild(apprBtn);
            addAppBtn.appendChild(denyBtn);
        }
    }
}
function appGrade(){
    let re = localStorage["Req"];
    re = JSON.parse(re);
    let reim = localStorage["Reimbursement"];
    reim = JSON.parse(reim);
    logged = localStorage["loggedUser"];
    logged = JSON.parse(logged);
    
    re.denialReason = logged.firstName + " " + logged.lastName + " has approved the grade. ";

    let url = 'http://localhost:8080/TRMS/updatereqandreim'

    let xhttp = new XMLHttpRequest();
    
    //xhttp.onreadystatechange = confirm;
    
    xhttp.open('POST', url +"?reqid="+ re.id +"&status=Awarded"  + "&reimid=" + reim.id + "&denRea=" + re.denialReason, true);
    
        
    xhttp.send();
    
    window.location.href = "D:\\Users\\markm\\Revature_Training\\TRMS\\TRMS\\TRMS\\src\\main\\webapp\\main.html";
}
function denyGrade(){
    let re = localStorage["Req"];
    re = JSON.parse(re);
    let reim = localStorage["Reimbursement"];
    reim = JSON.parse(reim);
    logged = localStorage["loggedUser"];
    logged = JSON.parse(logged);
    
    re.denialReason = logged.firstName + " " + logged.lastName + " has denied the grade. ";

    let url = 'http://localhost:8080/TRMS/updatereqandreim'

    let xhttp = new XMLHttpRequest();
    
    //xhttp.onreadystatechange = confirm;
    
    xhttp.open('POST', url +"?reqid="+ re.id +"&status=denied"  + "&reimid=" + reim.id + "&denRea=" + re.denialReason, true);
    
        
    xhttp.send();
    
    window.location.href = "D:\\Users\\markm\\Revature_Training\\TRMS\\TRMS\\TRMS\\src\\main\\webapp\\main.html";
}
function getGF(int1){
    if(int1 == 1){
        return "letter grade";
    }
    else if(int1 == 2){
        return "pass/fail";
    }
    else if(int1 == 3){
        return "project";
    }
    else if(int1 == 4) {
        return "participation certificate";
    }
}

function Back(){
    window.location.href = "D:\\Users\\markm\\Revature_Training\\TRMS\\TRMS\\TRMS\\src\\main\\webapp\\main.html";
}
function denyReason(){
    let moreInfo = document.getElementById("infoRequested");
    let input = document.createElement('input');
    input.id = "denialReason";
    input.placeholder = "Enter the reason fro denial";
    let sub = document.createElement('button');
    sub.innerHTML = "Submit";
    sub.addEventListener("click", Deny);
    moreInfo.appendChild(input);
    moreInfo.appendChild(sub);
    
    function Deny(){
   
        let request = localStorage["Req"];
        request = JSON.parse(request);
        let logged = localStorage['loggedUser'];
        logged = JSON.parse(logged);
        let denRea = document.getElementById('denialReason').value;
        let reqStatus = 'denied';
        let reqId = request.id;
        
    
    

        let url = 'http://localhost:8080/TRMS/updatereq'

        let xhttp = new XMLHttpRequest();

        //xhttp.onreadystatechange = confirm;

        xhttp.open('POST', url +"?id="+ reqId +"&status=" + reqStatus + "&app=false&pos=" + logged.position  , true);

    
        xhttp.send();

        window.location.href = "D:\\Users\\markm\\Revature_Training\\TRMS\\TRMS\\TRMS\\src\\main\\webapp\\main.html";
    }
}
function Approve(){

    let request = localStorage["Req"];
    request = JSON.parse(request);
    let logged = localStorage['loggedUser'];
    logged = JSON.parse(logged);
    let reqStatus = 'denied'
    let reqId = request.id;

    if ((logged.position ==2)||(logged.position == 3)){
        reqStatus = 'Pending Approval';
    }
    else if(logged.position == 4){
        reqStatus = 'Pending Grade';
    }
    
    let url = 'http://localhost:8080/TRMS/updatereq'

    let xhttp = new XMLHttpRequest();

    //xhttp.onreadystatechange = confirm;

    xhttp.open('POST', url +"?id="+ reqId +"&status=" + reqStatus + "&app=true&pos=" + logged.position  , true);

    
    xhttp.send();

    window.location.href = "D:\\Users\\markm\\Revature_Training\\TRMS\\TRMS\\TRMS\\src\\main\\webapp\\main.html";
    
}
function reqMoreInfo(){
    let moreInfo = document.getElementById("infoRequested");
    let input = document.createElement('input');
    input.id = "infoIn"
    input.placeholder = "Enter the information you would like to request";
    let sub = document.createElement('button');
    sub.innerHTML = "Submit";
    sub.addEventListener("click", submit);
    moreInfo.appendChild(input);
    moreInfo.appendChild(sub);

    function submit(){
       //change the denialReason field on request to the information the user input
       //change the reimbursement and request status to Pending More Info
        let re = localStorage["Req"];
        re = JSON.parse(re);
        let reim = localStorage["Reimbursement"];
        reim = JSON.parse(reim);
        logged = localStorage["loggedUser"];
        logged = JSON.parse(logged);
        let infoInput = document.getElementById('infoIn').value;
        re.denialReason = logged.firstName + " " + logged.lastName + " has requested more info: " + infoInput;

        let url = 'http://localhost:8080/TRMS/updatereqandreim'

        let xhttp = new XMLHttpRequest();
    
        //xhttp.onreadystatechange = confirm;
    
        xhttp.open('POST', url +"?reqid="+ re.id +"&status=Pending More Info"  + "&reimid=" + reim.id + "&denRea=" + re.denialReason, true);
    
        
        xhttp.send();
    
        window.location.href = "D:\\Users\\markm\\Revature_Training\\TRMS\\TRMS\\TRMS\\src\\main\\webapp\\main.html";
    }
}
function editReim(){
    
    console.log("editReim");
    let addElems = document.getElementById("BenCoEdit");
    let reimAmountInput = document.createElement('input');
    reimAmountInput.id = "raInput";
    reimAmountInput.placeholder = "Input New Amount...";
    
    let exReason = document.createElement('input');
    exReason.id = 'eReason';
    exReason.placeholder = "give a reason if you're increasing the amount";
    let subEdit = document.createElement('button')
    subEdit.innerHTML = "Submit";
    subEdit.addEventListener("click", finishEdit);

    addElems.appendChild(reimAmountInput);
    addElems.appendChild(exReason);
    addElems.appendChild(subEdit);
    
    function finishEdit(){
        console.log("finishEdit");
        let re = localStorage["Req"];
        re = JSON.parse(re);
        let reim = localStorage["Reimbursement"];
        reim = JSON.parse(reim);
        logged = localStorage["loggedUser"];
        logged = JSON.parse(logged);
        let ra = document.getElementById('raInput').value;
        let ae = false;
        if (ra > reim.amount){
            ae = true;
        }
        reim.amount = ra;
        let exReason = reim.exceededReason;
        if (ae){
            exReason = document.getElementById('eReason').value;
        }



        let url = 'http://localhost:8080/TRMS/editreim';

        let xhttp = new XMLHttpRequest();
    
        //xhttp.onreadystatechange = confirm;
    
        xhttp.open('POST', url +"?reqid="+ re.id +"&status=Pending Emp Confirmation"  + "&reimid=" + reim.id + "&amount=" + reim.amount +
                    "&isExceeded=" + ae + "&exReason=" + exReason, true);
    
        
        xhttp.send();
    
        window.location.href = "D:\\Users\\markm\\Revature_Training\\TRMS\\TRMS\\TRMS\\src\\main\\webapp\\main.html";

    }
}
