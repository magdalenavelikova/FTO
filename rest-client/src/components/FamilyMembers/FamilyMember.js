import { Accordion } from "react-bootstrap";
import { useTranslation } from "react-i18next";
import { NewMember } from "./NewMember";

export const FamilyMember = ({ count, familyName }) => {
  const countInt = parseInt(count);

  const { t } = useTranslation();
  const renderAccordionItems = () => {
    const accordionItems = [];
    for (let i = 0; i < countInt; i++) {
      accordionItems.push(
        <Accordion.Item
          style={{ backgroundColor: "rgba(255, 255, 255, 0.1)!important" }}
          key={i}
          eventKey={i.toString()}>
          <Accordion.Header>
            {t("Family Member")} {`${i + 1}`}
          </Accordion.Header>
          <Accordion.Body>
            <NewMember key={i} familyMemberId={i} familyName={familyName} />
          </Accordion.Body>
        </Accordion.Item>
      );
    }
    return accordionItems;
  };

  return (
    <Accordion
      style={{ backgroundColor: "rgba(255, 255, 255, 0.1)!important" }}
      className='m-auto col-xl-6 col-md-6 col-xs-12'>
      {renderAccordionItems()}
    </Accordion>
  );
};
