import React from 'react';
import Card from '@material-ui/core/Card';
import { CardContent } from '@material-ui/core';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faThumbsUp } from '@fortawesome/free-solid-svg-icons';
import { faReply } from '@fortawesome/free-solid-svg-icons';
import TextField from '@material-ui/core/TextField';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import ListItemAvatar from '@material-ui/core/ListItemAvatar';
import Avatar from '@material-ui/core/Avatar';
import "./styles.css";
import { Popup } from "./Popup";

export class Reply extends React.Component<any, any>{
    constructor(props){
        super(props);
        this.state = {
            voted: false,
            replies: [],
            answerForm: false,
            replyText: "",
            mention: false,
            idInterval: 0,
            notifyUsers: "",
            disable: false,
            open: false
        }
    }

    componentDidMount(){
        
        /* eslint-disable no-console */
        fetch("http://localhost:8080/ourApi/isvoted", {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },  
            body: JSON.stringify({
                answerVotedMap: {
                    idAnswer: this.props.replyA.idAnswer,
                    idUser: this.props.userID,
                }
            })
        })
        .then(response => response.json())
        .then(json => {
            this.setState({
                voted: json
            })
        })
        .catch(error => {console.log(error)})

        if(this.props.replyA.idAnswer !== null){

            fetch("http://localhost:8080/ourApi/replies/" + this.props.replyA.idAnswer)
            .then(response => response.json())
            .then(json => {
                this.setState({
                    replies: json
                })
            })
            .catch(error => console.log(error))

            this.setState({
                idInterval: setInterval(this.refreshReplies, 3000)
            })
        }
    }

    refreshReplies = () => {

        fetch("http://localhost:8080/ourApi/replies/" + this.props.replyA.idAnswer)
        .then(response => response.json())
        .then(json => {
            this.setState({
                replies: json
            })
        })
        .catch(error => console.log(error))

    }

    toggleVote = () => {
        console.log(this.state.disable);
        if(this.state.disable === false){
            this.setState(prevState => {
                return {
                    voted: !prevState.voted,
                    disable: true
                }
            })
        
        fetch("http://localhost:8080/ourApi/vote/", {
            method:'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                answerRatingMap: {
                    idAnswer: this.props.replyA.idAnswer,
                    idUser: this.props.userID,
                    vote: this.state.voted ? -1 : 1
                }
            })
        }).catch(error => {console.log(error)})

        setTimeout(()=>{ this.setState({
                            disable:false })}, 3000)
    }
}

    toggleAnswerForm = () =>{
        this.setState(prevState => {
            return {
                answerForm: !prevState.answerForm
            }
        })
    }

    pressEnter = (event : React.KeyboardEvent<HTMLInputElement>) => {
        if(event.keyCode === 13){
            this.replyTopic();
        }
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
                    idUser: this.props.userID,
                    idQA: this.props.replyA.idAnswer,
                    rating: 0,
                    text: this.state.replyText,
                    type: 'A'
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
                    answerForm: false,
                    notifyUsers: ""
                })
        }
    }
    
    triggerMention = (event : React.KeyboardEvent<HTMLInputElement>) => {

        if(event.key === "@"){
            this.setState({
                mention: true
            })
        }
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

    getIcon(imageUrl:string, marginL:string, marginT:string){
        
        return imageUrl !== null ? 
           <img src={imageUrl} 
               style={{height:"50px", marginTop: marginT, alignSelf:"center", marginLeft: marginL, borderRadius:"50%"}}></img> :
           <img src="content\images\user-icon.png"
               style={{height:"50px", marginTop: marginT, alignSelf:"center", marginLeft: marginL, borderRadius:"50%", border:"1px solid #D8D8D8"}}></img>
    }

    parseDate(date: string){
        const newDate = date.split("T");
        return newDate[0] + " " + newDate[1].split(".")[0];
    }

    handleClose = () => {
        this.setState({
            open: false
        })
    }

    componentWillUnmount(){
        clearInterval(this.state.idInterval);
    }

    render(){
        return(
            <div>              

                {this.state.open ? <Popup title={"Try giving a good reply"} 
                                            content={"Would you be so kind to actually write something? Your friends need relevant information"} 
                                            button={"My bad..."} popup={this.handleClose}></Popup> : null}          
            
                <div style={{display:"flex", marginLeft:"10%"}}>
                    <Card onClick={this.toggleAnswerForm}
                        style={{height:"45px", width:"80%", marginTop:"1%", marginLeft:"1%", textAlign:"end", backgroundColor:this.props.color, boxShadow:"0px 2px 2px #C6DBF0", color:this.props.textColor}}>
                        <CardContent>
                            {this.props.replyA.text}
                        </CardContent>
                    </Card>
                    {this.getIcon(this.props.replyA.userP.imageUrl, "1%", "1%")}
                </div>

                <div style={{display:"flex"}}>
                    <p style={{marginLeft:"16%", fontStyle:"italic", fontSize:"5", color:"grey"}}> posted by {this.props.replyA.userP.firstName + " " + this.props.replyA.userP.lastName} on {this.parseDate(this.props.replyA.timestamp)}  &nbsp; &nbsp; {this.props.replyA.rating} </p>
                    
                    <div style={{marginLeft:"0.5%"}} onClick={this.toggleVote}>
                        <FontAwesomeIcon style={this.state.voted ? {fontStyle: "italic", color:"#1F75FE"} : {fontStyle: "italic", color:"grey"} } icon={faThumbsUp} />
                    </div> 
                </div>
                
                {this.state.answerForm ?       
                    <div style={{marginLeft:"36%"}}> 
                        <div style={{display:"flex"}}>
                            <TextField style={{width:"73%", marginBottom:"1%"}}
                                    label="Reply to answer" variant="outlined" onChange={this.handleReplyText} 
                                        onKeyUp={this.pressEnter} onKeyPress={this.triggerMention} value={this.state.replyText} />
                        <div style={{marginLeft:"2%", marginTop:"1%", marginBottom:"1%"}} onClick={this.replyTopic}>
                            <FontAwesomeIcon style={{ color:"#191970"}} size="2x" icon={ faReply } />
                        </div>
                    </div>
                        
                    {this.state.mention ? 
                        <List style={{ position: "absolute", backgroundColor: "white", zIndex:5, width:"15%" }}  dense={true}>
                            {this.props.mentionusers.map(user=>(
                                    <ListItem key={user.idUser} className="mentionuser" onClick={() => this.mention(user.firstName + " " + user.lastName)}>
                                        <ListItemAvatar>
                                            <Avatar src={user.imageUrl} />
                                        </ListItemAvatar>
                                        <ListItemText primary={user.firstName + " " + user.lastName}/>
                                    </ListItem>
                            ))}
                        </List> : null}
                    </div> : null 
                }

                <div style={{width:"90%", marginLeft:"8%"}}>
                    {this.state.replies.map(reply => (
                                <Reply key={reply.idAnswer} replyA={reply} userID={this.props.userID} 
                                        mentionusers={this.props.mentionusers} color="#360568" textColor="white"/>
                    ))}
                </div>

            </div> 
        )
    }
}
