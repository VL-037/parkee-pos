import React from "react";
import "./Camera.scss";

const Camera = ({ types = [] }) => {
  const renderedCameras = Object.values(types).map((type) => {
    return (
      <div key={type.id} className="row camera-container">
        <div className="col-auto">
          <div className="camera-video"></div>
          <div className="camera-label bold-700 font-14">
            {type.label.toUpperCase()}
          </div>
        </div>
      </div>
    );
  });

  return (
    <div className="d-flex flex-wrap justify-content-between">
      {renderedCameras}
    </div>
  );
};

export default Camera;
