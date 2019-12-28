import React from 'react';
import Card from '@material-ui/core/Card';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import { CardContent } from '@material-ui/core';
import axios from 'axios';
import { Reply } from './Reply';
import "./styles.css"


export class TopicPage extends React.Component<any, any>{
    
    constructor(props){
        super(props);
        this.state = {
            replies:[],
            replyText: "",
            userID: 0,
            topicQuestion: this.props.location.state.from,
            open: false,
            mentionusers: []
        }
    }

    componentDidMount(){
        axios.get('api/account').
        /* eslint-disable no-console */
        then(response => this.setState({
            userID: response.data.id
        }))

        fetch('http://localhost:8080/ourApi/answers/'+this.state.topicQuestion.idQuestion)
        .then(response => response.json())
        .then(json => this.setState({
          replies: json
        }))
        /* eslint-disable no-console */
        .catch(error => console.log(error))
        
        setInterval(this.getQuestions, 2000);

        fetch('http://localhost:8080/ourApi/mentionusers')
        .then(response => response.json())
        .then(json => {
            console.log(json);
            this.setState({
            mentionusers: json }
        )})
        .catch(error => console.log(error))
    }

    getQuestions = () => {
        fetch('http://localhost:8080/ourApi/answers/'+this.state.topicQuestion.idQuestion)
        .then(response => response.json())
        .then(json => this.setState({
          replies: json
        }))
        /* eslint-disable no-console */
        .catch(error => console.log(error))
    }

    handleReplyText = (event: React.ChangeEvent<HTMLInputElement>) => {
        this.setState({
            replyText: event.target.value
        })
    }
    
    getIcon(imageUrl:string, marginL:string, marginT:string){
         return imageUrl !== null ? 
            <img src={imageUrl} 
                style={{height:"50px", marginTop: marginT, alignSelf:"center", marginLeft: marginL, borderRadius:"50%" }}></img> :
            <img src="content\images\user-icon.png"
                style={{height:"50px", marginTop: marginT, alignSelf:"center", marginLeft: marginL, borderRadius:"50%", border:"1px solid #D8D8D8"}}></img>
    }

    replyTopic = () => {
        if(this.state.replyText === ""){
            this.setState({
                open: true
            })
        }
        else{
            fetch('http://localhost:8080/ourApi/answer/', {
                method: 'POST',
                headers:{
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    idUser: this.state.userID,
                    idQA: this.state.topicQuestion.idQuestion,
                    rating: 0,
                    text: this.state.replyText,
                    type: 'Q'
                 })
                }).catch(error => console.log(error))

                this.setState({ 
                    replyText: ""
            })
        }
    }

    pressEnter = (event : React.KeyboardEvent<HTMLInputElement>) => {
        if(event.keyCode === 13){
            this.replyTopic();
        }
    }

    handleClose = () =>{
        this.setState({
            open: false
        })
    }

    render(){
        return(
            <div style={{overflow:'auto'}}>
                <Dialog open={this.state.open} onClose={this.handleClose} aria-labelledby="alert-dialog-title" aria-describedby="alert-dialog-description">
                <DialogTitle style={{fontSize:"30px", fontWeight:"bolder", color:"black", alignContent:"center"}}>{"Please write an answer!"}</DialogTitle>
                    <DialogContent>
                    <DialogContentText style={{fontSize:"13px"}}>
                            Your answer cannot be blank!
                    </DialogContentText>
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={this.handleClose} color="primary">
                            Ok
                        </Button>
                    </DialogActions>
                </Dialog>
                <div style={{display:'flex'}}>
                    {this.getIcon(this.state.topicQuestion.userP.imageUrl, "3%", "3%")}
                    <Card style={{height:"45px", width:"80%", marginLeft:"1%", marginTop:"3%",  textAlign:"justify", display:"flex", backgroundColor:"#59C3C3", boxShadow:"0px 2px 2px #C6DBF0", color:"white"}}>
                        <CardContent>
                            {this.state.topicQuestion.text}
                        </CardContent>
                    </Card>
                </div>
                <div>
                    <p style={{marginLeft:"60%", fontStyle:"italic", fontSize:"5", color:"grey"}}> posted by {this.state.topicQuestion.userP.firstName + " " + this.state.topicQuestion.userP.lastName} on {this.state.topicQuestion.timestamp} </p>
                    {this.state.replies.map(reply => (
                        <Reply key={reply.idAnswer} replyA={reply} userID={this.state.userID} mentionusers={this.state.mentionusers} color="#0D5C63" textColor="white" />
                    ))} 
                </div>
                <div style={{position:'relative', marginTop:'2%', marginLeft:'5%', width:'85%', display:'flex'}}>
                    <TextField style={{ width:'85%'}} label="Give an answer" variant="outlined" onChange={this.handleReplyText} onKeyUp={this.pressEnter} value={this.state.replyText} />
                    <Button variant="contained" size="small" style={{scale:'0.5', marginLeft:'1%', height:'50px', backgroundColor:'#1F75FE', color:'white'}}
                            onClick={this.replyTopic}> Answer topic </Button>
                </div>
            </div>
        )
    }
}