<template>
  <v-container>
    <v-row justify="space-around">
      <v-card width="1000">
        <v-img height="200px" src="IMG_4.jpg">
          <v-card-title class="white--text mt-8">
            <h2 class="ml-3" style="margin-top: 10%">
              <v-icon slot="icon" color="warning" size="36">
                mdi-account
              </v-icon>
              Espace client
            </h2>
          </v-card-title>
        </v-img>
        <v-card-text>
          <br /><br />
          <v-row justify="center">
            <v-expansion-panels popout>
              <v-expansion-panel hide-actions>
                <v-expansion-panel-header>
                  <v-row align="center" class="spacer" no-gutters>
                    <v-col cols="4" sm="2" md="1">
                      <v-avatar size="36px">
                        <img
                          alt="Avatar"
                          id="profile-img"
                          src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
                          class="profile-img-card"
                        />
                      </v-avatar>
                    </v-col>

                    <v-col class="hidden-xs-only" sm="5" md="3">
                      <strong
                        v-html="(title = 'Mes informations personnelles')"
                      ></strong>
                      <span class="grey--text"> </span>
                    </v-col>

                    <v-col class="grey--text text-truncate hidden-sm-and-down">
                      {{ user.username }}
                    </v-col>
                  </v-row>
                </v-expansion-panel-header>

                <v-expansion-panel-content>
                  <v-divider></v-divider>

                  <v-card max-width="644" outlined>
                    <v-list-item three-line>
                      <v-list-item-content>
                        <div class="overline mb-4"></div>
                        <v-list-item-title class="headline mb-1">
                        </v-list-item-title>
                        <v-list-item-subtitle></v-list-item-subtitle>
                      </v-list-item-content>

                      <v-list-item-avatar tile size="80" color="grey">
                        <img
                          alt="Avatar"
                          id="profile-img"
                          src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
                          class="profile-img-card"
                        />
                      </v-list-item-avatar>
                    </v-list-item>
                    <v-card-text>
                      <v-text-field
                        v-model="user.nom"
                        label="Nom"
                      ></v-text-field>
                      <v-text-field
                        v-model="user.prenom"
                        label="Prénom(s)"
                      ></v-text-field>
                      <v-text-field
                        v-model="user.email"
                        label="Email"
                        type="email"
                        required
                      ></v-text-field>
                      <v-text-field
                        v-model="user.adresse"
                        label="Adresse"
                      ></v-text-field>
                    </v-card-text>
                    <v-card-actions>
                      <v-btn outlined rounded text class="btn btn-primary">
                        Modifier
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-expansion-panel-content>
              </v-expansion-panel>
              <v-expansion-panel hide-actions>
                <v-expansion-panel-header>
                  <v-row align="center" class="spacer" no-gutters>
                    <v-col cols="4" sm="2" md="1">
                      <v-icon color="red">mdi-lock</v-icon>
                    </v-col>

                    <v-col class="hidden-xs-only" sm="5" md="3">
                      <strong
                        v-html="(title = 'Changer mon mot de passe')"
                      ></strong>
                      <span class="grey--text"> </span>
                    </v-col>
                  </v-row>
                </v-expansion-panel-header>

                <v-expansion-panel-content>
                  <v-divider></v-divider>

                  <v-card max-width="644" outlined>
                    <v-card-text>
                      <v-text-field
                        v-model="user.oldpass"
                        label="Ancien mot de passe"
                      ></v-text-field>
                      <v-text-field
                        v-model="user.newpass"
                        label="Nouveau mot de passe"
                      ></v-text-field>
                    </v-card-text>
                    <v-card-actions>
                      <v-btn outlined rounded text class="btn btn-warring">
                        Modifier
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-expansion-panel-content>
              </v-expansion-panel>
              <v-expansion-panel hide-actions>
                <v-expansion-panel-header>
                  <v-row align="center" class="spacer" no-gutters>
                    <v-col cols="4" sm="2" md="1">
                      <v-icon color="teal">mdi-tag</v-icon>
                    </v-col>

                    <v-col class="hidden-xs-only" sm="5" md="3">
                      <strong v-html="(title = 'Suivi de commande')"></strong>
                      <span class="grey--text"> </span>
                    </v-col>
                    <v-col class="text-no-wrap" cols="5" sm="3">
                      <v-chip
                        color="teal lighten-4"
                        class="ml-0 mr-2 black--text"
                        label
                        small
                      >
                        {{ orderList.length }}
                      </v-chip>
                    </v-col>
                  </v-row>
                </v-expansion-panel-header>

                <v-expansion-panel-content>
                  <v-divider></v-divider>
                  <v-row>
                    <v-col :cols="12" md="12" sm="12">
                      <order-details
                        :uploadURL="uploadURL"
                        :loading="loading"
                        :headers="headers"
                        :orderList="orderList"
                        :showBtn="false"
                      />
                    </v-col>
                  </v-row>
                </v-expansion-panel-content>
              </v-expansion-panel>
            </v-expansion-panels>
          </v-row>
          <br>
        </v-card-text>
      </v-card>
    </v-row>
  </v-container>
</template>
<script>
import OrderDetails from "../components/OrderDetails";
export default {
  components: {
    OrderDetails,
  },
  name: "Profile",
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
  },
  data: () => ({
    user: {},
    loading: true,
    uploadURL: "",
    orderList: [],
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

  async created() {
    this.$store.commit("auth/showNavBar", false);
    //this.user ={... this.$store.state.auth.user}
    if (!this.currentUser) {
      this.$router.push("/login");
      return;
    }
    this.user = { ...this.currentUser };
    console.log(this.currentUser);
    localStorage.setItem("listCard", "");
    this.$store.dispatch("order/uploadDir").then((res) => {
      this.uploadURL = res;
    });

    this.$store.dispatch("order/listSuivi").then(async (res) => {
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
      }
    });
  },
  methods: {},
};
</script>
<style scoped>
label {
  display: block;
  margin-top: 10px;
}

.card-container.card {
  max-width: 350px !important;
  padding: 40px 40px;
}

.card {
  background-color: #f7f7f7;
  padding: 20px 25px 30px;
  margin: 0 auto 25px;
  margin-top: 50px;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}

.profile-img-card {
  width: 96px;
  height: 96px;
  margin: 0 auto 10px;
  display: block;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  border-radius: 50%;
}
</style>