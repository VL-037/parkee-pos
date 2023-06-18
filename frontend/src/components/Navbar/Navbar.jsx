import React from "react";
import { CiSettings } from "react-icons/ci";
import { VscCircleLargeFilled } from "react-icons/vsc";
import "./Navbar.scss";

const Navbar = () => {
  return (
    <nav>
      <div className="left-nav d-flex align-items-center">
        <VscCircleLargeFilled className="icon" style={{ color: "#0BEB00" }} />
        <span className="bold-700">CONVENTIONAL / PARKEE Office Agent</span>
      </div>
      <div className="right-nav d-flex align-items-center">
        <CiSettings className="icon" />
        <button className="primary-btn nav-btn">Logout</button>
      </div>
    </nav>
  );
};

export default Navbar;
