
import React, { useState } from 'react';
import './ContributiontoSchoolForm.css';

const ContributiontoSociteyForm = () => {
  const [contributions, setContributions] = useState([{ responsibility: '', contribution: '', score: '' }]);

  const handleInputChange = (index, event) => {
    const { name, value } = event.target;
    const newContributions = [...contributions];
    newContributions[index][name] = value;
    setContributions(newContributions);
  };

  const handleAddRow = () => {
    setContributions([...contributions, { responsibility: '', contribution: '', score: '' }]);
  };

  const handleRemoveRow = (index) => {
    const newContributions = contributions.filter((_, i) => i !== index);
    setContributions(newContributions);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    // Add your submit logic here
    console.log(contributions);
  };

  return (
    <div className="contribution-form-container">
      <h3>Contribution to Society/Academic/Co-Curricular/Extra Curricular/Social Contribution/NSS/NCC</h3>
      <form onSubmit={handleSubmit}>
        <table className="table">
          <thead>
            <tr>
              <th>Responsibilities assigned</th>
              <th>Contribution(s)</th>
              <th>Score</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {contributions.map((contribution, index) => (
              <tr key={index}>
                <td>
                  <input
                    type="text"
                    name="responsibility"
                    value={contribution.responsibility}
                    placeholder='Enter Responsibilities assigned '
                    onChange={(event) => handleInputChange(index, event)}
                  />
                </td>
                <td>
                  <input
                    type="text"
                    name="contribution"
                     placeholder="Enter Contribution's"
                    value={contribution.contribution}
                    onChange={(event) => handleInputChange(index, event)}
                  />
                </td>
                <td>
                  <input
                    type="number"
                    name="score"
                    value={contribution.score}
                    onChange={(event) => handleInputChange(index, event)}
                  />
                </td>
                <td>
                  <button type="button" className='Action-btns' onClick={() => handleRemoveRow(index)}>Remove</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        <div className="actions-container-contribution">
          <button type="button" className='Action-btns' onClick={handleAddRow}>Add</button>
          <button type="submit" className='Action-btns'>Submit</button>
        </div>

      </form>
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
    </div>
  );
};

export default ContributiontoSociteyForm;
