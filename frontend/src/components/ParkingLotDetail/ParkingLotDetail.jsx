import React from "react";
import "./ParkingLotDetail.scss";

const ParkingLotDetail = ({ parkingLotDetail }) => {
  return (
    <div>
      <p className="font-14">{`${parkingLotDetail.companyName},`}</p>
      <p className="font-14">{parkingLotDetail.address}</p>
      <p
        id="occupiedSpots"
        className="font-14 bold-700"
      >{`Occupied: ${parkingLotDetail.occupiedSpots} / ${parkingLotDetail.vehicleCapacity}`}</p>
    </div>
  );
};

export default ParkingLotDetail;
