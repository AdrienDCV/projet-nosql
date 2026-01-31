export type ClientCart = {
  cartId: string;
  cartEntries: Map<string, CartEntry>;
}

export type CartEntry = {
  cartEntryId: string;
  productId: string;
  productName: string;
  productImage: string;
  quantity: number;
  unitPrice: number;
}