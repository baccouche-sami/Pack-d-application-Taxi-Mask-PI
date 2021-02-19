<template>
  <div>
    <section
      class="section has-background-white-bis"
      style="margin-top:5%"
    >
      <div class="container has-text-centered">
        <div class="is-size-5 mb-6 notification is-success">
           <v-alert
            dense
            outlined
            type="error"
          >
            Payement annulé, veillez réessayer :( 
          </v-alert>
                
        </div>
      </div>
    </section>
  </div>
</template>

<script> 
export default {
  components: {}, 
  props: { 
  }, 
  data() {
    return {
      
    }
  },
  created() {
   
  },
  methods: {
   
  },
}
</script>
<style scoped>
 
</style>
