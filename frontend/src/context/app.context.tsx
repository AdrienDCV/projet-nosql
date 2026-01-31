import {createContext, useEffect, useState,} from "react";
import {useAuthentication} from "../hooks/authentication-context.hook.tsx";
import type {CreateBusinessRequest} from "../models/create-business-request.model.tsx";
import {createProduct} from "../services/product.service.tsx";
import {createBusiness, retrieveCurrentProducerBusiness} from "../services/business.service.tsx";
import {useNavigate} from "react-router";
import type {Product} from "../models/product.model.tsx";
import {
  retrieveAllProductsClient,
  retrieveAllProductsProducer,
  retrieveProductDetails
} from "../services/product.service.tsx";
import type {Paginated} from "../models/paginated.model.tsx";
import type {CreateProductRequest} from "../models/create-product-request.model.tsx";
import type {Business} from "../models/business.model.tsx";
import {UserType} from "../models/enum/user-type.enum.ts";

export interface AppContextType {
  createNewBusiness: (createBusinessRequest: CreateBusinessRequest) => void;
  getProductDetails: (productId: string) => void;
  currentProductDetails: Product | undefined;
  setCurrentProductDetails: (productDetails: Product) => void;
  refreshProductListClient: () => void;
  refreshProductListProducer: (searchTerm: string, pageNumber: number) => void;
  productList: Paginated<Product> | undefined;
  createNewProduct: (createProductRequest: CreateProductRequest) => void;
  currentProducerBusiness: Business | undefined;
  getCurrentProducerBusiness: () => void;
}

interface AppContextProviderProps {
  children: React.JSX.Element;
}

export const AppContext = createContext<AppContextType | undefined>(undefined)

const AppContextProvider = ({ children }: AppContextProviderProps): React.JSX.Element => {
  const [currentProductDetails, setCurrentProductDetails] = useState<Product | undefined>(undefined);
  const [productList, setProductList] = useState<Paginated<Product> | undefined>(undefined);
  const [currentProducerBusiness, setCurrentProducerBusiness] = useState<Business | undefined>();
  const { isAuthenticated, setIsBusinessCreated, user } = useAuthentication();
  const navigate = useNavigate();

  useEffect(() => {
    if (!isAuthenticated || !user) return;

    if (user.userType === UserType.PRODUCER) {
      void getCurrentProducerBusiness();
      void refreshProductListProducer("", 0);
    } else {
      void refreshProductListClient();
    }
  }, [isAuthenticated, user]);

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

  async function getCurrentProducerBusiness() {
    try {
      if (isAuthenticated) {
        const data = await retrieveCurrentProducerBusiness();
        setCurrentProducerBusiness(data);
      }
    } catch (error) {
      console.error(error);
    }
  }

  async function refreshProductListProducer(searchTerm: string, pageNumber: number) {
    try {
      if (isAuthenticated) {
        const data = await retrieveAllProductsProducer(searchTerm, pageNumber);
        setProductList(data);
      }
    } catch(error) {
      console.error(error)
    }
  }

  async function createNewProduct(createNewProductRequest: CreateProductRequest) {
    try {
      if (isAuthenticated) {
        await createProduct(createNewProductRequest);

        void refreshProductListProducer("", 0);
      }
    } catch (error) {
      console.error(error)
    }
  }

  async function refreshProductListClient() {
    try {
      if (isAuthenticated) {
        const data = await retrieveAllProductsClient();
        setProductList(data);
      }
    } catch(error) {
      console.error(error)
    }
  }


  async function getProductDetails(productId: string) {
    try {
      if (!isAuthenticated) return;

      const currentProductDetails: Product = await retrieveProductDetails(productId);
      setCurrentProductDetails(currentProductDetails);

    } catch (error) {
      console.error(error);
    }
  }

  const value: AppContextType = {
    createNewBusiness,
    getProductDetails,
    currentProductDetails,
    setCurrentProductDetails,
    productList,
    createNewProduct,
    refreshProductListClient,
    refreshProductListProducer,
    currentProducerBusiness,
    getCurrentProducerBusiness
  };

  return (
      <AppContext.Provider value={value}>
        { children }
      </AppContext.Provider>
  );
}
export default AppContextProvider