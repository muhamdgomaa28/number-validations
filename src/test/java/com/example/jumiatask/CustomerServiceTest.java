package com.example.jumiatask;

import com.example.jumiatask.entities.Customer;
import com.example.jumiatask.models.CustomerInfoModel;
import com.example.jumiatask.models.CustomerSearchCriteriaModel;
import com.example.jumiatask.models.enums.CountriesInfoEnum;
import com.example.jumiatask.models.enums.CustomerMobileStateEnum;
import com.example.jumiatask.repositories.CustomerRepository;
import com.example.jumiatask.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerServiceTest {

    @MockBean
    private CustomerRepository customerRepository;
    @Autowired private CustomerService customerService;

    @BeforeEach
    void prepareMocksOutputs() {
        when(customerRepository.findAll()).thenReturn(buildCustomerModels());
    }

    @DisplayName("retrieve Customer Info Model WithOut Search Criteria Should Return Valid Data")
    @Test
    void retrieveCustomerInfoModelWithOutSearchCriteriaShouldReturnValidData() {

        CustomerSearchCriteriaModel customerSearchCriteriaModel = buildCustomerSearchCriteriaModel(null, null);
        List<CustomerInfoModel> customerInfoModelList = customerService.retrieveCustomerInfoModel(customerSearchCriteriaModel);

        assertEquals(customerInfoModelList.size(), 3);

        CustomerInfoModel customerInfoModel1 = CreateCustomerInfoModel(1,CountriesInfoEnum.MOROCCO.getCountryCode(),CountriesInfoEnum.MOROCCO.getCountry(), "6007989253","(212) 6007989253", CustomerMobileStateEnum.NOT_VALID);
        CustomerInfoModel customerInfoModel2 = CreateCustomerInfoModel(2,CountriesInfoEnum.ETHIOPIA.getCountryCode(),CountriesInfoEnum.ETHIOPIA.getCountry(), "988200000","(251) 988200000", CustomerMobileStateEnum.VALID);
        CustomerInfoModel customerInfoModel3 = CreateCustomerInfoModel(3,CountriesInfoEnum.CAMEROON.getCountryCode(),CountriesInfoEnum.CAMEROON.getCountry(), "697151594","(237) 697151594", CustomerMobileStateEnum.VALID);

        Iterable<CustomerInfoModel> customerInfosModels = new ArrayList<>(Arrays.asList(customerInfoModel1, customerInfoModel2,customerInfoModel3));
        Iterable<CustomerInfoModel> resultInfoModel = new ArrayList<>(customerInfoModelList);

        assertIterableEquals(customerInfosModels, resultInfoModel);
    }

    @DisplayName("retrieve Customer Info Model With Search Criteria State valid Should Return Valid Data")
    @Test
    void retrieveCustomerInfoModelWithSearchCriteriaValidStateShouldReturnValidData() {

        CustomerSearchCriteriaModel customerSearchCriteriaModel = buildCustomerSearchCriteriaModel(null, CustomerMobileStateEnum.VALID.name());
        List<CustomerInfoModel> customerInfoModelList = customerService.retrieveCustomerInfoModel(customerSearchCriteriaModel);

        assertEquals(customerInfoModelList.size(), 2);

        CustomerInfoModel customerInfoModel2 = CreateCustomerInfoModel(2,CountriesInfoEnum.ETHIOPIA.getCountryCode(),CountriesInfoEnum.ETHIOPIA.getCountry(), "988200000","(251) 988200000", CustomerMobileStateEnum.VALID);
        CustomerInfoModel customerInfoModel3 = CreateCustomerInfoModel(3,CountriesInfoEnum.CAMEROON.getCountryCode(),CountriesInfoEnum.CAMEROON.getCountry(), "697151594","(237) 697151594", CustomerMobileStateEnum.VALID);

        Iterable<CustomerInfoModel> customerInfosModels = new ArrayList<>(Arrays.asList(customerInfoModel2,customerInfoModel3));
        Iterable<CustomerInfoModel> resultInfoModel = new ArrayList<>(customerInfoModelList);

        assertIterableEquals(customerInfosModels, resultInfoModel);
    }

    @DisplayName("retrieve Customer Info Model With Search Criteria State Not valid Should Return Valid Data")
    @Test
    void retrieveCustomerInfoModelWithSearchCriteriaNotValidStateShouldReturnValidData() {

        CustomerSearchCriteriaModel customerSearchCriteriaModel = buildCustomerSearchCriteriaModel(null, CustomerMobileStateEnum.NOT_VALID.name());
        List<CustomerInfoModel> customerInfoModelList = customerService.retrieveCustomerInfoModel(customerSearchCriteriaModel);

        assertEquals(customerInfoModelList.size(), 1);

        CustomerInfoModel customerInfoModel1 = CreateCustomerInfoModel(1,CountriesInfoEnum.MOROCCO.getCountryCode(),CountriesInfoEnum.MOROCCO.getCountry(), "6007989253","(212) 6007989253", CustomerMobileStateEnum.NOT_VALID);

        Iterable<CustomerInfoModel> customerInfosModels = new ArrayList<>(Arrays.asList(customerInfoModel1));
        Iterable<CustomerInfoModel> resultInfoModel = new ArrayList<>(customerInfoModelList);

        assertIterableEquals(customerInfosModels, resultInfoModel);
    }

    @DisplayName("retrieve Customer Info Model With Search Criteria Country Should Return Valid Data")
    @Test
    void retrieveCustomerInfoModelWithSearchCriteriaCountryShouldReturnValidData() {

        CustomerSearchCriteriaModel customerSearchCriteriaModel = buildCustomerSearchCriteriaModel("Morocco", null);
        List<CustomerInfoModel> customerInfoModelList = customerService.retrieveCustomerInfoModel(customerSearchCriteriaModel);

        assertEquals(customerInfoModelList.size(), 1);

        CustomerInfoModel customerInfoModel1 = CreateCustomerInfoModel(1,CountriesInfoEnum.MOROCCO.getCountryCode(),CountriesInfoEnum.MOROCCO.getCountry(), "6007989253","(212) 6007989253", CustomerMobileStateEnum.NOT_VALID);

        Iterable<CustomerInfoModel> customerInfosModels = new ArrayList<>(Arrays.asList(customerInfoModel1));
        Iterable<CustomerInfoModel> resultInfoModel = new ArrayList<>(customerInfoModelList);

        assertIterableEquals(customerInfosModels, resultInfoModel);
    }

    @DisplayName("retrieve Customer Info Model With Search Criteria Country and state Should Return Valid Data")
    @Test
    void retrieveCustomerInfoModelWithSearchCriteriaCountryAndStateShouldReturnValidData() {

        CustomerSearchCriteriaModel customerSearchCriteriaModel = buildCustomerSearchCriteriaModel("Cameroon", CustomerMobileStateEnum.VALID.name());
        List<CustomerInfoModel> customerInfoModelList = customerService.retrieveCustomerInfoModel(customerSearchCriteriaModel);

        assertEquals(customerInfoModelList.size(), 1);

        CustomerInfoModel customerInfoModel3 = CreateCustomerInfoModel(3,CountriesInfoEnum.CAMEROON.getCountryCode(),CountriesInfoEnum.CAMEROON.getCountry(), "697151594","(237) 697151594", CustomerMobileStateEnum.VALID);

        Iterable<CustomerInfoModel> customerInfosModels = new ArrayList<>(Arrays.asList(customerInfoModel3));
        Iterable<CustomerInfoModel> resultInfoModel = new ArrayList<>(customerInfoModelList);

        assertIterableEquals(customerInfosModels, resultInfoModel);
    }


    private CustomerSearchCriteriaModel buildCustomerSearchCriteriaModel(String country, String customerMobileState) {
        CustomerSearchCriteriaModel customerSearchCriteriaModel = new CustomerSearchCriteriaModel();
        customerSearchCriteriaModel.setCountry(country);
        customerSearchCriteriaModel.setCustomerMobileState(customerMobileState);
        return customerSearchCriteriaModel;
    }


    private List<Customer> buildCustomerModels() {
        Customer customer1 = createCustomer(1, "Walid Hammadi", "(212) 6007989253");
        Customer customer2 = createCustomer(2, "Frehiwot Teka", "(251) 988200000");
        Customer customer3 = createCustomer(3, "EMILE CHRISTIAN KOUKOU DIKANDA HONORE ", "(237) 697151594");

        return Arrays.asList(customer1, customer2, customer3);
    }

    private CustomerInfoModel CreateCustomerInfoModel(long id, String countryCode,String country, String mobile, String fullMobileNumber, CustomerMobileStateEnum customerMobileState) {
        return CustomerInfoModel
                .builder()
                .id(id)
                .mobileNumber(mobile)
                .fullMobileNumber(fullMobileNumber)
                .countryCode(countryCode)
                .country(country)
                .state(customerMobileState)
                .build();

    }

    private Customer createCustomer(int id, String name, String mobile) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        customer.setPhone(mobile);
        return customer;
    }
}
