import {OrderStatus} from "../../../models/enum/client-order-status.enum.ts";
import ReceiptLongIcon from '@mui/icons-material/ReceiptLong';
import React from "react";

interface IProps {
    id: number;
    orderId: number;
    address: string;
    status: OrderStatus;
    price: number;
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

export function ClientOrderCardComponent({ address, orderId, price, status }: IProps): React.JSX.Element {
    return (
        <div className="flex items-center bg-white rounded-2xl shadow-md p-6 border border-gray-100">
            <div className="p-3">
                <a href="/receipt-order">
                    <ReceiptLongIcon style={{ color: "#ff6a41", fontSize: "42px" }} />
                </a>
            </div>
            <div className="flex flex-col justify-center flex-1">
                <span className="font-bold text-gray-800">Commande QUETTA-{orderId}</span>
                <div className="flex flex-col justify-center flex-1">
                    <span className="text-gray-500 text-sm">Adresse de livraison :</span>
                    <span className="text-gray-500 text-sm">{address}</span>
                </div>
                <span className="font-bold text-lg text-gray-900 mt-1">{price} €</span>
            </div>

            <div className="flex flex-col items-end min-w-[150px]">
                <span className="text-[10px] uppercase tracking-widest text-gray-400 mb-1 font-bold">
                    Suivi
                </span>
                <span className={`px-3 py-1 rounded-full text-xs font-bold border ${getStatusStyles(status)}`}>
                    {status}
                </span>
            </div>
        </div>
    );
}
