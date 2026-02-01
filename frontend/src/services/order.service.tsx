import type {OrderHistory} from "../models/order-history.model.tsx";
import axios from "axios";

export const retrieveClientOrderHistory = async (): Promise<OrderHistory> => {
  const response = await axios.get("http://localhost:8081/client-api/client-orders/history");

  return response.data;
}

export const retrieveProducerOrderHistory = async (): Promise<OrderHistory> => {
  const response = await axios.get("http://localhost:8081/client-api/client-orders/history");

  return response.data;
}