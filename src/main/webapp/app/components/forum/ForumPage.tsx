import React, {Component} from 'react';
import TextField from '@material-ui/core/TextField';
import MenuItem from '@material-ui/core/MenuItem';
import Paper from '@material-ui/core/Paper';
import InputBase from '@material-ui/core/InputBase';
import IconButton from '@material-ui/core/IconButton';
import SearchIcon from '@material-ui/icons/Search';
import { Questions } from './Questions';
  
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

export class ForumPage extends Component{
    constructor(props){
        super(props);
        this.state = {
            tags: "",
            specialization:"",
            language:""
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

    render() {
        return(
        <div>
            <h2 style={{marginTop: "2%", fontWeight: "bold", color:"#1F305E", fontStyle: "italic", textShadow: "2px 2px #DCDCDC"}}>Welcome to our questions page!</h2>
            <form style={{display:"flex", marginLeft:"3%"}} >
                <div>
                    <TextField style={{ marginLeft: "theme.spacing(1)", marginRight: "theme.spacing(1)", width: 100}}
                        label="Semester"
                        margin="normal" />
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
                        <InputBase style={{marginLeft:"10px"}} placeholder="Add tags for searching" inputProps={{ 'aria-label': 'search by tags' }} value={this.state.tags}/>
                        <IconButton type="submit" aria-label="search">
                           <SearchIcon />
                        </IconButton>
                    </Paper>
                    <this.tagsList></this.tagsList>
                </div>
            </form>
            <Questions specialization={this.state.specialization} tags={this.state.tags} language={this.state.language} />                
        </div>
    )};
}