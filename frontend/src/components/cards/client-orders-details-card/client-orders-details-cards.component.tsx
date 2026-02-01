import React from "react";
import type {Address} from "../../../models/address.model.tsx";
import type {ProducerOrder} from "../../../models/producer-order.model.tsx";
import {ProducerOrderList} from "../../producer-order-list.component.tsx/producer-order-list.component.tsx";

interface IProps {
  clientOrderId: string;
  deliveryAddress: Address;
  totalPrice: number;
  producerOrders: Map<string, ProducerOrder[]>;
  orderDate: Date;
}

export function ClientOrderDetailsCard({
                                         clientOrderId,
                                         deliveryAddress,
                                         totalPrice,
                                         producerOrders,
                                         orderDate,
                                       }: IProps): React.JSX.Element {

  const formatDeliveryAddress = (deliveryAddress: Address) => {
    return `${deliveryAddress.number} ${deliveryAddress.street}, ${deliveryAddress.postalCode} ${deliveryAddress.city} - ${deliveryAddress.country}`;
  }

  function formatterDateFr(orderDate: Date | string | number): string {
    const date = new Date(orderDate);

    if (isNaN(date.getTime())) {
      return "";
    }

    return date
        .toLocaleString("fr-FR", {
          day: "2-digit",
          month: "2-digit",
          year: "numeric",
          hour: "2-digit",
          minute: "2-digit",
          hour12: false,
        })
        .replace(",", "-")
        .replace(":", "h")
        .replace(' ', ' - ');
  }

  return (
      <div className="flex items-center bg-white rounded-2xl shadow-md p-6 border border-gray-100">
        <div className="flex flex-col justify-center flex-1 w-full">
          <span
              className="flex justify-center text-3xl rounded-2xl shadow-md p-4 bg-[#FCB177] mb-6 font-bold text-white">
            QUETTA-{clientOrderId}
          </span>
          <div className="w-full flex flex-row">
            <div className="flex flex-col justify-center flex-1">
              <span className="text-[#EB4511] font-bold text-xl">Date de la commande :</span>
              <span className="text-gray-500 text-sm">{formatterDateFr(orderDate)}</span>
            </div>
          </div>

          <div className="flex flex-col justify-center flex-1 mt-8">
            <span className="text-[#EB4511] font-bold text-xl">Adresse de livraison :</span>
            <span className="text-gray-500 text-sm">{formatDeliveryAddress(deliveryAddress)}</span>
          </div>

          {Object.entries(producerOrders).map(
              ([producerName, producerOrderList]: [string, ProducerOrder[]]) => (
                  <div key={producerName} className="w-full flex flex-col mb-10">

                    <div className="grid grid-cols-[220px_1fr] gap-6 items-start w-full mt-8">

                      <div className="text-lg font-bold text-[#EB4511]">
                        {producerName}
                      </div>

                      <div className="flex flex-col gap-6">
                        <ProducerOrderList producerOrderList={producerOrderList}/>
                      </div>
                    </div>
                  </div>
              )
          )}

          <div className="flex flex-col items-end min-w-[150px]">
          <span className="text-[10px] uppercase tracking-widest text-gray-400 mb-1 font-bold">
            Total de la commande :
          </span>
            <span className="font-bold text-xl rounded-2xl px-10 bg-[#FCB177] text-[#B02E0C] mt-1">
            {totalPrice} €
          </span>
          </div>
        </div>
      </div>
  );
}
