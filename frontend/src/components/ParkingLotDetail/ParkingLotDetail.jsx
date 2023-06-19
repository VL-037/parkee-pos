import axios from "axios";
import React, { useState, useEffect } from "react";
import { ApiPath } from "../../constants";
import "./ParkingLotDetail.scss";

const ParkingLotDetail = () => {
  const params = {
    id: "8d24318a-17a4-448a-9e2d-f351f1244a33",
  };

  const [detail, setDetail] = useState({});

  const fetchParkingLotDetail = async () => {
    try {
      const res = await axios.get(`http://localhost:8080/${ApiPath.Parking}`, {
        params,
      });
      setDetail(res.data.data);
      console.log(detail);
    } catch (error) {
      setDetail({});
    }
  };

  useEffect(() => {
    fetchParkingLotDetail();
  }, []);

  return (
    <div>
      <p className="font-14">{`${detail.companyName},`}</p>
      <p className="font-14">{detail.address}</p>
      <p
        id="occupiedSpots"
        className="font-14 bold-700"
      >{`Occupied: ${detail.occupiedSpots}/${detail.vehicleCapacity}`}</p>
    </div>
  );
};

export default ParkingLotDetail;
