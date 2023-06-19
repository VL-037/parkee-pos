import React from "react";
import "./TicketDetail.scss";
import Clock from "../Clock/Clock";
import ParkingLotDetail from "../ParkingLotDetail/ParkingLotDetail";
import { CheckInTicketDetail, CheckOutTicketDetail } from ".";
import axios from "axios";
import { ApiPath, TicketType } from "../../constants";

const TicketDetail = ({
  parkingLotDetail,
  vehicleType,
  member,
  plateNumber,
  officer,
  ticketType,
}) => {
  const handleRefresh = () => {
    window.location.reload();
  };

  const handlePrintTicket = async () => {
    const data = {
      plateNumber,
      vehicleTypeId: vehicleType,
      parkingLotId: parkingLotDetail.id,
      officerId: officer.id,
    };

    try {
      const res = await axios.post(
        `http://localhost:8080/${ApiPath.TicketCheckIn}`,
        data
      );
      console.log(res.data.data);
    } catch (error) {
      console.error(error.response.data);
    }
  };

  return (
    <div id="ticketDetail">
      <div id="generalInfo" className="text-center">
        <Clock />
        <ParkingLotDetail parkingLotDetail={parkingLotDetail} />
      </div>
      <div className="custom-hr" />
      {ticketType === TicketType.CHECK_IN ? (
        <CheckInTicketDetail vehicleType={vehicleType} member={member} />
      ) : (
        <CheckOutTicketDetail vehicleType={vehicleType} member={member} />
      )}
      <div className="custom-hr" />
      <div id="ticketButton" className="align-center">
        <button
          className={`long-btn primary-btn ${
            !vehicleType || !plateNumber ? "disabled-btn" : ""
          }`}
          disabled={!vehicleType || !plateNumber}
          onClick={handlePrintTicket}
        >
          Print Ticket
        </button>
        <p className="font-14" onClick={handleRefresh}>
          Refresh page(F5)
        </p>
      </div>
    </div>
  );
};

export default TicketDetail;
