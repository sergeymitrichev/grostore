<template lang="pug">
  div
    v-container(fluid red lighten-5 mt-5 pt-5 pb-5)
      v-layout(wrap)
        v-flex(md4 pl-5 pr-5) 
          logo-block.mb-4(dark)
          div.divider.mb-4 
          div Онлайн гипермаркет продуктов с доставкой на дом. Работаем с 2014 года и сотрудничаем с Метро C&C, Ашан, Вкусвилл. 
          v-list(dense style="background: transparent")
            v-list-tile
              v-list-tile-action
                v-icon send 
              v-list-tile-content sales@minutka-nn.ru
            v-list-tile
              v-list-tile-action
                v-icon https 
              v-list-tile-content 
                nuxt-link(to="/") https://minutka-nn.ru
            v-list-tile
             v-list-tile-action
               v-icon location_on 
             v-list-tile-content Нижний Нижний Новгород, ул. Нартова, 4а    
        v-flex(md2 pl-5)
          div.title.mt-4.mb-4
            span.font-weight-thin каталог 
            span продуктов
          div.divider.mb-3
          div.footer-menu
            v-list(dense style="background: transparent")
              v-list-tile
                v-list-tile-action
                  v-icon chevron_right 
                v-list-tile-content 
                  nuxt-link(to="/products") Все товары
              v-list-tile
                v-list-tile-action
                  v-icon chevron_right  
                v-list-tile-content 
                  nuxt-link(to="/products/disounts") Специальные предложения
              v-list-tile
                v-list-tile-action
                  v-icon chevron_right  
                v-list-tile-content 
                  nuxt-link(to="/brands") По производителям
        v-flex(md3 pl-5) 
          div.title.mt-4.mb-4
            span.font-weight-thin работа 
            span сервиса
          div.divider.mb-3
          div.footer-menu
            v-list(dense style="background: transparent")
              v-list-tile
                v-list-tile-action
                  v-icon chevron_right 
                v-list-tile-content 
                  nuxt-link(to="/products") Доставка
              v-list-tile
                v-list-tile-action
                  v-icon chevron_right  
                v-list-tile-content 
                  nuxt-link(to="/products/disounts") Способы оплаты
              v-list-tile
                v-list-tile-action
                  v-icon chevron_right  
                v-list-tile-content 
                  nuxt-link(to="/brands") Как мы выбираем продукты?
              v-list-tile
                v-list-tile-action
                  v-icon chevron_right  
                v-list-tile-content 
                  nuxt-link(to="/brands") Помощь
        v-flex(md3 pr-5) 
          div.title.mt-4.mb-4
            span.font-weight-thin наш 
            span Instagram    
          div.divider.mb-3
          v-container(fluid pa-0 grid-list-sm)
            v-layout(row wrap)
              v-flex(v-for="n in 6" :key="n" xs4)
                v-card(flat tile)
                  v-img(:src="`/img/inst1 (${n}).jpg`")
    v-container(fluid pt-4 pb-4)
      v-layout(wrap)
        v-flex(md4) 
          div.gray--text.lighten-5 2014 — 2019 © ООО «Свободная Минутка»
        v-flex(md4)

        v-flex(md4) 
          div.text-xs-center.text-sm-right.text-md-right
            div.font-weight-thin.white--text.menu
              span.mr-5
                nuxt-link.active(to="/catalog") связаться с нами
              span.mr-5
                nuxt-link(to="/delivery") пользовательское соглашение
              span.mr-5 
                nuxt-link(to="/about") карта сайта
</template>

<script>
import LogoBlock from '~/components/core/LogoBlock'

export default {
  components: {
    LogoBlock
  }
}
</script>

<style scoped>
div.divider {
  width: 30px;
  height: 3px;
  background: var(--v-error-base);
}
* >>> .v-list--dense .v-list__tile:not(.v-list__tile--avatar) {
  height: 30px;
  padding: 0px;
}
* >>> .v-list__tile__action {
  min-width: 36px;
}
</style>
