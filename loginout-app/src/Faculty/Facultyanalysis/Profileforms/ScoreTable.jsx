import React from 'react';
import './ScoreTable.css'; // Make sure to adjust the CSS file name as needed

const ScoreTable = () => {
  return (
    <div className="score-table-container">
      <h3>Score Table</h3>
      <table className="score-table">
        <thead>
          <tr>
            <th>Items to be considered</th>
            <th>Maximum API Score</th>
            <th>API Score attained</th>
            
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>Impression about the teaching work</td>
            <td>5</td>
            <td><input type="number" min="0" max="5" defaultValue="0" /></td>
            
          </tr>
          <tr>
            <td>Examination duties</td>
            <td>4</td>
            <td><input type="number" min="0" max="4" defaultValue="0" /></td>
           
          </tr>
          <tr>
            <td>Interpersonal Relationships and teamwork, Professional ethics, values and commitment</td>
            <td>6</td>
            <td><input type="number" min="0" max="6" defaultValue="0" /></td>
        
          </tr>
          <tr>
          <td>Total Score (Max. 15)</td>
          <td></td>
          {/* show the total score */}
          <td></td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};

export default ScoreTable;
