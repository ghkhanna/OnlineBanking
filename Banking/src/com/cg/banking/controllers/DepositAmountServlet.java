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
import com.cg.banking.exceptions.AccountBlockedException;
import com.cg.banking.exceptions.AccountNotFoundException;
import com.cg.banking.exceptions.BankingServicesDownException;
import com.cg.banking.services.BankingServices;
import com.cg.banking.services.BankingServicesImpl;

@WebServlet("/DepositAmountServlet")
public class DepositAmountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public DepositAmountServlet() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession(false);
		Account account=(Account)session.getAttribute("account");
		float amount=Float.parseFloat(request.getParameter("depositAmount"));
		BankingServices services= new BankingServicesImpl();
		try {
			if(amount<=0){
				RequestDispatcher dispatcher= request.getRequestDispatcher("LoginSuccessPage.jsp");
				request.setAttribute("redMessage", "Invalid Amount");
				dispatcher.forward(request, response);
			}
			else{
			float balance=services.depositAmount(account.getAccountNo(), amount);
			account=services.getAccountDetails(account.getAccountNo());
			session.setAttribute("account", account);
			RequestDispatcher dispatcher= request.getRequestDispatcher("LoginSuccessPage.jsp");
			request.setAttribute("Message", "Deposit Successful. Current Balance "+balance);
			dispatcher.forward(request, response);
			}
		} catch (AccountNotFoundException e) {
			System.out.println("AccountNotFoundException");
		} catch (BankingServicesDownException e) {
			System.out.println("BankingServicesDownException");
		} catch (AccountBlockedException e) {
			System.out.println("AccountBlockedException");
		}
	}

}
