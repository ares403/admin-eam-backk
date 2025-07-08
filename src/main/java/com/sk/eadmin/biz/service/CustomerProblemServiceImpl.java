package com.sk.eadmin.biz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sk.eadmin.biz.dto.*;
import com.sk.eadmin.biz.mapper.CustomerProblemMapper;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerProblemServiceImpl implements CustomerProblemService {

    private final CustomerProblemMapper customerProblemMapper;

    @Override
    public List<CustomerProblemRegistOutputDTO> getCustomerProblemRegistList(@NonNull CustomerProblemRegistInputDTO input) {
        log.debug(">>>>> {}.getCustomerProblemRegistList Start <<<<<", this.getClass().getName());
        log.debug("    Parameter 1 - input[{}]", input);

        final CustomerProblemRegistMapperInputDTO mapperInput = CustomerProblemRegistMapperInputDTO.builder()
            .problemCode(input.getProblemCode())
            .agentRegionCode(input.getAgentRegionCode())
            .progressStatusCode(input.getProgressStatusCode())
            .requestDesc(input.getRequestDesc())
            .build();

        log.debug("mapperInput - {}", mapperInput);
        final List<CustomerProblemRegistMapperOutputDTO> mapperResults = customerProblemMapper.getCustomerProblemRegistList(mapperInput);
        log.debug("mapperResults - {}", mapperResults);

        List<CustomerProblemRegistOutputDTO> retList = new ArrayList<>();
        for (CustomerProblemRegistMapperOutputDTO mapperResult : mapperResults) {
            final CustomerProblemRegistOutputDTO ret = CustomerProblemRegistOutputDTO.builder()
                .regId(mapperResult.getRegId())
                .custNm(mapperResult.getCustNm())
                .custMbl(mapperResult.getCustMbl())
                .reqDesc(mapperResult.getReqDesc())
                .prbmCd(mapperResult.getPrbmCd())
                .prbmDgr(mapperResult.getPrbmDgr())
                .prgsSts(mapperResult.getPrgsSts())
                .prgsStsVal(mapperResult.getPrgsStsVal())
                .crteDttm(mapperResult.getCrteDttm())
                .agntIcn(mapperResult.getAgntIcn())
                .build();
            retList.add(ret);
        }

        log.debug("retList - {}", retList);
        log.debug(">>>>> {}.getCustomerProblemRegistList Finish <<<<<", this.getClass().getName());
        return retList;
    }

    @Override
    public void createCustomerProblem(@NonNull CustomerProblemRegistInputDTO input) {
        log.debug(">>>>> {}.createCustomerProblem Start <<<<<", this.getClass().getName());
        log.debug("    Parameter 1 - input[{}]", input);

		AddCustomerProblemRegistInputDTO mapperInput = AddCustomerProblemRegistInputDTO.builder()
			.customerName(input.getCustomerName())        // custNm → customerName
			.customerMobile(input.getCustomerMobile())    // custMbl → customerMobile
			.requestDesc(input.getRequestDesc())
			.problemCode(input.getProblemCode())
			.problemDegree(input.getProblemDegree())      // 문제 등급도 DTO에 있으니 매핑
			.progressStatusCode(input.getProgressStatusCode())
			.build();


        customerProblemMapper.addCustomerProblemRegist(mapperInput);

        log.debug(">>>>> {}.createCustomerProblem Finish <<<<<", this.getClass().getName());
    }

    @Override
    public CustomerProblemRegistResDTO getCustomerProblemDetail(@NonNull String regId) {

		int id = Integer.parseInt(regId);
        log.debug(">>>>> {}.getCustomerProblemDetail Start <<<<<", this.getClass().getName());
        log.debug("    Parameter 1 - regId[{}]", regId);

        CustomerProblemRegistMapperOutputDTO mapperResult =
        customerProblemMapper.getCustomerProblemDetail(id);

        CustomerProblemRegistResDTO result = CustomerProblemRegistResDTO.builder()
            .regId(mapperResult.getRegId())
            .custNm(mapperResult.getCustNm())
            .custMbl(mapperResult.getCustMbl())
            .reqDesc(mapperResult.getReqDesc())
            .prbmCd(mapperResult.getPrbmCd())
            .prbmDgr(mapperResult.getPrbmDgr())
            .prgsSts(mapperResult.getPrgsSts())
            .prgsStsVal(mapperResult.getPrgsStsVal())
            .crteDttm(mapperResult.getCrteDttm())
            .agntIcn(mapperResult.getAgntIcn())
            .build();

        log.debug(">>>>> {}.getCustomerProblemDetail Finish <<<<<", this.getClass().getName());
        return result;
    }

    @Override
    public void deleteCustomerProblem(@NonNull String regId) {

		int id = Integer.parseInt(regId);
        log.debug(">>>>> {}.deleteCustomerProblem Start <<<<<", this.getClass().getName());
        log.debug("    Parameter 1 - regId[{}]", regId);

        customerProblemMapper.deleteCustomerProblem(Integer.parseInt(regId));

        log.debug(">>>>> {}.deleteCustomerProblem Finish <<<<<", this.getClass().getName());
    }

    @Override
    public void updateCustomerProblem(@NonNull String regId, @NonNull CustomerProblemRegistInputDTO input) {
        log.debug(">>>>> {}.updateCustomerProblem Start <<<<<", this.getClass().getName());
        log.debug("    Parameter 1 - regId[{}], input[{}]", regId, input);

		ModifyCustomerProblemRegistInputDTO mapperInput = ModifyCustomerProblemRegistInputDTO.builder()
			.reqestID(regId)                           // path 변수 regId → reqestID 매핑
			.customerName(input.getCustomerName())
			.customerMobile(input.getCustomerMobile())
			.requestDesc(input.getRequestDesc())
			.problemCode(input.getProblemCode())
			.problemDegree(input.getProblemDegree())
			.build();


        customerProblemMapper.modifyCustomerProblemRegist(mapperInput);

        log.debug(">>>>> {}.updateCustomerProblem Finish <<<<<", this.getClass().getName());
    }
}
