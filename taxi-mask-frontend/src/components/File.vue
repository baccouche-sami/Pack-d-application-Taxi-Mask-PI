<template>
  <div>
    <v-container>

      <p class="display-1 font-weight-light	text-center pa-4">file</p>
      <v-row>
         <v-col class="col-4"></v-col>
         <v-col class="col-4" style="box-shadow: 0 0 0 1px #dbdbdb;padding: 15px;" > 
            <v-form enctype="multipart/form-data"
              ref="form"  @submit.prevent="submit" 
              lazy-validation
            >  
            <v-file-input
              show-size
              counter
              multiple v-model="form.file"
              label="File input"
            ></v-file-input>
            <v-btn type="submit"    class="primary white--text" outlined tile dense>
               Send 
            </v-btn>
          </v-form>
         </v-col>
        
        
      </v-row>
    </v-container>
    <br><br>
  </div>
</template>
<script> 
    export default {
      components: { 
        },
        data: () => ({ 
           form:{}
        }),
        methods:{
        async submit () {  
          const form = new FormData() 
      form.append('file', this.form.file)
          this.$store.dispatch('order/saveFile', {...this.form}).then( async res =>{ 
            
             console.log(res)
              
            
          }).catch(err=>{
            console.log('errr='+err)
          // this.message = err.data.message  
            
          })
           
        },
          
        }
    }
</script>

