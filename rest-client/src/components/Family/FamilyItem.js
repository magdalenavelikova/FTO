import { Container, NavDropdown, Row, Col } from "react-bootstrap";

import Card from "react-bootstrap/Card";

import { useTranslation } from "react-i18next";
import { Link } from "react-router-dom";
import { useAuthContext } from "../../contexts/AuthContext";

export const FamilyItem = ({ family, onEditClick, onDeleteClick }) => {
  const { t } = useTranslation();
  const { isAuthenticated, authorities } = useAuthContext();
  const bgCardImage = require("../../assets/logo.png");
  const isAuthorized =
    isAuthenticated &&
    (authorities.some((item) => item === "ROLE_ADMIN") ||
      authorities.some((item) => item === "ROLE_FAMILY_MODERATOR"));

  return (
    <Card key={family.id} className='mx-3 m-2 p-2' style={{ width: "20rem" }}>
      {/*  <Card.Img
        className='avatar-md rounded-circle img-thumbnail'
        src={
          family.picture && family.picture.length !== ""
            ? family.picture
            : family.sex === "Male"
            ? man
            : woman
        }
      /> */}
      <Card.Header className='fluid'>
        <Row className='col-md-12 m-auto'>
          <Col className='col-10'>
            <Card.Title className='font-size-16 text-align-center mb-1'>
              {family && family.name}
            </Card.Title>
          </Col>
          <Col className='col-2'>
            {isAuthorized && (
              <NavDropdown id='nav-dropdown'>
                <NavDropdown.Item
                  as={Link}
                  onClick={() => onEditClick(family.id)}>
                  {t("nav.Edit")}
                </NavDropdown.Item>
                <NavDropdown.Item
                  as={Link}
                  onClick={() => onDeleteClick(family.id)}>
                  {t("nav.Delete")}
                </NavDropdown.Item>
              </NavDropdown>
            )}
          </Col>
        </Row>
      </Card.Header>

      <Card.Body>
        {/* <Container className='flex-1 ms-3'>
           <Card.Title className='font-size-16 mb-1'>
            {family && family.name}
          </Card.Title>
        </Container> */}
        <Container className='mt-3 pt-1'>
          {family.members &&
            family.members.length > 0 &&
            family.members.map((m) => {
              return (
                <Card.Text key={m.id + family.id} className='text-muted mb-2'>
                  {m.name}&nbsp; &nbsp; {m.ageCategory} &nbsp; &nbsp;
                  {m.role}
                </Card.Text>
              );
            })}
        </Container>
      </Card.Body>
      <Card.Footer
        style={{
          backgroundImage: `url(${bgCardImage})`,
          backgroundSize: "cover",
          backgroundPosition: "center",
          minHeight: "20vh",
        }}></Card.Footer>
    </Card>
  );
};
