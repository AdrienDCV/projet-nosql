import React, {useState} from "react";
import LocalMallIcon from "@mui/icons-material/LocalMall";
import {CartCardComponent} from "../../components/cards/basket-card/cart-card.component.tsx";
import {useApp} from "../../hooks/app-context.hook.tsx";
import {useAuthentication} from "../../hooks/authentication-context.hook.tsx";
import Button from "@mui/material/Button";
import {AddDeliveryAddressModal} from "../../modals/add-delivery-address/add-delivery.address.modal.tsx";
import type {Client} from "../../models/user.model.tsx";

export function CartPage(): React.JSX.Element {
  const [openAddDeliveryAddressModal, setOpenAddDeliveryAddressModal] = useState<boolean>(false);
  const { currentClientCart} = useApp();
  const { user } = useAuthentication();

    /*
    const removeItem = (id: number) => {
      setCart(cart.filter((item) => item.id !== id));
    };
    */

    if (!currentClientCart) {
      return (
          <div>Panier inexistant...</div>
      )
    }

    const emptyCart =
        <div className="flex flex-col items-center justify-center min-h-[60vh] gap-4 text-center">
          <LocalMallIcon
              style={{ fontSize: "64px", color: "#EB4511", opacity: 0.8 }}
          />
          <h2 className="text-2xl font-bold text-[#EB4511]">
            Votre panier est vide
          </h2>
          <p className="text-gray-600 max-w-md">
            Ajoutez des produits pour les retrouver ici et passer votre commande.
          </p>
        </div>

    return (
        <div className="w-full flex flex-col justify-center">
          {
            currentClientCart.cartEntries.length > 0 ?
                <div className="min-h-screen bg-[#FFF6E8] px-8">
                  <div className="flex justify-center mb-8">
                    <div className="inline-flex items-center gap-3 p-2 rounded-full px-4 sm:px-6 md:px-10 lg:px-20 xl:px-32">

                      <h1 className="text-3xl font-bold text-[#EB4511]">
                        Votre Panier
                      </h1>
                      <LocalMallIcon style={{ color: "#EB4511", fontSize: "34px" }} />
                    </div>
                  </div>
                  <>
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
                    <div className="w-full flex justify-center mt-10">
                      <Button
                          onClick={() => setOpenAddDeliveryAddressModal(true)}
                          style={{
                            backgroundColor: '#B02E0C',
                            borderRadius: '0.75rem',
                            color: 'white',
                            fontWeight: 'bold',
                            padding: '16px 48px',
                            fontSize: '1.1rem',
                          }}
                      >
                        Valider la commande
                      </Button>
                    </div>
                    {
                        user && currentClientCart &&
                        <AddDeliveryAddressModal
                            open={openAddDeliveryAddressModal}
                            onClose={() => setOpenAddDeliveryAddressModal(false)}
                            currentClientCart={currentClientCart}
                            client={(user as Client)}
                        />
                    }
                  </>
                </div> :
                emptyCart
          }
        </div>
    );
}
