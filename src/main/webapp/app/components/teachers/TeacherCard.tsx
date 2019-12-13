import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import "../teachers/cardStyle.css"

export default class TeacherCard extends React.Component{
  constructor(props){
    super(props);
    this.state={}
  }

  render(){
  return (
    <Card className="card">
      <CardActionArea>
        <img src={"http://www.cs.ubbcluj.ro/wp-content/uploads/Czibula-Istvan.jpg"} style={{marginLeft:"-400px"}}/>
        <CardContent>
          <Typography gutterBottom variant="h5" component="h2">
            Professor
          </Typography>
          <Typography variant="body2" color="textSecondary" component="p">
            Details about the teacher.
          </Typography>
        </CardContent>
      </CardActionArea>
      <CardActions>
        <Button size="small" color="primary" style={{marginLeft:"420px"}}>
          <a href={"http://www.cs.ubbcluj.ro/~istvanc/"} target={"_blank"}>Go to teacher site</a>
        </Button>
      </CardActions>
    </Card>
  )
}
}
