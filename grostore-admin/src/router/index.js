import Vue from 'vue'
import Router from 'vue-router'
import Products from '@/components/ProductList'
import ProductImportXls from '@/components/products/ProductImport'
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
      path: '/import',
      name: 'ProductImport',
      component: ProductImportXls
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
