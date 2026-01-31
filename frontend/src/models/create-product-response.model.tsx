import {MeasurementUnit} from "./enum/measurement-unit.enum.ts";
import type {StockStatus} from "./enum/stock-status.enum.ts";

export type CreateProductResponse = {
  productId: string;
  businessId: string;
  businessName: string;
  label: string;
  image: string;
  description: string;
  price: number;
  stock: number;
  stockStatus: StockStatus;
  measurementUnit: MeasurementUnit;
}