package com.example.jumiatask.services;

import com.example.jumiatask.models.CustomerInfoModel;

import java.util.List;

public interface AndCriteriaService {

	List<CustomerInfoModel> meetCriteria(List<CustomerInfoModel> customerInfoModels, String customerMobileState, String country);

	}
