import React from 'react';
import './SummaryTable.css'; // Make sure to adjust the CSS file name as needed

const SummaryTable = () => {
  return (
    <div className="summary-table-container">
      <h3>Summary of API Scores (to be filled by the functional head)</h3>
      <table className="summary-table">
        <thead>
          <tr>
            <th>Criteria</th>
            <th>Maximum API Score</th>
            <th>API Score attained</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>Academic Work</td>
            <td>50</td>
            <td><input type="number" min="0" max="50" defaultValue="0" /></td>
          </tr>
          <tr>
            <td>Research work</td>
            <td>20</td>
            <td><input type="number" min="0" max="20" defaultValue="0" /></td>
          </tr>
          <tr>
            <td>Contribution to the University</td>
            <td>10</td>
            <td><input type="number" min="0" max="10" defaultValue="0" /></td>
          </tr>
          <tr>
            <td>Contribution to the Department</td>
            <td>10</td>
            <td><input type="number" min="0" max="10" defaultValue="0" /></td>
          </tr>
          <tr>
            <td>Contribution to Society</td>
            <td>5</td>
            <td><input type="number" min="0" max="5" defaultValue="0" /></td>
          </tr>
          <tr>
            <td>Assessment by functional head</td>
            <td>5</td>
            <td><input type="number" min="0" max="5" defaultValue="0" /></td>
          </tr>
          <tr>
            <td>Total</td>
            <td>100</td>
            <td></td>
            {/* show out put here */}
          </tr>
        </tbody>
      </table>
    </div>
  );
};

export default SummaryTable;
