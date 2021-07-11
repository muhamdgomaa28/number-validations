package com.example.jumiatask.models;

import com.example.jumiatask.models.enums.CustomerMobileStateEnum;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustomerInfoModel {

	private long id;
	private String country;
	private String countryCode;
	private CustomerMobileStateEnum state;
	private String mobileNumber;
	private String fullMobileNumber;

}
