import Button from '@material-ui/core/Button';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Typography from '@material-ui/core/Typography';
import React from 'react';
import '../teachers/cardStyle.css';

type teacher = {
  idTeacher: number;
  firstName: string;
  lastName: string;
  photoPath: string;
  webPage: string;
  rank: string;
};

interface IOwnProps {
  teacher: teacher;
  showTeacherDetails: (teacher: teacher) => void;
}

export class TeacherCard extends React.Component<IOwnProps> {
  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    return (
      <Card
        className="card"
        style={{
          width: '80%',
          display: 'flex',
          flexDirection: 'row',
          paddingBlockStart: '0px',
          paddingInlineStart: '0px',
          paddingBlockEnd: '0px'
        }}
      >
        <div>
          <img style={{ height:"100%", borderRadius: '3%' }} src={this.props.teacher.photoPath} />
        </div>
        <div style={{ display: 'flex', flexDirection: 'column', width: '100%', marginLeft:"14%" }}>
          <CardContent>
            <Typography gutterBottom variant="h5" component="h2" align="left">
              {this.props.teacher.firstName + ' ' + this.props.teacher.lastName}
            </Typography>
          </CardContent>
          <CardActionArea>
            <CardContent>
              <Typography
                variant="body2"
                color="textSecondary"
                component="p"
                align="left"
                onClick={event => {
                  this.props.showTeacherDetails(this.props.teacher);
                }}
              >
                Go to details about the teacher.
              </Typography>
            </CardContent>
          </CardActionArea>
          <CardActions>
            <div style={{ marginLeft: 'auto' }}>
              <Button size="small" color="primary">
                <a href={this.props.teacher.webPage} target={'_blank'}>
                  Go to site
                </a>
              </Button>
            </div>
          </CardActions>
        </div>
      </Card>
    );
  }
}