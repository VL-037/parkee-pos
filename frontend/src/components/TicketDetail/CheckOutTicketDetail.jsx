import React from "react";

const CheckOutTicketDetail = ({ checkOutTicketDetail }) => {
  function formatDate(date) {
    if (!date) return "-";
    const formattedDate = new Date(date).toLocaleDateString("en-GB", {
      day: "numeric",
      month: "long",
      year: "numeric",
    });
    return formattedDate;
  }

  function getParkingDuration(seconds) {
    const hours = Math.floor(seconds / 3600);
    const minutes = Math.floor((seconds % 3600) / 60);
    const remainingSeconds = seconds % 60;

    let timeString = "";
    if (hours > 0) {
      timeString += hours + "h ";
    }
    if (minutes > 0) {
      timeString += minutes + "m ";
    }
    if (remainingSeconds > 0 || timeString === "") {
      timeString += remainingSeconds + "s";
    }

    return timeString.trim();
  }

  return (
    <table id="ticketTable" className="table table-borderless">
      <tbody>
        <tr>
          <td className="text-start">Parking Type</td>
          <td className="text-end">
            {checkOutTicketDetail.parkingType ?? "-"}
          </td>
        </tr>
        <tr>
          <td className="text-start">Parking Spot</td>
          <td className="text-end">
            {checkOutTicketDetail.parkingSpot ?? "-"}
          </td>
        </tr>
        <tr>
          <td className="text-start">Member Name</td>
          <td className="text-end">{checkOutTicketDetail.memberName ?? "-"}</td>
        </tr>
        <tr>
          <td className="text-start">Member Expired</td>
          <td className="text-end">
            {formatDate(checkOutTicketDetail?.memberExpiredDate)}
          </td>
        </tr>
        <tr>
          <td className="text-start">Check In Time</td>
          <td className="text-end">
            {formatDate(checkOutTicketDetail?.checkInDate)}
          </td>
        </tr>
        <tr>
          <td className="text-start">Check Out Time</td>
          <td className="text-end">
            {formatDate(checkOutTicketDetail?.createdDate)}
          </td>
        </tr>
        <tr>
          <td className="text-start">Duration</td>
          <td className="text-end">
            {checkOutTicketDetail?.durationInSeconds
              ? getParkingDuration(checkOutTicketDetail?.durationInSeconds)
              : "-"}
          </td>
        </tr>
        <tr>
          <td className="text-start">Price</td>
          <td className="text-end">{checkOutTicketDetail.price ?? "-"}</td>
        </tr>
        <tr>
          <td className="text-start">Discount</td>
          <td className="text-end">{checkOutTicketDetail.discount ?? "-"}</td>
        </tr>
        <tr>
          <td className="text-start bold">Final Price</td>
          <td className="text-end">{checkOutTicketDetail.finalPrice ?? "-"}</td>
        </tr>
      </tbody>
    </table>
  );
};

export default CheckOutTicketDetail;
