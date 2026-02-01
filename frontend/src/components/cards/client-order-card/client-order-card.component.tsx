import ReceiptLongIcon from '@mui/icons-material/ReceiptLong';
import React from "react";
import type {Address} from "../../../models/address.model.tsx";

interface IProps {
    orderId: string;
    deliveryAddress: Address;
    totalPrice: number;
}

export function ClientOrderCardComponent({ deliveryAddress, orderId, totalPrice }: IProps): React.JSX.Element {

  const formatDeliveryAddress = () => {
    return `${deliveryAddress.number} ${deliveryAddress.street}, ${deliveryAddress.postalCode} ${deliveryAddress.city} - ${deliveryAddress.country}`;
  }

  return (
        <div className="flex items-center bg-white rounded-2xl shadow-md p-6 border border-gray-100">
            <div className="p-3">
                <a href="/client-order-details">
                    <ReceiptLongIcon style={{ color: "#ff6a41", fontSize: "42px" }} />
                </a>
            </div>
            <div className="flex flex-col justify-center flex-1">
                <span className="font-bold text-gray-800">Commande QUETTA-{orderId}</span>
                <div className="flex flex-col justify-center flex-1">
                    <span className="text-gray-500 text-sm">Adresse de livraison :</span>
                    <span className="text-gray-500 text-sm">{formatDeliveryAddress()}</span>
                </div>
                <span className="font-bold text-lg text-gray-900 mt-1">{totalPrice} €</span>
            </div>
        </div>
    );
}
