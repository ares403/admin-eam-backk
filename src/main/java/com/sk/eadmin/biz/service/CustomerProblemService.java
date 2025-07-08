package com.sk.eadmin.biz.service;

import java.util.List;

import com.sk.eadmin.biz.dto.CustomerProblemRegistInputDTO;
import com.sk.eadmin.biz.dto.CustomerProblemRegistOutputDTO;
import com.sk.eadmin.biz.dto.CustomerProblemRegistResDTO;

public interface CustomerProblemService {

    /**
     * 고객문의 리스트 조회
     */
    List<CustomerProblemRegistOutputDTO> getCustomerProblemRegistList(CustomerProblemRegistInputDTO param);

    /**
     * 고객문의 신규 등록
     */
    void createCustomerProblem(CustomerProblemRegistInputDTO param);

    /**
     * 고객문의 상세 조회
     */
    CustomerProblemRegistResDTO getCustomerProblemDetail(String regId);

    /**
     * 고객문의 삭제
     */
    void deleteCustomerProblem(String regId);

    /**
     * 고객문의 수정
     */
    void updateCustomerProblem(String regId, CustomerProblemRegistInputDTO param);
}
