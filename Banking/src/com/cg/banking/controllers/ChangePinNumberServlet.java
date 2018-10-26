package com.cg.banking.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cg.banking.beans.Account;
import com.cg.banking.exceptions.AccountNotFoundException;
import com.cg.banking.exceptions.BankingServicesDownException;
import com.cg.banking.services.BankingServices;
import com.cg.banking.services.BankingServicesImpl;

@WebServlet("/ChangePinNumberServlet")
public class ChangePinNumberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ChangePinNumberServlet() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Account account= (Account) session.getAttribute("account");
		int oldPin=Integer.parseInt(request.getParameter("oldPinNumber")),newPin=Integer.parseInt(request.getParameter("newPinNumber"));
		if(oldPin==account.getPinNumber()){
			BankingServices services=new BankingServicesImpl();
			try {
				session.setAttribute("account", services.change(account.getAccountNo(), newPin));
				RequestDispatcher dispatcher= request.getRequestDispatcher("LoginSuccessPage.jsp");
				request.setAttribute("Message", "Pin Successfully Updated.");
				dispatcher.forward(request, response);
				
			} catch (AccountNotFoundException | BankingServicesDownException e) {
				System.out.println("AccountNotFoundException | BankingServicesDownException");
			}
		}
		else{
			//session.setAttribute("account", account);
			RequestDispatcher dispatcher= request.getRequestDispatcher("GenerateNewPinNumberPage.jsp");
			request.setAttribute("IncorrectPin", "Incorrect Pin");
			dispatcher.forward(request, response);
		}
	}

}
