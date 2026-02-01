import type {Address} from "./address.model.tsx";
import type {OrderStatus} from "./enum/order-status.enum.ts";

export type CreateClientOrderRequest = {
  clientId: string;
  orderItems: ClientOrderItem[];
  deliveryAddress: Address;
  email: string;
  phone: string;
}

export type ClientOrderItem = {
  productId: string;
  businessId: string;
  label: string;
  unitPrice: number;
  quantity: number;
}

export type CreateClientOrderResponse = {
  clientOrderId: string;
  clientId: string;
  orderItems: ClientOrderItem[];
  deliveryAddress: Address;
  email: string;
  phone: string;
  orderStatus: OrderStatus;
  orderDate: Date;
  totalPrice: number;
}