package com.example.exportexcell.service.impl;

import com.example.exportexcell.entity.Customer;
import com.example.exportexcell.repository.CustomerRepository;
import com.example.exportexcell.service.CustomerService;
import com.example.exportexcell.utils.*;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public BaseResponse importCustomerData(MultipartFile importFile) {

        BaseResponse baseResponse = new BaseResponse();

        Workbook workbook = FileFactory.getWorkbookStream(importFile);

        List<CustomerDTO> customerDTOList = ExcelUtils.getImportData(workbook, ImportConfig.customerImport);

        if (!CollectionUtils.isEmpty(customerDTOList)){
            saveData(customerDTOList);
            baseResponse.setCode(String.valueOf(HttpStatus.OK));
            baseResponse.setMessage("import successfully");
        }
        else {
            baseResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST));
            baseResponse.setMessage("import failed");
        }

        return baseResponse;
    }

    private void saveData(List<CustomerDTO> customerDTOList) {
        for (CustomerDTO customerDTO : customerDTOList){
            Customer customer = new Customer();
            customer.setCustomerNumber(customerDTO.getCustomerNumber());
            customer.setCustomerName(customerDTO.getCustomerName());
            customer.setAddressLine1(customerDTO.getAddressLine1());
            customer.setAddressLine2(customerDTO.getAddressLine2());
            customer.setContactFirstName(customerDTO.getContactFirstName());
            customer.setContactLastName(customerDTO.getContactLastName());
            customer.setPhone(customerDTO.getPhone());
            customer.setCity(customerDTO.getCity());
            customer.setState(customerDTO.getState());
            customer.setPostalCode(customerDTO.getPostalCode());
            customer.setCountry(customerDTO.getCountry());
            customer.setSaleLesEmployeeNumber(customerDTO.getSaleLesEmployeeNumber());
            customer.setCreditLimit(customerDTO.getCreditLimit());
            customerRepository.save(customer);
        }
    }
}
