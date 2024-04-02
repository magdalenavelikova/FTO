import { Button, Container, Form, Modal, Row } from "react-bootstrap";
import { useTranslation } from "react-i18next";
import { useForm } from "../../hooks/useForm";

import { useState, useEffect } from "react";
import { useFamilyContext } from "../../contexts/FamilyContext";

export const EditFamily = ({ onCloseClick, family }) => {
  const { t } = useTranslation();
  const { onEditFamilySubmitHandler } = useFamilyContext();
  const [show, setShow] = useState(true);
  const handleClose = () => {
    onCloseClick();
    setShow(false);
  };

  useEffect(() => {
    changeValues(family);
  }, [family]);

  const FamilyFormKeys = {
    Id: "id",
    Name: "name",
  };

  const { formValues, onChangeHandler, changeValues, onSubmit, validated } =
    useForm(
      {
        [FamilyFormKeys.Id]: "",
        [FamilyFormKeys.Name]: "",
      },
      onEditFamilySubmitHandler
    );

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
          <Container className='m-auto container-fluid-md  mt-5'>
            <Form
              noValidate
              validated={validated}
              method='POST'
              onSubmit={onSubmit}
              className='row g-3 m-auto mt-5 mb-5 border border-secondary rounded p-4'>
              <p className='mb-3'>{t("nav.Edit")}</p>
              <Row className='col-md-12 m-auto'>
                {true === false && (
                  <Form.Control
                    required
                    name={FamilyFormKeys.Id}
                    value={formValues[FamilyFormKeys.Id]}
                    onChange={onChangeHandler}
                    type='text'
                  />
                )}
                <Form.Group className='col-md-5 mb-3' controlId='formBasicName'>
                  <Form.Label>Name EN</Form.Label>
                  <Form.Control
                    required
                    name={FamilyFormKeys.Name}
                    value={formValues[FamilyFormKeys.Name]}
                    onChange={onChangeHandler}
                    type='text'
                  />
                  <Form.Control.Feedback type='invalid' className='text-danger'>
                    {t("validation")}
                  </Form.Control.Feedback>
                </Form.Group>
              </Row>

              <Row className='col-md-12 m-auto'>
                <Button
                  className='col-md-4  m-auto mt-4 mb-3'
                  variant='secondary'
                  type='submit'>
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
