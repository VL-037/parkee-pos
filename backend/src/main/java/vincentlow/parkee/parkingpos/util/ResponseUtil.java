package vincentlow.parkee.parkingpos.util;

import static vincentlow.parkee.parkingpos.util.ValidatorUtil.validateParkingSpot;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import vincentlow.parkee.parkingpos.model.entity.Address;
import vincentlow.parkee.parkingpos.model.entity.CheckInTicket;
import vincentlow.parkee.parkingpos.model.entity.CheckOutTicket;
import vincentlow.parkee.parkingpos.model.entity.Member;
import vincentlow.parkee.parkingpos.model.entity.Officer;
import vincentlow.parkee.parkingpos.model.entity.ParkingLot;
import vincentlow.parkee.parkingpos.model.entity.ParkingSpot;
import vincentlow.parkee.parkingpos.model.entity.PaymentMethod;
import vincentlow.parkee.parkingpos.model.entity.VehicleType;
import vincentlow.parkee.parkingpos.model.response.CheckInTicketResponse;
import vincentlow.parkee.parkingpos.model.response.CheckOutTicketResponse;
import vincentlow.parkee.parkingpos.model.response.GetCheckOutTicketDetailResponse;
import vincentlow.parkee.parkingpos.model.response.MemberResponse;
import vincentlow.parkee.parkingpos.model.response.OfficerResponse;
import vincentlow.parkee.parkingpos.model.response.ParkingLotResponse;
import vincentlow.parkee.parkingpos.model.response.ParkingPriceResponse;
import vincentlow.parkee.parkingpos.model.response.ParkingSpotResponse;
import vincentlow.parkee.parkingpos.model.response.PaymentMethodResponse;
import vincentlow.parkee.parkingpos.model.response.VehicleTypeResponse;

public class ResponseUtil {

  public static MemberResponse toMemberResponse(Member member) {

    return MemberResponse.builder()
        .name(member.getName())
        .plateNumber(member.getPlateNumber())
        .memberExpiredDate(member.getMemberExpiredDate())
        .build();
  }

  public static OfficerResponse toOfficerResponse(Officer officer) {

    OfficerResponse response = new OfficerResponse();
    BeanUtils.copyProperties(officer, response);

    return response;
  }

  public static ParkingLotResponse toParkingLotResponse(ParkingLot parkingLot, int occupiedSpots) {

    Address address = parkingLot.getAddress();
    String fullAddress = String.format("%s, %s, %s, %s, %s",
        address.getStreet(), address.getCity(), address.getState(), address.getCountry(), address.getPostalCode());
    List<VehicleTypeResponse> vehicleTypes = parkingLot.getVehicleTypes()
        .stream()
        .map(ResponseUtil::toVehicleTypeResponse)
        .collect(Collectors.toList());
    List<PaymentMethodResponse> paymentMethods = parkingLot.getPaymentMethods()
        .stream()
        .map(ResponseUtil::toPaymentMethodResponse)
        .collect(Collectors.toList());

    return ParkingLotResponse.builder()
        .companyName(parkingLot.getCompany()
            .getName())
        .address(fullAddress)
        .vehicleCapacity(parkingLot.getVehicleCapacity())
        .occupiedSpots(occupiedSpots)
        .vehicleTypes(vehicleTypes)
        .paymentMethods(paymentMethods)
        .build();
  }

  public static VehicleTypeResponse toVehicleTypeResponse(VehicleType vehicleType) {

    VehicleTypeResponse response = new VehicleTypeResponse();
    BeanUtils.copyProperties(vehicleType, response);

    return response;
  }

  public static PaymentMethodResponse toPaymentMethodResponse(PaymentMethod paymentMethod) {

    PaymentMethodResponse response = new PaymentMethodResponse();
    BeanUtils.copyProperties(paymentMethod, response);

    return response;
  }

  public static ParkingSpotResponse toParkingSpotResponse(ParkingSpot parkingSpot) {

    String spot = String.format("%s%s", parkingSpot.getArea(), parkingSpot.getCode());

    return ParkingSpotResponse.builder()
        .id(parkingSpot.getId())
        .spot(spot)
        .isOccupied(parkingSpot.isOccupied())
        .build();
  }

  public static CheckInTicketResponse toCheckInTicketResponse(CheckInTicket checkInTicket) {

    ParkingSpot parkingSpot = checkInTicket.getParkingSpot();
    ParkingLot parkingLot = parkingSpot.getParkingLot();
    Address parkingLotAddress = parkingLot.getAddress();

    String spot = String.format("%s%s", parkingSpot.getArea(), parkingSpot.getCode());
    String fullAddress = String.format("%s, %s, %s, %s, %s",
        parkingLotAddress.getStreet(), parkingLotAddress.getCity(), parkingLotAddress.getState(),
        parkingLotAddress.getCountry(), parkingLotAddress.getPostalCode());
    String companyName = parkingLot.getCompany()
        .getName();

    CheckInTicketResponse response = CheckInTicketResponse.builder()
        .plateNumber(checkInTicket.getPlateNumber())
        .vehicleType(checkInTicket.getParkingSpot()
            .getVehicleType()
            .getName())
        .memberName(checkInTicket.getMember()
            .getName())
        .parkingSpot(spot)
        .parkingLotAddress(fullAddress)
        .officerName(checkInTicket.getOfficer()
            .getName())
        .companyName(companyName)
        .build();
    BeanUtils.copyProperties(checkInTicket, response);

    return response;
  }

  public static GetCheckOutTicketDetailResponse toGetCheckOutTicketDetailResponse(CheckInTicket checkInTicket,
      ParkingPriceResponse priceResponse, LocalDateTime checkOutDate) {

    ParkingSpot parkingSpot = checkInTicket.getParkingSpot();
    validateParkingSpot(parkingSpot);
    String spot = String.format("%s%s", parkingSpot.getArea(), parkingSpot.getCode());

    Member member = checkInTicket.getMember();

    return GetCheckOutTicketDetailResponse.builder()
        .parkingType(parkingSpot.getVehicleType()
            .getId())
        .parkingSpot(spot)
        .memberName(getMemberName(member))
        .memberExpiredDate(member.getMemberExpiredDate())
        .checkInDate(checkInTicket.getCreatedDate())
        .durationInSeconds(priceResponse.getDurationInSeconds())
        .price(priceResponse.getPrice())
        .discount(priceResponse.getDiscount())
        .finalPrice(priceResponse.getFinalPrice())
        .createdDate(checkOutDate)
        .build();
  }

  private static String getMemberName(Member member) {

    return Objects.nonNull(member) ? member.getName() : null;
  }

  public static CheckOutTicketResponse toCheckOutTicketResponse(CheckOutTicket checkOutTicket) {

    CheckInTicket checkInTicket = checkOutTicket.getCheckInTicket();
    ParkingSpot parkingSpot = checkInTicket.getParkingSpot();
    ParkingLot parkingLot = parkingSpot.getParkingLot();
    Address parkingLotAddress = parkingLot.getAddress();

    String spot = String.format("%s%s", parkingSpot.getArea(), parkingSpot.getCode());
    String fullAddress = String.format("%s, %s, %s, %s, %s",
        parkingLotAddress.getStreet(), parkingLotAddress.getCity(), parkingLotAddress.getState(),
        parkingLotAddress.getCountry(), parkingLotAddress.getPostalCode());
    String companyName = parkingLot.getCompany()
        .getName();
    long duration =
        TimeUtil.getParkingDurationInSeconds(checkInTicket.getCreatedDate(), checkOutTicket.getCreatedDate());

    CheckOutTicketResponse response = CheckOutTicketResponse.builder()
        .checkInTicketId(checkInTicket.getId())
        .plateNumber(checkInTicket.getPlateNumber())
        .parkingType(parkingSpot.getVehicleType()
            .getName())
        .parkingSpot(spot)
        .parkingLotAddress(fullAddress)
        .officerName(checkOutTicket.getOfficer()
            .getName())
        .companyName(companyName)
        .checkInDate(checkInTicket.getCreatedDate())
        .duration(duration)
        .paymentMethodName(checkOutTicket.getPaymentMethod()
            .getName())
        .build();
    BeanUtils.copyProperties(checkOutTicket, response);

    return response;
  }
}
