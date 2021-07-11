package com.example.jumiatask.services;

import com.example.jumiatask.models.CustomerInfoModel;

public interface MobileValidationService {
	CustomerInfoModel checkCustomerMobileValidity(CustomerInfoModel customerInfoModel);
}
