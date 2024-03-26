import { Container, NavDropdown, NavbarBrand } from "react-bootstrap";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";

export const Navigation = () => {
  return (
    <Navbar
      className='m-3'
      bg='transparent'
      variant='dark'
      fixed='top'
      collapseOnSelect
      expand=''>
      <NavbarBrand></NavbarBrand>
      <Navbar.Toggle aria-controls='responsive-navbar-nav' />
      <Navbar.Collapse id='responsive-navbar-nav'>
        <Nav className='mr-auto' color='white'>
          <Nav.Link href='#login'>Login</Nav.Link>
          <Nav.Link href='#logout'>Logout</Nav.Link>
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
