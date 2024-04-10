import { createContext, useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuthContext } from "./AuthContext";
import { familyServiceFactory } from "../services/familyServiceFactory";
import { useLocalStorage } from "../hooks/useLocalStorage";

export const FamilyContext = createContext();
export const FamilyProvider = ({ children }) => {
  const [families, setFamilies] = useState([]);
  const navigate = useNavigate();
  const { token } = useAuthContext();
  const familyService = familyServiceFactory(token);
  const [error, setError] = useState({});
  const [errors, setErrors] = useState({});
  const [success, setSuccess] = useState(false);
  const [successMember, setSuccessMember] = useState(false);
  const [jwt, setJwt] = useLocalStorage("jwt", {});
  const [spinner, setSpinner] = useState(false);
  useEffect(() => {
    if (Object.keys(jwt).length !== 0) {
      try {
        Promise.all([familyService.getAll(token)]).then(([families]) => {
          setFamilies(families);
        });
      } catch (error) {
        navigate("/maintenance");
      }
    }
  }, [token]);

  const onCreateFamilySubmitHandler = async (data) => {
    setSuccess(false);
    setErrors({});
    setError({});
    setSpinner(true);
    const result = await familyService.create(data, token);

    if (result.status === "CONFLICT") {
      setSpinner(false);
      setErrors(result.fieldErrors);
    } else {
      setFamilies((state) => [...state, result]);
      setSuccess(true);
      setSpinner(false);
      setErrors({});
      navigate("/family");
    }
  };

  const onCreateMemberSubmitHandler = async (data) => {
    setSpinner(true);
    setError({});
    setSuccessMember(false);
    setErrors({});
    const result = await familyService.addMember(data, token);

    if (result.description === "Family member name is already exist!") {
      setSpinner(false);
      setError(result.description);
      setSuccessMember(false);
    } else {
      if (result.status === "CONFLICT") {
        setErrors(result.fieldErrors);
        setSuccessMember(false);
        setSpinner(false);
      } else {
        setFamilies((state) =>
          state.map((f) => (f.id === result.id ? result : f))
        );
        setErrors({});
        setSpinner(false);
        setError({});
        setSuccessMember(true);

        navigate("/family");
      }
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
    setErrors({});
  };

  const context = {
    onCreateFamilySubmitHandler,
    onCreateMemberSubmitHandler,
    onFamilyDelete,
    clearErrors,
    successMember,
    success,
    spinner,
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
