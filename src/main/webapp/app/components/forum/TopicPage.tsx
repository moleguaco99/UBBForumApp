import React, { Component } from 'react';
import Card from '@material-ui/core/Card';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import { CardContent } from '@material-ui/core';

export class TopicPage extends React.Component<any, any>{
    
    constructor(props){
        super(props);
        this.state = {
            replies:[],
            replyText: ""
        }
    }

    handleReplyText = (event) => {
        this.setState({
            replyText: event.target.value
        })
    }

    replyTopic = () => {
        this.setState(prevState => {
            prevState.replies.push(prevState.replyText)
            return {
                replies: prevState.replies,
                replyText: "" 
            }
        })
    }

    pressEnter = (event : React.KeyboardEvent<HTMLInputElement>) => {
        if(event.keyCode === 13){
            this.replyTopic();
        }
    }

    render(){
        return(
            <div style={{overflow:'auto'}}>
                <div style={{display:'flex'}}>
                    {this.props.location.state.from.userP.imageUrl !== null ? 
                            <img src={this.props.location.state.from.userP.imageUrl} style={{height:"50px", marginTop:"1%", alignSelf:"center", marginLeft:"3%", borderRadius:"50%" }}></img> 
                            :
                            <img src="content\images\user-icon.png" style={{height:"50px", marginTop:"1%", alignSelf:"center", marginLeft:"3%", borderRadius:"50%", border:"1px solid #D8D8D8"  }}></img>}

                    <Card style={{height:"45px", width:"80%", marginLeft:"1%", marginTop:"1%",  textAlign:"justify", display:"flex", backgroundColor:"#59C3C3", boxShadow:"0px 2px 2px #C6DBF0", color:"white"}}>
                        <CardContent>
                            {this.props.location.state.from.text}
                        </CardContent>
                    </Card>
                </div>
                <div>
                {this.state.replies.map(reply => (
                    <div key={reply} style={{display:'flex', marginLeft:"5%"}}>
                        <img src="content\images\user-icon.png" style={{height:"50px", marginTop:"1%", alignSelf:"center", marginLeft:"3%", borderRadius:"50%", border:"1px solid #D8D8D8"  }}></img>
                        <Card 
                            style={{height:"45px", width:"80%", marginTop:"1%", marginLeft:'1%', textAlign:"justify", display:"flex", backgroundColor:"#0D5C63", boxShadow:"0px 2px 2px #C6DBF0", color:"white"}}>
                            <CardContent>
                                {reply}
                            </CardContent>
                        </Card>
                    </div> ))} 
                </div>
                <div style={{position:'relative', marginTop:'1%', marginLeft:'5%', width:'85%', display:'flex'}}>
                    <TextField style={{ width:'85%'}} label="Give an answer" variant="outlined" onChange={this.handleReplyText} onKeyUp={this.pressEnter} value={this.state.replyText} />
                    <Button variant="contained" size="small" style={{scale:'0.5', marginLeft:'1%', height:'50px', backgroundColor:'#1F75FE', color:'white'}}
                            onClick={this.replyTopic}> Answer topic </Button>
                </div>
            </div>
        )
    }
}