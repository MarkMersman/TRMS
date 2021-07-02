package servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import beans.Employee;
import beans.Event;
import beans.Reimbursement;
import beans.Request;
import beans.Reimbursement;
import services.EmployeeServiceImpl;
import services.EventServiceImpl;
import services.ReimbursementServiceImpl;
import services.RequestServiceImpl;
import utils.AppLogger;

public class FrontControllerServlet extends HttpServlet{

	ReimbursementServiceImpl rs = new ReimbursementServiceImpl();
	EmployeeServiceImpl es = new EmployeeServiceImpl();
	EventServiceImpl evs =  new EventServiceImpl();
	RequestServiceImpl reqs = new RequestServiceImpl();
	public Gson gson = new Gson();
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
				
		String uri = request.getRequestURI();
		
		System.out.println(uri);
				
		HttpSession session = request.getSession();
				
		session.setMaxInactiveInterval(0);
		
		switch(uri) {
			
			case "/TRMS":{
				
				break;
			}
			case "/TRMS/loggedUser":{
				response.setHeader("Access-Control-Allow-Origin","*");
				//System.out.println(session.getAttribute("LoggedUser"));
				//System.out.println(gson.toJson(session.getAttribute("LoggedUser")));
				response.getWriter().append(gson.toJson(session.getAttribute("LoggedUser")));
				break;
			}
			case "/TRMS/login":{
				
				response.setHeader("Access-Control-Allow-Origin","*");
				//response.sendRedirect("/TRMS/EmpServlet/Login?un=" + request.getParameter("un")+"&pw="+request.getParameter("pw"));
				String un = request.getParameter("un");
				String pw = request.getParameter("pw");
				//System.out.println(un + " " + pw);
				Employee emp = es.getByUserName(un);
				
				if((emp != null) && (emp.getPass().equals(pw))) {
					//response.getWriter().append("success");
					//session.setAttribute("LoggedUser", gson.toJson(emp, Employee.class));
					AppLogger.logger.info(un+" Logged in.");
					response.getWriter().append(gson.toJson(emp, Employee.class));
				}
				else {
					AppLogger.logger.info(un+" Failed to log in.");
					response.getWriter().append("fail");
					
				}									
				
				break;
			}
			case "/TRMS/empreim":{
				response.setHeader("Access-Control-Allow-Origin","*");
				String emp = request.getParameter("empID");
				System.out.println(emp);
				Integer empId = Integer.parseInt(emp);
				
				List<Reimbursement> reimList = rs.getPending(empId);
				//Reimbursement r = reimList.get(0);
				
				response.getWriter().append(gson.toJson(reimList));
				
				
				break;
			}
			case "/TRMS/reqpending":{
				response.setHeader("Access-Control-Allow-Origin","*");
				String emp = request.getParameter("empID");
				Integer empId = Integer.parseInt(emp);
				//System.out.println(emp+ " 1");
				List<Request> reqList = reqs.needApproval(empId);
				Date today = new Date(System.currentTimeMillis());
				
				//for(Request r : reqList) {
				//	Calendar c = Calendar.getInstance();
				//	c.setTime(r.getLastChanged());
				//	c.add(Calendar.DATE, 1);
				//	Date plusOne = new Date(c.getTimeInMillis());
				//	if(plusOne.compareTo(today) < 0) {
					//	if((!r.isDeptHeadApproval())&&(r.isSupApproval())){
						//	r.setDeptHeadApproval(true);
						//	r.setLastChanged(today);
							
					//	}
					//	else if(!r.isSupApproval()) {
					//		r.setSupApproval(true);
					//		r.setLastChanged(today);
							
					//	}
						
					//	reqs.update(r);
					//	reqList.remove(r);
					//}
					
			//	}
				
				
				List<Request> list = reqList;
				
				response.getWriter().append(gson.toJson(list));
				break;
			}
			case "/TRMS/reimedit":{
				//response.setHeader("Access-Control-Allow-Origin","*");
				String re = request.getParameter("id");
				String grade = request.getParameter("grade");
				String status = request.getParameter("status");
				Integer reimId = Integer.parseInt(re);
				Reimbursement reim = rs.getById(reimId);
				Request req = reqs.getById(reim.getRequestId());
				reim.setStatus(status);
				reim.setEventGrade(grade);
				req.setStatus(status);				
				rs.update(reim);
				reqs.update(req);
				
				break;
			}
			case "/TRMS/newreq":{
				
				Time t = Time.valueOf(request.getParameter("time")+":00"); //prints 14:49
				Date d =  Date.valueOf(request.getParameter("date"));	//2021-06-30			
				Event e = new Event();
				e.setId(1);
				e.setEventType(request.getParameter("type"));
				e.setDescription(request.getParameter("desc"));
				e.setLocation(request.getParameter("loc"));
				e.setEventTime(t);
				e.setStartDate(d);
				e.setGradeFormat(Integer.parseInt(request.getParameter("form")));
				e.setCost(Double.parseDouble(request.getParameter("cost")));
				e.setTimeMissed(Double.parseDouble(request.getParameter("missed")));
				e.setpGrade(request.getParameter("pGrade")); 
				System.out.println(e);
				evs.add(e);
				int evId = evs.getLast();
				int empId = Integer.parseInt(request.getParameter("id"));
				reqs.add(empId,evId);
				int reqId = reqs.getLast();
				rs.add(reqId);
				AppLogger.logger.info("Created a new Request with id: "+reqId);
				break;
			}
			case "/TRMS/updatereq":{
				String req = request.getParameter("id");
				String status = request.getParameter("status");
				String appr = request.getParameter("app");
				String position = request.getParameter("pos");
				Integer reqId = Integer.parseInt(req);
				Integer posId = Integer.parseInt(position);
				Boolean approval = Boolean.parseBoolean(appr);
				Request reqFromDB = reqs.getById(reqId);
				reqFromDB.setStatus(status);
				if(posId == 2) {
					reqFromDB.setSupApproval(approval);
				}
				else if(posId == 3) {
					reqFromDB.setDeptHeadApproval(approval);
				}
				else if(posId == 4) {
					reqFromDB.setBenCoApproval(approval);
					if(reqFromDB.isBenCoApproval()) {
						Reimbursement reiFromDB = rs.getByReq(reqFromDB.getId());
						reiFromDB.setStatus("Pending Grade");
						rs.update(reiFromDB);
					}
				}
				reqs.update(reqFromDB);
				
				if(reqFromDB.getStatus().equals("denied")) {
					Reimbursement reimFromDB = rs.getByReq(reqFromDB.getId());
					reimFromDB.setStatus("denied");
					rs.update(reimFromDB);
				}
				
				
				break;
			}
			case "/TRMS/updatereqandreim":{
				String req = request.getParameter("reqid");
				String reim = request.getParameter("reimid");
				String status = request.getParameter("status");
				String reason = request.getParameter("denRea");
				Integer reqID = Integer.parseInt(req);
				Integer reimId = Integer.parseInt(reim);
				Request reqFromDB = reqs.getById(reqID);
				Reimbursement reimFromDB = rs.getById(reimId);
				
				reqFromDB.setStatus(status);
				reqFromDB.setDenialReason(reason);
				reimFromDB.setStatus(status);
				System.out.println(reqFromDB);
				System.out.println(reimFromDB);
				reqs.update(reqFromDB);
				rs.update(reimFromDB);
				
				break;
			}
			case "/TRMS/editreim":{
				String req = request.getParameter("reqid");
				String reim = request.getParameter("reimid");
				String status = request.getParameter("status");
				String reason = request.getParameter("exReason");
				String amount = request.getParameter("amount");
				String isEx = request.getParameter("isExceeded");
				Integer reqID = Integer.parseInt(req);
				Integer reimId = Integer.parseInt(reim);
				Double amt = Double.parseDouble(amount);
				Boolean isExceeded = Boolean.parseBoolean(isEx);
				
				Request reqFromDB = reqs.getById(reqID);
				Reimbursement reimFromDB = rs.getById(reimId);
				
				reqFromDB.setStatus(status);
				reimFromDB.setExceededReason(reason);
				reimFromDB.setStatus(status);
				reimFromDB.setAmount(amt);
				reimFromDB.setAmountExceeded(isExceeded);
				
				System.out.println(reqFromDB);
				System.out.println(reimFromDB);
				reqs.update(reqFromDB);
				rs.update(reimFromDB);
				
				break;
			}
			case "/TRMS/getreqemp" :{
				response.setHeader("Access-Control-Allow-Origin","*");
				String reqEmp = request.getParameter("empID");
				Integer empId = Integer.parseInt(reqEmp);
				
				Employee employee = es.getById(empId);
				
				response.getWriter().append(gson.toJson(employee));
				break;
			}
			case "/TRMS/getreqreim":{
				response.setHeader("Access-Control-Allow-Origin","*");
				
				String req = request.getParameter("reqID");
				Integer reqId = Integer.parseInt(req);
				
				Reimbursement reim = rs.getByReq(reqId);
				
				response.getWriter().append(gson.toJson(reim));
				
				
				break;
			}
			case "/TRMS/getevent":{
				response.setHeader("Access-Control-Allow-Origin","*");
				String event = request.getParameter("eventID");
				Integer eventId = Integer.parseInt(event);
				
				Event ev = evs.getbyId(eventId);
				
				response.getWriter().append(gson.toJson(ev));
				break;
			}
			default:{
				response.getWriter().write("default");
				break;
			}
		}
		
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request,response);
	}
}
