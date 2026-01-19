import type {UserType} from "./enum/user-type.enum.ts";

export type Role = 'USER' | 'ADMIN';

export type User = {
    username: string,
    firstname: string,
    lastname: string,
    phone: string,
    email: string,
    role: Role,
    userType: UserType,
};

export type Client = User & {
  clientId: string,
}

export type Producer = User & {
  producerId: string,
  businessCreated: boolean
}

export type UserSignUpRequest = {
  username: string,
  password: string,
  firstname: string,
  lastname: string,
  email: string,
  phone: string,
}

export type AuthResponse = {
  jwt: string,
  message: string,
  status: boolean,
}

export type ClientAuthResponse = AuthResponse & {
  client: User,
}

export type ProducerAuthResponse = AuthResponse & {
  producer: User,
}