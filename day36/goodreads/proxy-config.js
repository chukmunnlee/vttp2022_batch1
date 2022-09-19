module.exports = [
  {
    context: [ '/api/**' ], //match these request
    target: 'http://localhost:8080', //SpringBoot!
    secure: false
  }
]
