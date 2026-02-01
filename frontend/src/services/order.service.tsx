import type {clientOrderHistory} from "../models/order-history.model.tsx";
import axios from "axios";

export const retrieveClientOrderHistory = async (): Promise<clientOrderHistory> => {
  const response = await axios.get("http://localhost:8081/client-api/client-orders/history");

  return response.data;
}

export const retrieveProducerOrderHistory = async (): Promise<clientOrderHistory> => {
  const response = await axios.get("http://localhost:8080/producer-api/producer-orders/history");

  return response.data;
}