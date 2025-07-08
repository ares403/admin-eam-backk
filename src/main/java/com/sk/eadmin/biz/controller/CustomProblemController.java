package com.sk.eadmin.biz.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sk.eadmin.biz.dto.*;
import com.sk.eadmin.biz.service.CustomerProblemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;

@Tag(name = "customer-problem", description = "고객문의")
@RestController
@RequestMapping("/api/v1/customer/problem-mgmt")
@RequiredArgsConstructor
public class CustomProblemController {

    private final CustomerProblemService customerProblemService;

    // ✅ 고객접수문의 리스트 조회
    @Operation(summary = "고객접수문의 리스트 조회", description = "입력조건에 따라 접수된 고객 문의 리스트를 조회한다. 입력조건은 문의코드(0001, 0002, 0003 등), 담당지역코드를 포함한 4개의 값이다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "403", description = "접속 권한이 없습니다.")
    })
    @GetMapping("/customer-problem")
    public ResponseEntity<List<CustomerProblemRegistResDTO>> getCustomerProblemRegistList(
        @RequestParam(name = "problemCode", required = false)
        @Valid @Pattern(regexp = "^000[1-4]$")
        @Schema(description = "문의코드", example = "0001")
        String problemCode,

        @RequestParam(name = "agentRegionCode", required = false)
        @Valid @Pattern(regexp = "^0[1-3]$")
        @Schema(description = "담당지역코드", example = "01")
        String agentRegionCode,

        @RequestParam(name = "progressStatusCode", required = false)
        @Valid @Pattern(regexp = "^0[1-2]$")
        @Schema(description = "진행상태코드", example = "01")
        String progressStatusCode,

        @RequestParam(name = "requestFilter", required = false)
        @Valid
        @Schema(description = "요청필터", example = "샘플")
        String requestFilter
    ) {
        final List<CustomerProblemRegistOutputDTO> serviceResults = customerProblemService.getCustomerProblemRegistList(
            CustomerProblemRegistInputDTO.builder()
                .problemCode(problemCode)
                .agentRegionCode(agentRegionCode)
                .progressStatusCode(progressStatusCode)
                .requestDesc(requestFilter)
                .build()
        );

        List<CustomerProblemRegistResDTO> rets = new ArrayList<>();
        for (CustomerProblemRegistOutputDTO serviceResult : serviceResults) {
            rets.add(CustomerProblemRegistResDTO.builder()
                .regId(serviceResult.getRegId())
                .custNm(serviceResult.getCustNm())
                .custMbl(serviceResult.getCustMbl())
                .reqDesc(serviceResult.getReqDesc())
                .prbmCd(serviceResult.getPrbmCd())
                .prbmDgr(serviceResult.getPrbmDgr())
                .prgsSts(serviceResult.getPrgsSts())
                .prgsStsVal(serviceResult.getPrgsStsVal())
                .crteDttm(serviceResult.getCrteDttm())
                .agntIcn(serviceResult.getAgntIcn())
                .build());
        }
        return ResponseEntity.ok(rets);
    }

    // ✅ 고객접수문의 신규 등록
    @Operation(summary = "고객접수문의 신규 등록", description = "고객문의 정보를 신규로 등록한다.")
    @PostMapping("/customer-problem")
    public ResponseEntity<Void> createCustomerProblem(
        @RequestBody @Valid CustomerProblemRegistInputDTO requestDto
    ) {
        customerProblemService.createCustomerProblem(requestDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // ✅ 고객접수문의 상세 조회
    @Operation(summary = "고객접수문의 상세 조회", description = "고객문의 상세 정보를 조회한다.")
    @GetMapping("/customer-problem/{id}")
    public ResponseEntity<CustomerProblemRegistResDTO> getCustomerProblemDetail(
        @PathVariable("id") String regId
    ) {
        CustomerProblemRegistResDTO detail = customerProblemService.getCustomerProblemDetail(regId);
        return ResponseEntity.ok(detail);
    }

    // ✅ 고객접수문의 삭제
    @Operation(summary = "고객접수문의 삭제", description = "고객문의 정보를 삭제한다.")
    @DeleteMapping("/customer-problem/{id}")
    public ResponseEntity<Void> deleteCustomerProblem(
        @PathVariable("id") String regId
    ) {
        customerProblemService.deleteCustomerProblem(regId);
        return ResponseEntity.noContent().build();
    }

    // ✅ 고객접수문의 변경
    @Operation(summary = "고객접수문의 변경", description = "고객문의 정보를 수정한다.")
    @PatchMapping("/customer-problem/{id}")
    public ResponseEntity<Void> updateCustomerProblem(
        @PathVariable("id") String regId,
        @RequestBody @Valid CustomerProblemRegistInputDTO requestDto
    ) {
        customerProblemService.updateCustomerProblem(regId, requestDto);
        return ResponseEntity.ok().build();
    }
}
