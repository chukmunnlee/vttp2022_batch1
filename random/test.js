function capitalize(n) {
	let a = 4
	n[0] = n[0].toUpperCase()
	console.info('capitalize: ', n)
}

let name = [ 'fred' ]

console.info('before name: ', name)
capitalize(name)

console.info('after name: ', name)
