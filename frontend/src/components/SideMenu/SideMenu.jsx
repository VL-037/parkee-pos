import React, { useEffect, useState } from "react";
import axios from "axios";
import "./SideMenu.scss";
import { images, ApiPath } from "../../constants";

const SideMenu = () => {
  const OFFICER_IDS = ["ID23894", "ID55012", "ID75269", "ID12456", "ID98637"];
  const randomIndex = Math.floor(Math.random() * OFFICER_IDS.length);

  const [officer, setOfficer] = useState({});

  const fetchOfficerDetail = async () => {
    try {
      const res = await axios.get(
        `http://localhost:8080/${ApiPath.Officer}/${OFFICER_IDS[randomIndex]}`
      );
      setOfficer(res.data.data);
    } catch (error) {
      setOfficer({});
    }
  };

  useEffect(() => {
    fetchOfficerDetail();
  }, []);

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
