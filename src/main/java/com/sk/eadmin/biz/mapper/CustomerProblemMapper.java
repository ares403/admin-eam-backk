package com.sk.eadmin.biz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sk.eadmin.biz.dto.AddCustomerProblemRegistInputDTO;
import com.sk.eadmin.biz.dto.CustomerProblemRegistMapperInputDTO;
import com.sk.eadmin.biz.dto.CustomerProblemRegistMapperOutputDTO;
import com.sk.eadmin.biz.dto.ModifyCustomerProblemRegistInputDTO;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CustomerProblemMapper {

    // 고객문의 리스트 조회
    List<CustomerProblemRegistMapperOutputDTO> getCustomerProblemRegistList(CustomerProblemRegistMapperInputDTO param);

    // 고객문의 신규 등록
    void addCustomerProblemRegist(AddCustomerProblemRegistInputDTO param);

    // 고객문의 상세 조회
    CustomerProblemRegistMapperOutputDTO getCustomerProblemDetail(@Param("registID") int registID);

    // 고객문의 삭제
    void deleteCustomerProblem(@Param("registID") int registID);

    // 고객문의 수정
    void modifyCustomerProblemRegist(ModifyCustomerProblemRegistInputDTO param);
}
