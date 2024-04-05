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
  const [members, setMembers] = useState([]);
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
    if (result.status === "CONFLICT") {
      setErrors(result.fieldErrors);
    } else {
      setFamilies((state) => [...state, result]);
      setErrors({});
      navigate("/family");
    }
  };

  const onCreateMemberSubmitHandler = async (data) => {
    const result = await familyService.addMember(data, token);
    setError({});
    if (result.description == "Family member name is already exist!") {
      setErrors({ name: result.description });
      setSuccess({});
    }
    if (result.status == "400" || result.status == "401") {
      setErrors(result.fieldErrors);
    } else {
      setFamilies((state) => [...state, result]);
      setErrors({});
      navigate("/family");
    }
  };
  const onFamilyDelete = async (familyId) => {
    try {
      await familyService.remove(familyId);
    } catch (error) {
      setErrors(error);
    }
    setFamilies((state) => state.filter((x) => x.id !== familyId));
  };

  const clearErrors = () => {
    setError({});
  };

  const context = {
    onCreateFamilySubmitHandler,
    onCreateMemberSubmitHandler,
    onFamilyDelete,
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
