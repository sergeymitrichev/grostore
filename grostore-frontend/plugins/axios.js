export default function({ $axios, redirect }) {
  //$axios.defaults.baseURL = 'http://localhost:6100'

  $axios.onRequest(config => {
    console.log('Making request to ' + config.url)
  })

  $axios.onError(error => {
    console.log($axios.defaults.baseURL)
    console.log(error)
  })
}
