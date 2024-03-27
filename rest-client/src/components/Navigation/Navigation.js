import { Container, NavDropdown, NavbarBrand } from "react-bootstrap";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { useTranslation } from "react-i18next";
import { Link } from "react-router-dom";
import { LanguageSwitcher } from "./LanguageSwitcher";
export const Navigation = () => {
  const { t } = useTranslation();
  const logo = require("../../assets/logo.png");
  return (
    <Navbar
      className='p-3'
      bg='transparent'
      variant='dark'
      fixed='top'
      collapseOnSelect
      expand=''>
      <NavbarBrand>
        <Link
          className={"link-dark "}
          to={"/"}
          style={{ textDecoration: "none" }}>
          <img
            src={logo}
            width='160'
            height='80'
            className='d-inline-block align-top me-3'
            alt='Family Task Organizer'
          />
        </Link>
      </NavbarBrand>
      <Navbar.Toggle aria-controls='navbarScroll' />
      <Navbar.Collapse id='navbarScroll'>
        <Nav className='mr-auto' navbarScroll>
          <Nav.Link eventKey='1' as={Link} className='me-2' to={"/users/login"}>
            {t("nav.MembersArea.Login")}
          </Nav.Link>
          <Nav.Link
            eventKey='2'
            as={Link}
            className='me-2'
            to={"/users/logout"}>
            {t("nav.MembersArea.Logout")}
          </Nav.Link>
        </Nav>
        <Nav>
          <Nav.Link eventKey={3} href='#memes'>
            About Us
          </Nav.Link>
        </Nav>
        <LanguageSwitcher fluid />
      </Navbar.Collapse>
    </Navbar>
  );
};
