import { Col, Container, Row } from "react-bootstrap";
import { AuthTab } from "../Tabs/AuthTab";
import { useContext } from "react";
import { AuthContext } from "../../contexts/AuthContext";

export const Home = () => {
  const bgImage = require("../../assets/family.jpg");
  const { activeUser, success } = useContext(AuthContext);
  console.log(activeUser);
  console.log(success);
  return (
    <Container
      fluid
      style={{
        backgroundImage: `url(${bgImage})`,
        backgroundSize: "cover",
        backgroundPosition: "center",
        minHeight: "100vh",
      }}>
      <Row>
        <Col className='mt-lg-5 pt-lg-5 mt-2'>
          <Row
            className='mt-lg-5 pt-5 m-auto'
            style={{
              minHeight: "30vh",
            }}>
            <h1 className='title display-1 mt-lg-5 p-5 '>
              Family Task Organizer
            </h1>
          </Row>
          <Row
            style={{
              minHeight: "30vh",
            }}>
            <h2 className='info px-5'>
              Let's get our time in order. <br /> <br /> Plan, manage and track
              all your family's tasks in one flexible platform.
            </h2>
          </Row>
        </Col>
        <Col className='mt-lg-5 mt-md-5 pt-lg-5  pt-md-5 pt-sm-3'>
          <AuthTab></AuthTab>
        </Col>
      </Row>
    </Container>
  );
};
