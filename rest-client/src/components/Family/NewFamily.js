import { Button, Container, Form, Row } from "react-bootstrap";
import { useTranslation } from "react-i18next";
import { useForm } from "../../hooks/useForm";
import { useFamilyContext } from "../../contexts/FamilyContext";

export const NewFamily = () => {
  const { t } = useTranslation();
  const { onCreateFamilySubmitHandler } = useFamilyContext();

  const FamilyFormKeys = {
    Name: "name",
  };

  const { formValues, onChangeHandler, onSubmit, validated } = useForm(
    {
      [FamilyFormKeys.Name]: "",
    },
    onCreateFamilySubmitHandler
  );

  return (
    <>
      <Container className='m-auto container-fluid-md pt-5  mt-5'>
        <Form
          noValidate
          validated={validated}
          method='POST'
          onSubmit={onSubmit}
          className='row g-3 m-auto mt-5 mb-5 border border-light rounded p-4'>
          <p className='mb-3'>{t("nav.Family")}</p>
          <Row className='col-md-12 m-auto'>
            <Form.Group className='col-md-5 mb-3' controlId='formBasicName'>
              <Form.Label>{t("familyName")}</Form.Label>
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
    </>
  );
};
