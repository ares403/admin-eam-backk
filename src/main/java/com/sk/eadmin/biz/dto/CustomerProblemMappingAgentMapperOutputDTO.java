package com.sk.eadmin.biz.dto;

import lombok.Data;

@Data
public class CustomerProblemMappingAgentMapperOutputDTO {
    private String agntNm;       // C.agnt_nm
    private String agntRegnCd;   // C.agnt_regn_cd
    private String agentRegnVal; // D.cd_val as agent_regn_val
}