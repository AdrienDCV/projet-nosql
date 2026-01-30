import type {UnitMesure} from "./enum/unit-mesure.enum.tsx";

export type CreateProductRequest = {
    image: string,
    label: string,
    uniteMesure: UnitMesure,
    stock: number,
    price: number,
};