import React from "react";
import LocalMallIcon from "@mui/icons-material/LocalMall";
import {CartCardComponent} from "../../components/cards/basket-card/cart-card.component.tsx";
import {useApp} from "../../hooks/app-context.hook.tsx";

export function CartPage(): React.JSX.Element {
  const { currentClientCart } = useApp();

    /*
    const removeItem = (id: number) => {
      setCart(cart.filter((item) => item.id !== id));
    };
    */

    if (!currentClientCart) {
      return (
          <div>
            Panier vide...
          </div>
      )
    }

    return (
        <div className="w-full flex flex-col">
          <div className="min-h-screen bg-[#FFF6E8] px-8">
            <div className="flex justify-center mb-8">
              <div className="inline-flex items-center bg-[#EB4511] gap-3 p-2 rounded-full px-4 sm:px-6 md:px-10 lg:px-20 xl:px-32">

                <h1 className="text-3xl font-bold text-white">
                  Votre Panier
                </h1>
                <LocalMallIcon style={{ color: "#FFFFFF", fontSize: "40px" }} />
              </div>
            </div>

            <div className="flex flex-col gap-6 max-w-4xl mx-auto">
              {
                currentClientCart.cartEntries.map(
                    (entry, index) => (
                        <CartCardComponent
                            index={index}
                            name={entry.productName}
                            image={entry.productImage}
                            price={entry.unitPrice}
                            quantity={entry.quantity}
                            onRemove={() => {}}
                        />
                    )
                )
              }
            </div>
          </div>
        </div>
    );
}
