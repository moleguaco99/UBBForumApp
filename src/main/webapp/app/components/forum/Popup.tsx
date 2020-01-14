import React from 'react';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';

interface PopupInterface {
    open: boolean,
}

export class Popup extends React.Component<any, PopupInterface> {
    constructor(props){
        super(props);
        this.setState({
            open: props.open,
        })
    }

    closure = () => {
        this.props.popup();
    }

    render(){
        return(                
            <Dialog open={true} aria-labelledby="alert-dialog-title" aria-describedby="alert-dialog-description">
            <DialogTitle style={{fontSize:"30px", fontWeight:"bolder", color:"black", alignContent:"center"}}>{this.props.title}</DialogTitle>
            <DialogContent>
                <DialogContentText style={{fontSize:"13px"}}>
                        {this.props.content}
                </DialogContentText>
            </DialogContent>
            <DialogActions>
                <Button onClick={this.closure} color="primary">
                    {this.props.button}
                </Button>
            </DialogActions>
            </Dialog>
        )
    }
}