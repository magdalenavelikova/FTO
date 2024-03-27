import { useTranslation } from "react-i18next";
import { Form } from "react-bootstrap";
import React from "react";
import Select from "react-select";

export const LanguageSwitcher = () => {
  const { i18n } = useTranslation();

  const handleLangChange = (selectedOption) => {
    const lang = selectedOption.value;
    localStorage.setItem("lang", lang);
    i18n.changeLanguage(lang);
  };
  const customStyles = {
    control: (provided, state) => ({
      ...provided,
      fontSize: "0.8rem",
      width: state.isFocused ? "6rem" : "6rem",
      boxShadow:
        state.isFocused || state.isHovered
          ? "0 0 0 0 #77b890"
          : "0 0 0 0 #77b890", // Set box shadow
      borderColor: state.isFocused ? "#77b890" : "#77b890", // Set border color
      borderBottom: state.isFocused ? "2px solid #77b890" : "2px solid #77b890", // Set bottom border
    }),
    menu: (provided, state) => ({
      ...provided,
      fontSize: "0.8rem",
      width: state.isFocused ? "6rem" : "6rem", // Set width to 4rem
      boxShadow: state.isFocused ? "0 0 0 0 #77b890" : "0 0 0 0 #77b890", // Set box shadow
      borderColor: state.isFocused ? "#77b890" : "#77b890", // Set border color
      borderBottom: state.isFocused ? "2px solid #77b890" : "2px solid #77b890", // Set bottom border
    }),
    option: (provided, state) => ({
      ...provided,
      fontSize: "0.8rem",
      backgroundColor: state.isFocused ? "#77b890" : "transparent",
      color: state.isFocused ? "#fff" : "#333", // Custom text color
      "&:hover": {
        backgroundColor: "#77b890", // Hover color
        color: "#fff", // Hover text color
      },
    }),
  };

  const options = [
    { value: "en", label: "EN" },
    { value: "bg", label: "BG" },
  ];
  return (
    <Select
      onChange={handleLangChange}
      options={options}
      styles={customStyles}
      value={{
        value: localStorage.getItem("lang"),
        label: localStorage.getItem("lang"),
      }}
    />
    /*  <Form.Select
      className='form-control-custom'
      size='sm'
      label='Select language'
      aria-label='Default select example'
      onChange={handleLangChange}
      value={localStorage.getItem("lang")}>
      <option value='bg'>BG</option>
      <option value='en'>EN</option>
    </Form.Select> */
  );
};
