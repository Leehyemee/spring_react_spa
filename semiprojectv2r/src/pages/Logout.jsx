import React from "react";

const Logout = () => {
    //localStorage.clear();
    localStorage.removeItem("accessToken");

    return (
        location.href="/"
    );
}

export default Logout;
