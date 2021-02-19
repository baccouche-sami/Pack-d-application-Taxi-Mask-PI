<template>
  <v-app id="inspire">
    <v-app-bar
      :clipped-left="$vuetify.breakpoint.lgAndUp"
      app
      color="primary"
      dark
    >
<!--      <v-app-bar-nav-icon @click.stop="drawer = !drawer" />-->

      <v-toolbar-title
        style="width: 350px"
      >
        <a href="/" class="white--text" style="text-decoration: none"> <img style="width:50%" src="lg.png" alt="Logo"></a>
      </v-toolbar-title>
      <!-- <v-text-field
        flat
        solo-inverted
        hide-details
        prepend-inner-icon="mdi-magnify"
        label="Search"
        class="hidden-sm-and-down pl-10 ml-4"
      /> --> 
       
      <v-spacer />
      <v-btn v-if="!$store.state.auth.status.loggedIn" icon href="/login">
        <v-icon>mdi-account-circle</v-icon>
      </v-btn>

      <v-menu v-else open-on-hover offset-y>
          <template v-slot:activator="{ on }">
            <v-btn v-on="on">
             <v-icon>mdi-account-circle</v-icon>
            </v-btn>
          </template>

            
            <v-card
              elevation="12"
              width="256"
            >
            
              <v-list
                dense
                rounded
              >
                <v-list-item 
                  link
                >
                  <v-list-item-icon>
                    <v-icon> {{$store.state.auth.user.username}}  </v-icon>
                  </v-list-item-icon>

                  <v-list-item-content>
                    <v-list-item-title> {{$store.state.auth.user.email}}</v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
                <v-divider inset style="margin-bottom:0px;margin-top:0px"></v-divider>
                <v-list-item 
                  link
                >
                  <v-list-item-icon> 
                    <a href="/mes-commandes" @click="activeBtnOn(0)" class="ph-low"  >
                      <span>Mes commandes</span>
                    </a> 
                  </v-list-item-icon>

                  
                </v-list-item>
                 <v-divider inset style="margin-bottom:0px;margin-top:0px"></v-divider>
                <v-list-item 
                  link
                >
                  <v-list-item-icon>
                    <a href="/profile" @click="activeBtnOn(1)" class="ph-low" >
                      <span>Profil</span>
                    </a> 
                  </v-list-item-icon> 
                </v-list-item>
                <v-divider inset style="margin-bottom:0px;margin-top:0px"></v-divider>
                <div style="text-align:center">  <v-list-item style="margin-top:20px;margin-bottom:20px"
                  link
                >
                
                    <v-list-item-icon >
                    <a href="/profile" @click="logOut"   >
                      <span >Se deconnecter</span>
                    </a> 
                  </v-list-item-icon> 
                 
                  
                </v-list-item> </div>
              </v-list> 
            </v-card>  
        </v-menu> 
        
      <v-btn  href="/mes-commandes" icon>
        <v-badge
          :content="$store.state.order.cardContent"
          :value="$store.state.order.cardContent"
          color="green"
          overlap
        >  
          <v-icon>mdi-cart</v-icon>
        </v-badge>
      </v-btn>
      <v-menu  open-on-hover offset-y> 
          <template v-slot:activator="{ on }">
            <v-btn v-on="on" >
              <span><img title="Se connecter" :src="require('../assets/img/fr.png')"/></span>
            </v-btn>
          </template>
          <v-card
            class="mx-auto"
            max-width="344"
            outlined
          >

            <v-list-item  
              href="#"
            >
              <v-list-item-title><img :src="require('../assets/img/fr.png')"/></v-list-item-title>
            </v-list-item>
            <v-list-item  
              href="#"
            >
              <v-list-item-title><img :src="require('../assets/img/us.png')"/></v-list-item-title>
            </v-list-item>

          </v-card>
        </v-menu>
    </v-app-bar>
    <v-main v-if="$store.state.auth.showBar">
      <v-bottom-navigation
        :value="activeBtn"
        color="primary"
        horizontal
      >

        <nav class="horizontal-nav full-width horizontalNav-notprocessed">
          <ul>
            <li><a href="/"  @click="activeBtnOn(0)">Accueil</a></li>
            <li><a href="/order"  @click="activeBtnOn(1)">Commander</a></li>
            <li><a href="#">Nos réalisations</a></li>
            <li><a href="#contact">Nous contacter</a></li>
          </ul>
        </nav>

        <!-- <v-btn href="/" @click="activeBtnOn(0)"  class="v-btn" >
          <span>Home</span>
        </v-btn> 
        <v-btn href="/order" @click="activeBtnOn(1)"  class="v-btn">
          <span>Commander</span>
        </v-btn>
         
        <v-btn href="/" @click="activeBtnOn(2)">
          <span>Nous contacter</span>
        </v-btn> -->
       

      </v-bottom-navigation>
    </v-main>

      <router-view/>
    <v-footer
      :padless="true"
    >
      <v-card
        flat
        tile
        width="100%" id="#contact"
        class="secondary white--text text-center"
      >
        <v-card-text>
          <v-btn href="/" style="text-decoration:none"
            class="mx-4 white--text"
            icon
          >
            <v-icon size="24px">mdi-home</v-icon>
          </v-btn>
          <v-btn
            class="mx-4 white--text"
            icon
          >
            <v-icon size="24px">mdi-email</v-icon>
          </v-btn>
          <v-btn
            class="mx-4 white--text"
            icon
          >
            <v-icon size="24px">mdi-calendar</v-icon>
          </v-btn> 

        </v-card-text>

        <v-card-text class="white--text pt-0">
         </v-card-text>

        <v-divider></v-divider>

        <v-card-text class="white--text">
          {{ new Date().getFullYear() }} — <strong>Les 4mousqInfos</strong>
        </v-card-text>
      </v-card>
    </v-footer>
  </v-app>
</template>
<style>
  .v-item-group.v-bottom-navigation .v-btn.v-btn--active{
    background-color:#F7F7F7!important
  }
  .v-application--is-ltr .v-divider--inset:not(.v-divider--vertical) {
    margin-left: 0px;
  }
  .v-divider--inset:not(.v-divider--vertical) {
    max-width: calc(100% - 0px);
  }
  .ph-low{
    text-transform:none; 
    text-decoration:none;
  }

  
  .horizontal-nav {
  background: #efefef;
  border-radius: 6px;
}
.horizontal-nav ul {
  background: #128F9A;
  float: left;
  text-align: center;
  border-radius: 6px;
  border: 1px solid #0e7079;
  margin-top: 5px;
  list-style: none;
  padding: 0px;
}
.horizontal-nav ul li {
  float: left;
  border-left: 1px solid #0e7079;
}
.horizontal-nav ul li:first-child {
  border-left: 0 none;
}
.horizontal-nav ul li a {
  display: block;
  padding: 10px 20px;
  color: #fff;
  border-top: 1px solid rgba(255,255,255, 0.25);
  border-left: 1px solid rgba(255,255,255, 0.25);
}
.horizontal-nav ul li:first-child a {
  border-left: 0 none;
}
.horizontal-nav ul li a:hover {
  background: #12808a;
}
.horizontal-nav ul li:first-child a {
  border-top-left-radius: 6px;
  border-bottom-left-radius: 6px;
}
.horizontal-nav ul li:last-child a {
  border-top-right-radius: 6px;
  border-bottom-right-radius: 6px;
}
</style>
<script> 
    export default {
        data () {
            return { 
                activeBtn: 0, 
            }
        }, 
        async created(){
          console.log(this.$store.state.auth.showBar)
          this.activeBtnOn(this.$route.path)
        },
        updated(){ 
           this.activeBtnOn(this.$route.path)
        },
        computed:{
          selectedBtn(){
            return ''
          } 
        },
        methods:{
          on(){
            
          },
          activeBtnOn(path){
            switch(path){
              case '/':
                this.activeBtn=0
                break
              case '/order':
                this.activeBtn=1  
                break
              default:
                this.activeBtn=1
                break  
            }
          },
         async logOut(){ 
           await this.$store.dispatch('auth/logout');
           await this.$store.commit('order/updateCard',0)
            this.$router.push('/login'); 
          }

        }
    }
</script>
