import React from 'react';
import Collapsible from './Collapsible';
import AcademicWorkForm from './AcademicWorkForm';
import FacultyInformationForm from './FacultyInformationForm';
import ResearchAndDevelopment from './ResearchAndDevelopment';
import ContributionForm from './ContributiontoSchoolForm';
import ContributiontoDepartment from './ContributiontoDepartment';
import ContributiontoSocietyForm from './ContibutionstoSociety';
import ScoreTable from './ScoreTable';
import SummaryTable from './SummaryTable';
import './controller.css'
const Profile = () => {
  return (
    <div className='Profile-controller-container'>
      <h2>fill this details</h2>
      <Collapsible title="Faculty Information">
        <FacultyInformationForm />
      </Collapsible>
      <Collapsible title="Academic Work">
        <AcademicWorkForm />
      </Collapsible>
      <Collapsible title="Research and Development">
        <ResearchAndDevelopment />
      </Collapsible>
      <Collapsible title="Contribution to the School / University">
      <ContributionForm/>
      </Collapsible>
      <Collapsible title=" Contribution to the Department ">
      <ContributiontoDepartment/>
      </Collapsible>
      <Collapsible title=" Contribution to Society ">
      <ContributiontoSocietyForm/>
      </Collapsible>
      <Collapsible title=" Score Table ">
      <ScoreTable/>
      </Collapsible>
      <Collapsible title=" Summary Table ">
      <SummaryTable/>
      </Collapsible>
    </div>
  );
};

export default Profile;
