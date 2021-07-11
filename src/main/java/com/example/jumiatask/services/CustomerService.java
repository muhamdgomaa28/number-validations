package com.example.jumiatask.services;

import com.example.jumiatask.models.CustomerInfoModel;
import com.example.jumiatask.models.CustomerSearchCriteriaModel;

import java.util.List;

public interface CustomerService {

	List<CustomerInfoModel> retrieveCustomerInfoModel(CustomerSearchCriteriaModel customerSearchCriteriaModel);
}
