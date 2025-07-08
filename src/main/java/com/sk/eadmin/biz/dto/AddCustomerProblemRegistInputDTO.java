package com.sk.eadmin.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddCustomerProblemRegistInputDTO {
    private String  reqestID;
    private String  customerName;
    private String  customerMobile;
    private String  requestDesc;
    private String  problemCode;
    private Integer problemDegree;
    private String  progressStatusCode;
}
