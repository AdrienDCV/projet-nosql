import type {ClientAuthResponse} from "../models/user.model.tsx";
import axios from "axios";
import type {CreateBusinessRequest} from "../models/create-business-request.model.tsx";
import type {UpdateBusinessRequest} from "../models/update-business-request.model.tsx";
import type {Business} from "../models/business.model.tsx";

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

export const updateBusiness = async (updateBusinessRequest: UpdateBusinessRequest): Promise<Business> => {
  const response = await axios.put(
      `http://localhost:8080/producer-api/businesses`,
      {
        name: updateBusinessRequest.name,
        address: updateBusinessRequest.address,
        profession: updateBusinessRequest.profession,
        description: updateBusinessRequest.description,
        phone: updateBusinessRequest.phone,
        email: updateBusinessRequest.email,
        producerId: updateBusinessRequest.producerId,
      }
  );

  return response.data;
};