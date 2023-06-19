import React from "react";

const CheckInTicketDetail = ({ vehicleType, member }) => {
  function formatDate(date) {
    if (!date) return "-";
    const formattedDate = new Date(date).toLocaleDateString("en-GB", {
      day: "numeric",
      month: "long",
      year: "numeric",
    });
    return formattedDate;
  }

  return (
    <table id="ticketTable" className="table table-borderless">
      <tbody>
        <tr>
          <td className="text-start">Parking Type</td>
          <td className="text-end">{vehicleType.length ? vehicleType : "-"}</td>
        </tr>
        <tr>
          <td className="text-start">Member Name</td>
          <td className="text-end">{member.name ?? "-"}</td>
        </tr>
        <tr>
          <td className="text-start">Member Expired</td>
          <td className="text-end">{formatDate(member?.memberExpiredDate)}</td>
        </tr>
      </tbody>
    </table>
  );
};

export default CheckInTicketDetail;
