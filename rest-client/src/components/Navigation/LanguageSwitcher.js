import { useTranslation } from "react-i18next";
import { Form } from "react-bootstrap";
import React from "react";
import Select from "react-select";

export const LanguageSwitcher = () => {
  const { i18n } = useTranslation();
  const { t } = useTranslation();
  const handleLangChange = (selectedOption) => {
    const lang = selectedOption.value;
    localStorage.setItem("lang", lang);
    i18n.changeLanguage(lang);
  };
  const customStyles = {
    control: (provided, state) => ({
      ...provided,
      fontSize: "0.8rem",
      width: "5rem",
      boxShadow: "0 0 0 0 #77b890",
      borderColor: "#77b890",
      /*  borderBottom: "2px solid #77b890", */
      color: "#77b890",
      backgroundColor: "transparent",
      "&:hover": {
        boxShadow: "0 0 0 0 #77b890",
        borderColor: "#77b890",
      },
    }),
    singleValue: (provided, state) => ({
      ...provided,
      fontSize: "0.8rem",
      color: "#77b890",
    }),
    menu: (provided, state) => ({
      ...provided,
      fontSize: "0.8rem",
      width: "5rem",

      boxShadow: "0 0 0 0 #77b890",

      border: "1px solid #77b890",
      color: "#77b890",
      backgroundColor: "transparent",
    }),
    option: (provided, state) => ({
      ...provided,
      fontSize: "0.8rem",
      backgroundColor: "transparent",
      color: "#77b890",
      "&:hover": {
        backgroundColor: "white",
        /*  borderBottom: state.isFocused ? "2px solid #77b890" : "2px solid white", */
      },
    }),
    placeholder: (provided, state) => ({
      ...provided,
      color: "#77b890",
    }),
  };

  const options = [
    { value: "en", label: "EN" },
    { value: "bg", label: "BG" },
  ];
  return (
    <>
      <span style={{ color: "#77b890" }}> {t("nav.Language")}</span>
      <Select
        className='mt-1'
        onChange={handleLangChange}
        options={options}
        styles={customStyles}
        value={{
          value: localStorage.getItem("lang"),
          label: localStorage.getItem("lang"),
        }}
      />
    </>
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
