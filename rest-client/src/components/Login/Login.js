import {
  Button,
  Container,
  Form,
  Spinner,
  Nav,
  Row,
  Col,
} from "react-bootstrap";
import { useTranslation } from "react-i18next";
import { Link, useNavigate } from "react-router-dom";
import { useAuthContext } from "../../contexts/AuthContext";
import { useForm } from "../../hooks/useForm";
import { useState, useEffect } from "react";
import GoogleLogin from "../OauthLogin/GoogleLogin";

export const LoginPage = ({ onSelectHandler }) => {
  const { t } = useTranslation();
  const navigate = useNavigate();
  const { onLoginSubmitHandler, onOauthLoginSubmitHandler, errors, spinner } =
    useAuthContext();
  const [eye, setEye] = useState(true);
  useAuthContext();

  const [password, setPassword] = useState("password");
  const [isLoading, setIsLoading] = useState(false);
  const [isLogin, setIsLogin] = useState(false);
  const Eye = () => {
    // eslint-disable-next-line eqeqeq
    if (password == "password") {
      setPassword("text");
      setEye(false);
    } else {
      setPassword("password");
      setEye(true);
    }
  };
  const onGoogleSignIn = async (res) => {
    const { credential } = res;

    const result = await onOauthLoginSubmitHandler(credential, setIsLogin);
    setIsLogin(result);
  };

  useEffect(() => {
    if (!isLogin) return;
    navigate("/");
  }, [isLogin]);
  const LoginFormKeys = {
    Username: "username",
    Password: "password",
  };

  const { formValues, onChangeHandler, onSubmit, validated } = useForm(
    {
      [LoginFormKeys.Username]: "",
      [LoginFormKeys.Password]: "",
    },
    onLoginSubmitHandler
  );
  useEffect(() => {
    setIsLoading(spinner);
  }, [spinner]);

  return (
    <Container className='m-auto container-fluid-md container-sm '>
      <Form
        noValidate
        validated={validated}
        className='m-auto p-2 pt-5 transparent-input'
        method='POST'
        onSubmit={onSubmit}>
        <Form.Group className='mb-3' controlId='formBasicEmail'>
          <Form.Label>{t("email")} </Form.Label>
          <Form.Control
            required
            name={LoginFormKeys.Username}
            type='email'
            placeholder={t("email")}
            value={formValues[LoginFormKeys.Username]}
            autoComplete='username'
            onChange={onChangeHandler}
          />
        </Form.Group>
        <Form.Group className='mb-3' controlId='formBasicPassword'>
          <Form.Label>{t("forms.Password")}</Form.Label>
          <div className='form'>
            <Form.Control
              required
              name={LoginFormKeys.Password}
              value={formValues[LoginFormKeys.Password]}
              onChange={onChangeHandler}
              type={password}
              autoComplete='current-password'
              placeholder={t("forms.Password")}
            />
            <i
              onClick={Eye}
              className={`fa ${eye ? "fa-eye-slash" : "fa-eye"}`}></i>
          </div>

          {Object.keys(errors).length !== 0 && (
            <Form.Label className='text-danger'>
              {t("forms.Login.Validation")}
            </Form.Label>
          )}
        </Form.Group>
        <Container className='m-auto container-fluid-md container-sm mb-3 pt-lg-2 '>
          <Row className='m-auto'>
            <Col className='m-auto mb-2'>
              <Button variant='secondary' type='submit'>
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
                {t("forms.Button.Login")}
              </Button>
            </Col>
            <Col className='m-auto '>
              <GoogleLogin onGoogleSignIn={onGoogleSignIn} text='Google' />
            </Col>
          </Row>
        </Container>
      </Form>
      <Container className='m-auto container-sm mb-3 pt-lg-2'>
        <Link
          className={"nav-link d-inline-block me-4 mt-2 mt-lg-4"}
          style={{ textDecoration: "none" }}
          onClick={() => onSelectHandler("register")}>
          {t("linkRegister")}
        </Link>
        <Nav.Link
          as={Link}
          className={"link-info d-inline-block"}
          style={{ textDecoration: "none" }}
          to={"/users/forgotten-password"}>
          {t("ForgottenPassword")}
        </Nav.Link>
      </Container>
    </Container>
  );
};
