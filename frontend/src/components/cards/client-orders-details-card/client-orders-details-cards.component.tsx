import React from "react";
import {OrderStatus} from "../../../models/enum/order-status.enum.ts";
import type {ClientOrderDetails} from "../../../models/client-order-details.model.tsx";

interface IProps {
    clientOrderDetails: ClientOrderDetails;
}

const getStatusStyles = (status: OrderStatus) => {
    switch (status) {
        case OrderStatus.DELIVERED:
            return "bg-green-100 text-green-700 border-green-200";
        case OrderStatus.SHIPPING:
            return "bg-orange-200 text-orange-700 border-orange-200";
        case OrderStatus.PREPARING:
            return "bg-orange-100 text-orange-400 border-orange-200";
        case OrderStatus.ORDERED:
            return "bg-red-300 text-red-500 border-red-200" ;
        default:
            return "bg-gray-100 text-gray-700 border-gray-200";
    }
};

export function ClientOrderDetailsCard({ clientOrderDetails }: IProps): React.JSX.Element {
    return (
        <div className="flex items-center bg-white rounded-2xl shadow-md p-6 border border-gray-100">
            <div className="flex flex-col justify-center flex-1">
                {/* Numéro de la commande */}
                <span className="flex justify-center text-3xl rounded-2xl shadow-md p-4 bg-[#FCB177] mb-6 font-bold text-white">Commande : QUETTA-{orderId}</span>
                {/* Adresse de la commande */}
                <div className="flex flex-col justify-center flex-1">
                    <span className="text-[#EB4511] font-bold text-2xl">Adresse de livraison :</span>
                    <span className="text-gray-500 text-sm">{address}</span>
                </div>
                {/* Status de la commande */}
                <div className="flex flex-col pb-4 items-end min-w-[150px]">
                    <span className="text-[10px] uppercase tracking-widest text-gray-400 mb-1 font-bold">Suivi :</span>
                    <span className={`px-3 py-1 rounded-full text-xs font-bold border ${getStatusStyles(status)}`}>{status}</span>
                </div>

                {

                }

                {/* Prix de la commande */}
                <div className="flex flex-col items-end min-w-[150px]">
                    <span className="text-[10px] uppercase tracking-widest text-gray-400 mb-1 font-bold">
                        Total de la commande :
                    </span>
                    <span className="font-bold text-xl rounded-2xl px-10  bg-[#FCB177] text-[#B02E0C] mt-1">{price} €</span>
                </div>
            </div>
        </div>
    );
}