<template>
  <v-card flat>
    <label>
      Open file dialog
      <input type="file" style="position: fixed; top: -100em">
    </label>
  </v-card>
</template>

<script>

  export default {
    name: "ProductImportXls"
  }
</script>

<style scoped>
label {
  border: 1px solid color()
}
</style>
