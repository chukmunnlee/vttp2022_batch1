// mape
//
export const ordersDB = {}

const order = {
	id: '12345',
	name: 'fred',
	email: 'fred@gmail.com',
	items: [
		{ name: 'apple', quantity: 10 },
		{ name: 'orange', quantity: 10 },
	]
}

ordersDB[order.id] = order

console.info('>>>> order: ', ordersDB['12345'])
