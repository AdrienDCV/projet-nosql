import {createContext, useEffect, useState} from "react";
import type {
  Client,
  ClientAuthResponse,
  Producer,
  ProducerAuthResponse,
  User,
  UserSignUpRequest
} from "../models/user.model.tsx";
import {useNavigate} from "react-router";
import {signInClient, signInProducer, signUpClient, signUpProducer} from "../services/user.service.tsx";
import {UserType} from "../models/enum/user-type.enum.ts";

export interface AuthenticationContextType {
  user: User | undefined;
  authToken: string | undefined,
  isAuthenticated: boolean;
  isInitializing: boolean;
  setIsAuthenticated: (isAuthenticated: boolean) => void;
  setUser: (user: User | undefined) => void;
  setAuthToken: (authToken: string | undefined) => void;
  registerClient: (userData: UserSignUpRequest) => void;
  registerProducer: (userData: UserSignUpRequest) => void;
  logClientIn: (
      username: string,
      password: string,
  ) => void;
  logProducerIn: (
      username: string,
      password: string,
  ) => void;
  isBusinessCreated: boolean;
  setIsBusinessCreated: (isBusinessCreated: boolean) => void;
}

export const AuthenticationContext = createContext<AuthenticationContextType>(undefined as unknown as AuthenticationContextType);

interface IAuthenticationContextProviderProps {
  children: React.JSX.Element;
}

export const AuthenticationContextProvider = (props: IAuthenticationContextProviderProps): React.JSX.Element => {
  const [user, setUser] = useState<User | undefined>(() => {
    const storedUser = localStorage.getItem('user');
    return storedUser ? JSON.parse(storedUser) as User: undefined;
  });
  const [authToken, setAuthToken] = useState<string>()
  const [isAuthenticated, setIsAuthenticated] = useState<boolean>(false);
  const [isBusinessCreated, setIsBusinessCreated] = useState<boolean>(false);
  const [isInitializing, setIsInitializing] = useState<boolean>(true);
  const navigate = useNavigate();

  useEffect(() => {
    const existingToken = localStorage.getItem('token');
    if (existingToken) {
      const storedUser = localStorage.getItem('user');
      if  (storedUser) {
        setUser(JSON.parse(storedUser) as User);
      } else {
        setUser(undefined);
      };

      setAuthToken(existingToken);
      setIsAuthenticated(true);
    }
  }, []);

  useEffect(() => {
    if (authToken) {
      setIsAuthenticated(true);
      localStorage.setItem('token', authToken);

      if (user) {
        localStorage.setItem('user', JSON.stringify(user));
      }

      navigate("/home");
    }
    setIsInitializing(false);
  }, [authToken]);

  async function logClientIn(username: string, password: string) {
    try {
      const authResponse: ClientAuthResponse = await signInClient(username, password);
      if (authResponse) {
        setAuthToken(authResponse.jwt);
        const user: User = {
          ...authResponse.client,
          userType: UserType.CLIENT
        }
        setUser(user);
      }
    } catch (error) {
      setIsAuthenticated(false);
      console.error(error);
    }
  }

  async function registerClient(userData: UserSignUpRequest) {
    try {
      const authResponse: ClientAuthResponse = await signUpClient(userData);
      if (authResponse) {
        setAuthToken(authResponse.jwt);
        const user: User = {
          ...authResponse.client,
          userType: UserType.CLIENT
        }
        setUser(user);
      }
    } catch (error) {
      setIsAuthenticated(false);
      console.error(error);
    }
  }

  async function registerProducer(userData: UserSignUpRequest) {
    try {
      const authResponse: ProducerAuthResponse = await signUpProducer(userData);
      if (authResponse) {
        setAuthToken(authResponse.jwt);
        const user: User = {
          ...authResponse.producer,
          userType: UserType.PRODUCER
        }
        setUser(user);
        setIsBusinessCreated((user as Producer)?.businessCreated);
      }
    } catch (error) {
      setIsAuthenticated(false);
      console.error(error);
    }
  }

  async function logProducerIn(username: string, password: string) {
    try {
      const authResponse: ProducerAuthResponse = await signInProducer(username, password);
      if (authResponse) {
        setAuthToken(authResponse.jwt);
        const user: User = {
          ...authResponse.producer,
          userType: UserType.PRODUCER
        }
        setUser(user);
        setIsBusinessCreated((user as Producer)?.businessCreated);
      }
    } catch (error) {
      setIsAuthenticated(false);
      console.error(error);
    }
  }

  const value: AuthenticationContextType = {
    user,
    authToken,
    isAuthenticated,
    isInitializing,
    registerClient,
    registerProducer,
    logClientIn,
    logProducerIn,
    setIsAuthenticated,
    setUser,
    setAuthToken,
    isBusinessCreated,
    setIsBusinessCreated,
  }

  return <AuthenticationContext.Provider value={value}>{props.children}</AuthenticationContext.Provider>;
}