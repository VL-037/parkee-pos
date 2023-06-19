import axios from "axios";
import React, { useEffect, useState } from "react";
import { TicketType } from "../../constants";
import { ApiPath } from "../../constants";
import "./TicketInput.scss";

const TicketInput = ({
  vehicleTypes,
  handlePlateNumber,
  plateNumber,
  vehicleType,
  handleVehicleType,
  ticketType,
  paymentMethods,
}) => {
  return (
    <div className="row ticketInput">
      {ticketType === TicketType.CHECK_OUT && (
        <div className="inputs col-3">
          <label htmlFor="parkingSlipId" className="bold-700">
            Parking Slip ID <span className="shortcut">(F6)</span>
          </label>
          <input
            type="text"
            className="form-control"
            id="parkingSlipId"
            placeholder="Enter Parking slip ID..."
          />
        </div>
      )}
      <div className="inputs col-3">
        <label htmlFor="vehicleTypeId" className="bold-700">
          Vehicle Type <span className="shortcut">(F7)</span>
        </label>
        <select
          name="vehicleTypeId"
          id="vehicleTypeId"
          className="form-select"
          value={vehicleType}
          onChange={handleVehicleType}
        >
          <option value="">select...</option>
          {vehicleTypes.map((type) => (
            <option key={type.id} value={type.id}>
              {type.name.toUpperCase()}
            </option>
          ))}
        </select>
      </div>
      <div
        className={`inputs ${
          ticketType === TicketType.CHECK_IN ? "col-8" : "col-5"
        }`}
      >
        <label htmlFor="plateNumber" className="bold-700">
          Vehicle Plate Number <span className="shortcut">(F8)</span>
        </label>
        <input
          type="text"
          className="form-control bold-700"
          id="vehiclePlateNumber"
          placeholder="ex: AB 1234 CD"
          onChange={handlePlateNumber}
          value={plateNumber}
        />
      </div>
      {ticketType === TicketType.CHECK_OUT && (
        <div className="row ticketInput align-items-center">
          <div className="inputs col-3">
            <label htmlFor="paymentMethodId" className="bold-700">
              Payment Method <span className="shortcut">(F9)</span>
            </label>
            <select
              name="paymentMethodId"
              id="paymentMethodId"
              className="form-select"
            >
              <option value="">select...</option>
              {paymentMethods.map((method) => (
                <option key={method.id} value={method.id}>
                  {method.name}
                </option>
              ))}
            </select>
          </div>
          <div className="inputs col-3">
            <label htmlFor="voucherCode" className="bold-700">
              Voucher Code <span className="shortcut">(F10)</span>
            </label>
            <input
              type="text"
              className="form-control bold-700"
              id="voucherCode"
            />
          </div>
          <div className="col-5">
            <label htmlFor="applyVoucherBtn" className="d-block opacity-0">
              asdasd
            </label>
            <button id="applyVoucherBtn" className="primary-btn">
              Apply
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default TicketInput;
