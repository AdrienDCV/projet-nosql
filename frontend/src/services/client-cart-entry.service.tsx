import type {CreateClientCartEntry} from "../models/create-client-cart-entry.model.tsx";
import type {ClientCart} from "../models/client-cart.model.tsx";
import axios from "axios";

export const createNewClientCartEntry = async(createClientCartEntry: CreateClientCartEntry): Promise<ClientCart> => {
  const response = await axios.post(
      "http://localhost:8081/client-api/cart-entries",
      {
        cartId: createClientCartEntry.cartId,
        productId: createClientCartEntry.productId,
        quantity: createClientCartEntry.quantity,
      }
  );

  return response.data;
}

export const deleteClientCartEntry = async (cartEntryId: string): Promise<void> => {
    await axios.delete(`http://localhost:8081/client-api/cart-entries/${cartEntryId}`);
}