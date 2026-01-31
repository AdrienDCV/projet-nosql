import { Modal } from "@mui/material";
import RemoveIcon from "@mui/icons-material/Remove";
import AddIcon from "@mui/icons-material/Add";
import { useState } from "react";
import Button from "@mui/material/Button";
import type {Product} from "../../models/product.model.tsx";
import {useApp} from "../../hooks/app-context.hook.tsx";
import type {CreateClientCartEntry} from "../../models/create-client-cart-entry.model.tsx";

type IProps = {
  open: boolean;
  onClose: VoidFunction;
  product: Product
};

export const AddItemToCart = ({open, onClose, product,}: IProps): React.JSX.Element => {
  const [quantity, setQuantity] = useState<number>(1);
  const { currentClientCart,  addItemToClientCart } = useApp();

  const handleAddItemToCart = () => {
    if (!currentClientCart) return;

    const newClientCartEntry: CreateClientCartEntry = {
      cartId: currentClientCart?.cartId,
      productId: product.productId,
      quantity: quantity,
    }

    addItemToClientCart(newClientCartEntry);
  }

  return (
      <Modal open={open} onClose={onClose}>

        {
          product ?
            <div className=" absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 bg-[#EEEEEE] p-8 rounded-2xl outline-none w-[90vw] max-w-100">
                <div className="flex flex-col items-center gap-6">
                    <img
                        src={product.image}
                        alt={`${product.label}-image`}
                        className=" w-64 h-64 object-cover rounded-xl shadow-sm"
                    />
                    <h2 className="text-xl font-bold text-center">
                      {product.label}
                    </h2>
                    <div className="flex flex-row justify-center items-center gap-4">
                        <RemoveIcon
                            className="cursor-pointer"
                            onClick={() => setQuantity(Math.max(1, quantity - 1))}
                        />
                        <span className="font-semibold">{quantity}</span>
                        <AddIcon
                            className="cursor-pointer"
                            onClick={() => setQuantity(quantity + 1)}
                        />
                    </div>
                    <span className="font-semibold">
                      { (quantity * product.price).toFixed(2)} €
                    </span>
                    <div className="flex flex-col justify-center gap-4">
                        <Button
                            style={{
                              backgroundColor: '#FFE4B9',
                              borderRadius: '0.5rem',
                              color: 'black',
                              fontWeight: 'bold'
                            }}
                            onClick={() => {
                              handleAddItemToCart();
                              onClose();
                            }}>
                            Ajouter au panier
                        </Button>
                        <Button
                            style={{
                              backgroundColor: '#B02E0C',
                              borderRadius: '0.5rem',
                              color: 'white',
                              fontWeight: 'bold'
                            }}
                            onClick={onClose}
                        >
                            Annuler
                        </Button>
                    </div>
                </div>
            </div> : <div>Aucun contenu...</div>
        }


      </Modal>
  );
};
