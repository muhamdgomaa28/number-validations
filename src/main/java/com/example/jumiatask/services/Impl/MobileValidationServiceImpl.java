package com.example.jumiatask.services.Impl;

import com.example.jumiatask.models.CustomerInfoModel;
import com.example.jumiatask.models.enums.CountriesInfoEnum;
import com.example.jumiatask.models.enums.CustomerMobileStateEnum;
import com.example.jumiatask.services.MobileValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class MobileValidationServiceImpl implements MobileValidationService {
	@Override
	public CustomerInfoModel checkCustomerMobileValidity(final CustomerInfoModel customerInfoModel) {
		String appliedRegex = CountriesInfoEnum.getCountryByCode(customerInfoModel.getCountryCode()).getRegex();

		if (customerInfoModel.getFullMobileNumber().matches(appliedRegex)) {
			customerInfoModel.setState(CustomerMobileStateEnum.VALID);
		} else {
			customerInfoModel.setState(CustomerMobileStateEnum.NOT_VALID);
		}
		return customerInfoModel;
	}

}
