import { Button, Container, Form, Spinner, Nav } from "react-bootstrap";
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

  /* const handleFacebookLogin = async () => {
    try {
      // Make a request to the backend server to initiate the Facebook OAuth2 flow
      const response = await axios.get("/auth/facebook");
      window.location.href = response.data.redirectUrl;
    } catch (error) {
      console.error("Error initiating Facebook login:", error);
    }
  }; */
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
    <Container className='m-auto container-fluid-md container-sm'>
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
            autoComplete='on'
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
              autoComplete='on'
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

        <Button
          variant='secondary'
          className='col-md-3  m-auto mt-4 mb-3'
          type='submit'>
          {" "}
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
      </Form>
      <Container className='m-auto container-sm mb-3 pt-lg-2'>
        <GoogleLogin onGoogleSignIn={onGoogleSignIn} text='Google' />
        {/*  <button onClick={handleFacebookLogin}>Login with Facebook</button> */}
        <Link
          className={"nav-link d-inline-block me-4 mt-lg-4"}
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
