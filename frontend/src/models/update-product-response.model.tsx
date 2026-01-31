import type {StockStatus} from "./enum/stock-status.enum.ts";
import type {MeasurementUnit} from "./enum/measurement-unit.enum.ts";

export type UpdateProductResponse = {
   productId: string;
   businessId: string;
   label: string;
   description: string;
   image: string;
   price: number;
   stock: number;
   stockStatus: StockStatus;
   measurementUnit: MeasurementUnit;
   businessName: string,
}