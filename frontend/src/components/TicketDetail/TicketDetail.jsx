import React from "react";
import "./TicketDetail.scss";
import Clock from "../Clock/Clock";
import ParkingLotDetail from "../ParkingLotDetail/ParkingLotDetail";

const TicketDetail = ({ parkingLotDetail, member }) => {
  const handleRefresh = () => {
    window.location.reload();
  };

  function getExpiredDate(date) {
    if (!date) return "-";
    const formattedDate = new Date(date).toLocaleDateString("en-US", {
      day: "numeric",
      month: "long",
      year: "numeric",
    });
    return formattedDate;
  }

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
            <td className="text-end">CAR</td>
          </tr>
          <tr>
            <td className="text-start">Member Name</td>
            <td className="text-end">{member.name ?? "-"}</td>
          </tr>
          <tr>
            <td className="text-start">Member Expired</td>
            <td className="text-end">
              {getExpiredDate(member?.memberExpiredDate)}
            </td>
          </tr>
        </tbody>
      </table>
      <div className="custom-hr" />
      <div id="ticketButton" className="align-center">
        <button className="primary-btn long-btn">Print Ticket</button>
        <p className="font-14" onClick={handleRefresh}>
          Refresh page(F5)
        </p>
      </div>
    </div>
  );
};

export default TicketDetail;
