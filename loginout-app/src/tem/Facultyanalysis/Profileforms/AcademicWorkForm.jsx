import React, { useState } from 'react';
import './AcademicWorkForm.css'; // Import your CSS file
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const TeachingPerformanceTable = () => {
    const [rows, setRows] = useState([
        { id: 1, sno: '', semester: '', course: '', courseTaught: '', scheduledClasses: '', classesAttended: '', result: '', apiScore: '', studentFeedback: '', apiScoreFeedback: '' }
    ]);

    const handleAddRow = () => {
        const newRow = {
            id: rows.length + 1,
            sno: '',
            semester: '',
            course: '',
            courseTaught: '',
            scheduledClasses: '',
            classesAttended: '',
            result: '',
            apiScore: '',
            studentFeedback: '',
            apiScoreFeedback: ''
        };
        setRows([...rows, newRow]);
    };

    const handleRemoveLastRow = () => {
        if (rows.length === 1) {
            // Show toast notification if only one row is present
            toast.info("This is the last row. Remove action is canceled.");
        } else {
            const updatedRows = [...rows.slice(0, -1)]; // Remove the last row from the array
            setRows(updatedRows);
        }
    };

    const handleInputChange = (e, id) => {
        const { name, value } = e.target;
        const updatedRows = rows.map(row => (row.id === id ? { ...row, [name]: value } : row));
        setRows(updatedRows);
    };

    const handleSubmit = () => {
        // Handle submission logic here (e.g., sending data to server)
        console.log('Submitted data:', rows);
    };

    return (
        <div className="feedback-container">
            <h2>Teaching Performance Indicator for 2023</h2>
            <table className="feedback-table">
                <thead>
                    <tr>
                        <th>S.No</th>
                        <th>Semester</th>
                        <th>Course</th>
                        <th>Course Taught</th>
                        <th>Scheduled Classes</th>
                        <th>Classes Attended</th>
                        <th>Result (Pass %)</th>
                        <th>API Score - Results (Max. 20)</th>
                        <th>Student Feedback %</th>
                        <th>API Score - Feedback (Max. 20)</th>
                    </tr>
                </thead>
                <tbody>
                    {rows.map(row => (
                        <tr key={row.id}>
                            <td><input type="number" name="sno" value={row.sno} onChange={e => handleInputChange(e, row.id)} placeholder="1" /></td>
                            <td><input type="number" name="semester" value={row.semester} onChange={e => handleInputChange(e, row.id)} placeholder="Semester" /></td>
                            <td><input type="text" name="course" value={row.course} onChange={e => handleInputChange(e, row.id)} placeholder="Course" /></td>
                            <td><input type="text" name="courseTaught" value={row.courseTaught} onChange={e => handleInputChange(e, row.id)} placeholder="Course Taught" /></td>
                            <td><input type="number" name="scheduledClasses" value={row.scheduledClasses} onChange={e => handleInputChange(e, row.id)} placeholder="Scheduled Classes" /></td>
                            <td><input type="number" name="classesAttended" value={row.classesAttended} onChange={e => handleInputChange(e, row.id)} placeholder="Classes Attended" /></td>
                            <td><input type="number" name="result" value={row.result} onChange={e => handleInputChange(e, row.id)} placeholder="Result" /></td>
                            <td><input type="number" name="apiScore" value={row.apiScore} onChange={e => handleInputChange(e, row.id)} placeholder="API Score Results" min="0" max="20" /></td>
                            <td><input type="number" name="studentFeedback" value={row.studentFeedback} onChange={e => handleInputChange(e, row.id)} placeholder="Student Feedback" /></td>
                            <td><input type="number" name="apiScoreFeedback" value={row.apiScoreFeedback} onChange={e => handleInputChange(e, row.id)} placeholder="API Score Feedback" min="0" max="20" /></td>
                        </tr>
                    ))}
                    {/* Last row for Average percentage and Total API score
                    
                    <tr>
                        <td colSpan="2">Average percentage</td>
                        <td colSpan="8"></td>
                    </tr>
                    <tr>
                        <td colSpan="2">Total API score (Results + Feedback)</td>
                        <td colSpan="8"></td>
                    </tr> */}
                </tbody>
            </table>
            <div className="actions-container">
                <button className="feedback-table-button" onClick={handleAddRow}>Add Row</button>
                <button className="feedback-table-button" onClick={handleRemoveLastRow}>Remove Row</button>
                <button className="feedback-table-button" onClick={handleSubmit}>Submit</button>
            </div>
            <form onSubmit={handleSubmit}>

      <div className="extra-academics-container">
                <h3 className="extra-academics-heading">Do you wish to submit any other achievement or contribution?  </h3>
                <textarea className="extra-academics-textarea" name="extra-academics" placeholder="Enter" onInput={(e) => {
                  e.target.style.height = 'auto';
                  e.target.style.height = `${e.target.scrollHeight}px`;
                }}></textarea>
            </div>
            <button type="submit" style={{ marginTop: '50px' }} className='Action-btns'>Submit</button>

                </form>
            {/* Toast container for notifications */}
            <ToastContainer position="bottom-right" autoClose={3000} hideProgressBar={false} newestOnTop closeOnClick rtl={false} pauseOnFocusLoss draggable pauseOnHover />
        </div>
    );
}

export default TeachingPerformanceTable;
