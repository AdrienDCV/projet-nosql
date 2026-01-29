import React, {useState} from "react";
import LocalMallIcon from "@mui/icons-material/LocalMall";
import {BasketCardComponent} from "../../components/cards/basket-card/basket-card.component.tsx";

type CartItem = {
  id: number;
  name: string;
  image: string;
  price: number;
  quantity: number;
};

export function CartPage(): React.JSX.Element {

    const myCart = [
      {
        id: 1,
        name: "Tomates bio",
        image: "https://serreslavoie.com/cdn/shop/articles/shutterstock_267812054.jpg?v=1652289544&width=1920",
        price: 2.5,
        quantity: 3,
      },
      {
        id: 2,
        name: "Pommes rouges",
        image: "https://pepinieredubosc.fr/wp-content/uploads/2024/09/pepiniere-du-bosc-pommier-pomme-annurca.jpg",
        price: 1.8,
        quantity: 5,
      },
      {
        id: 3,
        name: "Pommes rouges",
        image: "https://pepinieredubosc.fr/wp-content/uploads/2024/09/pepiniere-du-bosc-pommier-pomme-annurca.jpg",
        price: 1.8,
        quantity: 5,
      },
    ];

    const [cart, setCart] = useState<CartItem[]>(myCart);

    const removeItem = (id: number) => {
      setCart(cart.filter((item) => item.id !== id));
    };

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
                cart.map((item) => (
                    <BasketCardComponent
                        id={item.id}
                        name={item.name}
                        image={item.image}
                        price={item.price}
                        quantity={item.quantity}
                        onRemove={() => removeItem(item.id)}
                    />
                ))
              }
            </div>
          </div>
        </div>
    );
}
