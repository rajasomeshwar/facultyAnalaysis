import "./FacultyInformationForm.css";
import React, { useState, useEffect } from "react";
import { useAuth } from "../security/AuthContext";
import { useNavigate } from "react-router-dom";
export default function FacultyInformationForm() {
  const navigate = useNavigate();
  const authContext = useAuth();
  const [facultyData, setFacultyData] = useState({
    name: "",
    department: "",
    teachingexperience: 0,
    industryexperience: 0,
    joining_date: "",
    designation: "",
    total_experience: 0,
  });
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const getData = async () => {
      try {
        const facultyDataOr = await authContext.getFacultyInfoByToken();
        if (facultyDataOr == false) {
          // token invalid toarst
          navigate(`/login`);
        }
        console.log("Fetched faculty data:", facultyDataOr); // Debugging
        setFacultyData(facultyDataOr);
      } catch (error) {
        console.error("Failed to fetch faculty data:", error);
      }
    };
    getData();
  }, [authContext]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFacultyData({
      ...facultyData,
      [name]: value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      const data = await authContext.UpdateFacultyInformationFormHandle(
        facultyData
      );
      if (data) {
        setFacultyData(data);
      } else {
        navigate(`/login`);
      }
    } catch (error) {
      console.error("Error updating data:", error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="form-group-container">
      <h2>Faculty Information Form</h2>
      <form onSubmit={handleSubmit}>
        <table>
          <thead>
            <tr>
              <th colSpan="2">Faculty Information</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>
                <div className="form-group">
                  <label htmlFor="name">Name of the Faculty:</label>
                  <input
                    type="text"
                    id="name"
                    name="name"
                    value={facultyData.name}
                    onChange={handleInputChange}
                    disabled={loading}
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="department">Department:</label>
                  <input
                    type="text"
                    id="department"
                    name="department"
                    value={facultyData.department}
                    onChange={handleInputChange}
                    disabled={loading}
                  />
                </div>
                <div className="form-group">
                  <div id="teaching_industry_experience">
                    <div id="teaching_experience">
                      <label htmlFor="teachingexperience">Teaching:</label>
                      <input
                        type="number"
                        name="teachingexperience"
                        value={facultyData.teachingexperience}
                        onChange={handleInputChange}
                        placeholder="Teaching"
                        disabled={loading}
                      />
                    </div>
                    <div id="industry_experience">
                      <label htmlFor="industryexperience">Industry:</label>
                      <input
                        type="number"
                        name="industryexperience"
                        value={facultyData.industryexperience}
                        onChange={handleInputChange}
                        placeholder="Industry"
                        disabled={loading}
                      />
                    </div>
                  </div>
                </div>
              </td>
              <td>
                <div className="form-group">
                  <label htmlFor="joining_date">Date of Joining:</label>
                  <input
                    type="date"
                    id="joining_date"
                    name="joining_date"
                    value={facultyData.joining_date}
                    onChange={handleInputChange}
                    disabled={loading}
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="designation">Designation:</label>
                  <input
                    type="text"
                    id="designation"
                    name="designation"
                    value={facultyData.designation}
                    onChange={handleInputChange}
                    disabled={loading}
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="total_experience">
                    Total experience (Years):
                  </label>
                  <input
                    type="number"
                    id="total_experience"
                    name="total_experience"
                    value={facultyData.total_experience}
                    onChange={handleInputChange}
                    disabled={loading}
                  />
                </div>
              </td>
            </tr>
          </tbody>
        </table>
        <div className="form-group">
          <input type="submit" value="Submit" disabled={loading} />
          {loading && <div>Loading...</div>}
        </div>
      </form>
    </div>
  );
}
