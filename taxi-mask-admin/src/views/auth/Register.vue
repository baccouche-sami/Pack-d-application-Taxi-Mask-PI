

<template>
    <div class="h-screen flex w-full bg-img vx-row no-gutter items-center justify-center">
        <div class="vx-col sm:w-1/2 md:w-1/2 lg:w-3/4 xl:w-3/5 sm:m-0 m-4">
            <vx-card>
                <div slot="no-body" class="full-page-bg-color">
                    <div class="vx-row no-gutter">
                        <div class="vx-col hidden sm:hidden md:hidden lg:block lg:w-1/2 mx-auto self-center">
                            <img src="@/assets/images/pages/register.jpg" alt="register" class="mx-auto">
                        </div>
                        <div class="vx-col sm:w-full md:w-full lg:w-1/2 mx-auto self-center  d-theme-dark-bg">
                            <div class="px-8 pt-8 register-tabs-container">
                                <div class="vx-card__title mb-4">
                                    <h4 class="mb-4">Create Account</h4>
                                    <p>Fill the below form to create a new account.</p>
                                </div>
                               <div class="clearfix">
                                        <vs-input
                                          v-validate="'required|alpha_dash|min:3'"
                                          data-vv-validate-on="blur"
                                          label-placeholder="Nom d'utilisateur"
                                          name="username"
                                          placeholder="Nom d'utilisateur"
                                          v-model="user.username"
                                          class="w-full" />
                                        <span class="text-danger text-sm">{{ errors.first('username') }}</span>

                                        <vs-input
                                          v-validate="'required|email'"
                                          data-vv-validate-on="blur"
                                          name="email"
                                          type="email"
                                          label-placeholder="Email"
                                          placeholder="Email"
                                          v-model="user.email"
                                          class="w-full mt-6" />
                                        <span class="text-danger text-sm">{{ errors.first('email') }}</span>

                                        <vs-input
                                          ref="password"
                                          type="password"
                                          data-vv-validate-on="blur"
                                          v-validate="'required|min:6|max:10'"
                                          name="password"
                                          label-placeholder="Password"
                                          placeholder="Password"
                                          v-model="user.password"
                                          class="w-full mt-6" />
                                        <span class="text-danger text-sm">{{ errors.first('password') }}</span>

                                        <vs-input
                                          type="password"
                                          v-validate="'min:6|max:10|confirmed:password'"
                                          data-vv-validate-on="blur"
                                          data-vv-as="password"
                                          name="confirm_password"
                                          label-placeholder="Confirm Password"
                                          placeholder="Confirm Password"
                                          v-model="confirm_password"
                                          class="w-full mt-6" />
                                        <span class="text-danger text-sm">{{ errors.first('confirm_password') }}</span>

                                        <vs-checkbox v-model="isTermsConditionAccepted" class="mt-6">I accept the terms & conditions.</vs-checkbox>
                                        <vs-button  type="border" to="/pages/login" class="mt-6">Login</vs-button>
                                        <vs-button class="float-right mt-6" @click="handleRegister" :disabled="!validateForm">Register</vs-button>
                              </div>
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
  name: 'Register',
    data() {
        return {
           user: new User('', '', ''),
            submitted: false,
            successful: false,
            message: '',
            confirm_password: '',
            isTermsConditionAccepted: true
        }
    },
    computed: {
        validateForm() {
            return !this.errors.any() && this.user.username != '' && this.user.email != '' && this.user.password != '' && this.confirm_password != '' && this.isTermsConditionAccepted === true;
        },
        loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
    },
    mounted() {
    if (this.loggedIn) {
      this.$router.push('/page2');
    }
  },
    methods: {
      handleRegister() {
      this.message = '';
      this.submitted = true;

      this.$validator.validate().then(isValid => {
         if (isValid) {
          this.$store.dispatch('auth/register', this.user).then(
            data => {
              this.message = data.message;
              this.successful = true;
            },
            error => {
              this.message =
                (error.response && error.response.data) ||
                error.message ||
                error.toString();
              this.successful = false;
            }
          );
        }
     });
    }
    }
}
</script>

<style lang="scss">
.register-tabs-container {
  min-height: 517px;

  .con-tab {
    padding-bottom: 23px;
  }
}
</style>
