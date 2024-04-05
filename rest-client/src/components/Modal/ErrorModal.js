import { useState } from "react";
import Modal from "react-bootstrap/Modal";

export const ErrorModal = ({ onCloseClick, name }) => {
  const [show, setShow] = useState(true);
  const handleClose = () => {
    onCloseClick();
    setShow(false);
  };
  return (
    <Modal
      show={show}
      onHide={handleClose}
      size='lg'
      aria-labelledby='contained-modal-title-vcenter'
      centered>
      <Modal.Header closeButton></Modal.Header>
      <Modal.Body>
        <p>{name}</p>
      </Modal.Body>
    </Modal>
  );
};
