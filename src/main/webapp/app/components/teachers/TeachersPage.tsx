import React from 'react';
import TeacherCard from '../teachers/TeacherCard';

export const TeachersPage = () => {
  return (
    <div style={{ textAlign: 'center' }}>
      <h1>Teachers</h1>
      <TeacherCard />
      <TeacherCard />
      <TeacherCard />
      <TeacherCard />
      <TeacherCard />
    </div>
  );
};
