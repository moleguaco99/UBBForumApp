import React from 'react';
import ImgMediaCard from "app/components/teachers/TeacherCard";
import TeacherCard from "app/components/teachers/TeacherCard";

export const TeachersPage = () => {
  return (
    <div style={{textAlign:"center"}}>
      <h1>Teachers</h1>
      <TeacherCard/>
      <TeacherCard/>
      <TeacherCard/>
      <TeacherCard/>
      <TeacherCard/>
    </div>
  );
};
