import { Accordion } from "react-bootstrap";
import { useTranslation } from "react-i18next";
import { NewMember } from "./NewMember";
import { useState } from "react";

export const FamilyMember = ({ count, familyName }) => {
  const countInt = parseInt(count);
  const [active, setActive] = useState(null);
  const [countMember, setCountMember] = useState(-1);
  const { t } = useTranslation();

  const onChangeActiveItem = (key) => {
    setCountMember(countMember + 1);
    setActive(key);
  };
  const renderAccordionItems = () => {
    const accordionItems = [];
    for (let i = 0; i < countInt; i++) {
      accordionItems.push(
        <Accordion.Item
          key={i}
          style={{ backgroundColor: "rgba(255, 255, 255, 0.1)!important" }}
          eventKey={active ? i : active}>
          <Accordion.Header>
            {familyName} {t("Family Member")} {`${i + 1}`}
          </Accordion.Header>
          <Accordion.Body>
            <NewMember
              key={i}
              familyMemberId={i}
              familyName={familyName}
              onChange={onChangeActiveItem}
            />
          </Accordion.Body>
        </Accordion.Item>
      );
    }
    return accordionItems;
  };

  return (
    <>
      {countInt > countMember && (
        <Accordion
          style={{ backgroundColor: "rgba(255, 255, 255, 0.1)!important" }}
          className='m-auto col-xl-6 col-md-6 col-xs-12'
          alwaysOpen
          activeKey={active}
          onSelect={(e) => setActive(e)}>
          {renderAccordionItems()}
        </Accordion>
      )}
    </>
  );
};
