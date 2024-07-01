import CoursesTable from './Courses/CourseTable';
import './App.css';

import React from 'react';

const App = () => {
    return (
        <div className="App">
            <p><b>UofT</b> Engineering <b>HSS/CS</b> Courses Selector</p>
            <CoursesTable />
        </div>
    );
};

export default App;
