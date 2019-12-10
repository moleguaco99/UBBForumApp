import React, {Component} from 'react';
import Card from '@material-ui/core/Card';
import { CardContent } from '@material-ui/core';

export class TopicPage extends React.Component<any, any>{
    
    constructor(props){
        super(props);
        this.state = {
            replies:['reply#1', 'reply#2', 'reply#3']
        }
    }

    render(){
        return(
            <div style={{overflow:'auto'}}>
                <div style={{display:'flex'}}>
                    {this.props.location.state.from.userP.imageUrl !== null ? 
                            <img src={this.props.location.state.from.userP.imageUrl} style={{height:"50px", marginTop:"1%", alignSelf:"center", marginLeft:"3%", borderRadius:"50%" }}></img> 
                            :
                            <img src="content\images\user-icon.png" style={{height:"50px", marginTop:"1%", alignSelf:"center", marginLeft:"3%", borderRadius:"50%", border:"1px solid #D8D8D8"  }}></img>
                    }
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
                        </div>
                        ))} 
                </div>
            </div>
        )
    }
}