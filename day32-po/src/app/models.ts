export interface LineItem {
  item: string
  quantity: number
}
export interface Order {
  orderId?: string
  name: string
  mobile: string
  lineItems: LineItem[]
}

export type OrderDB = {
  [ key: string ]: Order
}

export const orderDB: OrderDB = {}
