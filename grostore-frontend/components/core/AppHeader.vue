<template lang="pug">
  v-container.header(fluid pt-0)
    v-layout(wrap)
      v-flex(xs12 sm6 md5 order-xs2 order-sm1 order-md1 pt-2)
        div.text-xs-center.text-sm-left.text-md-left
          div.d-inline-flex
            img(src="/minutka-logo.png")
          div.title.d-inline-block.ml-3.mt-2
            div.error--text СВОБОДНАЯ 
            div.font-weight-thin.white--text МИНУТКА  
      v-flex(xs12 sm6 md7 order-xs2 order-sm1 order-md1 pt-0)
        div.text-xs-center.text-sm-right.text-md-right
          div.font-weight-thin.white--text.title.menu
            span.mr-5
              nuxt-link.active(to="/catalog") Продукты
            span.mr-5
              nuxt-link(to="/delivery") Доставка
            span.mr-5 
              nuxt-link(to="/about") О нас
            span.mr-0.ml-5.display-1
              v-btn(flat fab)
                v-icon.white--text shopping_cart
            span.display-1
              v-btn(flat fab)
                v-icon.white--text menu
</template>

<script>
export default {}
</script>
<style scoped>
* > .header {
  position: absolute;
  z-index: 1;
}
* > .menu a {
  color: #fff;
  padding-bottom: 5px;
  border-bottom: 3px solid transparent;
}
* > .menu a.active,
* > .menu a:hover {
  border-bottom-color: var(--v-error-base);
}
* > .title {
  vertical-align: top;
}
</style>
