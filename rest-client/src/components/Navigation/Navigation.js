import { Container, NavDropdown, NavbarBrand } from "react-bootstrap";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { Link } from "react-router-dom";
export const Navigation = () => {
  return (
    <Navbar
      className='p-3'
      bg='transparent'
      variant='dark'
      fixed='top'
      collapseOnSelect
      expand=''>
      <NavbarBrand></NavbarBrand>
      <Navbar.Toggle aria-controls='navbarScroll' />
      <Navbar.Collapse id='navbarScroll'>
        <Nav className='mr-auto' navbarScroll>
          <Nav.Link eventKey='1' as={Link} className='me-2' to={"/users/login"}>
            login
          </Nav.Link>
          <Nav.Link
            eventKey='2'
            as={Link}
            className='me-2'
            to={"/users/logout"}>
            logout
          </Nav.Link>
        </Nav>
        <Nav>
          <Nav.Link href='#deets'>Contact</Nav.Link>
          <Nav.Link eventKey={2} href='#memes'>
            About Us
          </Nav.Link>
        </Nav>
      </Navbar.Collapse>
    </Navbar>
  );
};
