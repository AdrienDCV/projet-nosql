import type { StockStatus } from "./enum/stock-status.enum";
import type { MeasurementUnit } from "./enum/measurement-unit.enum.ts";

export type Product = {
    productId: string;
    businessId: string;
    businessName: string;
    label: string;
    image: string;
    description: string;
    price: number;
    stock: number;
    stockStatus: StockStatus;
    uniteMesure: MeasurementUnit;
}