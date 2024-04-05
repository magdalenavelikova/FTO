import { Container, NavDropdown, NavbarBrand } from "react-bootstrap";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { useTranslation } from "react-i18next";
import { Link } from "react-router-dom";
import { LanguageSwitcher } from "./LanguageSwitcher";
import { useContext } from "react";
import { AuthContext } from "../../contexts/AuthContext";
export const Navigation = () => {
  const { t } = useTranslation();
  const logo = require("../../assets/logo.png");
  const { isAuthenticated, activeUser } = useContext(AuthContext);
  return (
    <Navbar
      className='p-1 px-lg-4 px-sm-2'
      style={{ backgroundColor: "rgba(255, 255, 255, 0.1)" }}
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
          {isAuthenticated && (
            <>
              <NavDropdown
                style={{ backgroundColor: "rgba(255, 255, 255, 0.1)" }}
                title={t("nav.MembersArea.Profile")}
                id='collapsible-nav-dropdown'>
                <NavDropdown.Item eventKey='27' as={Link} to={"users/profile"}>
                  {activeUser.name}
                </NavDropdown.Item>
                <NavDropdown.Divider />
                <NavDropdown.Item eventKey='29' as={Link} to={"users/logout"}>
                  {t("nav.MembersArea.Logout")}
                </NavDropdown.Item>
              </NavDropdown>
              <Nav.Link eventKey='2' as={Link} className='me-2' to={"/family"}>
                {t("nav.Family")}
              </Nav.Link>
            </>
          )}

          {!isAuthenticated && (
            <Nav.Link
              eventKey='1'
              as={Link}
              className='me-2'
              to={"/users/login"}>
              {t("nav.Login")}
            </Nav.Link>
          )}
        </Nav>

        <LanguageSwitcher fluid />
      </Navbar.Collapse>
    </Navbar>
  );
};
