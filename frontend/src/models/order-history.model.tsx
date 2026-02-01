import type {Address} from "./address.model.tsx";

export type ClientOrderHistory = {
  orderHistory: Map<string, ClientOrderHistoryRecord[]>;
}

export type ClientOrderHistoryRecord = {
  clientOrderId: string;
  deliveryAddress: Address;
  totalPrice: number;
  orderDate: Date;
}

export type ProducerOrderHistory = {
  orderHistory: Map<string, ProducerOrderHistoryRecord[]>;
}

export type ProducerOrderHistoryRecord = {
  producerOrderId: string;
  deliveryAddress: Address;
  totalPrice: number;
  orderDate: Date;
}