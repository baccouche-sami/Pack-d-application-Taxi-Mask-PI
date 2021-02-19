<template>
  <div>
    <v-container>
      <v-row justify="space-around">
        <v-card width="1000">
          <v-img height="200px" src="IMG_4.jpg">
            <v-card-title class="white--text mt-8">
              <h3 class="ml-3" style="margin-top: 10%">
                <h2>
                  <v-icon slot="icon" color="warning" size="36">
                    mdi-format-list-bulleted-square
                  </v-icon>
                  Mes commandes
                </h2>
              </h3>
            </v-card-title>
          </v-img>
          <v-card-text>
            <v-banner single-line>
              <template v-slot:actions>
                <v-btn color="primary" text>
                  <div class="text-center">
                    <v-btn href="/order" class="ma-2" outlined color="info">
                      Nouvelle commande
                    </v-btn>
                  </div>
                </v-btn>
              </template>
            </v-banner>
            <br /><br />
            <v-row>
              <v-col :cols="12" md="9" sm="12">
                <order-details
                  :uploadURL="uploadURL"
                  :loading="loading"
                  :headers="headers"
                  :orderList="orderList"
                  v-on:deleteItem="deleteItem"
                  v-on:editPicture="editPicture"
                  :showBtn="true"
                />
              </v-col>
              <v-col
                :cols="12"
                md="3"
                sm="12"
                style="background-color: lightgray"
              >
                <p class="headline">Récapitulatif</p>
                <p class="overline"></p>
                <v-simple-table>
                  <template v-slot:default>
                    <tbody>
                      <tr>
                        <td>Prix de protection</td>
                        <td class="text-right" style="width: 50px">
                          {{ totalPrix | formatPrice }}
                        </td>
                      </tr>
                      <tr>
                        <td>Total</td>
                        <td class="text-right" style="width: 50px">
                          <b
                            ><v-chip color="green"
                              >{{ totalPrix | formatPrice }}€</v-chip
                            ></b
                          >
                        </td>
                      </tr>
                    </tbody>
                  </template>
                </v-simple-table>
                <br />

                <div class="text-center">
                  <StripePay
                    :amount="amountStripe"
                    v-if="commandeIds"
                    :commandeId="commandeIds"
                  ></StripePay>
                  <!--              <v-btn class="primary white--text mt-5" @click="payerStripe">Payer €170.00</v-btn>  -->
                <br>
                <button class="btn btn-gold bold outline" style="background-color: #FFC20E;" v-on:click="paypalpay">Pay with Paypal</button>
                
                </div>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-row>
    </v-container>

    <v-dialog v-model="dialogDelete" max-width="500px">
      <v-card>
        <v-card-title class="headline"
          >Voulez vous supprimer cette commande?</v-card-title
        >
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
import StripePay from "./StripePay";
import OrderDetails from "./OrderDetails";
import axios from 'axios';
import {SERVER_URL} from '../services/config-server';
import { addScript } from "../services/utils";
export default {
  components: {
    StripePay,
    OrderDetails,
  },
  data: () => ({
    rating: 4.5,
    orderList: [],
    expanded: [],
    showExpand: false,
    totalPrix: 0,
    dialogDelete: false,
    itemDelete: {},
    amountStripe: 0,
    loading: true,
    uploadURL: "",
    commandeIds: "",
    headers: [
      {
        text: "Voiture",
        align: "start",
        sortable: false,
        value: "voiture",
        width: "20%",
      },
      { text: "Date", align: "start", value: "createdAt" },
      { text: "Etiquette", align: "start", value: "etiquette" },
      { text: "Protection", align: "start", value: "typeProtection" },
      { text: "Prix(€)", align: "start", value: "prixProtection" },
      { text: "#", align: "start", value: "action", sortable: false },
    ],
  }),
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
  },
  watch: {},
  created() {
    this.$store.commit("auth/showNavBar", false);
    if (!this.loggedIn) {
      this.$router.push("/login");
      return;
    }
    this.$store.dispatch("order/uploadDir").then((res) => {
      this.uploadURL = res;
    });

    this.$store.dispatch("order/listNoPaye").then(async (res) => {
      console.log(res.data);
      setTimeout(() => {
        this.loading = false;
      }, 2000);
      if (res.status === 200) {
        this.orderList = [...res.data];
        this.totalPrix = this.orderList.reduce(
          (sm, el) => sm + el["prixProtection"],
          0
        );
        this.orderList.forEach((e) => {
          this.commandeIds = e["id"] + "-";
        });
        this.$store.commit("order/updateCard", this.orderList.length);
        localStorage.setItem("listCard", JSON.stringify(this.orderList));
        console.log(this.commandeIds);
        this.amountStripe = this.totalPrix * 100;
        console.log(this.amountStripe);
      }
    });
  },
  methods: {
     async paypalpay(){
            alert('paying with paypal')
            const result = await axios.post("http://localhost:8080/api/test/pay")
            window.location = result.data.url;
          },
    deleteItem(commande) {
      this.dialogDelete = true;
      this.itemDelete = { ...commande };
    },
    closeDelete() {
      this.dialogDelete = false;
      this.itemDelete = {};
    },
    deleteItemConfirm() {
      console.log(this.itemDelete);
      this.$store
        .dispatch("order/delete", this.itemDelete)
        .then((res) => {
          console.log(res);
          this.$toasted.success("Commande supprimée avec succès!").goAway(2000);
          this.orderList = this.orderList.filter(
            (e) => e["id"] !== this.itemDelete.id
          );

          this.itemDelete = {};
          this.dialogDelete = false;
          setTimeout(() => {
            window.location.reload();
          }, 1000);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    editPicture(item) {},
  },
};
</script>

