import React from "react";
import "./TicketDetail.scss";
import Clock from "../Clock/Clock";
import ParkingLotDetail from "../ParkingLotDetail/ParkingLotDetail";
import axios from "axios";
import { ApiPath } from "../../constants";

const TicketDetail = ({
  parkingLotDetail,
  vehicleType,
  member,
  plateNumber,
  officer,
}) => {
  const handleRefresh = () => {
    window.location.reload();
  };

  function formatDate(date) {
    if (!date) return "-";
    const formattedDate = new Date(date).toLocaleDateString("en-GB", {
      day: "numeric",
      month: "long",
      year: "numeric",
    });
    return formattedDate;
  }

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
      <table id="ticketTable" className="table table-borderless">
        <tbody>
          <tr>
            <td className="text-start">Parking Type</td>
            <td className="text-end">
              {vehicleType.length ? vehicleType : "-"}
            </td>
          </tr>
          <tr>
            <td className="text-start">Member Name</td>
            <td className="text-end">{member.name ?? "-"}</td>
          </tr>
          <tr>
            <td className="text-start">Member Expired</td>
            <td className="text-end">
              {formatDate(member?.memberExpiredDate)}
            </td>
          </tr>
        </tbody>
      </table>
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
