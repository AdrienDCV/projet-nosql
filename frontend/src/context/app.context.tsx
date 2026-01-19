import {createContext, } from "react";
import {useAuthentication} from "../hooks/authentication-context.hook.tsx";
import type {CreateBusinessRequest} from "../models/create-business-request.model.tsx";
import {createBusiness} from "../services/business.service.tsx";
import {useNavigate} from "react-router";

export interface AppContextType {
  createNewBusiness: (createBusinessRequest: CreateBusinessRequest) => void;
}

interface AppContextProviderProps {
  children: React.JSX.Element;
}

export const AppContext = createContext<AppContextType | undefined>(undefined)

export const AppContextProvider = ({ children }: AppContextProviderProps): React.JSX.Element => {
  const { isAuthenticated, setIsBusinessCreated } = useAuthentication();
  const navigate = useNavigate();

  // retrieve data
  /*
  useEffect(() => {
    if (isAuthenticated) {
      // void refreshAppointmentSettingsList(); example
    }
  }, [isAuthenticated]);
  */

  async function createNewBusiness(createNewBusinessRequest: CreateBusinessRequest) {
    try {
      if (isAuthenticated) {
        await createBusiness(createNewBusinessRequest);

        setIsBusinessCreated(true);

        navigate('/home');
      }
    } catch (error) {
      console.error(error)
    }
  }


  const value: AppContextType = {
    createNewBusiness,
  };

  return (
      <AppContext.Provider value={value}>
        { children }
      </AppContext.Provider>
  );
}