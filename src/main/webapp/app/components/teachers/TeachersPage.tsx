import React from 'react';
import { TeacherCard } from '../teachers/TeacherCard';

export const TeachersPage = () => {
  return (
    <div style={{ textAlign: 'center', display: 'flex', flexDirection: 'row', justifyContent: 'center' }}>
      <div style={{ flexGrow: 0.2 }}>
        <h1>Filters</h1>
      </div>
      <div style={{ flexGrow: 2 }}>
        <h1>Teachers</h1>
        <TeacherCard />
        <TeacherCard />
        <TeacherCard />
        <TeacherCard />
        <TeacherCard />
      </div>
      <div style={{ flexGrow: 0.5 }}>
        <h1>Details</h1>
      </div>
    </div>
  );
};
