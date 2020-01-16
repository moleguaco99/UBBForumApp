import React from 'react';
import ReactSearchBox from 'react-search-box';
import '../teachers/cardStyle.css';
import { TeacherCard } from '../teachers/TeacherCard';
import TextField from '@material-ui/core/TextField';
import { faLongArrowAltRight } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

interface TeacherInterface {
  teachers: teacher[];
  filteredTeachers: teacher[];
  searchedValue: string;
  teacherDetailsActive: boolean;
  teacherToShow: teacher;
  subjectsOfInterest: any;
}
type teacher = {
  idTeacher: number;
  firstName: string;
  lastName: string;
  email: string;
  photoPath: string;
  webPage: string;
  rank: string;
};
export class TeachersPage extends React.Component<any, TeacherInterface> {
  constructor(props) {
    super(props);
    this.state = {
      teachers: [],
      filteredTeachers: [],
      searchedValue: '',
      teacherDetailsActive: false,
      teacherToShow: null,
      subjectsOfInterest: ""
    };
  }
  componentDidMount() {
    fetch('http://localhost:8080/ourApi/teachers')
      .then(response => response.json())
      .then(json => {
        this.setState({
          teachers: json,
          filteredTeachers: json
        });
      })
      /* eslint-disable no-console */
      .catch(error => console.log(error));
  }

  handleSearch = (value: string) => {
    this.setState({
      searchedValue: value
    });
    const lowValue = value.toLowerCase();
    const filteredTeachers1 = this.state.teachers.filter((teacher: teacher) => {
      if (lowValue.includes(' ')) {
        return lowValue.includes(teacher.firstName.toLowerCase()) || lowValue.includes(teacher.lastName.toLowerCase());
      } else {
        return teacher.firstName.toLowerCase().includes(lowValue) || teacher.lastName.toLowerCase().includes(lowValue);
      }
    });
    this.setState({
      filteredTeachers: filteredTeachers1
    });
  };
  showTeacherDetails = (teacher: teacher) => {
    this.setState({
      teacherDetailsActive: true,
      teacherToShow: teacher
    });
   
    fetch('http://localhost:8080/ourApi/subjectsTeacher/'+teacher.idTeacher)
      .then(response => response.json())
      .then(json => {
        this.setState({
          subjectsOfInterest: json.value
        })
      })
      /* eslint-disable no-console */
      .catch(error => console.log(error));
  }

  disableDetails = () => {
    this.setState({
      teacherDetailsActive: false,
      teacherToShow: null
    })
  }

  render() {
    return (
        <div style={{display:'flex', width:"100%"}}>
            <div style={{flexGrow:0.3}}>
              <h2 style={{fontWeight: "bold", color:"#1F305E", fontStyle: "italic", textShadow: "2px 2px #DCDCDC", marginTop:"2%", marginBottom:"2%"}}>Filter</h2>
              <div style={{width:"75%"}}>
              <ReactSearchBox placeholder="Search a teacher" value={this.state.searchedValue} onChange={value => this.handleSearch(value)} />
              </div>
            </div>
            <div style={{flexGrow:0.3}}> 
                <h2 style={{fontWeight: "bold", color:"#1F305E", fontStyle: "italic", textShadow: "2px 2px #DCDCDC", marginTop:"2%", marginBottom:"1%"}}>Teachers</h2>
                <div>
                {this.state.filteredTeachers !== [] ? this.state.filteredTeachers.map(t => (
                          <TeacherCard key={t.idTeacher} teacher={t} showTeacherDetails={this.showTeacherDetails}></TeacherCard>
                    )) : this.state.teachers.map(t => (
                          <TeacherCard key={t.idTeacher} teacher={t} showTeacherDetails={this.showTeacherDetails}></TeacherCard>
                  ))} 
                </div>
            </div>
            <div style={{flexGrow:0.3, display:"flex", flexDirection:"column", position:"relative"}}> 
                <h2 style={{fontWeight: "bold", color:"#1F305E", fontStyle: "italic", textShadow: "2px 2px #DCDCDC", marginTop:"2%", marginBottom:"1%"}}>Details</h2>
                {this.state.teacherDetailsActive ? 
                  <div>
                    <h4 style={{fontWeight: "bold", color:"#1F305E", fontStyle: "italic", textShadow: "2px 2px #DCDCDC", marginTop:"2%", marginBottom:"1%"}}> Some details about {this.state.teacherToShow.firstName+ " " + this.state.teacherToShow.lastName}</h4>
                    <TextField variant="outlined"
                        style={{ width: "60%"}}
                        value={this.state.teacherToShow.email}
                        label="E-mail address"
                        margin="normal"
                        read-only="true" />

                    <TextField variant="outlined"
                        style={{ width: "60%"}}
                        value={this.state.teacherToShow.rank}
                        label="Teaching rank"
                        margin="normal"
                        read-only="true" />

                    <TextField variant="outlined"
                        style={{ width: "60%"}}
                        value={this.state.subjectsOfInterest}
                        label="Domains of interest"
                        margin="normal"
                        read-only="true" />
                  <div style={{marginLeft:"1%"}} onClick={this.disableDetails}>
                  <FontAwesomeIcon icon={faLongArrowAltRight} ></FontAwesomeIcon>
                  </div>
                  </div>
                  : null}
            </div>
        </div>
    );
  }
}