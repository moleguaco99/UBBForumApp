import React, {Component} from 'react';
import Card from '@material-ui/core/Card';
import { CardContent, CardMedia } from '@material-ui/core';
import TextField from '@material-ui/core/TextField';
import { display } from '@material-ui/system';
import Button from '@material-ui/core/Button';

export class Questions extends Component<any, any>{
    intervals = [];
    constructor(props){
        super(props);
        this.state = {
            questions:[],
            questionText: ""
        }
    }
    
    componentDidMount(){
        /* eslint-disable no-console */
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

    getQuestions = (tags) =>{
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
                
                this.getQuestions(tags);
                this.intervals.forEach(clearInterval);
                this.intervals.push(setInterval(this.getQuestions, 2000, tags));
                
        }
    }

    askQuestion = (event) =>{
        const tagsa = []
        this.props.semester !== "" ? tagsa.push(this.props.semester) : null;
        this.props.specialization !== "" ? tagsa.push(this.props.specialization) : null;
        this.props.language !== "" ? tagsa.push(this.props.language) : null;
        this.props.tags !== "" ? new Set(this.props.tags.split(",")).forEach(item => {
                                                if(item !== "")
                                                tagsa.push(item); }) : null

        fetch('http://localhost:8080/ourApi/question', {
            method:'POST',
            headers:{
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                text: this.state.questionText,
                idUser: 4,
                tags: tagsa,
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

    render(){
        return(
            <div style={{ height:"60vh", width:"85%", marginTop:"2%", marginLeft:"3%", border:"1px solid #F8F8FF", boxShadow:"0px 0px 1px black"}}>
                {this.state.questions.map(question => (
                    <div key={question.text}>
                        <div style={{display:"flex"}}>
                            <img src={question.userP.imageUrl} style={{height:"50px", marginTop:"2%", alignSelf:"center", marginLeft:"3%", borderRadius:"50%" }}></img>
                            <Card style={{height:"45px", width:"80%", marginLeft:"1%", marginTop:"2%",  textAlign:"justify", display:"flex", backgroundColor:"#59C3C3", boxShadow:"0px 2px 2px #C6DBF0", color:"white"}}>
                                <CardContent>
                                    {question.text}
                                </CardContent>
                            </Card>
                        </div>
                            <p style={{marginLeft:'60%', fontStyle:'italic', fontSize:'5', color:'grey'}}>posted by {question.userP.firstName + " " + question.userP.lastName} on {question.timestamp} </p>
                    </div>
                ))} 
                <div style={{position:'absolute', bottom:'0px', marginLeft:'2%', width:'85%', display:'flex'}}>
                    <TextField style={{marginBottom:'2%', width:'85%'}} label="Ask a question" variant="outlined" onChange={this.handleQuestionText} value={this.state.questionText} />
                    <Button variant="contained" size="small" style={{scale:'0.5', marginLeft:'1%', height:'50px', backgroundColor:'#59C3C3', color:'white', marginRight:'5%'}} onClick={this.askQuestion}> Post question </Button>
                </div>          
            </div>
        );
    }
}