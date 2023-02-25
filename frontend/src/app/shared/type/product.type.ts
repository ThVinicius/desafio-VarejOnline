export interface IProductResponse {
  productId: number
  name: string
  barCode: string
  minimumAmount: number
  openBalance: number
}

export type IProduct = Omit<IProductResponse, "productId">

export interface INextProductId {
  nextProductId: number
}
