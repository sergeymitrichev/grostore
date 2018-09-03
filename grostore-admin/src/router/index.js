import Vue from 'vue'
import Router from 'vue-router'
import Products from '@/components/products/ProductList'
import ProductImportList from '@/components/products/ProductImportList'
import ProductImport from '@/components/products/ProductImport'
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
      path: '/imports',
      name: 'ProductImportList',
      component: ProductImportList
    },
    {
      path: '/imports/:id',
      component: ProductImport
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
