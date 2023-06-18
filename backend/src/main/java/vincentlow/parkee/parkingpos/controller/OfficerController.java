package vincentlow.parkee.parkingpos.controller;

import static vincentlow.parkee.parkingpos.util.ResponseUtil.toOfficerResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import vincentlow.parkee.parkingpos.model.constant.ApiPath;
import vincentlow.parkee.parkingpos.model.entity.Officer;
import vincentlow.parkee.parkingpos.model.response.OfficerResponse;
import vincentlow.parkee.parkingpos.model.response.api.ApiSingleResponse;
import vincentlow.parkee.parkingpos.service.OfficerService;

@Slf4j
@RestController
@RequestMapping(value = ApiPath.OFFICER, produces = MediaType.APPLICATION_JSON_VALUE)
public class OfficerController extends BaseController {

  @Autowired
  private OfficerService officerService;

  @GetMapping("/{id}")
  public ResponseEntity<ApiSingleResponse<OfficerResponse>> getOfficerDetail(@PathVariable String id) {

    try {
      Officer officer = officerService.getOfficerDetail(id);
      OfficerResponse response = toOfficerResponse(officer);

      return toSuccessResponseEntity(toApiSingleResponse(response));
    } catch (RuntimeException e) {
      log.error("#OfficerController#getOfficerDetail ERROR! with id: {} and error: {}", id, e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }
}
