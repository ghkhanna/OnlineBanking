package com.cg.banking.controllers;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cg.banking.beans.Account;
import com.cg.banking.beans.Transaction;
import com.cg.banking.daoservices.AccountDAO;
import com.cg.banking.daoservices.AccountDAOImpl;
import com.cg.banking.exceptions.AccountBlockedException;
import com.cg.banking.exceptions.AccountNotFoundException;
import com.cg.banking.exceptions.BankingServicesDownException;
import com.cg.banking.exceptions.InsufficientAmountException;
import com.cg.banking.services.BankingServices;
import com.cg.banking.services.BankingServicesImpl;
@WebServlet("/FundTransferServlet")
public class FundTransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FundTransferServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
	}
	public void destroy() {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession(false);
		Account account= (Account)session.getAttribute("account");
		BankingServices services=new BankingServicesImpl();
		int transferAccountNumber=Integer.parseInt(request.getParameter("transferAccountNumber"));		
		float transferAmount=Float.parseFloat(request.getParameter("transferAmount")) ;	
		try {
			Account account2 = services.getAccountDetails(transferAccountNumber);
			if (account2==null){
				RequestDispatcher dispatcher= request.getRequestDispatcher("FundTransferDetailsPage.jsp");
				request.setAttribute("transferErrorMessage", "Account does not exist.");
				dispatcher.forward(request, response);
			}
			else if(account.getAccountNo()==transferAccountNumber){
				RequestDispatcher dispatcher= request.getRequestDispatcher("FundTransferDetailsPage.jsp");
				request.setAttribute("transferErrorMessage", "Invalid request.");
				dispatcher.forward(request, response);
			}
			else if(transferAmount<=0){
				RequestDispatcher dispatcher= request.getRequestDispatcher("FundTransferDetailsPage.jsp");
				request.setAttribute("transferErrorMessage", "Invalid Amount.");
				dispatcher.forward(request, response);
			}

			else if(account.getAccountBalance()<transferAmount){
				RequestDispatcher dispatcher= request.getRequestDispatcher("FundTransferDetailsPage.jsp");
				request.setAttribute("transferErrorMessage", "Insufficient Balance.");
				dispatcher.forward(request, response);
			}
			else{
				services.fundTransfer( account2.getAccountNo(),account.getAccountNo(), transferAmount);
				account=services.getAccountDetails(account.getAccountNo());
				session.setAttribute("account", account);
				RequestDispatcher dispatcher= request.getRequestDispatcher("LoginSuccessPage.jsp");
				request.setAttribute("Message", "Fund transfer Successful. Current Balance "+account.getAccountBalance());
				dispatcher.forward(request, response);	
			}

		} catch (InsufficientAmountException | AccountNotFoundException
				| BankingServicesDownException | AccountBlockedException e) {
			e.printStackTrace();
		}		

	}

}
