package com.sk.eadmin.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CustomerProblemRegistMapperInputDTO {
    private Integer registID;           // getCustomerProblemRegistDetail, delete, update 용
    private String  reqestID;           // insert, update 시 CREATOR/UPDATER ID 용
    private String  customerName;       // #{customerName}
    private String  customerMobile;     // #{customerMobile}
    private String  requestDesc;        // #{requestDesc}
    private String  problemCode;        // #{problemCode}
    private Integer problemDegree;      // #{problemDegree}
    private String  progressStatusCode; // #{progressStatusCode}
    private String  agentRegionCode;    // get list 쿼리의 #{agentRegionCode}
}