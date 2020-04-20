package com.capg.bank.controller;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capg.bank.entity.BankBean;
import com.capg.bank.entity.History;
import com.capg.bank.service.BankServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BankRestController {

	@Autowired
	BankServiceImpl bsi;
     //http://localhost:8090/bank/create
	// POSTMAN (Post : body{ "customer_name": "Ramesh", "amount": 50000.0, "contact_number": "97000000"}
	//dont insert id ,  id is  :@GeneratedValue(strategy = GenerationType.SEQUENCE)

	@PostMapping("/bank/create")    //postman :POST
	public String createAccount(@RequestBody BankBean bean) {
		BankBean b = bsi.addAccount(bean);
		return  "Hello " + b.getCustomer_name() + "\n Your Registration is Successfull\n" + "Your Account Id is "
				+ b.getAccount_id();
	}
        //GET :  http://localhost:8090//bank/showBalance/10
	@GetMapping(value="/bank/showBalance/{id}", produces = "application/text")
	public String showBalance(@PathVariable int id) throws Exception {

		BankBean b = bsi.getBalance(id);

		if (b == null) {
			throw new Exception("Invalid id");
		}
		return "Your Account Balance is " + b.getAmount();
	}
                // Search by Account id  for all Transactions for that Account id
	@GetMapping("/bank/transactions/{id}")  //  http://localhost:8090/bank/transactions/10
	public List<History> transactions(@PathVariable int id) throws Exception {

		if (bsi.printTransactions(id) == null) {
			throw new Exception("Invalid id");
		}

		return bsi.printTransactions(id);
	}

	@GetMapping("/bank/deposit/{id}/{amount}")  //GET:   http://localhost:8090/bank/deposit/10/1000
	public String deposit(@PathVariable int id, @PathVariable double amount) throws Exception {
		BankBean b = bsi.deposit(id, amount);

		if (b == null) {
			throw new Exception("Invalid id");
		}
		return "Hello " + b.getCustomer_name() + "\n Your Amount is Deposited Succesfully\n"
				+ "Your Current Account Balance is " + b.getAmount();

	}
	    // GET :  http://localhost:8090/bank/withdraw/10/2000
	@GetMapping("/bank/withdraw/{id}/{amount}") 
	public String withdraw(@PathVariable int id, @PathVariable double amount) throws Exception {
		BankBean b = bsi.withdraw(id, amount);

		if (b == null) {
			throw new Exception("Invalid id");
		}
		return "Hello " + b.getCustomer_name() + "\n Your Amount is Withdrawn Succesfully\n"
		+ "Your Current Account Balance is " + b.getAmount();
		

	}
	//http://localhost:8090/bank/fundtransfer/10/20/500     
	@GetMapping("/bank/fundtransfer/{id1}/{id2}/{amount}")  
	public String deposit(@PathVariable int id1, @PathVariable int id2, @PathVariable double amount) throws Exception {
		BankBean b = bsi.fundTransfer(id1, id2, amount);

		if (b == null) {
			throw new Exception("Invalid id");
		}
		return "Hello " + b.getCustomer_name() + "\n Your Amount is Transfered Succesfully\n"
		+ "Your Current Account Balance is " + b.getAmount();
	}

	@GetMapping("/bank/findall")   //GET:    http://localhost:8090/bank/findall    
	public List<BankBean> getall() {

		List<BankBean> bean = bsi.getAll();
		return bean;
	}

	@ExceptionHandler(Exception.class)
	public String inValid(Exception e) {
		return e.getMessage();
	}

}
