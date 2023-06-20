import React from "react";
import "./TicketDetail.scss";
import Clock from "../Clock/Clock";
import ParkingLotDetail from "../ParkingLotDetail/ParkingLotDetail";
import { CheckInTicketDetail, CheckOutTicketDetail } from ".";
import axios from "axios";
import { ApiPath, TicketType } from "../../constants";
import Loader from "../Loader/Loader";

const TicketDetail = ({
  parkingLotDetail,
  vehicleType,
  member,
  plateNumber,
  officer,
  ticketType,
  parkingSlipId,
  checkOutTicketDetail,
  paymentMethodId,
}) => {
  const handleRefresh = () => {
    window.location.reload();
  };

  const handlePrintCheckInTicket = async () => {
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
      handleRefresh();
    } catch (error) {
      window.alert(error.response.data.error);
    }
  };

  const handlePrintCheckOutTicket = async () => {
    const data = {
      parkingLotId: parkingLotDetail.id,
      parkingSlipId,
      officerId: officer.id,
      plateNumber: plateNumber,
      paymentMethodId,
      durationInSeconds: checkOutTicketDetail.durationInSeconds,
      price: checkOutTicketDetail.price,
      discount: checkOutTicketDetail.discount,
      finalPrice: checkOutTicketDetail.finalPrice,
      checkOutDate: checkOutTicketDetail.createdDate,
    };

    try {
      const res = await axios.post(
        `http://localhost:8080/${ApiPath.TicketCheckOut}`,
        data
      );
      console.log(res.data.data);
      handleRefresh();
    } catch (error) {
      window.alert(error.response.data.error);
    }
  };

  const isCheckOutDetailNull = Object.keys(checkOutTicketDetail).length === 0;
  const isPaymentMethodNull = paymentMethodId.length === 0;

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
        <CheckOutTicketDetail checkOutTicketDetail={checkOutTicketDetail} />
      )}
      <div className="custom-hr" />
      <div id="ticketButton" className="align-center">
        <button
          className={
            ticketType === TicketType.CHECK_IN
              ? `long-btn primary-btn ${
                  !vehicleType || !plateNumber ? "disabled-btn" : ""
                }`
              : `long-btn primary-btn ${
                  isCheckOutDetailNull || isPaymentMethodNull
                    ? "disabled-btn"
                    : ""
                }`
          }
          disabled={
            ticketType === TicketType.CHECK_IN
              ? !vehicleType || !plateNumber
              : isCheckOutDetailNull || isPaymentMethodNull
          }
          onClick={
            ticketType === TicketType.CHECK_IN
              ? handlePrintCheckInTicket
              : handlePrintCheckOutTicket
          }
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
