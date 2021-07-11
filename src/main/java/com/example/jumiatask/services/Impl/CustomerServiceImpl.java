package com.example.jumiatask.services.Impl;

import com.example.jumiatask.models.CustomerInfoModel;
import com.example.jumiatask.models.CustomerSearchCriteriaModel;
import com.example.jumiatask.repositories.CustomerRepository;
import com.example.jumiatask.services.*;
import com.example.jumiatask.utilities.CheckEmptyUtility;
import com.example.jumiatask.utilities.CountriesInfoExtractorUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	@Autowired private CustomerRepository customerRepository;

	@Autowired private MobileValidationService mobileValidationService;

	@Autowired private StateCriteriaService stateCriteriaService;

	@Autowired private CountryCriteriaService countryCriteriaService;

	@Autowired private AndCriteriaService andCriteriaService;

	@Override
	public List<CustomerInfoModel> retrieveCustomerInfoModel(final CustomerSearchCriteriaModel customerSearchCriteriaModel) {
		log.info("get customer info model criteria :{}", customerSearchCriteriaModel);

		List<CustomerInfoModel> customerInfoModels = customerRepository.findAll()
				.stream()
				.map(customer -> CountriesInfoExtractorUtility.extractCountriesInfo(customer))
				.map(customerInfoModel -> checkCustomerMobileValidity(customerInfoModel))
				.collect(Collectors.toList());
		return buildFilteredCustomerInfoModels(customerSearchCriteriaModel, customerInfoModels);
	}


	private List<CustomerInfoModel> buildFilteredCustomerInfoModels(final CustomerSearchCriteriaModel customerSearchCriteriaModel, final List<CustomerInfoModel> customerInfoModels) {
		if (CheckEmptyUtility.isNotEmpty(customerSearchCriteriaModel.getCustomerMobileState()) && CheckEmptyUtility.isNotEmpty(customerSearchCriteriaModel.getCountry())) {
			return andCriteriaService.meetCriteria(customerInfoModels, customerSearchCriteriaModel.getCustomerMobileState(), customerSearchCriteriaModel.getCountry());
		} else if (CheckEmptyUtility.isNotEmpty(customerSearchCriteriaModel.getCountry())) {
			return  countryCriteriaService.meetCriteria(customerInfoModels, customerSearchCriteriaModel.getCountry());
		} else if (CheckEmptyUtility.isNotEmpty(customerSearchCriteriaModel.getCustomerMobileState())) {
			return stateCriteriaService.meetCriteria(customerInfoModels, customerSearchCriteriaModel.getCustomerMobileState());
		}
		return customerInfoModels;
	}

	private CustomerInfoModel checkCustomerMobileValidity(final CustomerInfoModel customerInfoModel) {
		return mobileValidationService.checkCustomerMobileValidity(customerInfoModel);
	}

}
