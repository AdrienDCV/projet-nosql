import React, {useEffect, useRef} from "react";
import LocalMallIcon from "@mui/icons-material/LocalMall";
import {
  ClientOrderDetailsCard
} from "../../components/cards/client-orders-details-card/client-orders-details-cards.component.tsx";
import {useApp} from "../../hooks/app-context.hook.tsx";
import {useParams} from "react-router-dom";

export function ClientOrderDetailsPage(): React.JSX.Element {
  const {clientOrderId} = useParams<{ clientOrderId: string }>();
  const {currentClientOrderDetails, getClientOrderDetails} = useApp();

  const lastFetchedIdRef = useRef<string | null>(null);

  useEffect(() => {
    if (!clientOrderId) return;
    if (lastFetchedIdRef.current === clientOrderId) return;

    void getClientOrderDetails(clientOrderId);
    lastFetchedIdRef.current = clientOrderId;
  }, [clientOrderId, getClientOrderDetails]);


  if (!currentClientOrderDetails) {
    return <div>Error...</div>
  }

  return (
      <div className="w-full flex flex-col">
        <div className="min-h-screen bg-[#FFF6E8] px-8 py-10">
          <div className="flex justify-center mb-8">
            <div className="inline-flex items-center gap-3 p-2 rounded-full px-4 sm:px-6 md:px-10 lg:px-20 xl:px-32">
              <h1 className="text-3xl font-bold text-[#EB4511]">
                Details de la commande
              </h1>
              <LocalMallIcon style={{color: "#EB4511", fontSize: "40px"}}/>
            </div>
          </div>

          <div className="flex flex-col gap-6 max-w-4xl mx-auto">
            {
              <ClientOrderDetailsCard
                  clientOrderId={currentClientOrderDetails.clientOrderId}
                  deliveryAddress={currentClientOrderDetails.deliveryAddress}
                  totalPrice={currentClientOrderDetails.totalPrice}
                  producerOrders={currentClientOrderDetails.producerOrders}
                  orderDate={currentClientOrderDetails.orderDate}
              />
            }
          </div>
        </div>
      </div>
  );
}