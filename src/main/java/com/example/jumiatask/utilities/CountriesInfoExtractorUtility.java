package com.example.jumiatask.utilities;

import com.example.jumiatask.entities.Customer;
import com.example.jumiatask.models.CustomerInfoModel;
import com.example.jumiatask.models.enums.CountriesInfoEnum;

public class CountriesInfoExtractorUtility {


	public  static CustomerInfoModel extractCountriesInfo(final Customer customer) {
		String[] spitedCustomerMobile = customer.getPhone().split("[\\(\\)]");
		String countryCode = spitedCustomerMobile[1];
		String mobileNumber = spitedCustomerMobile[2].trim();
		String country = CountriesInfoEnum.getCountryByCode(countryCode).getCountry();

		return buildCustomerCountriesInfoModel(customer, countryCode, mobileNumber, country);
	}

	private static CustomerInfoModel buildCustomerCountriesInfoModel(final Customer customer, final String countryCode, final String mobileNumber, final String country) {
		CustomerInfoModel customerInfoModel = CustomerInfoModel.builder()
				.id(customer.getId())
				.country(country)
				.countryCode(countryCode)
				.fullMobileNumber(customer.getPhone())
				.mobileNumber(mobileNumber)
				.build();
		return customerInfoModel;
	}
}
