import { Button, Col, Container, Form, Row } from "react-bootstrap";
import { useTranslation } from "react-i18next";
import { useForm } from "../../hooks/useForm";
import { useFamilyContext } from "../../contexts/FamilyContext";
import { useEffect, useState } from "react";
import { FamilyMember } from "../FamilyMembers/FamilyMember";
import { ErrorModal } from "../Modal/ErrorModal";

export const NewFamily = () => {
  const { t } = useTranslation();
  const { onCreateFamilySubmitHandler, errors } = useFamilyContext();
  const [showForm, setShowForm] = useState(false);
  const [showMembersForm, setShowMembersForm] = useState(false);
  const [errorModalShow, setErrorModalShow] = useState(false);
  const [count, setCount] = useState();
  const [familyName, setFamilyName] = useState();
  const [name, setName] = useState({});
  const FamilyFormKeys = {
    Name: "name",
    Count: "count",
  };
  const onCloseClick = () => {
    setErrorModalShow(null);
    formValues[FamilyFormKeys.Name] = "";
    setName({});
  };
  useEffect(() => {
    setName({});
    if (errors === null) {
      setName({});
    } else {
      for (const [key, value] of Object.entries(errors)) {
        switch (key) {
          case "name":
            setName(value);
            setErrorModalShow(true);
            break;
          default:
            break;
        }
      }
    }
  }, [errors]);

  const { formValues, onChangeHandler, onSubmit, validated } = useForm(
    {
      [FamilyFormKeys.Name]: "",
      [FamilyFormKeys.Count]: "",
    },
    onCreateFamilySubmitHandler
  );
  const ConfirmShowForm = () => {
    setShowForm(true);
  };
  const onChangeCountHandler = (event) => {
    const value = event.target.value;
    setShowMembersForm(true);
    setCount(value);
  };
  const onChangeFamilyName = (event) => {
    const value = event.target.value;
    setShowMembersForm(true);
    setFamilyName(value);
  };
  return (
    <>
      <Container className='m-auto container-fluid pt-5 pb-5 mt-5 border-bottom border-light'>
        <Row className='align-items-center col-md-12 m-auto'>
          {!showForm && (
            <Col className='col-xl-4 col-md-6 col-xs-6 '>
              <p className='form-title'>
                {t("nav.Family")}{" "}
                <span
                  onClick={ConfirmShowForm}
                  className='ms-2 col-1 m-auto border border-light rounded px-3 py-2'
                  style={{ fontSize: "1rem" }}>
                  <i className='fas fa-plus'></i>
                </span>
              </p>
            </Col>
          )}
          {showForm && (
            <Form
              noValidate
              validated={validated}
              method='POST'
              onSubmit={onSubmit}
              className='row g-3 m-auto mt-1 mb-1  p-3 pb-4'>
              <Col className='m-auto p-2 col-xl-6 col-md-6 col-xs-12'>
                <Row className='align-items-end mb-3'>
                  <Col className='col-5'>
                    <Form.Group controlId='formBasicName'>
                      <Form.Label>{t("familyName")}</Form.Label>
                      <Form.Control
                        required
                        name={FamilyFormKeys.Name}
                        value={formValues[FamilyFormKeys.Name]}
                        onChange={(event) => {
                          onChangeFamilyName(event);
                          onChangeHandler(event);
                        }}
                        type='text'
                      />
                      {/* <Form.Control.Feedback
                        type='invalid'
                        className='text-danger'>
                        {t("validation")}
                      </Form.Control.Feedback>
                      {Object.keys(name).length !== 0 && (
                        <Form.Control.Feedback
                          type='invalid'
                          className='text-danger'>
                          {name}
                        </Form.Control.Feedback>
                      )} */}
                      {errorModalShow && (
                        <ErrorModal
                          name={name}
                          onCloseClick={onCloseClick}></ErrorModal>
                      )}
                    </Form.Group>
                  </Col>
                  <Col className='col-3'>
                    <Form.Group controlId='formCountOfMembers'>
                      <Form.Label>{t("countOfMembers")}</Form.Label>
                      <Form.Control
                        onChange={(event) => {
                          onChangeCountHandler(event);
                          onChangeHandler(event);
                        }}
                        type='text'
                        name={FamilyFormKeys.Count}
                        value={formValues[FamilyFormKeys.Count]}
                      />
                    </Form.Group>
                  </Col>
                  <Col className='col-2 ms-2'>
                    <Button variant='light' type='submit'>
                      {t("forms.Button.Submit")}
                    </Button>
                  </Col>
                </Row>
              </Col>
            </Form>
          )}
        </Row>
        <Row className='col-md-12  m-auto '>
          {count > 0 && (
            <FamilyMember count={count} familyName={familyName}></FamilyMember>
          )}
        </Row>
      </Container>
    </>
  );
};
