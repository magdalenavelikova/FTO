import { useContext, useEffect } from "react";

import { Navigate } from "react-router-dom";
import { AuthContext } from "../../contexts/AuthContext";

export const Logout = () => {
  const { onLogoutHandler } = useContext(AuthContext);
  useEffect(() => {
    onLogoutHandler();
  }, [onLogoutHandler]);
  return <Navigate to='/' />;
};
