package com.sk.eadmin.biz.dto;

import java.util.Date;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class CustomerProblemRegistMapperOutputDTO {
    // list 조회 결과
    private Integer regId;      // A.reg_id
    private String  custNm;     // MAX(A.cust_nm) as cust_nm
    private Timestamp crteDttm; // MAX(A.crte_dttm) as crte_dttm
    private String  agntIcn;    // GROUP_CONCAT(...) as agnt_icn
    private Integer prbmDgr;    // MAX(A.prbm_dgr) as prbm_dgr
    private String  prgsSts;    // MAX(D.cd_val)  as prgs_sts

    // detail 조회 결과
    private String  reqDesc;     // A.req_desc
    private String  custMbl;     // A.cust_mbl
    private String  prgsStsVal;  // D1.cd_val as prgs_sts_val
    private String  prbmCd;      // A.prbm_cd
    private String  custPrbm;    // D2.cd_val as cust_prbm
}

