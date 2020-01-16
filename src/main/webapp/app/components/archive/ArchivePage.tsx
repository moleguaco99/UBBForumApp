import React from 'react';
import { storage } from './index';
import axios from 'axios';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import CloudUploadIcon from '@material-ui/icons/CloudUpload';
import CloudDownloadIcon from '@material-ui/icons/CloudDownload';
import SaveIcon from '@material-ui/icons/Save';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import Loader from 'react-loader-spinner';

interface ArchivePageInterface{
    fileName: string,
    file: any,
    url: string,
    archives: Archive[],
    uploadDescription: string,
    user: any,
    open: boolean,
    loading: boolean
}

type Archive = {
    idArchive: number,
    archivePath: string,
    title: string,
    description: string,
    timestamp: string,
    userA: any
}

export class ArchivePage extends React.Component<any, ArchivePageInterface>{
    constructor(props){
        super(props);
        this.state={
            fileName: "",
            file: null,
            url: "",
            archives: [],
            uploadDescription: "",
            user: null,
            open: false,
            loading: false
        }
    }

    componentDidMount(){
         /* eslint-disable no-console */
         axios.get('api/account').
         then(response => this.setState({
             user: response.data
         }))

        fetch("http://localhost:8080/ourApi/archives/")
        .then(response => response.json())
        .then(json => {
            this.setState({
                archives: json
            })
        })
        .catch(error => console.log(error))
    }

    parseDate(date: string){
        const newDate = date.split("T");
        return newDate[0] + " " + newDate[1].split(".")[0];
    }

    handleChange = (event) => {
        if(event.target.files[0]){
            const file = event.target.files[0]
            const fileName = file.name
            console.log(fileName);
            this.setState(() => ({file, fileName}))
        }
    }

    handleDescriptionChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        this.setState({
            uploadDescription: event.target.value
        })
    }

    handleUpload = () => {
        const {file} = this.state;
        const uploadTask = storage.ref(`files/${file.name}`).put(file);
        this.setState({
            loading: true
        })
        uploadTask.on('state_changed', (snapshot)=>{ },
         (error) => {
            console.log(error);
            }, ()=>{
            storage.ref('files').child(file.name).getDownloadURL().then(url => {
                console.log(this.state.uploadDescription)
                fetch("http://localhost:8080/ourApi/archiveUpload", {
                    method: "POST",
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        title: file.name,
                        description: this.state.uploadDescription,
                        archivePath: url,
                        approved: true,
                        userA: this.state.user
                    })
                }).then(response => {
                    this.setState({
                        open: true,
                        fileName: "",
                        url: "",
                        file: null,
                        uploadDescription: "",
                        loading: false
                    })
                })
            })
        });
    }

    handleClose = () => {
        this.setState({
            open: false
        })
    }

    getIcon(imageUrl: string){
        return imageUrl !== null ? 
           <img src={imageUrl} 
               style={{height:"25px", alignSelf:"center", borderRadius:"50%"}}></img> :
           <img src="content\images\user-icon.png"
               style={{height:"25px", alignSelf:"center", borderRadius:"50%", border:"1px solid #D8D8D8"}}></img>
    }

    render(){
        return(
        <div style={{height:"80vh", overflow:"auto"}}>
            <Dialog
                open={this.state.open}
                onClose={this.handleClose}
                aria-labelledby="alert-dialog-title"
                aria-describedby="alert-dialog-description">
                <DialogTitle>{"Upload succesfully"}</DialogTitle>
                <DialogContent>
                <DialogContentText >
                    Your archive has been uploaded succesfully! Thank you for using our page!
                </DialogContentText>
                </DialogContent>
                    <DialogActions>
                        <Button onClick={this.handleClose} color="primary" autoFocus>
                                Thank you!
                        </Button>
                    </DialogActions>
            </Dialog>
            <h2 style={{marginTop: "1%", fontWeight: "bold", color:"#1F305E", fontStyle: "italic", textShadow: "2px 2px #DCDCDC"}}>Welcome to our archive page!</h2>
                <div style={{display: "flex", marginTop:"2%"}}>
                    <div style={{marginLeft:"8%"}}>
                        <h3 style={{marginTop: "4%", fontWeight: "bold", color:"#1F305E", fontStyle: "italic", textShadow: "2px 2px #DCDCDC"}}>Uploading form:</h3>
                        <TextField style={{ width:'100%', marginTop:"2%"}} 
                                    label="Archive title" variant="outlined" value={this.state.fileName} read-only="true"></TextField>

                        <TextField style={{ width:'100%', marginTop:"2%"}} 
                                    label="Enter a description" variant="outlined" onChange={this.handleDescriptionChange} value={this.state.uploadDescription}></TextField>

                        <div style={{marginTop:"4%"}}>
                            <input
                                style={{display:"none"}}
                                id="contained-button-file"
                                multiple
                                type="file"
                                onChange={this.handleChange}
                            />
                            <label htmlFor="contained-button-file">
                                <Button variant="contained" component="span" color="secondary" startIcon={<SaveIcon />}>
                                      Choose a file
                                </Button>
                            </label>
                            <br/>
                            <div style={{display: "flex"}}>
                                <Button variant="contained" color="primary" startIcon={<CloudUploadIcon />} onClick={this.handleUpload}>
                                      Upload
                                </Button>
                                {this.state.loading ? <div style={{marginLeft:"2%", marginTop:"1%"}}>
                                                        <Loader type="Oval" color="#303F9F" height={30} width={30}/>
                                                      </div> : null}
                            </div>
                        </div>
                          
                    </div>
                    <div style={{marginLeft: "10%", borderLeft:"1px solid #D0D0D0"}}>
                    <h3 style={{marginTop: "2%", marginLeft:"16%", fontWeight: "bold", color:"#1F305E", fontStyle: "italic", textShadow: "2px 2px #DCDCDC"}}>Available archives:</h3>    
                    <table style={{border:"1px solid #D0D0D0",  marginTop:"4%", marginLeft:"16%", marginRight:"2%", width:"130%"}}>
                        <tbody>
                        <tr style={{textAlign:"center"}}>
                            <th style={{width:"20%", }}>Name</th>
                            <th style={{width:"20%", }}>Description</th>
                            <th style={{width:"20%", }}>Uploader</th>
                            <th style={{width:"20%", }}>Upload date</th>   
                            <th style={{width:"20%", }}>Download</th>
                        </tr> 
                        {this.state.archives.map(archive => (
                            <tr style={(archive.idArchive % 2 === 0) ? {textAlign:"center", backgroundColor:"#F5F5F5", paddingTop:"10%", paddingBottom:"10%"} : {textAlign:"center", paddingTop:"1%", paddingBottom:"1%"}} key={archive.idArchive}>
                                <td style={{paddingLeft:"1%", paddingRight:"1%"}}>{archive.title}</td>
                                <td style={{paddingLeft:"1%", paddingRight:"1%"}}>{archive.description}</td>
                                <td style={{paddingLeft:"1%", paddingRight:"1%"}}>{this.getIcon(archive.userA.imageUrl)} {archive.userA.firstName}</td>
                                <td style={{paddingLeft:"1%", paddingRight:"1%"}}>{this.parseDate(archive.timestamp)}</td>
                                <td style={{paddingTop:"1%", paddingBottom:"1%", paddingLeft:"1%", paddingRight:"1%"}}>
                                    <Button variant="text" size="medium" component="span" color="primary" startIcon={<CloudDownloadIcon />}>
                                         <a href={archive.archivePath} target="_blank" rel="noopener noreferrer" download={archive.title || archive.archivePath}> get</a>
                                    </Button>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                    </div>
                </div>
            </div>
        )
    }
}