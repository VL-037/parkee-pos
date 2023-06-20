import React, { useState, useEffect } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import {
  SideMenu,
  Navbar,
  ReportButton,
  TicketDetail,
  TicketInputPage,
} from "./components";
import { TicketType } from "./constants";
import "./App.scss";
import axios from "axios";
import { ApiPath } from "./constants";
import Loader from "./components/Loader/Loader";

function App() {
  const [isLoading, setIsLoading] = useState(true);
  const [officer, setOfficer] = useState({});
  const [parkingLotDetail, setParkingLotDetail] = useState({});
  const [vehicleType, setVehicleType] = useState("");
  const [plateNumber, setPlateNumber] = useState("");
  const [member, setMember] = useState({});
  const [ticketType, setTicketType] = useState("");
  const [parkingSlipId, setParkingSlipId] = useState("");
  const [checkOutTicketDetail, setCheckOutTicketDetail] = useState({});
  const [paymentMethodId, setpaymentMethodId] = useState("");
  const [voucherCode, setVoucherCode] = useState("");

  const parkingLotId = "8d24318a-17a4-448a-9e2d-f351f1244a33";
  const params = {
    id: parkingLotId,
  };

  const handleVehicleType = (e) => {
    setVehicleType(e.target.value);
  };

  const handlePlateNumber = (e) => {
    const capilatized = e.target.value.toUpperCase();
    setPlateNumber(capilatized);
  };

  const handleParkingSlipId = (e) => {
    setParkingSlipId(e.target.value);
  };

  const handlePaymentMethodId = (e) => {
    setpaymentMethodId(e.target.value);
  };

  const handleVoucherCode = (e) => {
    setVoucherCode(e.target.value);
  };

  const fetchParkingLotDetail = async () => {
    try {
      const res = await axios.get(`http://localhost:8080/${ApiPath.Parking}`, {
        params,
      });
      setParkingLotDetail(res.data.data);
      setIsLoading(false);
    } catch (error) {
      setParkingLotDetail({});
      setIsLoading(true);
    }
  };

  const fetchOfficerDetail = async () => {
    const OFFICER_IDS = ["ID23894", "ID55012", "ID75269", "ID12456", "ID98637"];
    const randomIndex = Math.floor(Math.random() * OFFICER_IDS.length);
    const officerId = OFFICER_IDS[randomIndex];
    try {
      const res = await axios.get(
        `http://localhost:8080/${ApiPath.Officer}/${officerId}`
      );
      setOfficer(res.data.data);
    } catch (error) {
      setOfficer({});
      setIsLoading(true);
    }
  };

  const fetchMemberDetail = async () => {
    try {
      const res = await axios.get(`http://localhost:8080/${ApiPath.Member}`, {
        params: {
          plateNumber,
        },
      });
      setMember(res.data.data);
    } catch (error) {
      setMember({});
    }
  };

  const fetchCheckOutTicketDetail = async () => {
    try {
      const res = await axios.get(
        `http://localhost:8080/${ApiPath.TicketCheckOut}`,
        {
          params: {
            parkingLotId,
            parkingSlipId,
            plateNumber,
            voucherCode,
          },
        }
      );
      setCheckOutTicketDetail(res.data.data);
    } catch (error) {
      setCheckOutTicketDetail({});
    }
  };

  useEffect(() => {
    const paths = ["/check-in", "/check-out"];
    const curr_path = window.location.pathname;

    if (curr_path === paths[0]) {
      setTicketType(TicketType.CHECK_IN);
    } else if (curr_path === paths[1]) {
      setTicketType(TicketType.CHECK_OUT);
    } else {
      window.location.href = "/check-in";
    }

    fetchParkingLotDetail();
    fetchOfficerDetail();
  }, []);

  useEffect(() => {
    if (ticketType === TicketType.CHECK_IN) {
      fetchMemberDetail();
    } else if (ticketType === TicketType.CHECK_OUT) {
      fetchCheckOutTicketDetail();
    }
  }, [plateNumber, parkingSlipId, voucherCode]);

  return (
    <div className="app_container">
      {isLoading && <Loader />}
      {!isLoading && (
        <div className="app_container container-fluid">
          <div className="row height-100vh">
            <div className="col-2">
              <SideMenu officer={officer} />
            </div>
            <div className="col box">
              <Navbar />
              {parkingLotDetail && officer ? (
                <div className="row fill-remaining">
                  <div className="col">
                    <BrowserRouter>
                      <Routes>
                        <Route
                          path="/check-in"
                          element={
                            <TicketInputPage
                              ticketType={ticketType}
                              vehicleTypes={parkingLotDetail.vehicleTypes}
                              vehicleType={vehicleType}
                              handleVehicleType={handleVehicleType}
                              handlePlateNumber={handlePlateNumber}
                              plateNumber={plateNumber}
                            />
                          }
                        ></Route>
                        <Route
                          path="/check-out"
                          element={
                            <TicketInputPage
                              ticketType={ticketType}
                              vehicleTypes={parkingLotDetail.vehicleTypes}
                              paymentMethods={parkingLotDetail.paymentMethods}
                              handlePaymentMethodId={handlePaymentMethodId}
                              vehicleType={vehicleType}
                              handleVehicleType={handleVehicleType}
                              handlePlateNumber={handlePlateNumber}
                              plateNumber={plateNumber}
                              handleParkingSlipId={handleParkingSlipId}
                              handleVoucherCode={handleVoucherCode}
                              checkOutTicketDetail={checkOutTicketDetail}
                            />
                          }
                        ></Route>
                      </Routes>
                    </BrowserRouter>
                  </div>
                  <div className="col-md-3">
                    <TicketDetail
                      parkingLotDetail={parkingLotDetail}
                      handlePlateNumber={handlePlateNumber}
                      plateNumber={plateNumber}
                      vehicleType={vehicleType}
                      member={member}
                      officer={officer}
                      ticketType={ticketType}
                      parkingSlipId={parkingSlipId}
                      checkOutTicketDetail={checkOutTicketDetail}
                      paymentMethodId={paymentMethodId}
                    />
                  </div>
                </div>
              ) : (
                "LOADING"
              )}
            </div>
            <ReportButton />
          </div>
        </div>
      )}
    </div>
  );
}

export default App;
