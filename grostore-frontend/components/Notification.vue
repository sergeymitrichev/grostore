<template>
  <v-alert 
    :value="true" 
    type="error">{{ message }}</v-alert>
</template>

<script>
export default {
  name: 'Notification',
  props: {
    message: {
      type: String,
      default: ''
    }
  }
}
</script>
