package com.example.jumiatask.services.Impl;

import com.example.jumiatask.models.CustomerInfoModel;
import com.example.jumiatask.services.CountryCriteriaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CountryCriteriaServiceImpl implements CountryCriteriaService {

	@Override
	public List<CustomerInfoModel> meetCriteria(final List<CustomerInfoModel> customerInfoModels, final String countryName) {
		log.info("retrieve customer countries info for criteria  country:{}", countryName);
		return customerInfoModels
				.stream()
				.filter(customerInfoModel -> customerInfoModel.getCountry().equalsIgnoreCase(countryName))
				.collect(Collectors.toList());
	}
}
