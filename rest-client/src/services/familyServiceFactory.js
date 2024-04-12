import { requestFactory } from "./requester";

const host =
  process.env.NODE_ENV === "development"
    ? "http://localhost:8000"
    : "http://localhost:8000";

const baseUrl = `${host}/family`;

export const familyServiceFactory = (token) => {
  const request = requestFactory(token);

  return {
    getAll: (token) => request.get(`${baseUrl}`, token),
    create: (data) => request.post(`${baseUrl}/add`, data),
    addMember: (data) => request.post(`${baseUrl}/member/add`, data),
    remove: (id) => request.remove(`${baseUrl}/${id}`),
    update: (id, data) => request.patch(`${baseUrl}/${id}`, data, token),
  };
};
