import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Typography from '@material-ui/core/Typography';
import React from 'react';
import ReactSearchBox from 'react-search-box';
import '../teachers/cardStyle.css';
import { TeacherCard } from '../teachers/TeacherCard';

interface TeacherInterface {
  teachers: teacher[];
  filteredTeachers: teacher[];
  searchedValue: string;
  teacherDetailsActive: boolean;
  teacherToShow?: teacher;
}
type teacher = {
  teacherId: number;
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
      teacherDetailsActive: false
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
  };
  render() {
    return (
      <div style={{ textAlign: 'center', display: 'flex', flexDirection: 'row', justifyContent: 'center' }}>
        <div className="searchBox" style={{ flexGrow: 0.1 }}>
          <ReactSearchBox placeholder="Search a teacher" value={this.state.searchedValue} onChange={value => this.handleSearch(value)} />
        </div>
        <div style={{ flexGrow: 2, paddingLeft: '11%' }}>
          <h1>Teachers</h1>
          {this.state.filteredTeachers !== []
            ? this.state.filteredTeachers.map(t => (
                <TeacherCard key={t.teacherId} teacher={t} showTeacherDetails={this.showTeacherDetails}></TeacherCard>
              ))
            : this.state.teachers.map(t => (
                <TeacherCard key={t.teacherId} teacher={t} showTeacherDetails={this.showTeacherDetails}></TeacherCard>
              ))}
        </div>
        <div style={{ flexGrow: 0.4, display: 'flex', justifyContent: 'space-around', alignItems: 'flex-start' }}>
          {this.state.teacherDetailsActive ? (
            <div className="sticky">
              <Card>
                <CardContent>
                  <Typography variant="h5" component="h2">
                    Teacher Details
                  </Typography>
                  <Typography color="textSecondary" gutterBottom>
                    {this.state.teacherToShow.email}
                  </Typography>
                  <Typography color="textSecondary" gutterBottom>
                    {this.state.teacherToShow.rank}
                  </Typography>
                  <Typography color="textSecondary" gutterBottom>
                    Subjects
                  </Typography>
                </CardContent>
              </Card>
            </div>
          ) : (
            <div></div>
          )}
        </div>
      </div>
    );
  }
}
