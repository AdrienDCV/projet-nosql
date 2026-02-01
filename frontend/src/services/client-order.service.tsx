import type {
  CreateClientOrderRequest,
  CreateClientOrderResponse
} from "../models/client-order.model.tsx";
import axios from "axios";
import type {ClientOrderDetails} from "../models/client-order-details.model.tsx";

export const createClientOrder = async (createClientOrderRequest: CreateClientOrderRequest): Promise<CreateClientOrderResponse> => {
  const response = await axios.post(
    "http://localhost:8081/client-api/client-orders",
      {
        clientId: createClientOrderRequest.clientId,
        orderItems: createClientOrderRequest.orderItems,
        deliveryAddress: createClientOrderRequest.deliveryAddress,
        email: createClientOrderRequest.email,
        phone: createClientOrderRequest.phone,
      }
  );

  return response.data;
}

export const retrieveClientOrderDetails = async (clientOrderId: string): Promise<ClientOrderDetails> => {
  const response = await axios.get(
      `http://localhost:8081/client-api/client-orders/details/${clientOrderId}`
  )

  return response.data
}