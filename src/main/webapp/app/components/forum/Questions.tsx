import React, {Component} from 'react';
import Card from '@material-ui/core/Card';
import { CardContent, Icon } from '@material-ui/core';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import { Redirect, Link } from 'react-router-dom';
import axios from 'axios';

export class Questions extends Component<any, any>{
    intervals = [];
    constructor(props){
        super(props);
        this.state = {
            questions:[],
            questionText: "",
            redirect: false,
            highlightedQuestion: "",
            userID: 0
        }
    }
    
    componentDidMount(){
        axios.get('api/account').
            /* eslint-disable no-console */
            then(response => this.setState({
                userID: response.data.id
            }))

        fetch('http://localhost:8080/ourApi/questions/')
            .then(response=> response.json())
            .then(json => {
                this.setState({
                    questions: json
                })
            })
            .catch((error) => console.log(error))

        this.intervals.forEach(clearInterval);
        this.intervals.push(setInterval(this.getQuestions, 2000));  
    }

    getQuestions = () => {
        fetch('http://localhost:8080/ourApi/questions/')
            .then(response=> response.json())
            .then(json => {
                this.setState({
                    questions: json
                })
            })
        /* eslint-disable no-console */
        .catch((error) => console.log(error))

    }

    getFilteredQuestions = (tags) =>{
        fetch('http://localhost:8080/ourApi/questions/filter', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                filters: tags
            })
            })
            .then(response=> response.json())
            .then(json => {
                this.setState({
                    questions: json
                })
            })
        /* eslint-disable no-console */
        .catch((error) => console.log(error))
    }

    createText(){
        let info = "";
        if(this.props.semester !== "")
            info = info + ("semester: ") + this.props.semester +", ";
        
        if(this.props.specialization !== "")
            info = info + this.props.specialization +", ";
        
        
        if(this.props.language !== "")
            info = info + this.props.language +" ";

        if(this.props.tags !== ""){
            new Set(this.props.tags.split(",")).forEach(item => {
                if(item !== "")
                    info += item + ", ";
            })
        }

        if(info.endsWith(", "))
            info = info.slice(0, info.length-2)
        
        if(this.props.semester === "" && this.props.specialization === ""
            && this.props.language === "" && this.props.tags === "")
            return "all the questions!";
        return info+"!";    
    }

    componentWillReceiveProps(nextProps){
        if(nextProps.semester !== this.props.semester ||  nextProps.specialization !== this.props.specialization ||
           nextProps.language !== this.props.language ||  nextProps.tags !== this.props.tags){
                const tags = []
                nextProps.semester !== "" ? tags.push(nextProps.semester) : null;
                nextProps.specialization !== "" ? tags.push(nextProps.specialization) : null;
                nextProps.language !== "" ? tags.push(nextProps.language) : null;
                nextProps.tags !== "" ? new Set(nextProps.tags.split(",")).forEach(item => {
                                                if(item !== "")
                                                tags.push(item); }) : null
                
                this.getFilteredQuestions(tags);
                this.intervals.forEach(clearInterval);
                this.intervals.push(setInterval(this.getFilteredQuestions, 2000, tags));   
        }
    }

    askQuestion = () =>{
        const newTags = []
        this.props.semester !== "" ? newTags.push(this.props.semester) : null;
        this.props.specialization !== "" ? newTags.push(this.props.specialization) : null;
        this.props.language !== "" ? newTags.push(this.props.language) : null;
        this.props.tags !== "" ? new Set(this.props.tags.split(",")).forEach(item => {
                                                if(item !== "")
                                                newTags.push(item); }) : null
        
        fetch('http://localhost:8080/ourApi/question', {
            method:'POST',
            headers:{
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                text: this.state.questionText,
                idUser: this.state.userID,
                tags: newTags,
            })
        }).catch((error) => {console.log(error)})
        this.setState({
            questionText: ""
        })
    }

    handleQuestionText = (event) => {
        this.setState({
            questionText: event.target.value
        })
    }

    pressEnter = (event: React.KeyboardEvent<HTMLInputElement>) => {
        if(event.keyCode === 13){
            this.askQuestion();
        }
    }

    componentWillUnmount(){
        this.intervals.forEach(clearInterval);
    }

    render(){
        return(
            <div>
            {this.state.redirect ?  <Redirect push to={{ pathname:'/topic', state:{ from : this.state.highlightedQuestion} }} /> : null}
            <div style={{height:"52vh", width:"85%", marginTop:"2%", marginLeft:"3%", border:"1px solid #F8F8FF", boxShadow:"0px 0px 1px black", overflow: 'auto'}}>
                <h5 style={{marginTop: "2%", marginLeft:"3%", fontWeight: "bold", color:"#1F305E", fontStyle: "italic", textShadow: "2px 2px #DCDCDC"}}>You searched for {this.createText()} </h5>
                {this.state.questions.map(question => (
                    <div key={question.text}>
                        <div style={{display:"flex"}}>
                            {question.userP.imageUrl !== null ? 
                                <img src={question.userP.imageUrl} 
                                    style={{height:"50px", marginTop:"1%", alignSelf:"center", marginLeft:"3%", borderRadius:"50%" }} /> :
                                <img src="content\images\user-icon.png"
                                    style={{height:"50px", marginTop:"1%", alignSelf:"center", marginLeft:"3%", borderRadius:"50%", border:"1px solid #D8D8D8" }} />
                            }
                            <Card onClick={() => {this.setState({
                                            redirect: true,
                                            highlightedQuestion: question})
                                        }} 
                                style={{height:"45px", width:"80%", marginLeft:"1%", marginTop:"1%",  textAlign:"justify", display:"flex", backgroundColor:"#59C3C3", boxShadow:"0px 2px 2px #C6DBF0", color:"white"}}>
                                <CardContent>
                                    {question.text}
                                </CardContent>
                            </Card>
                        </div>
                            <p style={{marginLeft:'60%', fontStyle:'italic', fontSize:'5', color:'grey'}}> posted by {question.userP.firstName + " " + question.userP.lastName} on {question.timestamp} </p>
                    </div>
                ))} 
            </div>
                <div style={{position:'relative', marginTop:'1%', marginLeft:'3%', width:'85%', display:'flex'}}>
                    <TextField style={{ width:'85%'}} label="Ask a question" variant="outlined" onChange={this.handleQuestionText} onKeyUp={this.pressEnter} value={this.state.questionText} />
                    <Button variant="contained" size="small" style={{scale:'0.5', marginLeft:'1%', height:'50px', backgroundColor:'#1F75FE', color:'white'}} onClick={this.askQuestion}> Post question </Button>
                </div>       
            </div>
        );
    }
}