import React from "react";
import type {ProducerOrder} from "../../../models/producer-order.model";
import {OrderStatus} from "../../../models/enum/order-status.enum.ts";

interface IProps {
  producerOrder: ProducerOrder;
}

export function ProducerOrderCard({producerOrder}: IProps): React.JSX.Element {

  const getStatusStyles = (status: OrderStatus) => {
    switch (status) {
      case OrderStatus.DELIVERED:
        return "bg-green-100 text-green-700 border-green-200";
      case OrderStatus.SHIPPING:
        return "bg-orange-200 text-orange-700 border-orange-200";
      case OrderStatus.PREPARING:
        return "bg-orange-100 text-orange-400 border-orange-200";
      case OrderStatus.ORDERED:
        return "bg-red-300 text-red-500 border-red-200";
      default:
        return "bg-gray-100 text-gray-700 border-gray-200";
    }
  };

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
      <div className="w-full rounded-2xl bg-white p-6]">

        <div className="mb-5 flex items-center justify-between">
        <span className="text-sm text-gray-500">
          {formatterDateFr(new Date(producerOrder.orderDate))}
        </span>

          <span
              className={`px-3 py-1 rounded-full text-xs font-bold border ${getStatusStyles(producerOrder.orderStatus)}`}
          >
            {producerOrder.orderStatus}
          </span>
        </div>

        <div className="flex flex-col gap-3">
          {producerOrder.clientOrderItems.map((item, index) => (
              <div
                  key={index}
                  className="flex items-center justify-between rounded-xl bg-orange-50 px-4 py-3"
              >
            <span className="font-semibold text-orange-600">
              {item.label}
            </span>

                <span className="font-semibold text-gray-900">
              {(item.unitPrice * item.quantity).toFixed(2)} €
              <span className="ml-1 text-sm font-normal text-gray-500">
                ({item.unitPrice} € × {item.quantity})
              </span>
            </span>
              </div>
          ))}
        </div>
      </div>
  );
}
