package com.example.jumiatask.services.Impl;

import com.example.jumiatask.models.CustomerInfoModel;
import com.example.jumiatask.services.AndCriteriaService;
import com.example.jumiatask.services.CountryCriteriaService;
import com.example.jumiatask.services.StateCriteriaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AndCriteriaServiceImpl implements AndCriteriaService {

	@Autowired private StateCriteriaService stateCriteria;
	@Autowired private CountryCriteriaService countryCriteria;


	@Override
	public List<CustomerInfoModel> meetCriteria(final List<CustomerInfoModel> customerInfoModels, final String state, final String country) {
		log.info("build (and) criteria for country:{} state:{}", country, state);
		List<CustomerInfoModel> stateCriteriaInfoModels = stateCriteria.meetCriteria(customerInfoModels, state);
		return countryCriteria.meetCriteria(stateCriteriaInfoModels, country);
	}
}
