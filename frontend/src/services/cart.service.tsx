import type {ClientCart} from "../models/client-cart.model.tsx";
import axios from "axios";

export const retrieveCurrenClientCart = async (): Promise<ClientCart> => {
  const response = await axios.get("http://localhost:8081/client-api/carts");

  return response.data;
}