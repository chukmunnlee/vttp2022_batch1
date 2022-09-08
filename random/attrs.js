const key = 'name'
const value = 'fred'

const fred = { key: value }

console.info('>>> fred: ', fred)

const fred2 = { [key]: value }

console.info('>>> fred2: ', fred2)

const key2 = 'email'

const fred3 = { ...fred2, [key2]: 'fred@gmail.com' }

console.info('>>> fred3: ', fred3)

const fred4 = { ...fred3, [key]: 'barney' } 

console.info('>>> fred4: ', fred4)


