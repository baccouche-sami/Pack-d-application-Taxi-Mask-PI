
<template>
  <div class="h-screen flex w-full bg-img vx-row no-gutter items-center justify-center" id="page-login">
    <div class="vx-col sm:w-1/2 md:w-1/2 lg:w-3/4 xl:w-3/5 sm:m-0 m-4">
      <vx-card>
        <div slot="no-body" class="full-page-bg-color">

          <div class="vx-row no-gutter justify-center items-center">

            <div class="vx-col hidden lg:block lg:w-1/2">
              <img src="@/assets/images/pages/login.png" alt="login" class="mx-auto">
            </div>


            <div class="vx-col sm:w-full md:w-full lg:w-1/2 d-theme-dark-bg">
              <div class="p-8 login-tabs-container">

                <div class="vx-card__title mb-4">
                  <h4 class="mb-4">Authentification</h4>
                  <p>Taxi Mask </p>
                </div>
                  <vs-input
                    v-validate="'required|min:3'"
                    data-vv-validate-on="blur"
                    name="username"
                    icon-no-border
                    icon="icon icon-user"
                    icon-pack="feather"
                    label-placeholder="Nom d'utilisateur"
                    v-model="user.username"
                    class="w-full"/>
                  <span class="text-danger text-sm">{{ errors.first('username') }}</span>

                  <vs-input
                    data-vv-validate-on="blur"
                    v-validate="'required|min:6|max:10'"
                    type="password"
                    name="password"
                    icon-no-border
                    icon="icon icon-lock"
                    icon-pack="feather"
                    label-placeholder="Mot de passe"
                    v-model="user.password"
                    class="w-full mt-6" />
                  <span class="text-danger text-sm">{{ errors.first('password') }}</span>

                  <div class="flex flex-wrap justify-between my-5">
                      <vs-checkbox v-model="checkbox_remember_me" class="mb-3">Se souvenir de moi</vs-checkbox>
                      <router-link to="/pages/forgot-password">Oublié votre mot de passe ?</router-link>
                  </div>

                  <vs-button class="float-right" :disabled="!validateForm" @click="handleLogin" >Connexion</vs-button>
                  <span v-show="loading" class="spinner-border spinner-border-sm"></span>
<br><br><br>
                </div>

              </div>
            </div>
          </div>
      </vx-card>
    </div>
  </div>
</template>
<script>
import User from '../../models/user';

export default {
   name: 'Login',
  data() {
    return {
      email: 'admin@admin.com',
      password: 'adminadmin',
      user: new User('', ''),
      loading: false,
      message: '',
      checkbox_remember_me: false
    }
  },
  computed: {
    validateForm() {
      return !this.errors.any() && this.email != '' && this.password != '';
    },
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
  },
  created() {
    if (this.loggedIn) {
      this.$router.push('/');
    }
  },
  methods: {
    checkLogin() {
      // If user is already logged in notify
      if (this.loggedIn) {

        // Close animation if passed as payload
        // this.$vs.loading.close()

        this.$vs.notify({
          title: 'Login Attempt',
          text: 'You are already logged in!',
          iconPack: 'feather',
          icon: 'icon-alert-circle',
          color: 'warning'
        })

        return false
      }
      return true
    },
     handleLogin() {
      this.loading = true;
      this.$validator.validateAll().then(isValid => {
        if (!isValid) {
          this.loading = false;
          return;
        }

        if (this.user.username && this.user.password) {
          this.$store.dispatch('auth/login', this.user).then(
            () => {
              this.$router.push('/admin');
            },
            error => {
              this.loading = false;
                this.$vs.notify({
                    title: 'Login Error',
                    text: "Nom d'utilisateur ou Mot de passe incorrect " ,
                    iconPack: 'feather',
                    icon: 'icon-alert-circle',
                    color: 'warning'
                  })
            }
          );
        }
      });
    },
    registerUser() {
      if (!this.checkLogin()) return
      this.$router.push('/pages/register').catch(() => {})
    }
    }

  };


</script>

