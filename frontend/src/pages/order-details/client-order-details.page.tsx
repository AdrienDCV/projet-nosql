import React, { useState } from "react";
import LocalMallIcon from "@mui/icons-material/LocalMall";
import {OrderStatus} from "../../models/enum/order-status.enum.ts";
import {ClientOrderDetailsCard} from "../../components/cards/client-orders-details-card/client-orders-details-cards.component.tsx";
import type {ProducerOrder} from "../../models/producer-order.model.tsx";
import {StockStatus} from "../../models/enum/stock-status.model.tsx";
import {UnitMesure} from "../../models/enum/unit-mesure.model.tsx";
import type {ClientOrderDetails} from "../../models/client-order-details.model.tsx";

export function ClientOrderDetailsPage(): React.JSX.Element {
    const clientOrderDetailsTest: ClientOrderDetails = {
        clientOrderId: "order_001",
        clientId: "client_123",

        producerOrders: new Map<string, ProducerOrder[]>([
            [
                "producer_001",
                [
                    {
                        productId: "prod_001",
                        businessId: "producer_001",
                        label: "Pommes bio",
                        price: 2.5,
                        stock: 100,
                        stockStatus: StockStatus.IN_STOCK,
                        uniteMesure: UnitMesure.KG
                    },
                    {
                        productId: "prod_002",
                        businessId: "producer_001",
                        label: "Jus de pomme",
                        price: 3.0,
                        stock: 25,
                        stockStatus: StockStatus.LIMITED_STOCK,
                        uniteMesure: UnitMesure.L
                    }
                ]
            ],
            [
                "producer_002",
                [
                    {
                        productId: "prod_003",
                        businessId: "producer_002",
                        label: "Fromage fermier",
                        price: 5.5,
                        stock: 0,
                        stockStatus: StockStatus.OUT_OF_STOCK,
                        uniteMesure: UnitMesure.UNIT
                    }
                ]
            ]
        ]),

        deliveryAddress: {
            street: "10 rue des Fleurs",
            city: "Lyon",
            postalCode: "69001",
            country: "France",
            number: 25,
        },

        email: "client@example.com",
        phone: "0601020304",
        orderStatus: OrderStatus.ORDERED,
        orderDate: new Date("2026-01-29T14:00:00"),
        totalPrice: 11.0
    };

    const [clientOrderDetails, setClientOrderDetails] = useState<ClientOrderDetails>(clientOrderDetailsTest);

    return (
        <div className="w-full flex flex-col">
            <div className="min-h-screen bg-[#FFF6E8] px-8 py-10">
                <div className="flex justify-center mb-8">
                    <div className="inline-flex items-center gap-3 p-2 rounded-full px-4 sm:px-6 md:px-10 lg:px-20 xl:px-32">
                        <h1 className="text-3xl font-bold text-[#EB4511]">
                            Details de la commande
                        </h1>
                        <LocalMallIcon style={{ color: "#EB4511", fontSize: "40px" }} />
                    </div>
                </div>

                <div className="flex flex-col gap-6 max-w-4xl mx-auto">
                    {
                        <ClientOrderDetailsCard
                            clientOrderDetails={clientOrderDetailsTest}
                        />
                    }
                </div>
            </div>
        </div>
    );
}