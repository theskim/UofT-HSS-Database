import React, { useEffect, useState } from 'react';
import axios from 'axios';

const CoursesTable = () => {
    const [courses, setCourses] = useState([]);

    useEffect(() => {
        axios.get('/api/courses')
            .then(response => {
                setCourses(response.data);
            })
            .catch(error => {
                console.error("There was an error fetching the courses!", error);
            });
    }, []);

    return (
        <div className="table-responsive">
            <table>
                <thead>
                    <tr>
                        <th>Course Code</th>
                        <th className="title">Title</th>
                        <th>Typical Avg.</th>
                        <th>Why this Course?</th>
                        <th>Terms</th>
                    </tr>
                </thead>
                <tbody>
                    {courses.map((course) => (
                        <tr key={course.id}>
                            <td><a href={course.url}>{course.dept + course.code}</a></td>
                            <td className="title">{course.cname}</td>
                            <td>{course.cavg}</td>
                            <td>{course.description}</td>
                            <td>
                                {course.fall && (
                                    <div className="tooltip">
                                        <span>&#x1F342;</span>
                                        <span className="tooltiptext">Fall</span>
                                    </div>
                                )}
                                {course.winter && (
                                    <div className="tooltip">
                                        <span>&#x2744;&#xFE0F;</span>
                                        <span className="tooltiptext">Winter</span>
                                    </div>
                                )}
                                {course.summer && (
                                    <div className="tooltip">
                                        <span>&#x1F31E;</span>
                                        <span className="tooltiptext">Summer</span>
                                    </div>
                                )}
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default CoursesTable;
