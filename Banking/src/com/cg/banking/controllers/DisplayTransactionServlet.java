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

@WebServlet("/DisplayTransactionServlet")
public class DisplayTransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public DisplayTransactionServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Account account= (Account) session.getAttribute("account");
		BankingServices services = new BankingServicesImpl();
		try {
			services.getAccountAllTransaction(account.getAccountNo());
		} catch (BankingServicesDownException | AccountNotFoundException e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher= request.getRequestDispatcher("DisplayTransaction.jsp");
		dispatcher.forward(request, response);
	}

}
