import axios from 'axios'

export default function({ isServer, req, res }) {
  if (isServer) {
    console.log('*** is server')
    res.header('Access-Control-Allow-Origin', 'http://localhost:3000')
    res.header('Access-Control-Allow-Credentials', true)
    res.header(
      'Access-Control-Allow-Headers',
      'Origin, X-Requested-With, Content-Type, Accept'
    )
    axios.defaults.headers.common.cookie = req.headers.cookie
  }
}
