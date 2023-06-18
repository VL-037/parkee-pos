package vincentlow.parkee.parkingpos.util;

import java.util.Objects;

import vincentlow.parkee.parkingpos.model.constant.ErrorMessage;
import vincentlow.parkee.parkingpos.model.entity.CheckInTicket;
import vincentlow.parkee.parkingpos.model.entity.Member;
import vincentlow.parkee.parkingpos.model.entity.Officer;
import vincentlow.parkee.parkingpos.model.entity.ParkingLot;
import vincentlow.parkee.parkingpos.model.entity.ParkingSpot;
import vincentlow.parkee.parkingpos.model.entity.PaymentMethod;
import vincentlow.parkee.parkingpos.model.response.exception.BadRequestException;
import vincentlow.parkee.parkingpos.model.response.exception.NotFoundException;

public class ValidatorUtil {

  public static void validatePageableRequest(int pageNumber, int pageSize) {

    if (pageNumber < 0) {
      throw new BadRequestException(ErrorMessage.PAGE_NUMBER_MUST_BE_AT_LEAST_0);
    } else if (pageSize < 1 || pageSize > 100) {
      throw new BadRequestException(ErrorMessage.PAGE_SIZE_MUST_BE_BETWEEN_1_AND_100);
    }
  }

  public static void validateRequest(boolean expression, String errorMessage) {

    if (!expression) {
      throw new BadRequestException(errorMessage);
    }
  }

  public static Member validateMember(Member member) {

    if (Objects.isNull(member)) {
      throw new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND);
    }
    return member;
  }

  public static void validateParkingSpot(ParkingSpot spot) {

    if (Objects.isNull(spot)) {
      throw new NotFoundException(ErrorMessage.PARKING_SPOT_NOT_FOUND);
    }
  }

  public static Officer validateOfficer(Officer officer) {

    if (Objects.isNull(officer)) {
      throw new NotFoundException(ErrorMessage.OFFICER_NOT_FOUND);
    }
    return officer;
  }

  public static void validatePaymentMethod(PaymentMethod paymentMethod) {

    if (Objects.isNull(paymentMethod)) {
      throw new NotFoundException(ErrorMessage.PAYMENT_METHOD_NOT_REGISTERED);
    }
  }

  public static CheckInTicket validateCheckInTicket(CheckInTicket ticket) {

    if (Objects.isNull(ticket)) {
      throw new NotFoundException(ErrorMessage.CHECK_IN_TICKET_NOT_FOUND);
    }
    return ticket;
  }

  public static ParkingLot validateParkingLot(ParkingLot parkingLot) {

    if (Objects.isNull(parkingLot)) {
      throw new NotFoundException(ErrorMessage.PARKING_LOT_NOT_FOUND);
    }
    return parkingLot;
  }
}
