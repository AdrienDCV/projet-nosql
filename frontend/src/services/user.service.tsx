import axios from "axios";
import type {ClientAuthResponse, ProducerAuthResponse, UserSignUpRequest} from "../models/user.model.tsx";

export const signUpClient = async (client: UserSignUpRequest): Promise<ClientAuthResponse> => {
  const response = await axios.post(`http://localhost:8081/client-api/auth/sign-up`, {
    username: client.username,
    password: client.password,
    firstname: client.firstname,
    lastname: client.lastname,
    email: client.email,
    phone: client.phone,
  });

  return response.data;
}

export const signUpProducer = async (producer: UserSignUpRequest): Promise<ProducerAuthResponse> => {
  const response = await axios.post(`http://localhost:8080/producer-api/auth/sign-up`, {
    username: producer.username,
    password: producer.password,
    firstname: producer.firstname,
    lastname: producer.lastname,
    email: producer.email,
    phone: producer.phone,
  });

  return response.data;
}

export const signInProducer = async (username: string, password: string): Promise<ProducerAuthResponse> => {
  const response = await axios.post(`http://localhost:8080/producer-api/auth/sign-in`, {
    username: username,
    password: password,
  });
  return response.data;
}

export const signInClient = async (username: string, password: string): Promise<ClientAuthResponse> => {
  const response = await axios.post(`http://localhost:8081/client-api/auth/sign-in`, {
    username: username,
    password: password,
  });

  return response.data;
}


export const signOutClient = async (): Promise<void> => {
  await axios.post(`http://localhost:8081/client-api/auth/sign-out`);
};
export const signOutProducer = async (): Promise<void> => {
  await axios.post(`http://localhost:8080/producer-api/auth/sign-out`);
};

/*
export const getProfile = async  (): Promise<Patient | Doctor> => {
  const response = await axios.get(`http://localhost:8080/users/profile`);
  return response.data;
}
*/