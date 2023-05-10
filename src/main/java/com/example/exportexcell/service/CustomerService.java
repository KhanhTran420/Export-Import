package com.example.exportexcell.service;

import com.example.exportexcell.utils.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

public interface CustomerService {

    BaseResponse importCustomerData(MultipartFile importFile);
}
