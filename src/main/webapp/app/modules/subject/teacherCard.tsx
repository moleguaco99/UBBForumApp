import Button from '@material-ui/core/Button';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Typography from '@material-ui/core/Typography';
import React from 'react';
import {Row, Col} from 'reactstrap';

export interface ITeacherCardProps {
  teacher: any
}

export const TeacherCard = ({teacher}: ITeacherCardProps) => {

  return (
    <Card className="card">
      <Row>
        <Col md={3}>
          <div><img width={150} height={150} src={"ourApi/teachers/photo/" + teacher.photoPath}/></div>
        </Col>
        <Col md={9}>
          <div>
            <CardActionArea>
              <CardContent>
                <Typography gutterBottom variant="h5" component="h2">
                  {teacher.lastName} {teacher.firstName}
                </Typography>
                <Typography variant="body2" color="textSecondary" component="p">
                  Rank: {teacher.rank} <br/>
                  E-mail: {teacher.email}
                </Typography>
              </CardContent>
            </CardActionArea>
            <CardActions>
              <div style={{marginLeft: "auto"}}>
                <Button size="small" color="primary">
                  <a href={teacher.webPage} target={"_blank"}>Go to teacher site</a>
                </Button>
              </div>
            </CardActions>
          </div>
        </Col>
      </Row>
    </Card>
  )
}
