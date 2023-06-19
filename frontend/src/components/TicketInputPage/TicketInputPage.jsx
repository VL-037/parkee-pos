import React from "react";
import { TicketType, CameraType } from "../../constants";
import TicketInput from "../TicketInput/TicketInput";
import Camera from "../Camera/Camera";
import "./TicketInputPage.scss";

const TicketInputPage = ({
  ticketType,
  vehicleTypes,
  paymentMethods,
  vehicleType,
  handleVehicleType,
  handlePlateNumber,
  plateNumber,
}) => {
  const cameraTypes = [CameraType.ENTRY_CAMERA, CameraType.FACE_ENTRY_CAMERA];

  let page;
  if (ticketType === TicketType.CHECK_IN) {
    page = (
      <div id="check-in-page">
        <h2>Check In Ticketing</h2>
        <Camera types={cameraTypes} />
        <TicketInput
          vehicleTypes={vehicleTypes}
          vehicleType={vehicleType}
          handleVehicleType={handleVehicleType}
          handlePlateNumber={handlePlateNumber}
          plateNumber={plateNumber}
        />
      </div>
    );
  } else if (ticketType === TicketType.CHECK_OUT) {
    cameraTypes.push(CameraType.EXIT_CAMERA, CameraType.FACE_EXIT_CAMERA);
    page = (
      <div id="check-out-page">
        <h2>Check Out Ticketing</h2>
        <Camera types={cameraTypes} />
        <TicketInput
          vehicleTypes={vehicleTypes}
          paymentMethods={paymentMethods}
        />
      </div>
    );
  }

  return (
    <div id="ticketInputPage" className="container">
      {page}
    </div>
  );
};

export default TicketInputPage;
