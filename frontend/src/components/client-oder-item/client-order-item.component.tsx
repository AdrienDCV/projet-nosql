import React from "react";
import type {Address} from "../../models/address.model.tsx";
import {OrderStatus} from "../../models/enum/order-status.enum.ts";
import type {ProducerOrder} from "../../models/producer-order.model.tsx";

interface IProps {
    clientOrderId: string;
    clientId : string;
    producerOrders : Map<string, ProducerOrder[]>;
    deliveryAddress : Address;
    email : string;
    phone : string;
    orderStatus : OrderStatus;
    orderDate : Date;
    totalPrice : number;
}

export const ClientOrderItem = ({clientOrderId, producerOrders, deliveryAddress, orderStatus, totalPrice}: IProps): React.JSX.Element => {
    return (
      <div className="w-full flex flex-row">
          <img className="w-[100px] h-[100px] rounded-md" src={image} alt="product-image"/>
          <div className="w-full flex flex-col">
              <span>{ label }</span>
              <span>{ quantity }</span>
              <span>{ totalPrice } ({unitPrice} x {quantity})</span>
          </div>
      </div>
    );
}