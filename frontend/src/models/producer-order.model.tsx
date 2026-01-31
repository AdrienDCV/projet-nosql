import type {StockStatus} from "./enum/stock-status.model.tsx";
import type {UnitMesure} from "./enum/unit-mesure.model.tsx";

export type ProducerOrder = {
    productId : string;
    businessId : string;
    label : string;
    price : number;
    stock : number;
    stockStatus : StockStatus;
    uniteMesure : UnitMesure;
}