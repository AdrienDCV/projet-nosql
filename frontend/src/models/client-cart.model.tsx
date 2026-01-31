export type ClientCart = {
  cartId: string;
  cartEntries: CartEntry[];
}

export type CartEntry = {
  cartEntryId: string;
  productId: string;
  productName: string;
  productImage: string;
  quantity: number;
  unitPrice: number;
}