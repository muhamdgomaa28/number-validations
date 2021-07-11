package com.example.jumiatask.services.Impl;

import com.example.jumiatask.models.CustomerInfoModel;
import com.example.jumiatask.services.StateCriteriaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StateCriteriaServiceImpl implements StateCriteriaService {

	@Override
	public List<CustomerInfoModel> meetCriteria(final List<CustomerInfoModel> customerInfoModels, final String state) {
		log.info("retrieve customer countries info for criteria state:{}", state);
		return customerInfoModels
				.stream()
				.filter(customerInfoModel -> customerInfoModel.getState().name().equals(state))
				.collect(Collectors.toList());
	}

}
