import { useContext, useEffect, useState, useTransition } from "react";
import { FamilyContext } from "../../contexts/FamilyContext";
import { Button, Col, Container, Row } from "react-bootstrap";

import { FamilyItem } from "./FamilyItem";
import { DeleteFamily } from "./DeleteFamily";
import { EditFamily } from "./EditFamily";
import { Maintenance } from "../Maintenance/Maintenance";
import { NewFamily } from "./NewFamily";

export const Families = () => {
  const { families, onFamilyDelete, success } = useContext(FamilyContext);
  const [familyList, setFamilyList] = useState([]);
  const [deleteFamilyShow, setDeleteFamilyShow] = useState(false);
  const [editFamilyShow, setEditFamilyShow] = useState(null);
  const [selectedFamily, setSelectedFamily] = useState({});
  const bgImage = require("../../assets/bg_image.jpg");

  const onCloseClick = () => {
    setDeleteFamilyShow(null);
    setEditFamilyShow(null);
  };

  const onFamilyDeleteHandler = () => {
    onFamilyDelete(deleteFamilyShow);
    setDeleteFamilyShow(null);
    setSelectedFamily({});
  };

  const onDeleteClick = (familyId) => {
    setSelectedFamily(familyList.filter((f) => f.id === familyId));
    setDeleteFamilyShow(familyId);
  };
  const onEditClick = (familyId) => {
    setSelectedFamily(familyList.filter((f) => f.id === familyId));
    setEditFamilyShow(familyId);
  };
  useEffect(() => {
    setFamilyList(families);
    if (success) {
      setEditFamilyShow(null);
    }
  }, []);
  useEffect(() => {
    setFamilyList(families);
    if (success) {
      setEditFamilyShow(null);
    }
  }, [families]);
  useEffect(() => {
    if (success) {
      setEditFamilyShow(null);
    }
  }, [success]);

  useEffect(() => {
    setFamilyList(families);
  }, [families]);

  return (
    <>
      {deleteFamilyShow && (
        <DeleteFamily
          family={selectedFamily[0]}
          onCloseClick={onCloseClick}
          onDelete={onFamilyDeleteHandler}
        />
      )}

      {editFamilyShow && (
        <EditFamily family={selectedFamily[0]} onCloseClick={onCloseClick} />
      )}

      <Container
        fluid
        style={{
          backgroundImage: `url(${bgImage})`,
          backgroundSize: "cover",
          backgroundPosition: "center",
          minHeight: "100vh",
        }}
        className='pt-2'>
        <Row className='m-auto'>
          <NewFamily></NewFamily>
        </Row>
        <Row className='align-items-center'>
          {familyList.length > 0 &&
            familyList.map((f) => {
              return (
                <Col
                  key={f.id}
                  className='align-items-center m-auto mt-5 mb-5 col-xl-2 col-sm-6 p-2'>
                  <FamilyItem
                    key={f.id + f.id}
                    family={f}
                    onDeleteClick={onDeleteClick}
                    onEditClick={onEditClick}></FamilyItem>
                </Col>
              );
            })}
        </Row>
      </Container>

      {!Array.isArray(families) && <Maintenance />}
    </>
  );
};
