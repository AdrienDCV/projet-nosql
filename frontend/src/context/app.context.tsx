import {createContext, } from "react";
import {useAuthentication} from "../hooks/authentication-context.hook.tsx";

export interface AppContextType {
}

interface AppContextProviderProps {
  children: React.JSX.Element;
}

export const AppContext = createContext<AppContextType | undefined>(undefined)

export const AppContextProvider = ({ children }: AppContextProviderProps): React.JSX.Element => {
  const { isAuthenticated } = useAuthentication();

  // retrieve data
  /*
  useEffect(() => {
    if (isAuthenticated) {
      // void refreshAppointmentSettingsList(); example
    }
  }, [isAuthenticated]);
  */


  const value: AppContextType = {
  };

  return (
      <AppContext.Provider value={value}>
        { children }
      </AppContext.Provider>
  );
}