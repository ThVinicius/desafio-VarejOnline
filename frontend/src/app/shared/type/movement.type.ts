export interface IMovementDto {
  productId: number

  movement: "ENTRADA" | "AJUSTE_ENTRADA" | "SAÍDA" | "AJUSTE_SAÍDA"

  amount: number

  movementDate: Date

  reason: string

  document?: string
}
