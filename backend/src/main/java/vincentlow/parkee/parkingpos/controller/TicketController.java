package vincentlow.parkee.parkingpos.controller;

import static vincentlow.parkee.parkingpos.util.ResponseUtil.toCheckInTicketResponse;
import static vincentlow.parkee.parkingpos.util.ResponseUtil.toCheckOutTicketResponse;

import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import vincentlow.parkee.parkingpos.model.constant.ApiPath;
import vincentlow.parkee.parkingpos.model.entity.CheckInTicket;
import vincentlow.parkee.parkingpos.model.entity.CheckOutTicket;
import vincentlow.parkee.parkingpos.model.entity.Voucher;
import vincentlow.parkee.parkingpos.model.request.CreateCheckInTicketRequest;
import vincentlow.parkee.parkingpos.model.request.CreateCheckOutTicketRequest;
import vincentlow.parkee.parkingpos.model.request.GetCheckoutTicketDetailRequest;
import vincentlow.parkee.parkingpos.model.response.CheckInTicketResponse;
import vincentlow.parkee.parkingpos.model.response.CheckOutTicketResponse;
import vincentlow.parkee.parkingpos.model.response.GetCheckOutTicketDetailResponse;
import vincentlow.parkee.parkingpos.model.response.ParkingPriceResponse;
import vincentlow.parkee.parkingpos.model.response.api.ApiSingleResponse;
import vincentlow.parkee.parkingpos.service.MemberService;
import vincentlow.parkee.parkingpos.service.ParkingRateService;
import vincentlow.parkee.parkingpos.service.TicketService;
import vincentlow.parkee.parkingpos.service.VoucherService;
import vincentlow.parkee.parkingpos.util.ResponseUtil;

@Slf4j
@RestController
@RequestMapping(value = ApiPath.TICKET, produces = MediaType.APPLICATION_JSON_VALUE)
public class TicketController extends BaseController {

  @Autowired
  private TicketService ticketService;

  @Autowired
  private MemberService memberService;

  @Autowired
  private ParkingRateService parkingRateService;

  @Autowired
  private VoucherService voucherService;

  @PostMapping("/check-in")
  public ResponseEntity<ApiSingleResponse<CheckInTicketResponse>> createCheckInTicket(
      @RequestBody CreateCheckInTicketRequest request) {

    try {
      CheckInTicket ticket = ticketService.createCheckInTicket(request);
      CheckInTicketResponse response = toCheckInTicketResponse(ticket);

      return toSuccessResponseEntity(toApiSingleResponse(response));
    } catch (RuntimeException e) {
      log.error("#TicketController#createCheckInTicket ERROR! with request: {} and error: {}", request, e.getMessage(),
          e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  @GetMapping("/check-out")
  public ResponseEntity<ApiSingleResponse<GetCheckOutTicketDetailResponse>> getCheckOutTicketDetail(
      @RequestParam String parkingLotId,
      @RequestParam String parkingSlipId,
      @RequestParam String plateNumber,
      @RequestParam String voucherCode) {

    try {
      LocalDateTime checkOutDate = LocalDateTime.now();
      GetCheckoutTicketDetailRequest request = GetCheckoutTicketDetailRequest.builder()
          .parkingLotId(parkingLotId)
          .parkingSlipId(parkingSlipId)
          .plateNumber(plateNumber)
          .voucherCode(voucherCode)
          .build();

      CheckInTicket checkInTicket = ticketService.getCheckInTicketDetail(request);
      Voucher voucher = null;
      if (StringUtils.isNotBlank(voucherCode)) {
        voucher = voucherService.findValidVoucherById(voucherCode);
      }

      ParkingPriceResponse priceResponse =
          parkingRateService.calculateParkingPrice(checkInTicket.getCreatedDate(), checkOutDate, voucher);
      GetCheckOutTicketDetailResponse response =
          ResponseUtil.toGetCheckOutTicketDetailResponse(checkInTicket, priceResponse, checkOutDate);

      return toSuccessResponseEntity(toApiSingleResponse(response));
    } catch (RuntimeException e) {
      log.error(
          "#TicketController#getCheckOutTicketDetail ERROR! with parkingLotId: {}, parkingSlipId: {}, plateNumber: {}, voucherCode: {} and error: {}",
          parkingLotId, parkingSlipId, plateNumber, voucherCode, e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  @PostMapping("/check-out")
  public ResponseEntity<ApiSingleResponse<CheckOutTicketResponse>> createCheckOutTicket(
      @RequestBody CreateCheckOutTicketRequest request) {

    try {
      CheckOutTicket ticket = ticketService.createCheckOutTicket(request);
      CheckOutTicketResponse response = toCheckOutTicketResponse(ticket);

      return toSuccessResponseEntity(toApiSingleResponse(response));
    } catch (RuntimeException e) {
      log.error("#TicketController#createCheckOutTicket ERROR! with request: {} and error: {}", request, e.getMessage(),
          e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }
}
