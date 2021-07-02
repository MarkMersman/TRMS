function getLoggedUser(){

    let emp = localStorage["loggedUser"];
    emp = JSON.parse(emp);
    console.log(emp);
    
    generatePage(emp);
   
}
function logOut(){
    window.location.href= "D:\\Users\\markm\\Revature_Training\\TRMS\\TRMS\\TRMS\\src\\main\\webapp\\index.html";
}
function generatePage(emp){
   
    let wel = document.getElementById("welcome");
    wel.innerHTML = "Welcome "+ emp.firstName + " " + emp.lastName;
   
      
        //populate employee page
        //get pending reimbursements
        
        let url = 'http://localhost:8080/TRMS/empreim'

        let xhttp = new XMLHttpRequest();

        xhttp.onreadystatechange = receiveData;

        xhttp.open('POST', url +"?empID="+ emp.id , true);

       // JSON.stringify(emp)
        xhttp.send();

        function receiveData(){
        
            let tn = document.getElementById('tn1');
            
            tn.innerHTML = "Your Reimbursements";
            let dataSection = document.getElementById('pendingTable');
            dataSection.innerHTML = '';
            
            
            if(xhttp.readyState == 4){
                if(xhttp.status == 200){
                   
                    let data = xhttp.responseText;
                    let amountReq = 0;
                    data = JSON.parse(data);
                    
                    console.log(data);
                    if(data == null){
                        dataSection.innerHTML = "No pending reimbursements";
                    }
                    else{
    
                        let reimTable = document.createElement('table');
                        reimTable.id = 'reimTable';
                        reimTable.classList.add("table");
    
                        let thRow = document.createElement('tr');
                    
                        let tHeaders = ["id", "Request", "Amount", "Amount_Exceeded", "Exceeded_Reason,","Status", "Final_Grade"];
    
                        for(let h of tHeaders){
                            let th = document.createElement('th');
                            th.innerHTML = h;
                            thRow.appendChild(th);
                        }
                    
    
                        reimTable.appendChild(thRow);
    
                        for (let reim of data){
    
                            let tr = document.createElement('tr');
                            tr.addEventListener("click", function(){
                                                               
                                //localStorage["empPosition"] = emp.position;
                                //console.log(this);
                                let reim ={
                                    id:this.cells[0].innerHTML,
                                    requestId :this.cells[1].innerHTML,
                                    amount: this.cells[2].innerHTML,
                                    amountExceeded: this.cells[3].innerHTML,
                                    exceededReason: this.cells[4].innerHTML,
                                    status: this.cells[5].innerHTML,
                                    eventGrade : this.cells[6].innerHTML
                                };
                                localStorage["Reim"] = JSON.stringify(reim);
                                console.log(localStorage["Reim"]);
                                window.location.href= "D:\\Users\\markm\\Revature_Training\\TRMS\\TRMS\\TRMS\\src\\main\\webapp\\Reimbursement.html";

                            })

                            let tdID = document.createElement('td');
                            tdID.innerHTML = reim.id;
                            tdID.id = "reid";
                            tr.appendChild(tdID);
    
                            let tdReq = document.createElement('td');
                            tdReq.innerHTML = reim.requestId;
                            tr.appendChild(tdReq);
    
                            let tdAmount= document.createElement('td');
                            tdAmount.innerHTML = reim.amount;
                            amountReq = amountReq + reim.amount;
                            tr.appendChild(tdAmount);
    
                            let tdExceeded = document.createElement('td');
                            tdExceeded.innerHTML = reim.amountExceeded;
                            tr.appendChild(tdExceeded);
                        
                            let tdExReason = document.createElement('td');
                            tdExReason.innerHTML = reim.exceededReason;
                            tr.appendChild(tdExReason);

                            let tdStatus = document.createElement('td');
                            tdStatus.innerHTML = reim.status;
                            tr.appendChild(tdStatus);

                            let tdGrade = document.createElement('td');
                            tdGrade.innerHTML = reim.eventGrade;
                            tr.appendChild(tdGrade);


                             reimTable.appendChild(tr);
                            
                         }
    
                        // empTable.appendChild(thRow);
                         dataSection.appendChild(reimTable);
                    }
                    //set amount claimed label
                    let aclaim = document.getElementById("aclaimed");
                    aclaim.innerHTML = "Total amount claimed this year: " + amountReq;


                    //After getting the Reimbursemtns for the employee this will create buttons to represent the options the emp has
                    let newReq = document.createElement('button');
                    newReq.innerHTML = "New Request";
                    newReq.addEventListener("click", function(){
                           if(amountReq >= 1000){
                               let lbl = document.createElement('label');
                               lbl.innerHTML = "You can't create a new request. You've maxed out your benifits for this year.";
                               document.body.appendChild(lbl);
                           }
                           else{
                            localStorage["loggedUser"] = JSON.stringify(emp);
                            
                            window.location.href= "D:\\Users\\markm\\Revature_Training\\TRMS\\TRMS\\TRMS\\src\\main\\webapp\\NewRequest.html";
                           }
                       })
                    let btnDiv = document.getElementById("table1btn");
                    btnDiv.appendChild(newReq);


                }
            }

        }

        
        
    
    if((emp.position == 2)||(emp.position == 3)||(emp.position == 4)){
        //sup and dept head

        let url = 'http://localhost:8080/TRMS/reqpending'

        let xhttp = new XMLHttpRequest();

        xhttp.onreadystatechange = receiveData;

        xhttp.open('POST', url +"?empID="+ emp.id , true);

       // JSON.stringify(emp)
        xhttp.send();

        function receiveData(){
        
            let tn = document.getElementById('tn2');
            
            tn.innerHTML = "Pending Employee Requests";
            let dataSection = document.getElementById('reqTable');
            dataSection.innerHTML = '';
            
            
            if(xhttp.readyState == 4){
                if(xhttp.status == 200){
                   
                    let data = xhttp.responseText;
                    
                    data = JSON.parse(data);
                    
                    console.log(data);
                    if(data == null){
                        dataSection.innerHTML = "No pending requests";
                    }
                    else{
    
                        let reqTable = document.createElement('table');
                        reqTable.id = 'reqTable';
                        reqTable.classList.add("table");
    
                        let thRow = document.createElement('tr');
                    
                        let tHeaders = ["id","Employee", "Event", "Date of Request", "Supervisor Approval", "Dept. Head Approval","BenCo Approval", "Status", "Request Notes", "isUrgent", 'Last Updated'];
    
                        for(let h of tHeaders){
                            let th = document.createElement('th');
                            th.innerHTML = h;
                            thRow.appendChild(th);
                        }
                    
    
                        reqTable.appendChild(thRow);
    
                        for (let req of data){
    
                            let tr = document.createElement('tr');
                            tr.addEventListener("click", function(){
                                                               
                                //localStorage["empPosition"] = emp.position;
                                //console.log(this);
                                let req ={
                                    id:this.cells[0].innerHTML,
                                    empId : this.cells[1].innerHTML,
                                    event :this.cells[2].innerHTML,
                                    requestDate: this.cells[3].innerHTML,
                                    supApproval: this.cells[4].innerHTML,
                                    deptHeadApproval: this.cells[5].innerHTML,
                                    benCoApproval: this.cells[6].innerHTML,
                                    status : this.cells[7].innerHTML,
                                    denialReason : this.cells[8].innerHTML,
                                    isUrgent: this.cells[9].innerHTML,
                                    lastChanged: this.cells[10].innerHTML
                                };
                                localStorage["Req"] = JSON.stringify(req);
                                console.log(localStorage["Req"]);

                                let url = 'http://localhost:8080/TRMS/getevent'

                                let xhttp = new XMLHttpRequest();

                                xhttp.onreadystatechange = getEvent;

                                xhttp.open('POST', url +"?eventID="+ req.event , true);

                                 
                                xhttp.send();
                                function getEvent(){
                                    if(xhttp.readyState == 4){
                                        if(xhttp.status == 200){
                                         let eventData = xhttp.responseText;
                                         localStorage["Event"] = eventData;
                                         console.log(localStorage["Event"]);
                                        }
                                    } 
                                }

                                  

                                let url1 = 'http://localhost:8080/TRMS/getreqemp'

                                let xhttp1 = new XMLHttpRequest();

                                xhttp1.onreadystatechange = getReqEmp;

                                xhttp1.open('POST', url1 +"?empID="+ req.empId, true);

                                 
                                xhttp1.send();
                                function getReqEmp(){
                                    if(xhttp1.readyState == 4){
                                        if(xhttp1.status == 200){
                                         let reqEmpData = xhttp1.responseText;    
                                         //console.log(reqEmpData);                                     
                                         localStorage["ReqEmp"] = reqEmpData;
                                         console.log(localStorage["ReqEmp"]);   
                                        }
                                    } 
                                }

                                let url2 = 'http://localhost:8080/TRMS/getreqreim'

                                let xhttp2 = new XMLHttpRequest();

                                xhttp2.onreadystatechange = getReqReim;

                                xhttp2.open('POST', url2 +"?reqID=" + req.id, true);

                                 
                                xhttp2.send();
                                function getReqReim(){
                                    if(xhttp2.readyState == 4){
                                        if(xhttp2.status == 200){
                                         let reqReimData = xhttp2.responseText;    
                                                                             
                                         localStorage["Reimbursement"] = reqReimData;
                                         console.log(localStorage["Reimbursement"])  ; 
                                         window.location.href= "D:\\Users\\markm\\Revature_Training\\TRMS\\TRMS\\TRMS\\src\\main\\webapp\\Request.html";
                                        }
                                    } 
                                }
                                 
                               

                            })

                            let tdID = document.createElement('td');
                            tdID.innerHTML = req.id;                            
                            tr.appendChild(tdID);
    
                            let tdEmp = document.createElement('td');
                            tdEmp.innerHTML = req.empId;
                            tr.appendChild(tdEmp);
    
                            let tdEvent= document.createElement('td');
                            tdEvent.innerHTML = req.event;                            
                            tr.appendChild(tdEvent);
    
                            let tdDate = document.createElement('td');
                            tdDate.innerHTML = req.requestDate;
                            tr.appendChild(tdDate);
                        
                            let tdSup = document.createElement('td');
                            tdSup.innerHTML = req.supApproval;
                            tr.appendChild(tdSup);

                            let tdHead = document.createElement('td');
                            tdHead.innerHTML = req.deptHeadApproval;
                            tr.appendChild(tdHead);

                            let tdBenCo = document.createElement('td');
                            tdBenCo.innerHTML = req.benCoApproval;
                            tr.appendChild(tdBenCo);

                            let tdStatus = document.createElement('td');
                            tdStatus.innerHTML = req.status;
                            tr.appendChild(tdStatus);

                            let tdReason = document.createElement('td');
                            tdReason.innerHTML = req.denialReason;
                            tr.appendChild(tdReason);

                            let tdUrgent = document.createElement('td');
                            tdUrgent.innerHTML = req.isUrgent;
                            if (tdUrgent.innerHTML == 'true'){
                                tr.style.backgroundColor = 'crimson';
                            }
                            tr.appendChild(tdUrgent);

                            let tdChanged = document.createElement('td');
                            tdChanged.innerHTML = req.lastChanged;
                            tr.appendChild(tdChanged);


                             reqTable.appendChild(tr);
                            
                         }
    
                        // empTable.appendChild(thRow);
                         dataSection.appendChild(reqTable);
                    }
                    

                    //After getting the Reimbursemtns for the employee this will create buttons to represent the options the emp has
                    // let newReq = document.createElement('button');
                    // newReq.innerHTML = "New Request";
                    // newReq.addEventListener("click", function(){
                    //        if(amountReq >= 1000){
                    //            let lbl = document.createElement('label');
                    //            lbl.innerHTML = "You can't create a new request. You've maxed out your benifits for this year.";
                    //            document.body.appendChild(lbl);
                    //        }
                    //        else{
                    //         localStorage["loggedUser"] = JSON.stringify(emp);
                            
                    //         window.location.href= "D:\\Users\\markm\\Revature_Training\\TRMS\\TRMS\\TRMS\\src\\main\\webapp\\NewRequest.html";
                    //        }
                    //    })
                    // document.body.appendChild(newReq);


                }
            }

        }



    }
    else if(emp.position == 4){
        //depthead
    }
    

    
}