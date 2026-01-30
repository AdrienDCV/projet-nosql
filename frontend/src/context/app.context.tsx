import {createContext, useEffect, useState,} from "react";
import {useAuthentication} from "../hooks/authentication-context.hook.tsx";
import type {CreateBusinessRequest} from "../models/create-business-request.model.tsx";
import {createBusiness} from "../services/business.service.tsx";
import {createProduct} from "../services/product.service.tsx";
import {useNavigate} from "react-router";
import type {Product} from "../models/product.model.tsx";
import {retrieveAllProducts, retrieveProductDetails} from "../services/product.service.tsx";
import type {Paginated} from "../models/paginated.model.tsx";
import type {CreateProductRequest} from "../models/create-product-request.model.tsx";

export interface AppContextType {
  createNewBusiness: (createBusinessRequest: CreateBusinessRequest) => void;
  getProductDetails: (productId: string) => void;
  currentProductDetails: Product | undefined;
  setCurrentProductDetails: (productDetails: Product) => void;
  refreshProductList: () => void;
  productList: Paginated<Product> | undefined;
  createNewProduct: (createProductRequest: CreateProductRequest) => void;
}

interface AppContextProviderProps {
  children: React.JSX.Element;
}

export const AppContext = createContext<AppContextType | undefined>(undefined)

const AppContextProvider = ({ children }: AppContextProviderProps): React.JSX.Element => {
  const [currentProductDetails, setCurrentProductDetails] = useState<Product | undefined>(undefined);
  const [productList, setProductList] = useState<Paginated<Product> | undefined>(undefined);
  const { isAuthenticated, setIsBusinessCreated } = useAuthentication();
  const navigate = useNavigate();

  useEffect(() => {
    if (isAuthenticated) {
      void refreshProductList();
    }
  }, [isAuthenticated]);

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

  async function refreshProductList() {
    try {
      if (isAuthenticated) {
        const data = await retrieveAllProducts();
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
      }
    } catch (error) {
      console.error(error)
    }
  }

  async function getProductDetails(productId: string) {
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
    setCurrentProductDetails,
    productList,
    refreshProductList,
    createNewProduct,
  };

  return (
      <AppContext.Provider value={value}>
        { children }
      </AppContext.Provider>
  );
}
export default AppContextProvider