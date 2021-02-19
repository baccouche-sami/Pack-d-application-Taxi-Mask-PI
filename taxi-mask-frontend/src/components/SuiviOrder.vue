<template>
  <div>
    <v-container>

      <v-banner
        single-line 
      >
        <v-icon
          slot="icon"
          color="warning"
          size="36"
        >
          mdi-format-list-bulleted-square
        </v-icon>
          <h2>Mes commandes</h2>
        <template v-slot:actions>
          <v-btn
            color="primary"
            text
          >
           <div class="text-center">
           </div>
          </v-btn>
        </template>
      </v-banner>
      <br><br>
      <v-row>
        <v-col :cols="12" md="9" sm="12" >
          <v-data-table
            :headers="headers"
            :items="orderList"
            :loading="loading"
            loading-text="Loading..." 
            show-expand
            striped
          >
          <template #item.voiture="{item}"> 
            <v-list-item >
              <v-list-item-avatar>
                <v-img :src="uploadURL+item.photoVoitureFileName"></v-img>
              </v-list-item-avatar>  
              <v-list-item-content>
                <v-list-item-title >{{item.voiture.immatriculation}}</v-list-item-title>
                <v-list-item-subtitle>{{item.voiture.marque}}</v-list-item-subtitle>
                <v-list-item-subtitle>{{item.voiture.modele}}</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
          </template>
          <template #item.createdAt="{value}">
            {{value|formatDate}}
          </template>
          <template #item.etiquette="{value}">
            {{value}}
          </template>
          <template #item.typeProtection="{value}">
            {{value}}
          </template>
          <template #item.prixProtection="{value}">
            {{value|formatPrice}}
          </template>
          <template #item.action="{item}">
            <v-btn class="ma-2" small outlined fab color="red"><v-icon small  @click="deleteItem(item)">mdi-delete</v-icon></v-btn>
          </template>
          <template #expanded-item="{headers, item}">
            <td :colspan="headers.length">
              <div class="row">
              <div class="col-md-3 col-sm-4 col-xs-12 text-center">
                <v-hover
                  v-slot:default="{ hover }"
                  open-delay="200"
                >
                  <v-card
                    :elevation="hover ? 16 : 2"
                  >
                    <v-img
                      class="white--text align-end"
                      height="200px"
                      :src="uploadURL+item.photoVoitureFileName"
                    > 
                    </v-img>

                    <v-card-text class="text--primary text-center">
                      <div>Voiture</div>
                    </v-card-text>

                    <div class="text-center">
                      <v-btn
                        class="ma-2"
                        outlined
                        color="teal"
                      >
                        <v-icon small  @click="editPicture(item)">mdi-pencil</v-icon>
                      </v-btn>
                    </div>
                  </v-card>
                </v-hover>
              </div>
              <div class="col-md-3 col-sm-4 col-xs-12 text-center" >
                <v-hover
                  v-slot:default="{ hover }"
                  open-delay="200"
                >
                  <v-card
                    :elevation="hover ? 16 : 2"
                  >
                    <v-img
                      class="white--text align-end"
                      height="200px"
                      :src="uploadURL+item.carteGriseFileName"
                    > 
                    </v-img>

                    <v-card-text class="text--primary text-center">
                      <div>Carte grise</div> 
                    </v-card-text>

                    <div class="text-center">
                      <v-btn
                        class="ma-2"
                        outlined
                        color="teal"
                      >
                        <v-icon small  @click="editPicture(item)">mdi-pencil</v-icon>
                      </v-btn>
                    </div>
                  </v-card>
                </v-hover>
              </div>
              <div class="col-md-6 col-sm-4 col-xs-12" > 
                <v-chip class="ma-2" label ><b>TOIT {{item.toit|upperCase}}</b></v-chip><br>
                <v-chip class="ma-2" label color="pink" text-color="white"><b>Date de passage: {{item.datePassage|formatDate}}</b></v-chip>
              
              </div> 
            </div>
            </td>
          </template>

        </v-data-table>
        
        </v-col>
        <v-col :cols="12" md="3" sm="12" style="background-color: lightgray">
          <p class="headline">Récapitulatif</p>
          <p class="overline">
          </p>
          <v-simple-table>
            <template v-slot:default>
              <tbody>
              <tr>
                <td>Prix de protection</td>
                <td class="text-right" style="width: 50px;">{{totalPrix|formatPrice}}</td>
              </tr> 
              <tr>
                <td>Total</td> 
                <td class="text-right" style="width: 50px;"><b><v-chip color="green">{{totalPrix|formatPrice}}€</v-chip></b></td>
              </tr>
              </tbody>
            </template>
          </v-simple-table>
          <br>
          
        </v-col>
      </v-row>
    </v-container>
   
    <v-dialog v-model="dialogDelete" max-width="500px">
        <v-card>
          <v-card-title class="headline">Voulez vous supprimer cette commande?</v-card-title>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text @click="closeDelete">Annuler</v-btn>
            <v-btn color="red" text @click="deleteItemConfirm">Ok</v-btn>
            <v-spacer></v-spacer>
          </v-card-actions>
        </v-card>
      </v-dialog>
  </div>
</template>   
<script>  
     
    export default {
        components:{   
        },
        data: () => ({
            rating: 4.5,
            orderList: [],
            expanded: [],
            showExpand: false,
            totalPrix:0,
            dialogDelete: false,
            itemDelete:{},
            amountStripe:0,
            loading:true,
            uploadURL:'',
            commandeIds:'',
            headers: [
              {
                text: 'Voiture',
                align: 'start',
                sortable: false,
                value: 'voiture',
                 width: "20%"
              },
              { text: 'Date', align: 'start', value: 'createdAt' },
              { text: 'Etiquette', align: 'start', value: 'etiquette' },
              { text: 'Protection', align: 'start', value: 'typeProtection' },
              { text: 'Prix(€)', align: 'start', value: 'prixProtection' },
              { text: '#',  align: 'start', value:'action', sortable: false,},
            ],
            

        }),
        computed: {
          loggedIn() {
            return this.$store.state.auth.status.loggedIn;
          }, 
          
        }, 
        watch:{
         
        },
        created(){
          if (!this.loggedIn) {
            this.$router.push('/login');
            return
          }
          this.$store.dispatch('order/uploadDir').then(res=>{
            this.uploadURL= res
          })

          this.$store.dispatch('order/listNoPaye').then( async res =>{ 
            console.log(res.data)
            setTimeout(() => {
              this.loading = false 
            }, 2000); 
            if(res.status===200){  
              this.orderList = [...res.data]
              this.totalPrix = this.orderList.reduce((sm,el)=>sm+el['prixProtection'],0)
              this.orderList.forEach(e =>{
                this.commandeIds =e['id']+'-'
              })
              this.$store.commit('order/updateCard', this.orderList.length)
              console.log(this.commandeIds)
              this.amountStripe=this.totalPrix*100
              console.log(this.amountStripe)
            }
          })

        },
        methods:{
           
          deleteItem(commande){
              this.dialogDelete = true;
              this.itemDelete = {...commande}
          },
          closeDelete(){
            this.dialogDelete=false
            this.itemDelete={}
          },
          deleteItemConfirm(){
            console.log(this.itemDelete)
            this.$store.dispatch('order/delete', this.itemDelete).then(res=>{
              console.log(res)
              this.$toasted.success('Commande supprimée avec succès!').goAway(2000)
              this.orderList = this.orderList.filter(e=>e['id']!== this.itemDelete.id)
              this.itemDelete ={}
              this.dialogDelete = false 
            }).catch(err=>{
              console.log(err)
            })

          },
          editPicture(item){

          },
        
        },
    }
</script>

