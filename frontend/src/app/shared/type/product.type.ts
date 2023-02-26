export interface IProductResponse {
  id: number
  name: string
  barCode: string
  minimumAmount: number
  openBalance: number
}

export type IProduct = Omit<IProductResponse, "id">

export interface INextProductId {
  nextProductId: number
}

export interface IProductInfo {
  productId: number
  name: string
  barCode: string
  minimumAmount: number
  openBalance: number
  registrationDate: Date
}
