import { requestFactory } from "./requester";
const host =
  process.env.NODE_ENV === "development"
    ? "http://localhost:8000"
    : "http://localhost:8000";

const baseUrl = `${host}/users`;

export const authServiceFactory = (token) => {
  const request = requestFactory(token);

  return {
    login: (loginData) => request.post(`${baseUrl}/login`, loginData),
    oauthlogin: (loginData) =>
      request.post(`${baseUrl}/oauth/login`, loginData),
    register: (data) => request.post(`${baseUrl}/register`, data),
    verify: (data) => request.get(`${baseUrl}/registerConfirm?token=${data}`),
    onMembershipRequest: (data) => request.post(`${baseUrl}/membership`, data),
    changePassword: (data) => request.patch(`${baseUrl}/change-password`, data),
    forgottenPassword: (data) =>
      request.post(`${baseUrl}/forgotten-password`, data),
    forgottenPasswordNewPassword: (data) =>
      request.patch(`${baseUrl}/forgotten-password/new-password`, data),
    getAll: () => request.get(`${baseUrl}`),
    getAllRoles: () => request.get(`${baseUrl}/roles`),
    remove: (id) => request.remove(`${baseUrl}/${id}`),
    update: (id, data) => request.patch(`${baseUrl}/${id}`, data),
    find: (id) => request.get(`${baseUrl}/${id}`),
  };
};
