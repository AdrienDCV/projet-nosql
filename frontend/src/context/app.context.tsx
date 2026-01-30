import {createContext, useState,} from "react";
import {useAuthentication} from "../hooks/authentication-context.hook.tsx";
import type {CreateBusinessRequest} from "../models/create-business-request.model.tsx";
import {createBusiness} from "../services/business.service.tsx";
import {useNavigate} from "react-router";
import type {Product} from "../models/product.model.tsx";
import {retrieveProductDetails} from "../services/product.service.tsx";

export interface AppContextType {
  createNewBusiness: (createBusinessRequest: CreateBusinessRequest) => void;
  getProductDetails: (productId: string) => void;
  currentProductDetails: Product | undefined;
  setCurrentProductDetails: (productDetails: Product) => void;
}

interface AppContextProviderProps {
  children: React.JSX.Element;
}

export const AppContext = createContext<AppContextType | undefined>(undefined)

const AppContextProvider = ({ children }: AppContextProviderProps): React.JSX.Element => {
  const [currentProductDetails, setCurrentProductDetails] = useState<Product | undefined>(undefined);
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

  async function getProductDetails(productId: string){
    try {
      if (isAuthenticated) {
        const currentProductDetails: Product = await retrieveProductDetails(productId)

        setCurrentProductDetails(currentProductDetails);
      }
    } catch (error) {
      console.error(error)
    }
  }


  const value: AppContextType = {
    createNewBusiness,
    getProductDetails,
    currentProductDetails,
    setCurrentProductDetails
  };

  return (
      <AppContext.Provider value={value}>
        { children }
      </AppContext.Provider>
  );
}
export default AppContextProvider