import Button from '@material-ui/core/Button';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Typography from '@material-ui/core/Typography';
import React from 'react';
import "../teachers/cardStyle.css";

export default class TeacherCard extends React.Component{
  constructor(props){
    super(props);
    this.state={}
  }

  render(){
  return (
    <Card className="card" style={{padding: "auto", width:"40%", display:"flex", flexDirection:"row", paddingBlockStart:"0px", paddingInlineStart:"0px", paddingBlockEnd:"0px"}}>
        <div><img style={{width:"138px", borderRadius:"3%"}} src={"http://www.cs.ubbcluj.ro/wp-content/uploads/Czibula-Istvan.jpg"}/></div>
        <div style={{display:"flex", flexDirection:"column", width:"100%"}}>
      <CardActionArea>
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
        <div style={{marginLeft:"auto"}}>
        <Button size="small" color="primary">
          <a href={"http://www.cs.ubbcluj.ro/~istvanc/"} target={"_blank"}>Go to teacher site</a>
        </Button>
        </div>
      </CardActions>
      </div>
    </Card>
  )
}
}
