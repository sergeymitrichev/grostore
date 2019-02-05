<template lang="pug">
  v-parallax(
    height="800"
    src="/paralax.jpg"
  )
    v-layout(
      align-center
      column
      justify-center
    )
      h1.display-2.font-weight-thin.mb-3 Promo
      h4.subheading.mb-2 Subhead
      v-btn.success(large round) Click!
</template>

<style>
.v-parallax__image {
  width: 100%;
}
</style>
