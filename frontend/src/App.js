import React from "react";
import { SideMenu, Navbar, ReportButton, TicketDetail } from "./components";
import "./App.scss";

function App() {
  return (
    <div className="app_container container-fluid">
      <div className="row height-100vh">
        <div className="col-2">
          <SideMenu />
        </div>
        <div className="col box">
          <Navbar />
          <div className="row fill-remaining">
            <div className="col">
              <h2>Check In Ticketing</h2>
            </div>
            <div className="col-md-3">
              <TicketDetail />
            </div>
          </div>
        </div>
        <ReportButton />
      </div>
    </div>
  );
}

export default App;
