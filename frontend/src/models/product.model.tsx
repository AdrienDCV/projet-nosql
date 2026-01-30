import type { StockStatus } from "./enum/stock-status.enum";
import type { UniteMesure } from "./enum/unit-mesure.enum";

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
    uniteMesure: UniteMesure;
}