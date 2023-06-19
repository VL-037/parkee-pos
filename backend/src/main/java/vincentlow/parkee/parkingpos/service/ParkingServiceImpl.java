package vincentlow.parkee.parkingpos.service;

import static vincentlow.parkee.parkingpos.util.ValidatorUtil.validateParkingLot;
import static vincentlow.parkee.parkingpos.util.ValidatorUtil.validateRequest;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import vincentlow.parkee.parkingpos.model.constant.ErrorMessage;
import vincentlow.parkee.parkingpos.model.entity.ParkingLot;
import vincentlow.parkee.parkingpos.model.entity.ParkingSpot;
import vincentlow.parkee.parkingpos.model.request.GetParkingSpotsRequest;
import vincentlow.parkee.parkingpos.repository.ParkingLotRepository;
import vincentlow.parkee.parkingpos.repository.ParkingSpotRepository;
import vincentlow.parkee.parkingpos.util.StringUtil;

@Service
public class ParkingServiceImpl implements ParkingService {

  @Autowired
  private ParkingLotRepository parkingLotRepository;

  @Autowired
  private ParkingSpotRepository parkingSpotRepository;

  @Override
  public ParkingLot getParkingLotDetail(String id) {

    validateRequest(StringUtils.isNotBlank(id), ErrorMessage.PARKING_LOT_ID_MUST_BE_NOT_BLANK);
    return validateParkingLot(parkingLotRepository.findByIdAndMarkForDeleteFalse(id));
  }

  @Override
  public int countOccupiedSpots(String id) {

    validateRequest(StringUtils.isNotBlank(id), ErrorMessage.PARKING_LOT_ID_MUST_BE_NOT_BLANK);
    return parkingSpotRepository.countByParkingLotIdAndIsOccupiedTrue(id);
  }

  @Override
  public Page<ParkingSpot> getParkingSpots(int pageNumber, int pageSize, GetParkingSpotsRequest request) {

    validateRequest(Objects.nonNull(request), ErrorMessage.REQUEST_MUST_NOT_BE_NULL);
    StringUtil.trimStrings(request);
    validateRequest(StringUtils.isNotBlank(request.getParkingLotId()), ErrorMessage.PARKING_LOT_ID_MUST_BE_NOT_BLANK);

    validateParkingLot(parkingLotRepository.findByIdAndMarkForDeleteFalse(request.getParkingLotId()));

    return parkingSpotRepository.findAllByParkingLotIdAndIsOccupiedFalseAndMarkForDeleteFalseOrderByAreaAscCodeAsc(
        request.getParkingLotId(), PageRequest.of(pageNumber, pageSize));
  }
}
