import { Col, Container, Row } from "react-bootstrap";

export const Home = () => {
  const bgImage = require("../../assets/family.jpg");
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
        <Col className='mt-5'>
          <Row
            className='mt-5'
            style={{
              minHeight: "30vh",
            }}>
            <h1 className='title p-5'>Family Task Organizer</h1>
          </Row>
          <Row
            className='ps-5'
            style={{
              minHeight: "30vh",
            }}>
            <h2 className='info'>
              Let's get our time in order! <br /> Plan, manage and track all
              your family's tasks in one flexible platform.
            </h2>
          </Row>
        </Col>
        <Col className='mt-5 '></Col>
      </Row>
    </Container>
  );
};
