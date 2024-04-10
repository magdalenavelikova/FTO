import { Button, Container, Form, Row, Spinner } from "react-bootstrap";
import { useTranslation } from "react-i18next";

import { useForm } from "../../hooks/useForm";
import { useContext, useEffect, useState } from "react";

import { FamilyContext } from "../../contexts/FamilyContext";

export const NewMember = ({ familyMemberId, familyName, onChange }) => {
  const { t } = useTranslation();
  const {
    onCreateMemberSubmitHandler,
    errors,
    error,
    spinner,
    successMember,
    clearErrors,
  } = useContext(FamilyContext);
  const [isLoading, setIsLoading] = useState(false);
  const [name, setName] = useState({});
  const [pinCode, setPinCode] = useState({});

  const MemberFormKeys = {
    FamilyName: "familyName",
    Name: "name",
    Pin: "pinCode",
    Role: "role",
    AgeCategory: "ageCategory",
    PictureUrl: "pictureUrl",
  };

  const { formValues, onChangeHandler, onSubmit, validated } = useForm(
    {
      [MemberFormKeys.FamilyName]: `${familyName}`,
      [MemberFormKeys.Name]: "",
      [MemberFormKeys.Pin]: "",
      [MemberFormKeys.Role]: "",
      [MemberFormKeys.AgeCategory]: "",
      [MemberFormKeys.PictureUrl]: "",
    },

    onCreateMemberSubmitHandler
  );
  useEffect(() => {
    clearErrors();
  }, []);

  useEffect(() => {
    setIsLoading(spinner);
  }, [spinner]);

  useEffect(() => {
    if (successMember) {
      onChange(familyMemberId + 1);
    }
  }, [successMember, familyMemberId]);

  useEffect(() => {
    setName({});
    setPinCode({});
    if (errors === null) {
      setName({});
      setPinCode({});
    } else {
      for (const [key, value] of Object.entries(errors)) {
        switch (key) {
          case "name":
            setName(value);
            break;
          case "pinCode":
            setPinCode(value);
            break;
          default:
            break;
        }
      }
    }
  }, [errors]);

  useEffect(() => {
    setName(error);
  }, [error]);

  return (
    <>
      <Container className='m-auto container-fluid  ' key={familyMemberId}>
        <Form
          name={"new" + familyMemberId}
          noValidate
          validated={validated}
          method='POST'
          onSubmit={onSubmit}
          /*     onSubmit={(e) => {
            onSubmit(e);
            if (successMember) {
              onChange(familyMemberId + 1);
            }
          }} */
          className='row g-3 m-auto mb-2 px-2'>
          <Row className='col-12 m-auto'>
            <Form.Group
              className='m-auto col-4 mb-3'
              controlId={"formBasicName" + familyMemberId}>
              <Form.Label>{t("nickName")}</Form.Label>
              <Form.Control
                required
                name={MemberFormKeys.Name}
                value={formValues[MemberFormKeys.Name]}
                onChange={onChangeHandler}
                type='text'
              />
              <Form.Control.Feedback type='invalid' className='text-danger'>
                {t("validation")}
              </Form.Control.Feedback>
              {Object.keys(name).length !== 0 && (
                <Form.Control.Feedback className='text-danger'>
                  {name}
                </Form.Control.Feedback>
              )}
            </Form.Group>
            <Form.Group
              className=' m-auto col-4 mb-3'
              controlId={"formPinCode" + familyMemberId}>
              <Form.Label>{t("pin")}</Form.Label>
              <Form.Control
                required
                name={MemberFormKeys.Pin}
                value={formValues[MemberFormKeys.Pin]}
                autoComplete='PIN'
                onChange={onChangeHandler}
                type='password'
              />
              <Form.Control.Feedback type='invalid' className='text-danger'>
                {t("validation")}
              </Form.Control.Feedback>
              {Object.keys(pinCode).length !== 0 && (
                <Form.Control.Feedback className='text-danger'>
                  {pinCode}
                </Form.Control.Feedback>
              )}
            </Form.Group>
          </Row>
          <Row className='col-12 m-auto'>
            <Form.Group
              className='m-auto col-4 mb-3'
              controlId={"formBasicAgeCategory" + familyMemberId}>
              <Form.Label>{t("ageCategory")}</Form.Label>
              <Form.Select
                required
                size='sm'
                name={MemberFormKeys.AgeCategory}
                onChange={onChangeHandler}>
                <option label={t("select")} value=''>
                  {t("select")}
                </option>
                <option value='ADULT'>ADULT</option>
                <option value='TEEN'>TEEN</option>
                <option value='INFANT'>INFANT</option>
              </Form.Select>
              <Form.Control.Feedback type='invalid' className='text-danger'>
                {t("validation")}
              </Form.Control.Feedback>
            </Form.Group>
            <Form.Group
              className='m-auto col-4 mb-3'
              controlId={"formBasicRole" + familyMemberId}>
              <Form.Label>{t("familyRole")}</Form.Label>
              <Form.Select
                required
                size='sm'
                name={MemberFormKeys.Role}
                onChange={onChangeHandler}>
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
              <Form.Control.Feedback type='invalid' className='text-danger'>
                {t("validation")}
              </Form.Control.Feedback>
            </Form.Group>
          </Row>
          <Row className='col-12 m-auto'>
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
    </>
  );
};
