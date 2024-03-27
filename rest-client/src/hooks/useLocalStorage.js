import {  useState } from "react";

export const useLocalStorage = (key, initialValue) => {
//const key= useId();
  const [state, setState] = useState(() => {

    const persistedStateSerialized = localStorage.getItem(key);
    if (persistedStateSerialized) {
      const persistedStateDeserialized = JSON.parse(persistedStateSerialized);
      return persistedStateDeserialized;
    }
    return initialValue;
  });

  const setLocalStorageState = (value) => {
    setState(value);
    localStorage.setItem(key, JSON.stringify(value));
  };
  return [state, setLocalStorageState];
};
