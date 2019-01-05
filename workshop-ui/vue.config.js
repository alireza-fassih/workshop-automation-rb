module.exports = {
    devServer: {
        port: 3000,
        proxy: {
            "^/rest" : {
                target: 'http://localhost:8080',
                headers: {
                    'Workshop-app': 'demo'
                }
            }
        }
    }
}
  