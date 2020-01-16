import React from 'react';
import Card from '@material-ui/core/Card';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import { CardContent } from '@material-ui/core';
import axios from 'axios';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import ListItemAvatar from '@material-ui/core/ListItemAvatar';
import Avatar from '@material-ui/core/Avatar';
import { Reply } from './Reply';
import "./styles.css";
import { Popup } from './Popup';

export class TopicPage extends React.Component<any, any>{
    
    constructor(props){
        super(props);
        this.state = {
            replies:[],
            replyText: "",
            userID: 0,
            topicQuestion: this.props.location.state.from,
            open: false,
            mentionusers: [],
            notifyUsers: "",
            idInterval: 0,
            mention: false
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
        .catch(error => console.log(error))
        
        this.setState({
            idInterval: setInterval(this.refreshQuestions, 2000)
        })

        fetch('http://localhost:8080/ourApi/mentionedusers')
        .then(response => response.json())
        .then(json => {
            this.setState({
                mentionusers: json 
            })
        })
        .catch(error => console.log(error))
    }

    refreshQuestions = () => {
        fetch('http://localhost:8080/ourApi/answers/'+this.state.topicQuestion.idQuestion)
        .then(response => response.json())
        .then(json => this.setState({
          replies: json
        }))
        .catch(error => console.log(error))
    }

    handleReplyText = (event: React.ChangeEvent<HTMLInputElement>) => {
        this.setState({
            replyText: event.target.value
        })
        if(!event.target.value.includes("@")){
            this.setState({
                mention: false
            })  
        }
    }
    
    getIcon(imageUrl:string, marginL:string, marginT:string){
        
        return imageUrl !== null ? 
            <img src={imageUrl} 
                style={{height:"50px", marginTop: marginT, alignSelf:"center", marginLeft: marginL, borderRadius:"50%" }}></img> :
            <img src="content\images\user-icon.png"
                style={{height:"50px", marginTop: marginT, alignSelf:"center", marginLeft: marginL, borderRadius:"50%", border:"1px solid #D8D8D8"}}></img>
    }
 
    triggerMention = (event : React.KeyboardEvent<HTMLInputElement>) => {

        if(event.key === "@"){
            this.setState({
                mention: true
            })
        }
    }

    replyTopic = () => {

        if(this.state.replyText === ""){
            this.setState({
                open: true
            })
        }
        else {
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
                const usersList = this.state.notifyUsers.split(";");
                const taggedUsers = [];

                usersList.forEach(element => {
                    if(this.state.replyText.includes(element) && element !== "")
                        taggedUsers.push(element);
                });
                
                fetch('http://localhost:8080/ourApi/tagusers/', {
                    method: 'POST',
                    headers:{
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({ 
                        users: taggedUsers })
                }).catch(error => console.log(error))

                this.setState({ 
                    replyText: "",
                    notifyUsers: ""
                })
        }
    }

    pressEnter = (event : React.KeyboardEvent<HTMLInputElement>) => {
        if(event.keyCode === 13){
            this.replyTopic();
        }
    }

    handleClose = () =>{
        this.setState({ open: false })
    }

    parseDate(date: string){
        const newDate = date.split("T");
        return newDate[0] + " " + newDate[1].split(".")[0];
    }

    mention = (msg: string) => {

        let val = this.state.replyText.substring(0, this.state.replyText.length-1)
        val += msg;
        this.setState(prevState => {
            return {
                replyText: val,
                mention: false,
                notifyUsers: prevState.notifyUsers + msg + ";"
            }
        })
    }

    componentWillUnmount(){
        clearInterval(this.state.idInterval)
    }

    render(){
        return(
            <div style={{overflow:'auto'}}>
                
                {this.state.open ? <Popup title={"Try giving a better answer"} 
                                            content={"You have to do a lot better than that if you want to post an answer"} 
                                            button={"My bad..."} popup={this.handleClose}></Popup> : null}
                
                <div style={{display:'flex'}}>
                    {this.getIcon(this.state.topicQuestion.userP.imageUrl, "3%", "3%")}
                    <Card style={{height:"45px", width:"80%", marginLeft:"1%", marginTop:"3%",  textAlign:"justify", display:"flex", backgroundColor:"#59C3C3", boxShadow:"0px 2px 2px #C6DBF0", color:"white"}}>
                        <CardContent>
                            {this.state.topicQuestion.text}
                        </CardContent>
                    </Card>
                </div>
                
                <div>
                    <p style={{marginLeft:"60%", fontStyle:"italic", fontSize:"5", color:"grey"}}> posted by {this.state.topicQuestion.userP.firstName + " " + this.state.topicQuestion.userP.lastName} on {this.parseDate(this.state.topicQuestion.timestamp)} </p>
                    {this.state.replies.map(reply => (
                        <Reply key={reply.idAnswer} replyA={reply} userID={this.state.userID} mentionusers={this.state.mentionusers} color="#0D5C63" textColor="white" />
                    ))} 
                </div>

                <div style={{position:'relative', marginTop:'2%', marginLeft:'5%', width:'85%', display:'flex'}}>
                    <TextField style={{ width:'85%'}} label="Give an answer" variant="outlined" onChange={this.handleReplyText} onKeyUp={this.pressEnter} onKeyPress={this.triggerMention} value={this.state.replyText} />
                    <Button variant="contained" size="small" style={{scale:'0.5', marginLeft:'1%', height:'50px', backgroundColor:'#1F75FE', color:'white'}}
                            onClick={this.replyTopic}> Answer topic </Button>
                </div>
                {this.state.mention ? 
                        <List style={{ backgroundColor: "white", zIndex:5 , width:"15%", marginLeft:"5%"}} dense={true} >
                            {this.state.mentionusers.map(user=>(
                                    <ListItem key={user.idUser} className="mentionuser" onClick={() => this.mention(user.firstName + " " + user.lastName)}>
                                        <ListItemAvatar>
                                            <Avatar src={user.imageUrl} />
                                        </ListItemAvatar>
                                        <ListItemText primary={user.firstName + " " + user.lastName}/>
                                    </ListItem>
                            ))}
                        </List> : null}

            </div>
        )
    }
}