import type {ClientOrderItem} from "./client-order.model.tsx";
import type {OrderStatus} from "./enum/order-status.enum.ts";

export type ProducerOrder = {
    producerOrderId: string;
    businessId: string;
    clientOrderItems: ClientOrderItem[];
    orderStatus: OrderStatus;
    orderDate: Date;
    updatedAt: Date;
}