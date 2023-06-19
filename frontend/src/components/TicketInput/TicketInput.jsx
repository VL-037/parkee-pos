import axios from "axios";
import React, { useEffect, useState } from "react";
import { ApiPath } from "../../constants";
import "./TicketInput.scss";

const TicketInput = ({
  vehicleTypes,
  handlePlateNumber,
  plateNumber,
  vehicleType,
  handleVehicleType,
}) => {
  const [a, setA] = useState({});

  const fetchMemberDetail = async () => {
    try {
      const res = await axios.get(`localhost:8080/${ApiPath.Member}`, {
        data: {
          plateNumber,
        },
      });
      setA(res.data.data);
    } catch (error) {
      setA({});
    }
  };

  useEffect(() => {
    fetchMemberDetail();
    console.log(a);
  }, []);

  return (
    <div id="ticketInput" className="row">
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
      <div className="inputs col-8">
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
    </div>
  );
};

export default TicketInput;
