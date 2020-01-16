import React, {Component} from 'react';
import TextField from '@material-ui/core/TextField';
import MenuItem from '@material-ui/core/MenuItem';
import Paper from '@material-ui/core/Paper';
import InputBase from '@material-ui/core/InputBase';
import IconButton from '@material-ui/core/IconButton';
import SearchIcon from '@material-ui/icons/Search';
import { Questions } from './Questions';
  
const semesters = [
    { value:1 },
    { value:2 },
    { value:3 },
    { value:4 },
    { value:5 },
    { value:6 }
]

const specializations = [
    { value:"Informatics" },
    { value:"Mathematics & Informatics" },
    { value:"Mathematics" }
]

const languages = [
    { value:"Romanian" },
    { value:"English" },
    { value:"German" }
]

const tags = [
    { value:"c++" },
    { value:"assembly" },
    { value:"observer" },
    { value: "abc" }
]

interface ForumState {
    semester: string,
    tags: string,
    specialization: string,
    language: string,
    searchSemester: string,
    searchTags: string,
    searchSpecialization: string,
    searchLanguage: string
}

export class ForumPage extends Component<any, ForumState>{
    constructor(props){
        super(props);
        this.state = {
            semester: "",
            tags: "",
            specialization:"",
            language:"",
            searchSemester:"",
            searchTags:"",
            searchSpecialization:"",
            searchLanguage:""
        }        
    }

    tagsList = () => {
        return(
            <div style={{display:"flex"}}>
                {tags.map(option => (
                    <MenuItem style={{fontSize:"10px", backgroundColor:"#89CFF0", color:"white", opacity:"0.7", fontStyle:"italic", marginLeft:"3px", marginTop:"10px"}}
                        key={option.value} value={option.value} onClick={()=>this.concatTag(option.value)}>
                    {option.value}
                    </MenuItem>
                ))}
            </div>
        )
    }

    concatTag (optionValue: string){
        this.setState({
            tags : this.state.tags + optionValue + ","
        }) 
    };

    handleChangeSemester = (event: React.ChangeEvent<HTMLInputElement>) => {
        this.setState({
            semester: (event.target.value)
        })
    }

    handleChangeSpecialization = (event: React.ChangeEvent<HTMLInputElement>) => {
        this.setState({
            specialization : (event.target.value)
        })
      };

    handleChangeLanguage = (event: React.ChangeEvent<HTMLInputElement>) => {
        this.setState({
            language : (event.target.value)
        })
      };

    changeTags = (event) => {
        this.setState({
            tags: event.target.value
        })
    }

    searchQuestions = (event) =>{
        this.setState({ 
                searchSemester: this.state.semester,
                searchTags: this.state.tags,
                searchSpecialization: this.state.specialization,
                searchLanguage: this.state.language,
                semester:"",
                tags:"",
                specialization:"",
                language:""
        })
        /* eslint-disable no-console */
        console.log(this.state.semester+";"+this.state.tags+";"+this.state.specialization+";"+this.state.language);
        /* eslint-enable no-console */        
    }

    render() {
        return(
        <div>
            <h2 style={{marginTop: "1%", fontWeight: "bold", color:"#1F305E", fontStyle: "italic", textShadow: "2px 2px #DCDCDC"}}>Welcome to our questions page!</h2>
            <div style={{display:"flex", marginLeft:"3%"}}>
                <div>
                    <TextField id="standard-select-currency" select
                        style={{ marginLeft: "theme.spacing(1)", marginRight: "theme.spacing(1)", width: 100}}
                        value={this.state.semester} onChange={this.handleChangeSemester}
                        label="Semester"
                        margin="normal">
                    {semesters.map(option => (
                        <MenuItem key={option.value} value={option.value}>
                            {option.value}
                        </MenuItem>
                    ))}
                    </TextField>
                </div>
                <div>
                    <TextField id="standard-select-currency" select label="Select specialization"
                            style={{ marginLeft: "10px", marginRight: "theme.spacing(1)", width: 200}}
                            value={this.state.specialization} onChange={this.handleChangeSpecialization}
                            helperText="Please select your specialization" margin="normal" >
                    {specializations.map(option => (
                        <MenuItem key={option.value} value={option.value}>
                            {option.value}
                        </MenuItem>
                    ))}
                    </TextField>
                </div>
                <div>
                    <TextField id="standard-select-currency" select label="Select language"
                            style={{ marginLeft: "10px", marginRight: "theme.spacing(1)", width: 200}}
                            value={this.state.language} onChange={this.handleChangeLanguage}
                            helperText="Please select your language" margin="normal" >
                    {languages.map(option => (
                        <MenuItem key={option.value} value={option.value}>
                            {option.value}
                        </MenuItem>
                    ))}
                    </TextField>
                </div>
                <div style={{marginTop:"1.5%"}}>
                    <Paper style={{width:250, display:"flex", marginLeft:"15%"}}>
                        <InputBase style={{marginLeft:"10px"}} placeholder="Add tags for searching" onChange={event => {this.changeTags(event)}} 
                                                               inputProps={{ 'aria-label': 'search by tags' }} value={this.state.tags}/>
                        <IconButton onClick={this.searchQuestions} aria-label="search">
                           <SearchIcon />
                        </IconButton>
                    </Paper>
                    <this.tagsList></this.tagsList>
                </div>
            </div>
            <Questions semester={this.state.searchSemester} specialization={this.state.searchSpecialization} tags={this.state.searchTags} language={this.state.searchLanguage}/>                
        </div>
    )};
}