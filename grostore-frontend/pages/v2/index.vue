<template>
  <div>
    <promo-slide />
    <category-list />
    <product-list />
    <workflow />
    <mention-list />
    <subscribe-block />
    <article-list />
  </div>
</template>

<script>
import PromoSlide from '~/components/core/PromoSlide'
import CategoryList from '~/components/category/CategoryList'
import ProductList from '~/components/product/ProductList'
import Workflow from '~/components/about/Workflow'
import MentionList from '~/components/account/MentionList'
import SubscribeBlock from '~/components/account/SubscribeBlock'
import ArticleList from '~/components/article/ArticleList'

export default {
  components: {
    PromoSlide,
    CategoryList,
    ProductList,
    Workflow,
    MentionList,
    SubscribeBlock,
    ArticleList
  }
}
</script>

<style scoped>
</style>
