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
  const [parkingLotDetail, setParkingLotDetail] = useState({});
  const [plateNumber, setPlateNumber] = useState("");
  const [member, setMember] = useState({});

  const paths = ["/check-in", "/check-out"];
  if (!paths.includes(window.location.pathname)) {
    window.location.href = "/check-in";
  }

  const parkingLotId = "8d24318a-17a4-448a-9e2d-f351f1244a33";
  const params = {
    id: parkingLotId,
  };

  const handlePlateNumber = (e) => {
    const capilatized = e.target.value.toUpperCase();
    setPlateNumber(capilatized);
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
      setIsLoading(false);
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

  useEffect(() => {
    fetchParkingLotDetail();
  }, []);

  useEffect(() => {
    fetchMemberDetail();
  }, [plateNumber]);

  return (
    <div className="app_container">
      {isLoading && <Loader />}
      {!isLoading && (
        <div className="app_container container-fluid">
          <div className="row height-100vh">
            <div className="col-2">
              <SideMenu />
            </div>
            <div className="col box">
              <Navbar />
              {parkingLotDetail ? (
                <div className="row fill-remaining">
                  <div className="col">
                    <BrowserRouter>
                      <Routes>
                        <Route
                          path="/check-in"
                          element={
                            <TicketInputPage
                              ticketType={TicketType.CHECK_IN}
                              vehicleTypes={parkingLotDetail.vehicleTypes}
                              paymentMethods={parkingLotDetail.paymentMethods}
                              handlePlateNumber={handlePlateNumber}
                              plateNumber={plateNumber}
                            />
                          }
                        ></Route>
                        <Route
                          path="/check-out"
                          element={
                            <TicketInputPage
                              ticketType={TicketType.CHECK_OUT}
                              vehicleTypes={parkingLotDetail.vehicleTypes}
                              paymentMethods={parkingLotDetail.paymentMethods}
                              handlePlateNumber={handlePlateNumber}
                              plateNumber={plateNumber}
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
                      member={member}
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
