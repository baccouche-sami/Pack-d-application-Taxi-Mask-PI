<template>
  <div class="formPh" style="background: #f8f8f8;">

    <v-container>
    <v-row justify="space-around">
      <v-card width="1000">
        <v-img
          height="200px"
          src="IMG_4.jpg"
        >
         
          
          <v-card-title class="white--text mt-8">
           
            <h3 class="ml-3" style="margin-top:10%">
              Faites votre commande
            </h3>
          </v-card-title>
        </v-img>
        <v-form enctype="multipart/form-data" @submit.prevent="submit" onsubmit="return false;" ref="form" lazy-validation > 
          <v-card-text>
            <div class="font-weight-bold ml-8 mb-2">
              <p></p>
              <p class="text-grey">
                 
                <a href="/" class="btn btn-dark" style="color:white"> 
                <v-icon
                    dark
                    left
                  >
                    mdi-arrow-left
                  </v-icon>
                  Retour
                </a><br>
                <b>C</b>ommande en quelques clics, délai de production ultra rapide et montage parfait. <strong> #AntiCovid</strong></p>
            
            </div>

            <v-timeline
              align-top
              dense
            >
                <v-timeline-item
                
                color="deep-purple lighten-1"
                small
              >
                <div>
                  <div class="font-weight-black">
                    <h4><strong>Information sur le véhicule </strong></h4>
                  </div>
                  <div>
                    <v-card ref="form">
                      <v-card-text>
                          <v-row>
                            <v-col
                              cols="12"
                              sm="6"
                            >
                              <p class="title">Marque</p>
                                <v-select   v-model="formu.voiture.marque"
                                    :items="marques" 
                                    item-text="nom"
                                    item-value="nom"
                                    :rules="[v=>!!v || 'Veuillez choisir un modèle']"
                                    label="Choisissez la marque"
                                    solo
                                  ></v-select>
                            </v-col>
                            <v-col
                              cols="12"
                              sm="6"
                            >
                              <p class="title">Modèle</p> 
                              <v-text-field  placeholder="Modèle"
                                :rules="[v=>!!v || 'Veuillez choisir un modèle']" 
                                v-model="formu.voiture.modele"
                                hide-details="auto"
                              ></v-text-field>
                            </v-col>

                            <v-col
                              cols="12"
                              sm="6"
                            >
                              <p class="title">Immatriculation</p> 
                              <v-text-field placeholder="Immatriculation"
                                :rules="[v => !!v || 'Veuillez saisir le n° matricule du véhicule']"
                                v-model="formu.voiture.immatriculation"
                                hide-details="auto"
                              ></v-text-field>
                            </v-col>
                            <v-col
                              cols="12"
                              sm="6"
                            >
                              <p class="title">Date de la première immatriculation</p> 
                              <v-menu 
                                ref="menu"
                                :close-on-content-click="false"
                                transition="scale-transition"
                                offset-y
                                min-width="290px"
                              >
                                <template v-slot:activator="{ on, attrs }">
                                  <v-text-field 
                                    v-model="date" 
                                    prepend-icon="mdi-calendar"
                                    readonly
                                    v-bind="attrs"
                                    v-on="on"
                                  ></v-text-field>
                                </template>
                                <v-date-picker
                                  ref="picker"
                                  v-model="date"
                                  :max="new Date().toISOString().substr(0, 10)"
                                  min="1950-01-01" 
                                ></v-date-picker>
                              </v-menu>
                            </v-col>
                            <v-col
                              cols="12"
                              sm="12"
                            >
                              <p class="title">Toit</p>
                              <v-radio-group :rules="[v => !!v || 'Choisissez le type de toit']"  required v-model="formu.toit"  row>
                                <span v-for="(item,id) in toits" :key="id">
                                  <v-radio :label="item.label" :value="item.id"></v-radio> 
                                </span>
                              </v-radio-group> 
                            </v-col>
                            <v-col cols="12"
                              sm="6"> 
                              
                                <p class="title">Photo de la carte grise</p>
                                <v-file-input style="margin-top: -30px;"
                                  show-size v-model="formu.file" 
                                  truncate-length="15"
                                ></v-file-input>  
                            </v-col>
                            <v-col cols="12"
                              sm="6"> 
                              <p class="title">Photo de la voiture</p>
                              <v-file-input v-model="formu.file2" style="margin-top: -30px;"
                                show-size
                                truncate-length="15"
                              ></v-file-input>  
                            </v-col>

                          </v-row>  
                      </v-card-text>
                    </v-card>
                  </div>
                </div>
              </v-timeline-item>
                <v-timeline-item
                    color="green"
                    small
                  >
                <div>
                  <div class="font-weight-black">
                    <h4><strong>Information sur le Paroi </strong> </h4>
                  </div>
                  <div>
                    <v-card ref="form">
                    <v-card-text>
                        <v-row>  
                          <v-col cols="12" sm="12">
                            <p class="title">Type de protection</p>
                            <v-radio-group required :rules="[v => !!v || 'Choisissez un type de protection']" v-model="formu.typeProtection" v-for="(item,id) in protections" :key="id">
                              <v-radio :title="item.title" :label="item.label" :value="item"></v-radio> 
                            </v-radio-group> 
                          </v-col>
                        
                        <v-col cols="12" sm="12">
                          <p class="title">Trappe</p>
                          <v-radio-group  required
                          :rules="[v => !!v || 'Choisissez un trappe']"
                          v-model="formu.trappe" row>
                            <v-radio label="Avec trappe" value="w-trappe"></v-radio>
                            <v-radio label="Sans trappe" value="s-trappe"></v-radio> 
                          </v-radio-group>
                        </v-col>
                        <v-col cols="12" sm="12">
                          <p class="title">Etiquette</p>
                          <v-radio-group :rules="[v => !!v || 'Choisissez une étiquette']"  required v-model="formu.etiquette" row>
                            <span v-for="(item,id) in etiquettes" :key="id">
                              <v-radio :label="item.label" :value="item"></v-radio>
                            </span> 
                          </v-radio-group>
                        </v-col>
                      </v-row>  
                    </v-card-text>
                    </v-card>
                  </div>
                </div> 
                </v-timeline-item>
                <v-timeline-item
                
                color="yellow lighten-1"
                small
              >
                <div>
                  <div class="font-weight-black">
                    <h4><strong>Prise de Rendez-vous </strong> </h4>
                  </div>
                  <div>
                    <v-card ref="form">
                    <v-card-text>
                        <v-row>  
                          <v-col cols="12" sm="12">
                            <p class="title">Date de passage</p>
                            <v-row>
                              <div class="col-6">
                                <v-date-picker v-model="formu.datePassage"  
                                  
                                  :min="new Date().toISOString().substr(0, 10)">
                                  </v-date-picker>
                              </div>
                              <div class="col-6">
                                <v-time-picker
                                  format="ampm" v-model="formu.timePassage"
                                  ampm-in-title
                                  
                                ></v-time-picker>
                              </div>
                            </v-row>
                          </v-col>
                          <v-col cols="12" sm="12">
                            <p>NB: Durée de l'intervention 20 minutes</p>
                          </v-col>
                        </v-row> 
                    </v-card-text>
                    </v-card>
                  </div>
                    
                </div> 
              
                </v-timeline-item> 
                
            </v-timeline>
            
          </v-card-text>
          <v-card ref="form">
            <v-card-text style="text-align:center">
              <v-btn type="submit" :disabled="loading"  class="primary white--text" outlined tile dense>
              <v-icon>mdi-cart</v-icon>Réserver maintenant</v-btn>
            </v-card-text>
          </v-card>
        </v-form>
      </v-card>
      
    </v-row>
  </v-container> 
  
   </div>
</template>
<script> 
import {CARD_LIST} from '../services/config-server';
import {getMarques} from '../services/marque';
    export default {
      components:{ 
      },
        data: () => ({ 
          messages: [
        {
          from: 'Informations sur le véhicule',
          color: 'deep-purple lighten-1',
        },
        {
          from: 'Informations sur le Paroi', 
          color: 'green',
        },
        {
          from: 'Prise de Rendez-vous', 
          color: 'deep-purple lighten-1',
        },
      ],
            message:'',
            isLoading:false,
            breadcrums: [
                {
                  text: 'Home',
                  disabled: false,
                  href: '/',
                },
                {
                  text: 'Commander',
                  disabled: true,
                  href: 'product',
                } 
            ],
            item: 1,
            protections: [
                {
                  id: 'partielle',
                  prix:180,
                  label: 'Partielle : (€180,00)', 
                  title: 'Paroi de séparation partielle sur mesure , allant du niveau d\'accoudoir jusqu\'à plafond avec fixation sur les montant ( siège en mouvement libre) (€180,00)', 
                },
                {
                  id: 'totale',
                  prix:360,
                  label: 'Totale : (€360,00)', 
                  title: 'Paroi de séparation hermétique sur mesure, en poli carbonate de 2 mm d\'épaisseur (€360,00)', 
                },
                 
            ],
            toits: [
                {
                  id: 'sans',
                  label: 'Sans', 
                },
                {
                  id: 'ouvrant',
                  label: 'Ouvrant'
                },
                {
                  id: 'panoramique',
                  label: 'Panoramique'
                },
                 
            ],
            etiquettes: [
                {
                  id: 'SANS',
                  label: 'Sans (€0.00)', 
                  prix:0
                },
                {
                  id: 'M',
                  label: 'M(€12.00)',
                  prix:12
                },
                {
                  id: 'XL',
                  label: 'XL(€12.00)',
                  prix:12
                },
                 
            ],
            marques:[], 
            menu: false,
            date: null,
            picker: new Date().toISOString().substr(0, 10),
            formu:{voiture:{},photoCarteGrise:"",photoVoiture:""},
            loading:false
        }),
      watch: {
        menu (val) {
          val && setTimeout(() => (this.$refs.picker.activePicker = 'YEAR'))
        },
        
      }, 
      async created(){ 
        this.$store.commit('auth/showNavBar',false)
         const result = await getMarques();
        if(result.status ===200){
          this.marques = [... result.data]
        }else{
          this.$toasted.error(result.message).goAway(2000)
        }

      },
      methods: { 
        async submit () {   
          console.log(this.formu)
          this.loading = true 
          this.formu.voiture.datePreimma = this.date
          if(!this.$refs.form.validate()){
            this.loading = false
            return
          } 
          this.formu.prixProtection = parseFloat(this.formu.typeProtection.prix) + 
          parseFloat(this.formu.etiquette.prix)
          this.formu.typeProtection = this.formu.typeProtection.id
          const form = new FormData()
          const voiture={...this.formu.voiture}
          form.append('etiquette', this.formu.etiquette.id)
          form.append('trappe', this.formu.trappe)
          form.append('prixProtection', this.formu.prixProtection) 
          form.append('typeProtection', this.formu.typeProtection) 
          form.append('carteGrise', this.formu.file)
          form.append('photoVoiture', this.formu.file2)
          form.append('toit', this.formu.toit)
          form.append('dateImmatriculation', new Date(this.date))
          form.append('datePassage', new Date(this.formu.datePassage+' '+this.formu.timePassage))
          form.append('modele', voiture.modele)
          form.append('marque',voiture.marque)
          form.append('immatriculation',voiture.immatriculation)
          this.$store.dispatch('order/save', form).then( async res =>{ 
            
             console.log(res)
            if(res.status===200){ 
              this.$toasted.success('Commande enregistrée avec succès!').goAway(1200)
              setTimeout(() => {
                 this.loading=false
              }, 1000);
              if(!this.$store.state.auth.status.loggedIn){
                let ids = res.data
                console.log(res)
                let cards = localStorage.getItem(CARD_LIST)
                if(cards!==null && cards!=='undefined'){
                  ids = ids+' '+cards
                  await localStorage.setItem(CARD_LIST,ids);
                }else{
                  await localStorage.setItem(CARD_LIST,ids);
                }
                setTimeout(() => {
                    this.$router.push('/login') 
                }, 1500);
              }else{
                setTimeout(() => { 
                    this.$router.push('/mes-commandes')
                }, 1500);
              } 
            }else{
              this.$toasted.error(res.data.message).goAway(3000)
            }
            
          }).catch(err=>{
            console.log(err+'eeeeeeeee')
          // this.message = err.data.message 
            this.$toasted.error(err.message).goAway(5000)
            setTimeout(() => {
              this.loading =false
            }, 1000); 
          
          })
           
        },
        clear () { 
          this.formu={}
          this.$refs.form.reset()
        },

      },
    }
</script> 
<style scoped>
  .formPh{
    font-family: "Montserrat", Helvetica, Arial, sans-serif;
    font-weight: 500;
    line-height: 1.2;
    color: #2c2c2c;
  }
</style>
