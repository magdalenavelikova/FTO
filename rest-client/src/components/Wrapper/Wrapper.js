import { Container } from "react-bootstrap";
import { AuthTab } from "../Tabs/AuthTab";

export const Wrapper = () => {
  const bgImage = require("../../assets/bg_image.jpg");
  return (
    <Container
      fluid
      style={{
        backgroundImage: `url(${bgImage})`,
        backgroundSize: "cover",
        backgroundPosition: "center",
        minHeight: "100vh",
      }}>
      <AuthTab></AuthTab>
    </Container>
  );
};
