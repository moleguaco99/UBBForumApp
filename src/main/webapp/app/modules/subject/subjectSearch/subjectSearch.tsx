import React, {useEffect} from 'react';
import {connect} from 'react-redux';
import {withRouter, RouteComponentProps} from 'react-router-dom';

import {Button, Modal, ModalHeader, ModalBody, ModalFooter, Row, Col} from 'reactstrap';
import {AvForm, AvField} from 'availity-reactstrap-validation';
import {
  showModal,
  hideModal,
  fetchSubjects,
  changeSection,
  changeSemester,
  searchSubject,
  resetForm
} from "./subjectSearch.reducer";
import {IRootState} from "../../../shared/reducers";

export interface ISubjectSearchProps extends StateProps, DispatchProps, RouteComponentProps<{}> {
}

export const SubjectSearch = (props: ISubjectSearchProps) => {
  useEffect(() => {
    props.showModal();
    props.fetchSubjects();
  }, []);
  const handleClose = () => {
    props.hideModal();
    props.history.push('/');
  };
  const handleSectionChange = (event) => {
    props.changeSection(event.target.value);
  };
  const handleSemesterChange = (event) => {
    props.changeSemester(event.target.value);
  };
  const handleSubmit = (event, errors, {subject}) => {
    props.searchSubject(subject);

  };
  if (props.subjectID !== 0) {
    const id = props.subjectID;
    props.resetForm();
    props.history.push('subject?id=' + id);
  }
  return (

    <Modal backdrop="static" isOpen={props.isModalShown} autoFocus={false} toggle={handleClose}>
      <AvForm onSubmit={handleSubmit}>
        <ModalHeader id="login-title">
          Search subject
        </ModalHeader>
        <ModalBody>
          <Row>
            <Col md="12">
            </Col>
            <Col md="12">
              <AvField
                type="select"
                name="section"
                label="Section"
                placeholder="Section"
                required
                errorMessage="You must select the section "
                autoFocus
                onChange={handleSectionChange}
              >
                <option></option>
                {props.sectionOptions.map(section => (
                  <option key={section} value={section}>
                    {section}
                  </option>
                ))}
              </AvField>
              <AvField
                type="select"
                name="semester"
                label="Semester"
                placeholder="Semester"
                required
                errorMessage="You must select the semester "
                autoFocus
                disabled={props.isSemesterDisabled}
                onChange={handleSemesterChange}
              >
                <option></option>
                {props.semesterOptions.map(semester => (
                  <option key={semester}>
                    {semester}
                  </option>
                ))}
              </AvField>
              <AvField
                type="select"
                name="subject"
                label="Subject"
                placeholder="Subject"
                required
                errorMessage="You must select the subject "
                autoFocus
                disabled={props.isSubjectDisabled}
              >
                <option></option>
                {props.subjectOptions.map(subject => (
                  <option key={subject}>
                    {subject}
                  </option>
                ))}
              </AvField>
            </Col>
          </Row>
          <div className="mt-1">&nbsp;</div>
        </ModalBody>
        <ModalFooter>
          <Button color="secondary" tabIndex="1" onClick={handleClose}>
            Cancel
          </Button>{' '}
          <Button color="primary" type="submit">
            Search
          </Button>
        </ModalFooter>
      </AvForm>
    </Modal>
  );
};
const mapStateToProps = ({subjectSearch}: IRootState) => ({
  isModalShown: subjectSearch.isModalShown,
  isSemesterDisabled: subjectSearch.isSemesterDisabled,
  isSubjectDisabled: subjectSearch.isSubjectDisabled,
  sectionOptions: subjectSearch.sectionOptions,
  semesterOptions: subjectSearch.semesterOptions,
  subjectOptions: subjectSearch.subjectOptions,
  subjectID: subjectSearch.subjectID
});

const mapDispatchToProps = {
  showModal,
  hideModal,
  fetchSubjects,
  changeSection,
  changeSemester,
  searchSubject,
  resetForm
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;
export default connect(
  mapStateToProps,
  mapDispatchToProps
)(withRouter(SubjectSearch));
