import React, { useEffect, useState } from "react";
import "./Loader.scss";

const Loader = () => {
  const [showMessage, setShowMessage] = useState(false);

  useEffect(() => {
    setTimeout(() => setShowMessage(true), 1000);
  }, []);

  return (
    <div className="loader-container">
      <div className="loader"></div>
      {showMessage ? (
        <div className="d-block center">
          <h2>if loading gets too long, please check the console output.</h2>
          <h2>ERR_CONNECTION_REFUSED {"=>"} run the BACKEND</h2>
          <h2>Refresh after run the backend</h2>
        </div>
      ) : (
        []
      )}
    </div>
  );
};

export default Loader;
