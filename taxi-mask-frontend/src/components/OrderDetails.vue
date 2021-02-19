<template> 
  
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
      <v-btn v-if="showBtn" class="ma-2" small outlined fab color="red"><v-icon small  @click="deleteItem(item)">mdi-delete</v-icon></v-btn>
      <v-btn class="ma-2" title="Commande livrée" v-if="!showBtn && item.status" small outlined fab color="teal"><v-icon small  >mdi-check</v-icon></v-btn>
      <v-btn class="ma-2" title="Commande en cours" v-if="!showBtn && item.complete" small outlined fab color="red"><v-icon small  >mdi-gavel</v-icon></v-btn>
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
                height="100px" width="300px"
                :src="uploadURL+item.photoVoitureFileName"
              > 
              </v-img>

              <v-card-text class="text--primary text-center">
                <div>Voiture</div>
              </v-card-text>

              <div class="text-center"  v-if="showBtn">
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
                height="100px" width="300px"
                :src="uploadURL+item.carteGriseFileName"
              > 
              </v-img>

              <v-card-text class="text--primary text-center">
                <div>Carte grise</div> 
              </v-card-text>

              <div class="text-center"  v-if="showBtn">
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
          <v-chip class="ma-2" label color="teal" v-if="!showBtn" ><b>N° Transaction: {{item.numTransaction}}</b></v-chip><br>
          <v-chip class="ma-2" label ><b>TOIT {{item.toit|upperCase}}</b></v-chip><br>
          <v-chip class="ma-2" label color="pink" text-color="white"><b>Date de passage: {{item.datePassage|formatDate}}</b></v-chip>
          
          
        
        </div> 
      </div>
      </td>
    </template>

  </v-data-table> 
</template>
<script>
    export default {
      props:{
        headers: Array,
        orderList: Array,
        loading:Boolean,
        uploadURL:String,
        showBtn:Boolean
      },
      data: () => ({
      }),
      created(){
        
      },
      methods:{
        deleteItem(item){
          this.$emit("deleteItem", item)
        },
        editPicture(item){
          this.$emit("editPicture", item)
        }
      }
    }
</script>

