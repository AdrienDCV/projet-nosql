import type {ProducerOrder} from "./producer-order.model.tsx";
import type {Address} from "./address.model.tsx";
import {OrderStatus} from "./enum/order-status.enum.ts";

export type ClientOrderDetails = {
    clientOrderId: string;
    clientId : string;
    producerOrders : Map<string, ProducerOrder[]>;
    deliveryAddress : Address;
    email : string;
    phone : string;
    orderStatus : OrderStatus;
    orderDate : Date;
    totalPrice : number;
};