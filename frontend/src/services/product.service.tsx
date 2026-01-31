import axios from "axios";
import type { Product } from "../models/product.model.tsx";
import type {Paginated} from "../models/paginated.model.tsx";
import type {CreateProductRequest} from "../models/create-product-request.model.tsx";
import type {CreateProductResponse} from "../models/create-product-response.model.tsx";

export const retrieveAllProductsClient = async (): Promise<Paginated<Product>> => {
  const response = await axios.get(
      "http://localhost:8081/client-api/products"
  )

  return response.data;
}

export const retrieveAllProductsProducer = async (): Promise<Paginated<Product>> => {
  const response = await axios.get(
      "http://localhost:8080/producer-api/products"
  )

  return response.data;
}

export const retrieveProductDetails = async (productId: string): Promise<Product> => {
  const response = await axios.get(
      `http://localhost:8080/producer-api/products/${productId}`
  );

  return response.data;
}

export const createProduct = async (
    createProductRequest: CreateProductRequest,
): Promise<CreateProductResponse> => {
  const response = await axios.post(`http://localhost:8080/producer-api/products`, {
    businessId: createProductRequest.businessId,
    label: createProductRequest.label,
    image: createProductRequest.image,
    uniteMesure: createProductRequest.measurementUnit,
    stock: createProductRequest.stock,
    price: createProductRequest.price,
    businessName: createProductRequest.businessName
  });

    return response.data;
}