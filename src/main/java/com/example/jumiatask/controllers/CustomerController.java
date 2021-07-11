package com.example.jumiatask.controllers;

import com.example.jumiatask.models.CustomerInfoModel;
import com.example.jumiatask.models.CustomerSearchCriteriaModel;
import com.example.jumiatask.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/info")
	public List<CustomerInfoModel> retrieveCustomersCountriesInfo(final CustomerSearchCriteriaModel customerSearchCriteriaModel) {
		return customerService.retrieveCustomerInfoModel(customerSearchCriteriaModel);
	}
}
