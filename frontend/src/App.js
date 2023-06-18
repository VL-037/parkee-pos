import React from "react";
import { SideMenu, Navbar, ReportButton } from "./components";
import "./App.scss";

function App() {
  return (
    <div className="app container">
      <SideMenu />
      <Navbar />
      <ReportButton />
    </div>
  );
}

export default App;
