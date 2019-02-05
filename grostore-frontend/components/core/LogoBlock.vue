<template lang="pug">
  div.text-xs-center.text-sm-left.text-md-left
    div.d-inline-flex
      img(src="/minutka-logo.png")
    div.title.d-inline-block.ml-3
      div.error--text СВОБОДНАЯ 
      div.font-weight-thin(:class="{ 'white--text': !dark }") МИНУТКА  
</template>
<script>
export default {
  props: {
    dark: {
      default: false,
      type: Boolean
    }
  }
}
</script>

<style scoped>
* > img {
  width: 40px;
  height: 40px;
}
* > .title {
  vertical-align: top;
}
</style>
