import React, { useState } from "react";
import "./ContributiontoSchoolForm.css";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../security/AuthContext";
import { useEffect } from "react";
const ContributiontoDepartmentForm = () => {
  const navigate = useNavigate();
  const authContext = useAuth();
  const [contributions, setContributions] = useState([
    { indx: 0, responsibility: "", contribution: "", score: 0 },
  ]);
  const [nextIndex, setNextIndex] = useState(1);
  const [singledata, setsinglgedata] = useState({
    indx: 0,
    responsibility: "",
    contribution: "",
    score: 0,
  });
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const getData = async () => {
      try {
        const data = await authContext.getContributionToDepByToken();
        //console.log("ss " + data);

        if (data == false && data.length != 0) {
          // token inval  toarst
          navigate(`/login`);
        }
        setContributions(data);
        console.log("Fetched faculty data:", data); // Debugging

        //setFacultyData(data);
      } catch (error) {
        console.error("Failed to fetch faculty data:", error);
      }
    };
    getData();
  }, [nextIndex]);

  const handleInputChange = (index, event) => {
    const { name, value } = event.target;
    const newContributions = contributions.map((contribution) =>
      contribution.indx === index
        ? { ...contribution, [name]: value }
        : contribution
    );
    setContributions(newContributions);
  };

  const handleAddRow = async () => {
    console.log("ding ");

    const getData1 = async () => {
      try {
        const data = await authContext.AddContributionToDepByToken({
          responsibility: "",
          contribution: "",
          score: 0,
        });
        // console.log("ss " + data);

        if (data === "x") {
          // token inval  toarst

          navigate(`/login`);
        }

        console.log("Fetched faculty data:", JSON.stringify(data)); // Debugging
        setsinglgedata(data);
        //setFacultyData(data);
      } catch (error) {
        console.error("Failed to fetch faculty data:", error);
      }
    };
    await getData1();
    console.log(singledata);
    setContributions([...contributions, { singledata }]);
    setNextIndex(nextIndex + 1);
  };

  const handleRemoveRow = async (index) => {
    console.log("remove " + index);
    setNextIndex(nextIndex + 1);
    const removerow = async () => {
      if (index)
        try {
          // can be

          const data = await authContext.RemoveContributionToDepByToken(index);
          // console.log("ss " + data);

          if (data == "x") {
            // token inval  toarst

            navigate(`/login`);
          }

          console.log("Fetched faculty data:", JSON.stringify(data)); // Debugging

          setContributions(data);
          //setFacultyData(data);
        } catch (error) {
          console.error("Failed to fetch faculty data:", error);
        }
    };
    await removerow();
    setNextIndex(nextIndex + 1);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      // can be

      const data = await authContext.AddAllContributionToDepByToken(
        contributions
      );
      // console.log("ss " + data);

      if (data == "x") {
        // token inval  toarst

        navigate(`/login`);
      }

      console.log("Fetched faculty data:", JSON.stringify(data)); // Debugging
      setContributions(data);
      //setFacultyData(data);
    } catch (error) {
      console.error("Failed to fetch faculty data:", error);
    }
    // Add your submit logic here
    setNextIndex(nextIndex + 1);
    console.log(contributions);
  };

  return (
    <div className="contribution-form-container">
      <h3>Contribution to the Department</h3>
      <form onSubmit={handleSubmit}>
        <table className="table">
          <thead>
            <tr>
              <th>Responsibility / Activity</th>
              <th>Contribution(s)</th>
              <th>Score</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {contributions.map((contribution) => (
              <tr key={contribution.indx}>
                <td>
                  <input
                    type="text"
                    name="responsibility"
                    value={contribution.responsibility}
                    placeholder="Enter Responsibility / Activity"
                    onChange={(event) =>
                      handleInputChange(contribution.indx, event)
                    }
                  />
                </td>
                <td>
                  <input
                    type="text"
                    name="contribution"
                    placeholder="Enter Contribution's"
                    value={contribution.contribution}
                    onChange={(event) =>
                      handleInputChange(contribution.indx, event)
                    }
                  />
                </td>
                <td>
                  <input
                    type="number"
                    name="score"
                    value={contribution.score}
                    onChange={(event) =>
                      handleInputChange(contribution.indx, event)
                    }
                  />
                </td>
                <td>
                  <button
                    type="button"
                    className="Action-btns"
                    onClick={() => handleRemoveRow(contribution.indx)}
                  >
                    Remove
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        <div className="actions-container-contribution">
          <button type="button" className="Action-btns" onClick={handleAddRow}>
            Add
          </button>
          <button type="submit" className="Action-btns">
            Submit
          </button>
        </div>
      </form>
    </div>
  );
};

export default ContributiontoDepartmentForm;
