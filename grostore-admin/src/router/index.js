import Vue from 'vue'
import Router from 'vue-router'
import Products from '@/components/ProductList'
import Orders from '@/components/OrderList'
import Customers from '@/components/CustomerList'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/products',
      name: 'ProductList',
      component: Products
    },
    {
      path: '/orders',
      name: 'OrderList',
      component: Orders
    },
    {
      path: '/customers',
      name: 'CustomerList',
      component: Customers
    },
  ]
})
