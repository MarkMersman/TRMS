function getRID(){
    let rID = localStorage["Reim"];
    
    rID = JSON.parse(rID);
    console.log(rID);
    generatePage(rID);
}

function generatePage(reimId){
    let emp = localStorage['loggedUser'];
    emp = JSON.parse(emp);
    console.log(emp);
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
    let status = document.getElementById("status");
    status.innerHTML = "Status: " + reimId.status;
    let grade = document.getElementById("grade");    
    if ((reimId.eventGrade == undefined)||(reimId.eventGrade =='N/A')){
        grade.innerHTML = "Grade: N/A";
        let gradeIn = document.createElement('input');
        gradeIn.id = "gradeInput";
        gradeIn.placeholder= "Enter a grade to upload..."
        let add = document.getElementById("gradeEdit")
        add.appendChild(gradeIn);
        let getButtons = document.getElementById('buttons');
        let addButton = document.createElement('button');
        addButton.innerHTML = "Upload Grade";
        addButton.addEventListener("click", uploadGrade);
        getButtons.appendChild(addButton);
    }
    else{
        grade.innerHTML = "Grade: " + reimId.eventGrade;
    }
    if (emp.position ==4){
        let addEdit = document.getElementById('buttons');
        let editBtn = document.createElement('button');
        editBtn.innerHTML = "Edit Amount";
        editBtn.addEventListener('click', editUI);
        addEdit.appendChild(editBtn);
    }
    if (reimId.status =='Pending More Info'){
        let statusInput = document.createElement('input');
        statusInput.id = "statusInput";
        let addInput = document.getElementById('sInput');
        statusInput.placeholder ="Please give more information about this request";
        let subStat = document.createElement('button');
        subStat.innerHTML = "Submit Info";
        subStat.addEventListener('click', submitInfo);

        addInput.appendChild(statusInput);
        addInput.appendChild(subStat);
    }

    if (reimId.status == 'Pending Emp Confirmation'){
        let addbtns = document.getElementById('BenCoEdit');
        let editLab = document.createElement('p');
        let acceptBtn = document.createElement('button');
        let cancelBtn = document.createElement('button');
        editLab.innerHTML = "The amount was changed by the Ben Co what would you like to do:";
        acceptBtn.innerHTML = "Accept";
        cancelBtn.innerHTML = "Cancel Request";
        acceptBtn.addEventListener('click', empAccResponse);
        cancelBtn.addEventListener('click', empCancel);

        addbtns.appendChild(editLab);
        addbtns.appendChild(acceptBtn);
        addbtns.appendChild(cancelBtn);

    }
    


}

function empAccResponse(){
   
    let reim = localStorage["Reim"];
    reim = JSON.parse(reim);    
    let input = document.getElementById('statusInput');
    let re = reim.requestId;
    let denialReason = "User accepted change to amount";

    let url = 'http://localhost:8080/TRMS/updatereqandreim'

    let xhttp = new XMLHttpRequest();

    //xhttp.onreadystatechange = confirm;

    xhttp.open('POST', url +"?reqid="+ re +"&status=Pending Approval"  + "&reimid=" + reim.id + "&denRea=" + denialReason, true);

    
    xhttp.send();

    window.location.href = "D:\\Users\\markm\\Revature_Training\\TRMS\\TRMS\\TRMS\\src\\main\\webapp\\main.html";
}
function empCancel(){
   
    let reim = localStorage["Reim"];
    reim = JSON.parse(reim);    
    let input = document.getElementById('statusInput');
    let re = reim.requestId;
    let denialReason = "User denied change to amount and canceled request";

    let url = 'http://localhost:8080/TRMS/updatereqandreim'

    let xhttp = new XMLHttpRequest();

    //xhttp.onreadystatechange = confirm;

    xhttp.open('POST', url +"?reqid="+ re +"&status=canceled"  + "&reimid=" + reim.id + "&denRea=" + denialReason, true);

    
    xhttp.send();

    window.location.href = "D:\\Users\\markm\\Revature_Training\\TRMS\\TRMS\\TRMS\\src\\main\\webapp\\main.html";
}
function submitInfo(){
    
    let reim = localStorage["Reim"];
    reim = JSON.parse(reim);
    logged = localStorage["loggedUser"];
    logged = JSON.parse(logged);
    let input = document.getElementById('statusInput');
    let re = reim.requestId;
    let denialReason = logged.firstName + " " + logged.lastName + " has submitted this info: " + input.value;

    let url = 'http://localhost:8080/TRMS/updatereqandreim'

    let xhttp = new XMLHttpRequest();

    //xhttp.onreadystatechange = confirm;

    xhttp.open('POST', url +"?reqid="+ re +"&status=Pending Approval"  + "&reimid=" + reim.id + "&denRea=" + denialReason, true);

    
    xhttp.send();

    window.location.href = "D:\\Users\\markm\\Revature_Training\\TRMS\\TRMS\\TRMS\\src\\main\\webapp\\main.html";
}
function uploadGrade(){
    let edit = localStorage["Reim"];
    let editOb = JSON.parse(edit);
    let gr = document.getElementById("gradeInput").value;
    editOb.eventGrade = gr;
    editOb.status = 'Pending Grade Approval';
    upload(editOb);
}
function uploadEdits(){
    let edit = localStorage["Reim"];
    let editOb = JSON.parse(edit);
    let aEdit = document.getElementById("ediAmt").value;
    let rEdit = document.getElementById("ediReason").value;
    editOb.amount = aEdit;
    editOb.amountExceeded = true;
    editOb.exceededReason = rEdit;
    
    upload(editOb);
}
function upload(editObject){
    
    //Getting CORS Error here. Works in POSTMAN

    console.log(editObject);

    let url = 'http://localhost:8080/TRMS/reimedit'

    let xhttp = new XMLHttpRequest();

    //xhttp.onreadystatechange = confirm;

    xhttp.open('POST', url +"?id=" + editObject.id + "&grade=" + editObject.eventGrade +"&status=" + editObject.status, true);


    //+"?id="+ editObject.id
    //xhttp.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
    xhttp.send();

    
    window.location.href = "D:\\Users\\markm\\Revature_Training\\TRMS\\TRMS\\TRMS\\src\\main\\webapp\\main.html";
}

function Back(){
    window.location.href = "D:\\Users\\markm\\Revature_Training\\TRMS\\TRMS\\TRMS\\src\\main\\webapp\\main.html";
}

function editUI(){
    let edits =document.getElementById("BenCoEdit");
    let ediAmt = document.createElement('input');
    ediAmt.id = "ediAmt";
    ediAmt.placeholder = "Edit Amount...";
    let ediReason = document.createElement('input');
    ediReason.id = "ediReason";
    ediReason.placeholder = "Reason for editing amount...";
    let btns = document.getElementById('buttons');
    let save = document.createElement('button');
    save.innerHTML = "Upload Edits";
    save.addEventListener('click', uploadEdits);
    edits.appendChild(ediAmt);
    edits.appendChild(ediReason);
    btns.appendChild(save);

}