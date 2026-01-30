import axios from "axios";
import type { Product } from "../models/product.model.tsx";
import type {Paginated} from "../models/paginated.model.tsx";

export const retrieveAllProducts = async (): Promise<Paginated<Product>> => {
  const response = await axios.get(
      "http://localhost:8081/client-api/products"
  )

  return response.data;
}

export const retrieveProductDetails = async (productId: string): Promise<Product> => {
  const response = await axios.get(
      `http://localhost:8081/client-api/products/${productId}`      
  );

  return response.data;
}