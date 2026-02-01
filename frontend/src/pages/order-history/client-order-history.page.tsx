import React, {useEffect} from "react";
import LocalMallIcon from "@mui/icons-material/LocalMall";
import {ClientOrderCardComponent} from "../../components/cards/client-order-card/client-order-card.component.tsx";
import {useApp} from "../../hooks/app-context.hook.tsx";
import type {ClientOrderHistoryRecord} from "../../models/order-history.model.tsx";
import {useNavigate} from "react-router-dom";

export function ClientOrderHistoryPage(): React.JSX.Element {
    const { currentClientOrderHistory, refreshCurrentClientOrderHistory } = useApp();
    const navigate = useNavigate()

    useEffect(() => {
        void refreshCurrentClientOrderHistory();
    }, []);

    const orderHistory = currentClientOrderHistory?.orderHistory;

    const hasNoHistory =
        !orderHistory ||
        (orderHistory instanceof Map
            ? orderHistory.size === 0
            : Object.keys(orderHistory).length === 0);

    if (hasNoHistory) {
        return (
            <div className="flex flex-col items-center justify-center min-h-[60vh] gap-4 text-center">
                <LocalMallIcon
                    style={{ fontSize: "64px", color: "#EB4511", opacity: 0.8 }}
                />
                <h2 className="text-2xl font-bold text-[#EB4511]">
                    Aucun historique pour le moment
                </h2>
                <p className="text-gray-600 max-w-md">
                    Vous n’avez pas encore passé de commande.
                    Une fois une commande validée, elle apparaîtra ici.
                </p>
                <button
                    className="mt-2 bg-[#B02E0C] text-white font-bold py-2 px-6 rounded-xl hover:bg-[#8f220a] transition"
                    onClick={() => navigate("/home")}
                >
                    Retour à l’accueil
                </button>
            </div>
        );
    }

    return (
        <div className="w-full flex flex-col">
            <div className="w-full h-full bg-[#FFF6E8] px-8 py-10">
                <div className="flex justify-center mb-8">
                    <div className="inline-flex items-center gap-3 p-2 rounded-full px-4 sm:px-6 md:px-10 lg:px-20 xl:px-32">
                        <h1 className="text-3xl font-bold text-[#EB4511]">
                            Historique des commandes
                        </h1>
                        <LocalMallIcon style={{ color: "#EB4511", fontSize: "34px" }} />
                    </div>
                </div>

                {
                    Object.entries(orderHistory).map(
                    ([monthLabel, orderRecords]: [string, ClientOrderHistoryRecord[]]) => (
                        <div key={monthLabel} className="w-full flex flex-col mb-10">

                            <div className="max-w-4xl mx-auto text-lg font-bold text-[#EB4511] mb-4">
                                {monthLabel}
                            </div>

                            <div className="flex flex-col gap-6 max-w-4xl mx-auto">
                                {orderRecords.map((record: ClientOrderHistoryRecord) => (
                                    <ClientOrderCardComponent
                                        key={record.clientOrderId}
                                        orderId={record.clientOrderId}
                                        deliveryAddress={record.deliveryAddress}
                                        totalPrice={record.totalPrice}
                                    />
                                ))}
                            </div>
                        </div>
                    )
                )}
            </div>
        </div>
    );
}

