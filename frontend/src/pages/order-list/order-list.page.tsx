import React, { useState } from "react";
import LocalMallIcon from "@mui/icons-material/LocalMall";
import {ClientOrderCardComponent} from "../../components/cards/client-order-card/client-order-card.component.tsx";
import {OrderStatus} from "../../models/enum/client-order-status.enum.ts";

type OrderItem = {
    id: number;
    orderId : number;
    address: string;
    status: OrderStatus;
    price: number;
};

export function OrderListPage(): React.JSX.Element {
    const myOrderList: OrderItem[] = [
        {
            id: 1,
            orderId: 19362734,
            address: "12 rue des Tomates, Paris",
            status: OrderStatus.ORDERED,
            price: 75,
        },
        {
            id: 2,
            orderId: 398162, // Attention : pas de 0 au début d'un number
            address: "45 avenue des Pommes, Lyon",
            status: OrderStatus.SHIPPING,
            price: 43,
        },
        {
            id: 3,
            orderId: 398162, // Attention : pas de 0 au début d'un number
            address: "45 avenue des Pommes, Lyon",
            status: OrderStatus.PREPARING,
            price: 43,
        },
        {
            id: 4,
            orderId: 398162, // Attention : pas de 0 au début d'un number
            address: "45 avenue des Pommes, Lyon",
            status: OrderStatus.DELIVERED,
            price: 43,
        },
    ];

    const [order] = useState<OrderItem[]>(myOrderList);

    return (
        <div className="w-full flex flex-col">
            <div className="min-h-screen bg-[#FFF6E8] px-8 py-10">
                <div className="flex justify-center mb-8">
                    <div className="inline-flex items-center bg-[#EB4511] gap-3 p-2 rounded-full px-4 sm:px-6 md:px-10 lg:px-20 xl:px-32">
                        <h1 className="text-3xl font-bold text-white">
                            Votre historique de commande
                        </h1>
                        <LocalMallIcon style={{ color: "#FFFFFF", fontSize: "40px" }} />
                    </div>
                </div>

                <div className="flex flex-col gap-6 max-w-4xl mx-auto">
                    {order.map((item) => (
                        <ClientOrderCardComponent
                            key={item.id} // Indispensable pour React
                            id={item.id}
                            orderId={item.orderId}
                            deliveryAddress={item.address}
                            status={item.status}
                            price={item.price}
                        />
                    ))}
                </div>
            </div>
        </div>
    );
}