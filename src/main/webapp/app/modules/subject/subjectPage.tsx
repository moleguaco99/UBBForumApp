import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Card, CardText, CardBody, CardSubtitle, Row, Col, Alert } from 'reactstrap';
import { getUrlParameter } from 'react-jhipster';
import { fetchSubject, fetchSubjectTeachers, setOffset } from './subject.reducer';
import Pagination from "material-ui-flat-pagination";
import { TeacherCard } from './teacherCard';

import { IRootState } from 'app/shared/reducers';

export interface ISubjectProps extends StateProps, DispatchProps, RouteComponentProps<{ id: any }> {}

export const SubjectPage = (props: ISubjectProps) => {
  useEffect(() => {
    const id = getUrlParameter('id', props.location.search);
    props.fetchSubject(id);
    props.fetchSubjectTeachers(id);
  }, []);
  if(!props.fetchSuccess)
    return (<Alert color="danger">Could not retrieve subject.</Alert>);
  return (
      <Card style={{height: "72vh"}}>
        <CardBody>
          <Row>
            <Col>
              <h2>
                {props.subject.title}
              </h2>
            </Col>
            <Col>
              <h2>
                Teachers
              </h2>
            </Col>
          </Row>

          <CardSubtitle className="mb-2 text-muted">
            {props.subject.section} {props.subject.language}, Semester {props.subject.semester}, {props.subject.type}
          </CardSubtitle>
          <CardText>
            <Row md={12}>
              <Col>
                <h4>{props.subject.description}</h4>
              </Col>
              {props.fetchTeachersSuccess ?
                (<Col>
                  {props.teachers.length > 2 ?
                    (<Pagination
                      limit={2}
                      total={props.teachers.length}
                      offset={props.offset}
                      onClick={props.setOffset}
                    />)
                    :
                    (<Alert color="danger">The page administrator assigned no teachers to this course </Alert>)
                  }
                  {props.teachers.length > 0 ?
                    (<TeacherCard teacher={props.teachers[props.offset]}/>)
                    :
                    (<div></div>)
                  }
                  {props.offset + 1 < props.teachers.length ?
                    (<TeacherCard teacher={props.teachers[props.offset + 1]}/>)
                    :
                    (<div></div>)
                  }
                </Col>)
                : (<Alert color="danger">Could not retrieve teachers.</Alert>)
              }
            </Row>
          </CardText>
        </CardBody>
      </Card>
  );
};

const mapStateToProps = ({ subject }: IRootState) => ({
    subject: subject.subject,
    teachers: subject.teachers,
    fetchSuccess: subject.fetchSuccess,
    fetchTeachersSuccess: subject.fetchTeachersSuccess,
    offset: subject.offset
});

const mapDispatchToProps = { fetchSubject, fetchSubjectTeachers, setOffset };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SubjectPage);
