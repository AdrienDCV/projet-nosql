import {OrderStatus} from "./enum/order-status.enum.ts";

export type ClientOrderItem = {
    id: number;
    orderId : number;
    address: string;
    status: OrderStatus;
    price: number;
};