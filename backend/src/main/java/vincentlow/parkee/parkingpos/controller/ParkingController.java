package vincentlow.parkee.parkingpos.controller;

import static vincentlow.parkee.parkingpos.util.ResponseUtil.toParkingLotResponse;
import static vincentlow.parkee.parkingpos.util.ValidatorUtil.validatePageableRequest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import vincentlow.parkee.parkingpos.model.constant.ApiPath;
import vincentlow.parkee.parkingpos.model.entity.ParkingLot;
import vincentlow.parkee.parkingpos.model.entity.ParkingSpot;
import vincentlow.parkee.parkingpos.model.request.GetParkingSpotsRequest;
import vincentlow.parkee.parkingpos.model.response.ParkingLotResponse;
import vincentlow.parkee.parkingpos.model.response.ParkingSpotResponse;
import vincentlow.parkee.parkingpos.model.response.api.ApiListResponse;
import vincentlow.parkee.parkingpos.model.response.api.ApiSingleResponse;
import vincentlow.parkee.parkingpos.model.response.wrapper.PageMetaData;
import vincentlow.parkee.parkingpos.service.ParkingService;
import vincentlow.parkee.parkingpos.util.ResponseUtil;

@Slf4j
@RestController
@RequestMapping(value = ApiPath.PARKING, produces = MediaType.APPLICATION_JSON_VALUE)
public class ParkingController extends BaseController {

  @Autowired
  private ParkingService parkingService;

  @GetMapping
  public ResponseEntity<ApiSingleResponse<ParkingLotResponse>> getParkingLotDetail(@RequestParam String id) {

    try {
      ParkingLot parkingLot = parkingService.getParkingLotDetail(id);
      int occupiedSpots = parkingService.countOccupiedSpots(id);
      ParkingLotResponse response = toParkingLotResponse(parkingLot, occupiedSpots);

      return toSuccessResponseEntity(toApiSingleResponse(response));
    } catch (RuntimeException e) {
      log.error("#ParkingController#getParkingLotDetail ERROR! with id: {} and error: {}", id, e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  @GetMapping("parking-spots")
  public ResponseEntity<ApiListResponse<ParkingSpotResponse>> getParkingSpots(
      @RequestParam(defaultValue = "0") int pageNumber,
      @RequestParam(defaultValue = "50") int pageSize,
      @RequestBody GetParkingSpotsRequest request) {

    try {
      validatePageableRequest(pageNumber, pageSize);

      Page<ParkingSpot> parkingSpots = parkingService.getParkingSpots(pageNumber, pageSize, request);
      List<ParkingSpotResponse> response = parkingSpots.stream()
          .map(ResponseUtil::toParkingSpotResponse)
          .collect(Collectors.toList());
      PageMetaData pageMetaData = getPageMetaData(parkingSpots, pageNumber, pageSize);

      return toSuccessResponseEntity(toApiListResponse(response, pageMetaData));
    } catch (RuntimeException e) {
      log.error("#ParkingController#getParkingSpots ERROR! with request: {} and error: {}", request, e.getMessage(),
          e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }
}
