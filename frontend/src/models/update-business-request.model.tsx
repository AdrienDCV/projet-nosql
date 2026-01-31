import type {Address} from "./address.model.tsx";
import type {Profession} from "./enum/profession.enum.ts";

export type UpdateBusinessRequest = {
  businessId: string;
  name: string;
  address: Address;
  profession: Profession;
  description: string;
  phone: string;
  email: string;
  producerId: string;
};
