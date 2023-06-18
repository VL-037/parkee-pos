package vincentlow.parkee.parkingpos.service;

import static vincentlow.parkee.parkingpos.util.ValidatorUtil.validateCheckInTicket;
import static vincentlow.parkee.parkingpos.util.ValidatorUtil.validateOfficer;
import static vincentlow.parkee.parkingpos.util.ValidatorUtil.validateParkingSpot;
import static vincentlow.parkee.parkingpos.util.ValidatorUtil.validatePaymentMethod;
import static vincentlow.parkee.parkingpos.util.ValidatorUtil.validateRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vincentlow.parkee.parkingpos.model.constant.ErrorMessage;
import vincentlow.parkee.parkingpos.model.entity.CheckInTicket;
import vincentlow.parkee.parkingpos.model.entity.CheckOutTicket;
import vincentlow.parkee.parkingpos.model.entity.Member;
import vincentlow.parkee.parkingpos.model.entity.Officer;
import vincentlow.parkee.parkingpos.model.entity.ParkingSpot;
import vincentlow.parkee.parkingpos.model.entity.PaymentMethod;
import vincentlow.parkee.parkingpos.model.request.CreateCheckInTicketRequest;
import vincentlow.parkee.parkingpos.model.request.CreateCheckOutTicketRequest;
import vincentlow.parkee.parkingpos.model.request.GetCheckoutTicketDetailRequest;
import vincentlow.parkee.parkingpos.model.response.exception.BadRequestException;
import vincentlow.parkee.parkingpos.model.response.exception.ConflictException;
import vincentlow.parkee.parkingpos.repository.CheckInTicketRepository;
import vincentlow.parkee.parkingpos.repository.CheckOutTicketRepository;
import vincentlow.parkee.parkingpos.repository.MemberRepository;
import vincentlow.parkee.parkingpos.repository.OfficerRepository;
import vincentlow.parkee.parkingpos.repository.ParkingLotRepository;
import vincentlow.parkee.parkingpos.repository.ParkingRateRepository;
import vincentlow.parkee.parkingpos.repository.ParkingSpotRepository;
import vincentlow.parkee.parkingpos.repository.PaymentMethodRepository;
import vincentlow.parkee.parkingpos.util.StringUtil;

@Service
public class TicketServiceImpl implements TicketService {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private ParkingSpotRepository parkingSpotRepository;

  @Autowired
  private ParkingLotRepository parkingLotRepository;

  @Autowired
  private OfficerRepository officerRepository;

  @Autowired
  private CheckInTicketRepository checkInTicketRepository;

  @Autowired
  private ParkingRateRepository parkingRateRepository;

  @Autowired
  private PaymentMethodRepository paymentMethodRepository;

  @Autowired
  private CheckOutTicketRepository checkOutTicketRepository;

  @Autowired
  private MemberService memberService;

  @Override
  public CheckInTicket createCheckInTicket(CreateCheckInTicketRequest request) {

    validateRequest(Objects.nonNull(request), ErrorMessage.REQUEST_MUST_NOT_BE_NULL);

    StringUtil.trimStrings(request);

    validateRequest(StringUtils.isNotBlank(request.getPlateNumber()), ErrorMessage.PLATE_NUMBER_MUST_NOT_BE_BLANK);
    validateRequest(StringUtils.isNotBlank(request.getVehicleTypeId()), ErrorMessage.VEHICLE_TYPE_ID_MUST_NOT_BE_BLANK);
    validateRequest(StringUtils.isNotBlank(request.getParkingLotId()), ErrorMessage.PARKING_LOT_ID_MUST_BE_NOT_BLANK);
    validateRequest(StringUtils.isNotBlank(request.getOfficerId()), ErrorMessage.OFFICER_ID_MUST_NOT_BE_BLANK);

    Member member = memberRepository.findByPlateNumberAndMarkForDeleteFalse(request.getPlateNumber());

    ParkingSpot parkingSpot =
        parkingSpotRepository.findTopByParkingLotIdAndVehicleTypeIdAndIsOccupiedFalseAndMarkForDeleteFalse(
            request.getParkingLotId(), request.getVehicleTypeId());
    validateParkingSpot(parkingSpot);

    boolean isOccupiedInParkingLot =
        checkInTicketRepository.existsByPlateNumberAndParkingSpotParkingLotIdAndIsActiveTrue(
            request.getPlateNumber(), parkingSpot.getParkingLot()
                .getId());
    if (isOccupiedInParkingLot) {
      throw new ConflictException(ErrorMessage.PLATE_NUMBER_IS_OCCUPIED_IN_PARKING_LOT);
    }

    Officer officer = officerRepository.findByIdAndMarkForDeleteFalse(request.getOfficerId());
    validateOfficer(officer);

    CheckInTicket ticket = new CheckInTicket();
    ticket.setPlateNumber(request.getPlateNumber());
    ticket.setMember(member);
    ticket.setParkingSpot(parkingSpot);
    ticket.setOfficer(officer);
    ticket.setActive(true);

    LocalDateTime now = LocalDateTime.now();
    ticket.setCreatedDate(now);
    ticket.setCreatedBy(officer.getId());
    ticket.setUpdatedDate(now);
    ticket.setUpdatedBy(officer.getId());

    parkingSpot.setOccupied(true);
    parkingSpotRepository.save(parkingSpot);

    return checkInTicketRepository.save(ticket);
  }

  @Override
  public CheckInTicket getCheckInTicketDetail(GetCheckoutTicketDetailRequest request) {

    CheckInTicket checkInTicket =
        checkInTicketRepository.findByIdAndPlateNumberAndParkingSpotParkingLotIdAndIsActiveTrue(
            request.getParkingSlipId(), request.getPlateNumber(), request.getParkingLotId());
    return validateCheckInTicket(checkInTicket);
  }

  @Override
  public CheckOutTicket createCheckOutTicket(CreateCheckOutTicketRequest request) {

    validateRequest(Objects.nonNull(request), ErrorMessage.REQUEST_MUST_NOT_BE_NULL);

    StringUtil.trimStrings(request);

    validateRequest(StringUtils.isNotBlank(request.getPlateNumber()), ErrorMessage.PLATE_NUMBER_MUST_NOT_BE_BLANK);
    validateRequest(StringUtils.isNotBlank(request.getOfficerId()), ErrorMessage.OFFICER_ID_MUST_NOT_BE_BLANK);
    validateRequest(StringUtils.isNotBlank(request.getParkingLotId()), ErrorMessage.PARKING_LOT_ID_MUST_BE_NOT_BLANK);
    validateRequest(StringUtils.isNotBlank(request.getPaymentMethodId()),
        ErrorMessage.PAYMENT_METHOD_MUST_NOT_BE_BLANK);

    LocalDateTime checkOutDate;
    try {
      checkOutDate = LocalDateTime.parse(request.getCheckOutDate(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    } catch (DateTimeParseException e) {
      throw new BadRequestException(ErrorMessage.CHECK_OUT_TICKET_DATE_FORMAT_FALSE);
    }

    CheckInTicket checkInTicket =
        checkInTicketRepository.findByIdAndPlateNumberAndParkingSpotParkingLotIdAndIsActiveTrue(
            request.getParkingSlipId(), request.getPlateNumber(), request.getParkingLotId());
    validateCheckInTicket(checkInTicket);

    Officer officer = officerRepository.findByIdAndMarkForDeleteFalse(request.getOfficerId());
    validateOfficer(officer);

    PaymentMethod paymentMethod = paymentMethodRepository.findByIdAndMarkForDeleteFalse(request.getPaymentMethodId());
    validatePaymentMethod(paymentMethod);

    CheckOutTicket ticket = new CheckOutTicket();
    ticket.setCheckInTicket(checkInTicket);
    ticket.setOfficer(officer);
    ticket.setPaymentMethod(paymentMethod);

    ticket.setCreatedDate(checkOutDate);
    ticket.setCreatedBy(officer.getId());
    ticket.setUpdatedDate(checkOutDate);
    ticket.setUpdatedBy(officer.getId());

    ticket.setDiscount(request.getDiscount());
    ticket.setPrice(request.getPrice());
    ticket.setFinalPrice(request.getFinalPrice());

    checkInTicket.setActive(false);
    checkInTicketRepository.save(checkInTicket);

    ParkingSpot parkingSpot = parkingSpotRepository.findByIdAndMarkForDeleteFalse(checkInTicket.getParkingSpot()
        .getId());
    parkingSpot.setOccupied(false);
    parkingSpotRepository.save(parkingSpot);

    return checkOutTicketRepository.save(ticket);
  }
}
