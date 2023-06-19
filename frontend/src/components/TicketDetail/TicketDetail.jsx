import React, { useState, useEffect } from "react";
import "./TicketDetail.scss";
import Clock from "../Clock/Clock";
import ParkingLotDetail from "../ParkingLotDetail/ParkingLotDetail";

const TicketDetail = () => {
  // const [ticketDetail, setTicketDetail]

  return (
    <div id="ticketDetail">
      <div id="generalInfo" className="text-center">
        <Clock />
        <ParkingLotDetail />
        <div className="custom-hr" />
      </div>
      <table id="ticketTable" className="table table-borderless">
        <tbody>
          <tr>
            <td className="text-start">Parking Type</td>
            <td className="text-end">CAR</td>
          </tr>
          <tr>
            <td className="text-start">Member Name</td>
            <td className="text-end">John Smith</td>
          </tr>
          <tr>
            <td className="text-start">Member Expired</td>
            <td className="text-end">02 April 2024</td>
          </tr>
        </tbody>
      </table>
      <div id="ticketButton" className="align-center">
        <button className="primary-btn long-btn">Print Ticket</button>
        <a href="/" className="font-14">
          Refresh page(F5)
        </a>
      </div>
    </div>
  );
};

export default TicketDetail;
