import { useState } from "react";
import { FiTrash2 } from "react-icons/fi";
import LocalMallIcon from '@mui/icons-material/LocalMall';

type CartItem = {
    id: number;
    name: string;
    image: string;
    price: number;
    quantity: number;
};

export function BasketCard() {
    const [cart, setCart] = useState<CartItem[]>([
        {
            id: 1,
            name: "Tomates bio",
            image: "/src/assets/images/tomate.png",
            price: 2.5,
            quantity: 3,
        },
        {
            id: 2,
            name: "Pommes rouges",
            image: "/src/assets/images/pommeRouge.png",
            price: 1.8,
            quantity: 5,
        },
    ]);

    const removeItem = (id: number) => {
        setCart(cart.filter((item) => item.id !== id));
    };

    return (
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
                {cart.map((item) => (
                    <div
                        key={item.id}
                        className="flex items-center bg-white rounded-2xl shadow-md p-4 relative"
                    >
                        {/* Poubelle */}
                        <button
                            onClick={() => removeItem(item.id)}
                            className="absolute left-4 text-red-500 hover:text-red-700"
                        >
                            <FiTrash2 size={24} />
                        </button>

                        {/* Image */}
                        <img
                            src={item.image}
                            alt={item.name}
                            className="w-24 h-24 rounded-lg object-cover ml-12"
                        />

                        {/* Infos produit */}
                        <div className="flex flex-col justify-center ml-6 flex-1">
                            <span className="font-semibold text-lg">{item.name}</span>
                            <span className="text-gray-600 mt-2">
                {item.price.toFixed(2)} € × {item.quantity} ={" "}
                                <span className="font-bold">
                  {(item.price * item.quantity).toFixed(2)} €
                </span>
              </span>
                        </div>

                        {/* Quantité */}
                        <div className="flex flex-col items-center">
                            <span className="text-gray-800 font-semibold">Quantité : {item.quantity}</span>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}
