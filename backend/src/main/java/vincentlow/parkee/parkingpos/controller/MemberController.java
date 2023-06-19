package vincentlow.parkee.parkingpos.controller;

import static vincentlow.parkee.parkingpos.util.ResponseUtil.toMemberResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import vincentlow.parkee.parkingpos.model.constant.ApiPath;
import vincentlow.parkee.parkingpos.model.entity.Member;
import vincentlow.parkee.parkingpos.model.response.MemberResponse;
import vincentlow.parkee.parkingpos.model.response.api.ApiSingleResponse;
import vincentlow.parkee.parkingpos.service.MemberService;

@Slf4j
@RestController
@RequestMapping(value = ApiPath.MEMBER, produces = MediaType.APPLICATION_JSON_VALUE)
public class MemberController extends BaseController {

  @Autowired
  private MemberService memberService;

  @GetMapping
  public ResponseEntity<ApiSingleResponse<MemberResponse>> getMemberDetail(@RequestParam String plateNumber) {

    try {
      Member member = memberService.getMemberDetail(plateNumber);
      MemberResponse response = toMemberResponse(member);

      return toSuccessResponseEntity(toApiSingleResponse(response));
    } catch (RuntimeException e) {
      log.error("#MemberController#getMemberDetail ERROR! with plateNumber: {} and error: {}", plateNumber,
          e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }
}
