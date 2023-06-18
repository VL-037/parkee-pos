import React from "react";
import { SideMenu, Navbar, ReportButton } from "./components";
import "./App.scss";

function App() {
  return (
    <div className="app_container container-fluid">
      <div className="row">
        <div className="col-2">
          <SideMenu />
        </div>
        <div className="col">
          <Navbar />
        </div>
        <ReportButton />
      </div>
    </div>
  );
}

export default App;
