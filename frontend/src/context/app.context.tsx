import {createContext, useEffect, useState,} from "react";
import {useAuthentication} from "../hooks/authentication-context.hook.tsx";
import type {CreateBusinessRequest} from "../models/create-business-request.model.tsx";
import {
  createProduct,
  retrieveAllProductsClient,
  retrieveAllProductsProducer,
  retrieveCurrentProducerInventory,
  retrieveProductDetails,
  updateProduct
} from "../services/product.service.tsx";
import {createBusiness, retrieveCurrentProducerBusiness} from "../services/business.service.tsx";
import {useNavigate} from "react-router";
import type {Product} from "../models/product.model.tsx";
import type {Paginated} from "../models/paginated.model.tsx";
import type {CreateProductRequest} from "../models/create-product-request.model.tsx";
import type {Business} from "../models/business.model.tsx";
import {UserType} from "../models/enum/user-type.enum.ts";
import type {UpdateProductRequest} from "../models/update-product-resquest.model.tsx";
import type {UpdateBusinessRequest} from "../models/update-business-request.model.tsx";
import type {ClientCart} from "../models/client-cart.model.tsx";
import {retrieveCurrenClientCart} from "../services/cart.service.tsx";
import type {CreateClientCartEntry} from "../models/create-client-cart-entry.model.tsx";
import {createNewClientCartEntry, deleteClientCartEntry} from "../services/client-cart-entry.service.tsx";
import type {CreateClientOrderRequest} from "../models/client-order.model.tsx";
import {createClientOrder} from "../services/client-order.service.tsx";
import type {ClientOrderHistory, ProducerOrderHistory} from "../models/order-history.model.tsx";
import {retrieveClientOrderHistory, retrieveProducerOrderHistory} from "../services/order.service.tsx";

export interface AppContextType {
  createNewBusiness: (createBusinessRequest: CreateBusinessRequest) => void;
  updateBusiness: (updateBusinessRequest: UpdateBusinessRequest) => void;
  getProductDetails: (productId: string) => void;
  currentProductDetails: Product | undefined;
  setCurrentProductDetails: (productDetails: Product) => void;
  refreshProductListClient: () => void;
  refreshProductListProducer: (searchTerm: string, pageNumber: number) => void;
  productList: Paginated<Product> | undefined;
  createNewProduct: (createProductRequest: CreateProductRequest) => void;
  currentProducerBusiness: Business | undefined;
  getCurrentProducerBusiness: () => void;
  currentProducerInventory: Product[] | undefined;
  setCurrentProducerInventory: (currentProducerInventory: Product[]) => void;
  refreshCurrentProducerInventory: () => void;
  updateCurrentProduct: (updateProductRequest: UpdateProductRequest) => void;
  refreshCurrentClientCart: () => void;
  currentClientCart: ClientCart | undefined;
  addItemToClientCart: (createClientCartEntry: CreateClientCartEntry) => void;
  createNewClientOrder: (createClientOrderRequest: CreateClientOrderRequest) => void;
  refreshCurrentClientOrderHistory: () => void;
  currentClientOrderHistory: ClientOrderHistory | undefined;
  refreshCurrentProducerOrderHistory: () => void;
  currentProducerOrderHistory: ProducerOrderHistory | undefined;
  deleteCartEntry: (cartEntryId: string) => void;
}

interface AppContextProviderProps {
  children: React.JSX.Element;
}

export const AppContext = createContext<AppContextType | undefined>(undefined)

const AppContextProvider = ({ children }: AppContextProviderProps): React.JSX.Element => {
  const [currentProductDetails, setCurrentProductDetails] = useState<Product | undefined>(undefined);
  const [productList, setProductList] = useState<Paginated<Product> | undefined>(undefined);
  const [currentProducerInventory, setCurrentProducerInventory] = useState<Product[] | undefined>(undefined);
  const [currentProducerBusiness, setCurrentProducerBusiness] = useState<Business | undefined>();
  const [currentClientCart, setCurrentClientCart] = useState<ClientCart | undefined>(undefined);
  const [currentClientOrderHistory, setCurrentClientOrderHistory] = useState<ClientOrderHistory | undefined>(undefined);
  const [currentProducerOrderHistory, setCurrentProducerOrderHistory] = useState<ProducerOrderHistory | undefined>(undefined);
  const { isAuthenticated, setIsBusinessCreated, user } = useAuthentication();
  const navigate = useNavigate();

  useEffect(() => {
    if (!isAuthenticated || !user) return;

    if (user.userType === UserType.PRODUCER) {
      void getCurrentProducerBusiness();
    } else {
      void refreshProductListProducer("", 0);
      void refreshCurrentClientCart();
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

  async function updateBusiness(updateBusinessRequest: UpdateBusinessRequest) {
    try {
      if (isAuthenticated) {
        await updateBusiness(updateBusinessRequest);
        void getCurrentProducerBusiness();
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

  async function refreshCurrentProducerInventory() {
    try {
      if (isAuthenticated) {
        if (!currentProducerBusiness) return;

        const data = await retrieveCurrentProducerInventory(currentProducerBusiness?.businessId);
        setCurrentProducerInventory(data);
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

  async function updateCurrentProduct(updateProductRequest: UpdateProductRequest) {
    try {
      if (!isAuthenticated) return;

      await updateProduct(updateProductRequest);
      void getProductDetails(updateProductRequest.productId);
      void refreshCurrentProducerInventory();
    } catch (error) {
      console.error(error);
    }
  }
  
  async function refreshCurrentClientCart() {
    try {
      const data = await retrieveCurrenClientCart();
      setCurrentClientCart(data);
    } catch (error) {
      console.error(error);
    }
  }

  async function addItemToClientCart(createClientCartEntry: CreateClientCartEntry) {
    try {
      await createNewClientCartEntry(createClientCartEntry);
      void refreshCurrentClientCart();
    } catch (error) {
      console.error(error);
    }
  }

  async function createNewClientOrder(createClientOrderRequst: CreateClientOrderRequest) {
    try {
      await createClientOrder(createClientOrderRequst);
      void refreshCurrentClientCart();
    } catch(error) {
      console.error(error);
    }
  }

  async function refreshCurrentClientOrderHistory() {
    try {
      if (!isAuthenticated && !user) return;
      setCurrentClientOrderHistory(await retrieveClientOrderHistory());
    } catch (error) {
      console.error(error);
    }
  }

  async function refreshCurrentProducerOrderHistory() {
    try {
      if (!isAuthenticated && !user) return;
      setCurrentProducerOrderHistory(await retrieveProducerOrderHistory());
    } catch (error) {
      console.error(error);
    }
  }

  async function deleteCartEntry(cartEntryId: string) {
    try {
      if (!isAuthenticated) return;
      await deleteClientCartEntry(cartEntryId);
      void refreshCurrentClientCart();
    } catch (error) {
      console.error(error);
    }
  }

  const value: AppContextType = {
    createNewBusiness,
    updateBusiness,
    getProductDetails,
    currentProductDetails,
    setCurrentProductDetails,
    productList,
    createNewProduct,
    refreshProductListClient,
    refreshProductListProducer,
    currentProducerBusiness,
    getCurrentProducerBusiness,
    currentProducerInventory,
    setCurrentProducerInventory,
    refreshCurrentProducerInventory,
    updateCurrentProduct,
    refreshCurrentClientCart,
    currentClientCart,
    addItemToClientCart,
    createNewClientOrder,
    refreshCurrentClientOrderHistory,
    currentClientOrderHistory,
    refreshCurrentProducerOrderHistory,
    currentProducerOrderHistory,
    deleteCartEntry
  };

  return (
      <AppContext.Provider value={value}>
        { children }
      </AppContext.Provider>
  );
}
export default AppContextProvider