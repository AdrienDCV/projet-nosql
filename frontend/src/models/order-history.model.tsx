import type {Address} from "./address.model.tsx";

export type OrderHistory = {
  orderHistory: Map<string, OrderHistoryRecord[]>;
}

export type OrderHistoryRecord = {
  clientOrderId: string;
  deliveryAddress: Address;
  totalPrice: number;
  orderDate: Date;
}