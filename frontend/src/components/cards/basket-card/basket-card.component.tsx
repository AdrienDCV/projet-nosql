import { FiTrash2 } from "react-icons/fi";

interface IProps {
    id: number;
    name: string;
    image: string;
    price: number;
    quantity: number;
    onRemove: VoidFunction;
};

export function BasketCardComponent({id, name, image, price, quantity, onRemove}: IProps): React.JSX.Element {

    return (
        <>
          <div
              key={id}
              className="flex items-center bg-white rounded-2xl shadow-md p-4 relative"
          >
            {/* Poubelle */}
            <button
                onClick={onRemove}
                className="absolute left-4 text-red-500 hover:text-red-700"
            >
              <FiTrash2 size={24} />
            </button>

            {/* Image */}
            <img
                src={image}
                alt={name}
                className="w-24 h-24 rounded-lg object-cover ml-12"
            />

            {/* Infos produit */}
            <div className="flex flex-col justify-center ml-6 flex-1">
              <span className="font-semibold text-lg">{name}</span>
              <span className="text-gray-600 mt-2">
                          {price.toFixed(2)} € × {quantity} ={" "}
                <span className="font-bold">
                            {(price * quantity).toFixed(2)} €
                          </span>
                        </span>
            </div>

            {/* Quantité */}
            <div className="flex flex-col items-center">
              <span className="text-gray-800 font-semibold">Quantité : {quantity}</span>
            </div>
          </div>
        </>
    );
}
