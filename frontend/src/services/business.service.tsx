import type {ClientAuthResponse} from "../models/user.model.tsx";
import axios from "axios";
import type {CreateBusinessRequest} from "../models/create-business-request.model.tsx";

export const retrieveCurrentProducerBusiness = async() => {
  const response = await axios.get(
      "http://localhost:8080/producer-api/businesses"
  )

  return response.data;
}

export const createBusiness = async (createBusinessRequest: CreateBusinessRequest): Promise<ClientAuthResponse> => {
  const response = await axios.post(
      `http://localhost:8080/producer-api/businesses`,
      {
        name: createBusinessRequest.name,
        address: createBusinessRequest.address,
        profession: createBusinessRequest.profession,
        description: createBusinessRequest.description,
        phone: createBusinessRequest.phone,
        email: createBusinessRequest.email,
        producerId: createBusinessRequest.producerId,
      }
  );

  return response.data;
}