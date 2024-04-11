import {
  Container,
  Modal,
  Row,
  Col,
  Button,
  Form,
  Spinner,
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
  const { onEditFamilySubmitHandler, spinner, clearErrors } =
    useFamilyContext();
  const [show, setShow] = useState(true);
  const [familyData, setFamilyData] = useState(family);
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    setIsLoading(spinner);
  }, [spinner]);
  const handleFamilyNameChange = (e) => {
    setFamilyData({ ...familyData, name: e.target.value });
  };
  console.log(familyData);

  const handleMemberChange = (index, field, value) => {
    const updatedMembers = [...familyData.members];
    updatedMembers[index][field] = value;
    setFamilyData({ ...familyData, members: updatedMembers });
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

  const handleSubmit = (e) => {
    e.preventDefault();
    // Call onSubmit prop with updated family data
    onSubmit(familyData);
  };

  const handleClose = () => {
    onCloseClick();
    setShow(false);
    clearErrors();
  };

  const { onSubmit, validated } = useForm(onEditFamilySubmitHandler);

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
            <Form
              noValidate
              validated={validated}
              method='POST'
              onSubmit={handleSubmit}
              className='row g-3 m-auto mt-3 mb-3  p-2'>
              {/*  <p className='mb-3'>{t("nav.Edit")}</p> */}
              <Row className='col-12 m-auto'>
                {true === false && (
                  <Form.Control
                    required
                    /*  id='familyId' */
                    value={familyData.id}
                    type='text'
                  />
                )}
                <Form.Group className='col-5 mb-3' controlId='formBasicName'>
                  <Form.Label> {t("familyName")}</Form.Label>
                  <Form.Control
                    required
                    /* id='familyName' */
                    value={familyData.name}
                    onChange={handleFamilyNameChange}
                    type='text'
                  />
                  <Form.Control.Feedback type='invalid' className='text-danger'>
                    {t("validation")}
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
                        type='text'
                      />
                      <Form.Control.Feedback
                        type='invalid'
                        className='text-danger'>
                        {t("validation")}
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
                        type='text'
                      />
                      <Form.Control.Feedback
                        type='invalid'
                        className='text-danger'>
                        {t("validation")}
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
