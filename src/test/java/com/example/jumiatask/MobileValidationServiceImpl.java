package com.example.jumiatask;

import com.example.jumiatask.models.CustomerInfoModel;
import com.example.jumiatask.models.enums.CountriesInfoEnum;
import com.example.jumiatask.models.enums.CustomerMobileStateEnum;
import com.example.jumiatask.services.MobileValidationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MobileValidationServiceImpl {

    @Autowired
    private MobileValidationService mobileValidationService;


    @DisplayName("applied Customer Info With Valid Mobile Should Return Valid State")
    @Test
    void appliedCustomerInfoWithValidMobileShouldReturnValidState() {
        CustomerInfoModel customerInfoModel = CreateCustomerInfoModel(1L,CountriesInfoEnum.CAMEROON.getCountryCode(),CountriesInfoEnum.CAMEROON.getCountry(),"26985476", "(237) 26985476");

        CustomerInfoModel customerInfoModelList = mobileValidationService.checkCustomerMobileValidity(customerInfoModel);

        assertEquals(customerInfoModelList.getState(), CustomerMobileStateEnum.VALID);

    }

    @DisplayName("applied Customer Info With Not Valid Mobile Should Return Not Valid State")
    @Test
    void appliedCustomerInfoWithNotValidMobileShouldReturnNotValidState() {
        CustomerInfoModel customerInfoModel = CreateCustomerInfoModel(1L,CountriesInfoEnum.CAMEROON.getCountryCode(),CountriesInfoEnum.CAMEROON.getCountry(),"26985476", "(237) 269854761212");

        CustomerInfoModel customerInfoModelList = mobileValidationService.checkCustomerMobileValidity(customerInfoModel);

        assertEquals(customerInfoModelList.getState(), CustomerMobileStateEnum.NOT_VALID);

    }

    private CustomerInfoModel CreateCustomerInfoModel(long id, String countryCode,String country, String mobile, String fullMobileNumber) {
        return CustomerInfoModel
                .builder()
                .id(id)
                .mobileNumber(mobile)
                .fullMobileNumber(fullMobileNumber)
                .countryCode(countryCode)
                .country(country)
                .build();


    }


}
