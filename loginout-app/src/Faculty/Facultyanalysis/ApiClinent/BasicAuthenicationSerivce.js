import { apiClient } from "./clientApi";

export const executeJwtBasicAuth = (username, password) => {
  return apiClient.post(`/auth/login`, { username, password });
};

export const createAccountApi = (username, password) => {
  return apiClient.post(`/auth/register`, { username, password });
};
export const sendEmailVerification = (email) => {
  return apiClient.post(`/auth/resend`, { email });
};
export const ValidateVerficationCode = (email, code) => {
  return apiClient.post(`/auth/verify`, { email, code });
};
export const VerifyEmailByToken = (token) => {
  return apiClient.get(`/auth/verifyEmail?token=${token}`);
};
export const MailTokenForForgetPasswordApi = (token) => {
  return apiClient.get(`/auth/verifyEmail?token=${token}`);
};
export const SendToMailTokenForgetPasswordApi = async (email) => {
  return await apiClient.post(`/auth/forgetpassword/resend`, { email });
};

export const updatePasswordByToken = async (token, password) => {
  return await apiClient.post(`/auth/forgetpassword`, {
    token,
    password,
  });
};
