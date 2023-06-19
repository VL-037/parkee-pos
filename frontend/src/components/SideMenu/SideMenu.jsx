import React from "react";
import "./SideMenu.scss";
import { images } from "../../constants";

const SideMenu = ({ officer }) => {
  return (
    <div id="sideMenu" className="white-bg">
      <a href="/check-in">
        <img src={images.parkeeLogo} alt="Parkee Logo" id="parkeeLogo" />
      </a>
      <div className="center">
        <img src={images.avatarProfile} alt="Officer" id="avatarProfile" />
        <p className="bold-700">
          <u>{officer.name}</u>
        </p>
        <p>{officer.id}</p>
      </div>
      <div id="pageList">
        <a href="/check-in" className="page">
          <img src={images.entryLogo} alt="Check In" width={40} />
          <p className="font-24 bold-700">Check In</p>
        </a>
        <a href="check-out" className="page">
          <img src={images.exitLogo} alt="Check Out" width={40} />
          <p className="font-24 bold-700">Check Out</p>
        </a>
      </div>
    </div>
  );
};

export default SideMenu;
