<template>
    <div class="container">
      <form enctype="multipart/form-data" action="http://localhost:8080/import" method="post" >
        <input type="file" multiple name="file" @change="upload($event)"/>
        <v-btn color="error" type="submit">Submit</v-btn>
      </form>

    </div>
</template>

<script>
  import HttpService from "../../services/http.service";

  export default {
    name: 'ProductImportXls',

    methods: {
      upload: function(e) {
        let formData = new FormData()
        formData.append('file', e.target.files[0], e.target.name)
        console.log(e);
        HttpService.upload(formData)
      }
    }
  }
</script>

<style>

</style>
