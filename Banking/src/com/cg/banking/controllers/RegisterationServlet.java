package com.cg.banking.controllers;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cg.banking.beans.Account;
import com.cg.banking.daoservices.AccountDAO;
import com.cg.banking.daoservices.AccountDAOImpl;
import com.cg.banking.exceptions.AccountNotFoundException;
import com.cg.banking.exceptions.BankingServicesDownException;
import com.cg.banking.exceptions.InvalidAccountTypeException;
import com.cg.banking.exceptions.InvalidAmountException;
import com.cg.banking.services.BankingServices;
import com.cg.banking.services.BankingServicesImpl;
@WebServlet("/RegisterationServlet")
public class RegisterationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountType= request.getParameter("accountType");
		float accountBalance=Float.parseFloat(request.getParameter("initialAccountBalance"));
		Account account=new Account(accountType, accountBalance);
		BankingServices services = new BankingServicesImpl();
		try {
			if(accountBalance<0){
				RequestDispatcher dispatcher= request.getRequestDispatcher("RegisterationPage.jsp");
				request.setAttribute("invalidAmount", "Invalid Amount");
				dispatcher.forward(request, response);
			}
			else{
				long accountNumber= services.openAccount(accountType, accountBalance);
				account=services.getAccountDetails(accountNumber);
				RequestDispatcher dispatcher= request.getRequestDispatcher("RegisterationSuccessPage.jsp");
				request.setAttribute("account", account);
				dispatcher.forward(request, response);
			}
			
		} catch (InvalidAmountException | InvalidAccountTypeException| BankingServicesDownException e) {
			e.printStackTrace();
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}