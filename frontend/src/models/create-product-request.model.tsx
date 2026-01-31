import type {MeasurementUnit} from "./enum/measurement-unit.enum.ts";

export type CreateProductRequest = {
    businessId: string;
    businessName: string;
    label: string;
    image: string;
    description: string;
    price: number;
    stock: number;
    measurementUnit: MeasurementUnit;
};