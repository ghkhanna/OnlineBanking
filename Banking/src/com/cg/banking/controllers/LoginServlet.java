package com.cg.banking.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cg.banking.beans.Account;
import com.cg.banking.daoservices.AccountDAO;
import com.cg.banking.daoservices.AccountDAOImpl;
import com.cg.banking.exceptions.AccountNotFoundException;
import com.cg.banking.exceptions.BankingServicesDownException;
import com.cg.banking.services.BankingServices;
import com.cg.banking.services.BankingServicesImpl;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public LoginServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
	}

	public void destroy() {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int accountNo=Integer.parseInt(request.getParameter("accountNo"));
		int pinNumber=Integer.parseInt(request.getParameter("pinNumber"));
		BankingServices services = new BankingServicesImpl();
		Account account;
		try {
			account = services.getAccountDetails(accountNo);		
			if(account.getPinNumber()== pinNumber){
				HttpSession session = request.getSession();
				session.setAttribute("account", account);
				RequestDispatcher dispatcher= request.getRequestDispatcher("LoginSuccessPage.jsp");
				dispatcher.forward(request, response);
			}
			else{
				RequestDispatcher dispatcher= request.getRequestDispatcher("HomePage.jsp");
				request.setAttribute("errorMessage", "Invalid Pin Number");
				dispatcher.forward(request, response);
			}

		} catch (AccountNotFoundException | BankingServicesDownException e) {
			e.printStackTrace();
			RequestDispatcher dispatcher= request.getRequestDispatcher("HomePage.jsp");
			request.setAttribute("errorMessage", e.getMessage());
			dispatcher.forward(request, response);
		}
	}
}

