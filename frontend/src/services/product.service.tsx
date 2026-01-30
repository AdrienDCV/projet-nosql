import axios from "axios";
import type { Product } from "../models/product.model.tsx";
import type {Paginated} from "../models/paginated.model.tsx";
import type {ClientAuthResponse} from "../models/user.model.tsx";
import type {CreateProductRequest} from "../models/create-product-request.model.tsx";

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

export const createProduct = async (createProductRequest: CreateProductRequest): Promise<ClientAuthResponse> => {
    const response = await axios.post(`http://localhost:8080/producer-api/products`, {
        lable: createProductRequest.label,
        image: createProductRequest.image,
        uniteMesure: createProductRequest.uniteMesure,
        stock: createProductRequest.stock,
        price: createProductRequest.price,
    });

    return response.data;
}