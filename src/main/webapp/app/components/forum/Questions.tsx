import React, {Component} from 'react';
import Card from '@material-ui/core/Card';
import { CardContent, CardMedia } from '@material-ui/core';
import InputBase from '@material-ui/core/InputBase';
import { display } from '@material-ui/system';

export class Questions extends Component<any, any>{
    constructor(props){
        super(props);
        this.state = {
            questions:[]
        }
    }

    componentDidMount(){
        this.setState({
            questions: [
                {text:"Este examenul la ASC greu?"},
                {text:"Are cineva seminariile la analiza?"},
                {text: "Cum se face abonamentul de CTP?"}
            ]
        })
    }

    render(){
        return(
            <div style={{ height:"60vh", width:"85%", marginTop:"2%", marginLeft:"3%", border:"1px solid #F8F8FF", boxShadow:"0px 0px 1px black"}}>
                {this.state.questions.map(question => (
                    <div key={question.value}>
                        <div style={{display:"flex"}}>
                            <img src="http://www.cs.ubbcluj.ro/wp-content/uploads/Czibula-Istvan.jpg" style={{height:"50px", marginTop:"2%", alignSelf:"center", marginLeft:"3%", borderRadius:"50%" }}></img>
                            <Card style={{height:"45px", width:"80%", marginLeft:"1%", marginTop:"2%",  textAlign:"justify", display:"flex", backgroundColor:"#59C3C3", boxShadow:"0px 2px 2px #C6DBF0", color:"white"}}>
                                <CardContent>
                                    {question.text}
                                </CardContent>
                            </Card>
                        </div>
                    </div>
                ))}                
            </div>
        );
    }
}