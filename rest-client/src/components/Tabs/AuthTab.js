import { Container, Row, Col, Tab, Tabs } from "react-bootstrap";

import { useTranslation } from "react-i18next";
import { LoginPage } from "../Login/Login";
import { RegisterPage } from "../Register/Register";
import { useEffect, useState } from "react";
export const AuthTab = () => {
  const { t } = useTranslation();
  const [key, setKey] = useState("login");
  const onSelectHandler = (k) => {
    setKey(k);
  };

  return (
    <Container fluid className='m-auto  pt-lg-5'>
      <Row xl={12} xs={1} md={2} className='pt-lg-5 mb-1'>
        <Col className=' m-auto border-secondary pt-lg-5'>
          <Tabs
            className='mt-lg-5 '
            justify
            activeKey={key}
            onSelect={(k) => setKey(k)}>
            <Tab
              className='border border-top-0 rounded'
              eventKey='login'
              title={t("nav.MembersArea.Login")}>
              <LoginPage onSelectHandler={onSelectHandler} />
            </Tab>
            <Tab
              className='border border-top-0'
              eventKey='register'
              title={t("nav.MembersArea.Register")}>
              <RegisterPage />
            </Tab>
          </Tabs>
        </Col>
      </Row>
    </Container>
  );
};
