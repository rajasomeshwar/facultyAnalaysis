import { apiClient } from "./clientApi";

export const executeJwtBasicAuth = async (email, password) => {
  return await apiClient.post(`/auth/login`, { email, password });
};

export const createAccountApi = async (datax) => {
  //console.log(datax);
  return await apiClient.post(`/auth/register`, datax);
};
export const sendEmailVerification = async (email) => {
  return await apiClient.post(`/auth/resend`, { email });
};
export const ValidateVerficationCode = async (email, code) => {
  return await apiClient.post(`/auth/verify`, { email, code });
};
export const VerifyEmailByToken = async (token) => {
  return await apiClient.get(`/auth/verifyEmail?token=${token}`);
};
export const MailTokenForForgetPasswordApi = async (token) => {
  return await apiClient.get(`/auth/verifyEmail?token=${token}`);
};
export const SendToMailTokenForgetPasswordApi = async (email) => {
  return await apiClient.post(`/auth/forgetpassword/resend`, { email });
};

export const getFacultyInfoByTokenApi = async () => {
  return await apiClient.get(`/T/user/facultyInfo`);
};
export const updatePasswordByToken = async (token, password) => {
  return await apiClient.post(`/auth/forgetpassword`, {
    token,
    password,
  });
};

export const submitFacultyInfoDataApi = async (data1) => {
  //console.log(data1 + " x ");
  return await apiClient.post(`/T/user/facultyInfo`, data1);
};

// school form

export const AddContributionToSchoolByTokenApi = async (data) => {
  return await apiClient.post(`/SC/user/contributions/one`, data);
};
export const AddAllContributionToSchoolByTokenApi = async (data) => {
  return await apiClient.post(`/SC/user/contributions/bulk`, data);
};
export const RemoveContributionToSchoolByTokenApi = async (indx) => {
  return await apiClient.delete(`/SC/user/contributions/${indx}`);
};
export const getContributionToSchoolByTokenApi = async () => {
  //  const token = localStorage.getItem("jwtToken");
  //console.log("inside ther ", token);
  return await apiClient.get(`/SC/user/contributions`);
};

// school end

// start department

export const AddContributionToDepByTokenApi = async (data) => {
  return await apiClient.post(`/D/user/contributions/one`, data);
};
export const AddAllContributionToDepByTokenApi = async (data) => {
  return await apiClient.post(`/D/user/contributions/bulk`, data);
};
export const RemoveContributionToDepTokenApi = async (indx) => {
  return await apiClient.delete(`/D/user/contributions/${indx}`);
};
export const getContributionToDepByTokenApi = async () => {
  //  const token = localStorage.getItem("jwtToken");
  //console.log("inside ther ", token);
  return await apiClient.get(`/D/user/contributions`);
};
