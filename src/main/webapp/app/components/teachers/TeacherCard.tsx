import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';

const useStyles = makeStyles({
  card: {
    maxWidth: 600,
    marginBottom: 20,
    marginLeft:"auto",
    marginRight:"auto"
  },
  image: {
    marginLeft:"-390px",
  },
});

export default function TeacherCard() {
  const classes = useStyles();

  return (
    <Card className={classes.card}>
      <CardActionArea>
        <img src={"http://www.cs.ubbcluj.ro/wp-content/uploads/Czibula-Istvan.jpg"} style={{marginLeft:"-400px"}}/>
        <CardContent>
          <Typography gutterBottom variant="h5" component="h2">
            Professor
          </Typography>
          <Typography variant="body2" color="textSecondary" component="p">
            Something about the teacher...Idk
          </Typography>
        </CardContent>
      </CardActionArea>
      <CardActions>
        <Button size="small" color="primary" style={{marginLeft:"420px"}}>
          <a href={"http://www.cs.ubbcluj.ro/~istvanc/"} target={"_blank"}>Go to teacher site</a>
        </Button>
      </CardActions>
    </Card>
  );
}
