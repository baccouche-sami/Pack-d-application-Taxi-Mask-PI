<template>
  <div>
    <v-container>

      <p class="display-1 font-weight-light	text-center pa-4">Authentification</p>
      <v-row>
         <v-col class="col-4"></v-col>
         <v-col class="col-4" style="box-shadow: 0 0 0 1px #dbdbdb;padding: 15px;" > 
            <v-form
              ref="form"  @submit.prevent="submit"
              v-model="valid"
              lazy-validation
            >
          
            <v-text-field
              v-model="name" 
              :rules="rules"
              label="Nom d'utilisateur"
              required
              prepend-icon="mdi-account"
            ></v-text-field>
            <v-text-field
              v-model="password" 
              :rules="rules"
              type="password"
              label="Mot de passe"
              required
               prepend-icon="mdi-lock"
            ></v-text-field>
    

            <v-checkbox
              v-model="checkbox" 
              label="Se souvenir de moi"
              required
            ></v-checkbox>

            <v-btn style="float: right;"
              :disabled="!valid"
              color="success"
              class="mr-4"
              @click="login"
            >
              Se connecter
            </v-btn>
          </v-form>
         </v-col>
         <v-col class="col-4">
           <GoogleLogin :params="params" :renderParams="renderParams" :onSuccess="onSuccess" :onFailure="onFailure"></GoogleLogin>
         </v-col>
        
      </v-row>
    </v-container>
    <br><br>
  </div>
</template>
<script>
import GoogleLogin from 'vue-google-login';
    export default {
      components: {
            GoogleLogin
        },
        data: () => ({ 
          valid: true,
          checkbox:false,
          name:'',
          rules: [
            v => !!v || 'Required.', 
          ],
          password:'',
      // client_id is the only required property but you can add several more params, full list down bellow on the Auth api section
          params: {
              client_id: "xxxxxx"
          },
          // only needed if you want to render the button with the google ui
          renderParams: {
              width: 250,
              height: 50,
              longtitle: true
          },
          breadcrums: [
              {
                  text: 'Home',
                  disabled: false,
                  href: 'breadcrumbs_home',
              },
              {
                  text: 'Login',
                  disabled: true,
                  href: 'breadcrumbs_login',
              }, 
          ],

        }),
        methods:{
          login(){

          },
          onSuccess(googleUser) {
            console.log(googleUser);

            // This only gets the user information: id, name, imageUrl and email
            console.log(googleUser.getBasicProfile());
          },
          onFailure(error){
            console.log(error);
          }
        }
    }
</script>

