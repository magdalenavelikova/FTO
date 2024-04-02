import { Container, NavDropdown } from "react-bootstrap";

import Card from "react-bootstrap/Card";

import { useTranslation } from "react-i18next";
import { Link } from "react-router-dom";
import { useAuthContext } from "../../contexts/AuthContext";

export const FamilyItem = ({ family, onEditClick, onDeleteClick }) => {
  const { t } = useTranslation();
  const lang = localStorage.getItem("lang");
  const { isAuthenticated, authorities } = useAuthContext();

  const isAuthorized =
    isAuthenticated &&
    (authorities.some((item) => item === "ROLE_ADMIN") ||
      authorities.some((item) => item === "ROLE_MODERATOR"));

  return (
    <Card className='mx-3 m-2 p-2' style={{ width: "20rem" }}>
      {isAuthorized && (
        <NavDropdown id='nav-dropdown'>
          <NavDropdown.Item as={Link} onClick={() => onEditClick(family.id)}>
            {t("nav.Edit")}
          </NavDropdown.Item>
          <NavDropdown.Item as={Link} onClick={() => onDeleteClick(family.id)}>
            {t("nav.Delete")}
          </NavDropdown.Item>
        </NavDropdown>
      )}
      <Container fluid className='align-items-center m-2 p-2'></Container>
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
      <Card.Body>
        <Container className='flex-1 ms-3'>
          <Card.Title className='font-size-16 mb-1'>
            {family && family.name}
          </Card.Title>
        </Container>
        <Container className='mt-3 pt-1'>
          <Card.Text className='text-muted mb-2'></Card.Text>
        </Container>
      </Card.Body>
    </Card>
  );
};
