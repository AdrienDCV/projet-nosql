import axios from "axios";
import type { Product } from "../models/product.model.tsx";
import type {Paginated} from "../models/paginated.model.tsx";
import type {CreateProductRequest} from "../models/create-product-request.model.tsx";
import type {CreateProductResponse} from "../models/create-product-response.model.tsx";
import type {UpdateProductRequest} from "../models/update-product-resquest.model.tsx";
import type {UpdateProductResponse} from "../models/update-product-response.model.tsx";

export const retrieveAllProductsClient = async (): Promise<Paginated<Product>> => {
  const response = await axios.get(
      "http://localhost:8081/client-api/products"
  )

  return response.data;
}

export const retrieveAllProductsProducer = async (searchTerm: string, pageNumber: number): Promise<Paginated<Product>> => {
  const url = searchTerm.length > 0 ?
      `http://localhost:8080/producer-api/products?page=${pageNumber}&size=20&label=${searchTerm}` :
      `http://localhost:8080/producer-api/products?page=${pageNumber}&size=20`

  const response = await axios.get(url)

  return response.data;
}

export const retrieveCurrentProducerInventory = async(businessId: string): Promise<Product[]> => {
  const response = await axios.get(
      `http://localhost:8080/producer-api/products/${businessId}/inventory`
  );

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

export const updateProduct = async (
    updateProduct: UpdateProductRequest,
): Promise<UpdateProductResponse> => {
  const response = await axios.put(`http://localhost:8080/producer-api/products`, {
    productId: updateProduct.productId,
    businessId: updateProduct.businessId,
    label: updateProduct.label,
    description: updateProduct.description,
    image: updateProduct.image,
    stock: updateProduct.stock,
    price: updateProduct.price,
    stockStatus: updateProduct.stockStatus
  });

  return response.data;
}