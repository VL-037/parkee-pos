import React from "react";
import "./ReportButton.scss";
import { images } from "../../constants";

const ReportButton = () => {
  return (
    <div id="report-button-container" className="col-auto">
      <button className="secondary-btn" id="report-button">
        Report Problem
        <img src={images.problemReport} alt="" width={19} />
      </button>
      <p className="font-14">
        or{" "}
        <span className="bold-700 font-14" style={{ color: "#25D366" }}>
          WhatsApp
        </span>{" "}
        to{" "}
        <span className="font-14" style={{ textDecoration: "underline" }}>
          +62 8123-456-7890
        </span>
      </p>
    </div>
  );
};

export default ReportButton;
