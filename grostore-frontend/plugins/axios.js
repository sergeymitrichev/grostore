const Cookie = process.client ? require('js-cookie') : undefined

export default function({ $axios, redirect }) {
  $axios.onResponse(response => {
    console.log('** axios response')
    console.log(localStorage.getItem('auth._refresh_token.local'))
    console.log(Cookie.get('auth._refresh_token.local'))
    console.log(response.headers)
  })
}
