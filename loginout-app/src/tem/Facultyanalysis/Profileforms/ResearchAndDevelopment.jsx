import React, { useState } from 'react';
import './ResearchAndDevelopment.css';
import Collapsible from './Collapsible';

const Patents = () => {
  const [patents, setPatents] = useState([{ title: '', agency: '', applicationNo: '', grantDate: '', status: '', score: '' }]);

  const addRow = () => {
    setPatents([...patents, { title: '', agency: '', applicationNo: '', grantDate: '', status: '', score: '' }]);
  };

  const removeRow = (index) => {
    const updatedPatents = patents.filter((_, i) => i !== index);
    setPatents(updatedPatents);
  };

  const handleChange = (index, event) => {
    const { name, value } = event.target;
    const updatedPatents = patents.map((pat, i) => i === index ? { ...pat, [name]: value } : pat);
    setPatents(updatedPatents);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log('Submitted Patents:', patents);
  };

  return (
    <div className='container-patents'>
      <form onSubmit={handleSubmit}>
        <h3>Details of Patents Published/Granted</h3>
        <table>
          <thead>
            <tr>
              <th>Title of Patent</th>
              <th>Agency</th>
              <th>Patent/Application No.</th>
              <th>Grant Date</th>
              <th>Status (Published/Granted)</th>
              <th>Score (Max. 10)</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {patents.map((pat, index) => (
              <tr key={index}>
                <td><input name="title" value={pat.title} onChange={(e) => handleChange(index, e)} placeholder="Enter Title" /></td>
                <td><input name="agency" value={pat.agency} onChange={(e) => handleChange(index, e)} placeholder="Enter Agency" /></td>
                <td><input name="applicationNo" value={pat.applicationNo} onChange={(e) => handleChange(index, e)} placeholder="Enter Application No." /></td>
                <td><input name="grantDate" value={pat.grantDate} onChange={(e) => handleChange(index, e)} placeholder="Enter Grant Date" /></td>
                <td><input name="status" value={pat.status} onChange={(e) => handleChange(index, e)} placeholder="Enter Status" /></td>
                <td><input name="score" value={pat.score} onChange={(e) => handleChange(index, e)} placeholder="Enter Score" /></td>
                <td>
                  <button type="button" className="remove-btn" onClick={() => removeRow(index)}>Remove</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        <div className="actions-container1">
          <button type="button" onClick={addRow}>Add Row</button>
          <button type="submit">Submit</button>
        </div>
      </form>
    </div>
  );
};

const Certifications = () => {
  const [certifications, setCertifications] = useState([{ name: '', organization: '', score: '' }]);

  const addRow = () => {
    setCertifications([...certifications, { name: '', organization: '', score: '' }]);
  };

  const removeRow = (index) => {
    const updatedCertifications = certifications.filter((_, i) => i !== index);
    setCertifications(updatedCertifications);
  };

  const handleChange = (index, event) => {
    const { name, value } = event.target;
    const updatedCertifications = certifications.map((cert, i) => i === index ? { ...cert, [name]: value } : cert);
    setCertifications(updatedCertifications);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log('Submitted Certifications:', certifications);
  };

  return (
    <div className='container-certifications'>
      <form onSubmit={handleSubmit}>
        <h3>Certifications from Reputed Professional Bodies/NPTEL/SWAYAM/Industry/Other Notable Certification Agencies</h3>
        <table>
          <thead>
            <tr>
              <th>Name of Certification</th>
              <th>Organization</th>
              <th>Score (Max. 5)</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {certifications.map((cert, index) => (
              <tr key={index}>
                <td><input name="name" value={cert.name} onChange={(e) => handleChange(index, e)} placeholder="Enter Certification Name" /></td>
                <td><input name="organization" value={cert.organization} onChange={(e) => handleChange(index, e)} placeholder="Enter Organization" /></td>
                <td><input name="score" value={cert.score} onChange={(e) => handleChange(index, e)} placeholder="Enter Score" /></td>
                <td>
                  <button type="button" className="remove-btn" onClick={() => removeRow(index)}>Remove</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        <div className="actions-container1">
          <button type="button" onClick={addRow}>Add Row</button>
          <button type="submit">Submit</button>
        </div>
      </form>
    </div>
  );
};

const FundedProjects = () => {
  const [projects, setProjects] = useState([{ title: '', agency: '', grant: '', status: '', score: '' }]);

  const addRow = () => {
    setProjects([...projects, { title: '', agency: '', grant: '', status: '', score: '' }]);
  };

  const removeRow = (index) => {
    const updatedProjects = projects.filter((_, i) => i !== index);
    setProjects(updatedProjects);
  };

  const handleChange = (index, event) => {
    const { name, value } = event.target;
    const updatedProjects = projects.map((proj, i) => i === index ? { ...proj, [name]: value } : proj);
    setProjects(updatedProjects);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log('Submitted Projects:', projects);
  };

  return (
    <div className='container-projects'>
      <form onSubmit={handleSubmit}>
        <h3>Sanction of Funded Projects and Projects Applied</h3>
        <table>
          <thead>
            <tr>
              <th>Title of the Funding Proposal</th>
              <th>Funding Agency</th>
              <th>Grant (in Rs.)</th>
              <th>Status (Sanctioned/Submitted)</th>
              <th>Score (Max. 5)</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {projects.map((proj, index) => (
              <tr key={index}>
                <td><input name="title" value={proj.title} onChange={(e) => handleChange(index, e)} placeholder="Enter Title" /></td>
                <td><input name="agency" value={proj.agency} onChange={(e) => handleChange(index, e)} placeholder="Enter Agency" /></td>
                <td><input name="grant" value={proj.grant} onChange={(e) => handleChange(index, e)} placeholder="Enter Grant Amount" /></td>
                <td><input name="status" value={proj.status} onChange={(e) => handleChange(index, e)} placeholder="Enter Status" /></td>
                <td><input name="score" value={proj.score} onChange={(e) => handleChange(index, e)} placeholder="Enter Score" /></td>
                <td>
                  <button type="button" className="remove-btn" onClick={() => removeRow(index)}>Remove</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        <div className="actions-container1">
          <button type="button" onClick={addRow}>Add Row</button>
          <button type="submit">Submit</button>
        </div>
      </form>
    </div>
  );
};

const ConferencePresentations = () => {
  const [presentations, setPresentations] = useState([{ title: '', theme: '', organizedBy: '', indexed: '', days: '', score: '' }]);

  const addRow = () => {
    setPresentations([...presentations, { title: '', theme: '', organizedBy: '', indexed: '', days: '', score: '' }]);
  };

  const removeRow = (index) => {
    const updatedPresentations = presentations.filter((_, i) => i !== index);
    setPresentations(updatedPresentations);
  };

  const handleChange = (index, event) => {
    const { name, value } = event.target;
    const updatedPresentations = presentations.map((pres, i) => i === index ? { ...pres, [name]: value } : pres);
    setPresentations(updatedPresentations);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log('Submitted Presentations:', presentations);
  };

  return (
    <div className='container-projects'>
      <form onSubmit={handleSubmit}>
        <h3>Presentation in International Conference/Symposia OR attended FDP/STTP</h3>
        <table>
          <thead>
            <tr>
              <th>Title of the Paper</th>
              <th>Title/Theme of Conference/Symposia/FDP/STTP</th>
              <th>Organized By</th>
              <th>Indexed in?</th>
              <th>No. of Days</th>
              <th>Score (Max. 5)</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {presentations.map((pres, index) => (
              <tr key={index}>
                <td><input name="title" value={pres.title} onChange={(e) => handleChange(index, e)} placeholder="Enter Title" /></td>
                <td><input name="theme" value={pres.theme} onChange={(e) => handleChange(index, e)} placeholder="Enter Theme" /></td>
                <td><input name="organizedBy" value={pres.organizedBy} onChange={(e) => handleChange(index, e)} placeholder="Enter Organizer" /></td>
                <td><input name="indexed" value={pres.indexed} onChange={(e) => handleChange(index, e)} placeholder="Indexed in?" /></td>
                <td><input name="days" value={pres.days} onChange={(e) => handleChange(index, e)} placeholder="Enter Number of Days" /></td>
                <td><input name="score" value={pres.score} onChange={(e) => handleChange(index, e)} placeholder="Enter Score" /></td>
                <td>
                  <button type="button" className="remove-btn" onClick={() => removeRow(index)}>Remove</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        <div className="actions-container1">
          <button type="button" onClick={addRow}>Add Row</button>
          <button type="submit">Submit</button>
        </div>
      </form>
    </div>
  );
};

const ResearchPublications = () => {
  const [publications, setPublications] = useState([{ title: '', journal: '', indexed: '', date: '', author: '', score: '' }]);

  const addRow = () => {
    setPublications([...publications, { title: '', journal: '', indexed: '', date: '', author: '', score: '' }]);
  };

  const removeRow = (index) => {
    const updatedPublications = publications.filter((_, i) => i !== index);
    setPublications(updatedPublications);
  };

  const handleChange = (index, event) => {
    const { name, value } = event.target;
    const updatedPublications = publications.map((pub, i) => i === index ? { ...pub, [name]: value } : pub);
    setPublications(updatedPublications);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log('Submitted Publications:', publications);
  };

  return (
    <div className='container-projects'>
      <form onSubmit={handleSubmit}>
        <h3>Details of Research Publications</h3>
        <table>
          <thead>
            <tr>
              <th>Article Title</th>
              <th>Journal Name with ISSN/ISBN No.</th>
              <th>Indexed in?</th>
              <th>Date of Publication</th>
              <th>1st or Corresponding Author?</th>
              <th>API Score (Max.15)</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {publications.map((pub, index) => (
              <tr key={index}>
                <td><input name="title" value={pub.title} onChange={(e) => handleChange(index, e)} placeholder="Enter Article Title" /></td>
                <td><input name="journal" value={pub.journal} onChange={(e) => handleChange(index, e)} placeholder="Enter Journal Name" /></td>
                <td><input name="indexed" value={pub.indexed} onChange={(e) => handleChange(index, e)} placeholder="Indexed in?" /></td>
                <td><input name="date" value={pub.date} onChange={(e) => handleChange(index, e)} placeholder="Publication Date" /></td>
                <td><input name="author" value={pub.author} onChange={(e) => handleChange(index, e)} placeholder="1st or Corresponding Author?" /></td>
                <td><input name="score" value={pub.score} onChange={(e) => handleChange(index, e)} placeholder="API Score (Max. 15)" /></td>
                <td>
                  <button type="button" className="remove-btn" onClick={() => removeRow(index)}>Remove</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        <div className="actions-container1">
          <button type="button" onClick={addRow}>Add Row</button>
          <button type="submit">Submit</button>
        </div>
      </form>
    </div>
  );
};

const ResearchAndDevelopment = () => {
  return (
    <div className='Formcontainer'>
      <Collapsible title={'ResearchPublications '}>
      
      < ResearchPublications/>
      </Collapsible>
      <Collapsible title={'ConferencePresentations '}>
      
      < ConferencePresentations/>
      </Collapsible>
      <Collapsible title={'FundedProjects'}>
      
      < FundedProjects/>
      </Collapsible>
      <Collapsible title={'Certifications '}>
      
      < Certifications/>
      </Collapsible>
      <Collapsible title={'Patents '}>
      
      < Patents/>
      </Collapsible>
    </div>
  );
};

export default ResearchAndDevelopment;
