import { createContext, useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuthContext } from "./AuthContext";
import { familyServiceFactory } from "../services/familyServiceFactory";

export const FamilyContext = createContext();
export const FamilyProvider = ({ children }) => {
  const [families, setFamilies] = useState([]);
  const navigate = useNavigate();
  const { token } = useAuthContext();
  const familyService = familyServiceFactory(token);
  const [error, setError] = useState({});
  const [errors, setErrors] = useState({});
  const [success, setSuccess] = useState(false);
  useEffect(() => {
    try {
      Promise.all([familyService.getAll(token)]).then(([families]) => {
        setFamilies(families);
      });
    } catch (error) {
      navigate("/maintenance");
    }
  }, []);
  useEffect(() => {
    try {
      Promise.all([familyService.getAll(token)]).then(([families]) => {
        setFamilies(families);
      });
    } catch (error) {
      navigate("/maintenance");
    }
  }, [token]);

  const onCreateFamilySubmitHandler = async (data) => {
    const result = await familyService.create(data, token);
    setError({});

    if (result.status == "400") {
      setErrors(result.fieldErrors);
    } else {
      setFamilies((state) => [...state, result]);
      setErrors({});
      navigate("/family");
    }
  };
  const clearErrors = () => {
    setError({});
  };

  const context = {
    onCreateFamilySubmitHandler,
    clearErrors,
    success,
    error,
    errors,
    families,
  };

  return (
    <>
      <FamilyContext.Provider value={context}>
        {children}
      </FamilyContext.Provider>
    </>
  );
};
export const useFamilyContext = () => {
  const context = useContext(FamilyContext);
  return context;
};
