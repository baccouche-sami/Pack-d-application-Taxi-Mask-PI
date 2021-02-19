<template>
  <div>
    <v-carousel hide-delimiters>
      <v-carousel-item
        :src="require('../assets/img/home/totale.jpg')"
      >
        <v-row
          class="fill-height"
          align="center"
          justify="center"
        >
          <div class="display-2 white--text pl-5 pr-5 hidden-sm-only">
            <br>
            <br>
            <strong>Faites vos commandes!</strong><br>
            <strong>Paroi de protection</strong><br>
            <p>#AntiCovid</p>
            </div><br/>
        </v-row>
      </v-carousel-item>
      <v-carousel-item
        :src="require('../assets/img/home/slider1.jpg')"
      >
        <v-row
          class="fill-height"
          align="center"
          justify="center"
        >
          <div class="display-2 white--text pl-5 pr-5 hidden-sm-only"><strong></strong></div><br/>
        </v-row>
      </v-carousel-item>
     

    </v-carousel>
    <v-container style="margin-top:5%">
       <div class="text-center">
                    <v-btn
                      href="/order"
                      class="ma-2"
                      outlined
                      color="info"
                    >
                      Commander
                    </v-btn>
                  </div>
    </v-container>
     
    <v-container>
      <v-row no-gutters>
        <v-col :cols="12">
          <v-card-text
            class=""
            tile
            outlined
          >
            <v-card-title class="subheading ">Caractéristiques de base</v-card-title>
            <v-divider></v-divider>
            <div class="row">
              <div class="col-12 col-md-3 col-sm-6 col-xs-6 text-center">
                <v-hover
                  v-slot:default="{ hover }"
                  open-delay="200"
                >
                  <v-card
                    :elevation="hover ? 16 : 2" style="padding:5%"
                >
                  <img
                    class="white--text align-end" 
                    :src="require('../assets/img/Caracteristiques4.png')"
                  />
                  <div style="text-align:center">
                  <b>Anti-Postillon </b>
                  </div>
                 
  
                </v-card>
                </v-hover>
              </div>
              <div class="col-12 col-md-3 col-sm-6 col-xs-6 text-center" >
                <v-hover
                  v-slot:default="{ hover }" 
                  open-delay="200"
                >
                  <v-card
                    :elevation="hover ? 16 : 2" style="padding:5%"
                  >
                  <img
                    class="white--text align-end" 
                    :src="require('../assets/img/Caracteristiques2.png')"
                  />
                   <div style="text-align:center">
                    <b>Séparation Hermétique </b>
                   </div>
                    
 
                </v-card>
                </v-hover>
              </div>
              <div class="col-12 col-md-3 col-sm-6 col-xs-6 text-center" >
                <v-hover
                  v-slot:default="{ hover }"
                  open-delay="200"
                >
                  <v-card
                    :elevation="hover ? 16 : 2" style="padding:5%"
                  >
                  <img
                    class="white--text align-end" 
                    :src="require('../assets/img/Caracteristiques3.png')"
                  />
                   <div style="text-align:center">
                    <b>Isolation phonique</b>
                  </div>
                   
                   
                </v-card>
                </v-hover>
              </div>
              <div class="col-12 col-md-3 col-sm-6 col-xs-6 text-center" >
                <v-hover
                  v-slot:default="{ hover }"
                  open-delay="200"
                >
                  <v-card
                    :elevation="hover ? 16 : 2" style="padding:5%"
                  >
                  <img
                    class="white--text align-end" 
                    :src="require('../assets/img/Caracteristiques1.png')"
                  />
                   <div style="text-align:center">
                    <b>Anti-Agression</b>
                  </div>
                   
                </v-card>
                </v-hover>
              </div>
            </div>
          </v-card-text>
        </v-col>
      </v-row>
    </v-container>
    <v-card  class="accent" >
      <v-container>
        <v-row no-gutters>
          <v-col class="col-12 col-md-4 col-sm-12">
            <v-row >
              <v-col class="col-12 col-sm-3 pr-4" align="right">
                <v-icon class="display-2">mdi-truck</v-icon>
              </v-col>
              <v-col class="col-12 col-sm-9 pr-4">
                <h3 class="font-weight-light">Paroi de protection</h3>
                              <p class="font-weight-thin">A partir de $360</p>
              </v-col>
            </v-row>
          </v-col>
          <v-col class="col-12 col-md-4 col-sm-12">
            <v-row >
              <v-col class="col-12 col-sm-3 pr-4" align="right">
                <v-icon class="display-2">mdi-cash-usd</v-icon>
              </v-col>
              <v-col  class="col-12 col-sm-9 pr-4">
                <h3 class="font-weight-light">Carte Blue, Master Carte</h3>
                <p class="font-weight-thin">30 Days Money Back Guarantee</p>
              </v-col>
            </v-row>
          </v-col>
          <v-col class="col-12 col-md-4 col-sm-12">
            <v-row>
              <v-col class="col-12 col-sm-3 pr-4" align="right">
                <v-icon class="display-2">mdi-headset</v-icon>
              </v-col>
              <v-col  class="col-12 col-sm-9 pr-4">
                <h3 class="font-weight-light">06 16 12 17 86</h3>
                <p class="font-weight-thin">24/7 Available Support</p>
              </v-col>
            </v-row>
          </v-col>
        </v-row>
      </v-container>
    </v-card>
  </div>
</template>

<script>
import UserService from '../services/user.service';
    export default {
        name: 'Home',
        data () {
            return {
                content: '',
                activeBtn: 1, 
                colors: [
                    'indigo',
                    'warning',
                    'pink darken-2',
                    'red lighten-1',
                    'deep-purple accent-4',
                ],
            }
        },
        mounted() {
          UserService.getPublicContent().then(
            response => {
              this.content = response.data;
            },
            error => {
              this.content =
                (error.response && error.response.data) ||
                error.message ||
                error.toString();
            }
          );
        }
    }
</script>
<style>
  .v-card--reveal {
    align-items: center;
    bottom: 0;
    justify-content: center;
    opacity: .5;
    position: absolute;
    width: 100%;
  }
</style>

