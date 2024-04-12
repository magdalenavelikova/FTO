import {
  Container,
  Modal,
  Row,
  Button,
  Form,
  Spinner,
  Alert,
} from "react-bootstrap";
import { useTranslation } from "react-i18next";
import { useForm } from "../../hooks/useForm";
import * as formatString from "../../utils/StringUtils";
import { useState, useEffect } from "react";
import { useFamilyContext } from "../../contexts/FamilyContext";
import { red } from "@ant-design/colors";
import { MinusCircleOutlined, PlusOutlined } from "@ant-design/icons";

export const EditFamily = ({ onCloseClick, family }) => {
  const { t } = useTranslation();
  const { onEditFamilySubmitHandler, spinner, clearErrors, errors, error } =
    useFamilyContext();
  const [show, setShow] = useState(true);
  const [familyData, setFamilyData] = useState(family);
  const [isLoading, setIsLoading] = useState(false);
  const [validated, setValidated] = useState(false);
  const [validationError, setValidationError] = useState();
  const [validationErrorName, setValidationErrorName] = useState();
  const [validationErrorPin, setValidationErrorPin] = useState();
  useEffect(() => {
    setIsLoading(spinner);
  }, [spinner]);

  const handleMemberChange = (index, field, value) => {
    const updatedMembers = [...familyData.members];
    updatedMembers[index][field] = value;
    if (field === "name") {
      handleMemberNameChange(updatedMembers[index][field]);
    }
    if (field === "pinCode") {
      handleMemberPinCodeChange(updatedMembers[index][field]);
    }
    setFamilyData({ ...familyData, members: updatedMembers });
  };

  const handleMemberNameChange = (value) => {
    if (value.length < 3 || value.length > 20) {
      setValidationErrorName("Please enter between 3 and 20 characters.");
    } else {
      setValidationErrorName();
    }
  };

  const handleMemberPinCodeChange = (value) => {
    if (value.length !== 4) {
      setValidationErrorPin("Please enter 4 characters.");
    } else {
      setValidationErrorPin();
    }
  };

  const handleAddMember = () => {
    setFamilyData({
      ...familyData,
      members: [
        ...familyData.members,
        { id: Math.random(), name: "", pinCode: "", role: "", ageCategory: "" },
      ],
    });
  };

  const handleRemoveMember = (index) => {
    const updatedMembers = [...familyData.members];
    updatedMembers.splice(index, 1);
    setFamilyData({ ...familyData, members: updatedMembers });
  };

  const handleFamilyNameChange = (e) => {
    const { value } = e.target;
    if (value.length < 3 || value.length > 20) {
      setValidationError("Please enter between 3 and 20 characters.");
    } else {
      setValidationError();
    }
    setFamilyData({ ...familyData, name: value });
  };

  const handleSubmit = (e) => {
    const form = e.currentTarget;
    if (form.checkValidity() === false) {
      e.preventDefault();
      e.stopPropagation();
    }
    setValidated(true);
    e.preventDefault();
    if (
      form.checkValidity() === true &&
      !validationError &&
      !validationErrorName
    ) {
      onEditFamilySubmitHandler(familyData);
    }
  };

  const handleClose = () => {
    onCloseClick();
    setShow(false);
    clearErrors();
  };

  return (
    <>
      <Modal
        id='modal'
        size='xl'
        aria-labelledby='example-modal-sizes-title-lg'
        show={show}
        onHide={handleClose}
        backdrop='static'
        keyboard={false}>
        <Modal.Header closeButton>
          <Modal.Title>{t("Details")}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Container className='m-auto container-fluid-md  mt-2'>
            {(Object.keys(error).length !== 0 ||
              Object.keys(errors).length !== 0) && (
              <Row xs={1} md={2} className=' mt-4'>
                <Alert
                  className='col-md-6 m-auto  text-center'
                  variant='danger'>
                  {error ? error : errors}
                </Alert>
              </Row>
            )}
            <Form
              noValidate
              validated={validated}
              method='POST'
              onSubmit={handleSubmit}
              className='row g-3 m-auto mt-3 mb-3  p-2'>
              <Row className='col-12 m-auto'>
                {true === false && (
                  <Form.Control required value={familyData.id} type='text' />
                )}
                <Form.Group className='col-5 mb-3' controlId='formBasicName'>
                  <Form.Label> {t("familyName")}</Form.Label>
                  <Form.Control
                    required
                    value={familyData.name}
                    onChange={handleFamilyNameChange}
                    type='text'
                    isInvalid={!!validationError}
                  />
                  <Form.Control.Feedback type='invalid' className='text-danger'>
                    {validationError}
                  </Form.Control.Feedback>
                </Form.Group>

                {/* Render form for each member */}
                {familyData.members.map((member, index) => (
                  <Row className='col-12 m-auto' key={member.id}>
                    <h5>
                      {t("Family Member")} {index + 1}{" "}
                      <MinusCircleOutlined
                        title={
                          "Delete " +
                          formatString.formatStringToUpperCase(member.name)
                        }
                        style={{ color: red[5] }}
                        className='dynamic-delete-button'
                        onClick={() => handleRemoveMember(index)}
                      />
                    </h5>
                    {true === false && (
                      <Form.Control required value={member.id} type='text' />
                    )}
                    <Form.Group
                      className='m-auto col-3 mb-3'
                      controlId={"formBasicName" + member.id}>
                      <Form.Label>{t("nickName")}</Form.Label>
                      <Form.Control
                        required
                        value={member.name}
                        onChange={(e) =>
                          handleMemberChange(index, "name", e.target.value)
                        }
                        isInvalid={!!validationErrorName}
                        type='text'
                      />
                      <Form.Control.Feedback
                        type='invalid'
                        className='text-danger'>
                        {validationErrorName}
                      </Form.Control.Feedback>
                    </Form.Group>
                    <Form.Group
                      className=' m-auto col-3 mb-3'
                      controlId={"formPinCode" + member.id}>
                      <Form.Label>{t("pin")}</Form.Label>
                      <Form.Control
                        required
                        /*  id={`memberPin${index}`} */
                        value={member.pinCode}
                        autoComplete='pinCode'
                        onChange={(e) =>
                          handleMemberChange(index, "pinCode", e.target.value)
                        }
                        isInvalid={!!validationErrorPin}
                        type='text'
                      />
                      <Form.Control.Feedback
                        type='invalid'
                        className='text-danger'>
                        {validationErrorPin}
                      </Form.Control.Feedback>
                    </Form.Group>

                    <Form.Group
                      className='m-auto col-3 mb-3'
                      controlId={"formBasicAgeCategory" + member.id}>
                      <Form.Label>{t("ageCategory")}</Form.Label>
                      <Form.Select
                        required
                        size='sm'
                        value={member.ageCategory}
                        onChange={(e) =>
                          handleMemberChange(
                            index,
                            "ageCategory",
                            e.target.value
                          )
                        }>
                        <option label={t("select")} value=''>
                          {t("select")}
                        </option>
                        <option value='ADULT'>ADULT</option>
                        <option value='TEEN'>TEEN</option>
                        <option value='INFANT'>INFANT</option>
                      </Form.Select>
                      <Form.Control.Feedback
                        type='invalid'
                        className='text-danger'>
                        {t("validation")}
                      </Form.Control.Feedback>
                    </Form.Group>
                    <Form.Group
                      className='m-auto col-3 mb-3'
                      controlId={"formBasicRole" + member.id}>
                      <Form.Label>{t("familyRole")}</Form.Label>
                      <Form.Select
                        required
                        size='sm'
                        value={member.role}
                        onChange={(e) =>
                          handleMemberChange(index, "role", e.target.value)
                        }>
                        <option label={t("select")} value=''>
                          {t("select")}
                        </option>
                        <option value='PARTNER'>PARTNER</option>
                        <option value='CHILD'>CHILD</option>
                        <option value='PARENT'>PARENT</option>
                        <option value='SIBLING'>SIBLING</option>
                        <option value='GRANDCHILD'>GRANDCHILD</option>
                        <option value='GRANDPARENT'>GRANDPARENT</option>
                        <option value='SON_IN_LAW'>SON_IN_LAW</option>
                        <option value='DAUGHTER_IN_LAW'>DAUGHTER_IN_LAW</option>
                        <option value='UNRELATED'>UNRELATED</option>
                      </Form.Select>
                      <Form.Control.Feedback
                        type='invalid'
                        className='text-danger'>
                        {t("validation")}
                      </Form.Control.Feedback>
                    </Form.Group>
                  </Row>
                ))}
              </Row>
              <Row>
                <Button
                  className='col-4 btn-light m-auto mt-4 mb-3'
                  onClick={handleAddMember}>
                  <PlusOutlined /> Add member
                </Button>
              </Row>
              <Row>
                <Button
                  className='col-4 btn-light m-auto mt-4 mb-3'
                  variant='secondary'
                  type='submit'>
                  {isLoading && (
                    <Spinner
                      as='span'
                      animation='border'
                      size='sm'
                      role='status'
                      aria-hidden='true'
                      className='me-1'
                    />
                  )}
                  {t("forms.Button.Submit")}
                </Button>
              </Row>
            </Form>
          </Container>
        </Modal.Body>
      </Modal>
    </>
  );
};
