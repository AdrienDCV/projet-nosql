import type {StockStatus} from "./enum/stock-status.enum.ts";

export type UpdateProductRequest = {
  productId: string,
  businessId: string,
  label: string,
  description: string,
  image: string,
  price: number,
  stock: number,
  stockStatus: StockStatus
}