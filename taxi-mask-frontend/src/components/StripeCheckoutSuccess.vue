<template>
  <div>
    <section
      class="section has-background-white-bis"
      style="margin-top:5%"
    >
      <div class="container has-text-centered">
        <v-alert
            dense
            text
            type="success"
          >
           Payement effectué avec succès.
          </v-alert>
        <div class="is-size-5 mb-6 notification is-success">
          <p>Merci pour votre confiance.</p>
          <br> 
          <div class="columns is-centered">
            <div class="column is-one-quarter">
              <!--   <a target="_black"  :href="$route('paiement.generateBill',{'token':data.token,'typeOp': data.typeOp})"  class="button is-primary is-medium is-fullwidth">Téléchager le reçu</a> -->
              
              <v-btn  color="teal" text @click="exporter">Téléchager le reçu</v-btn>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script> 
import {addScript} from '../services/utils';
import moment from 'moment';
export default {  
  props: { 
  }, 
  data() {
    return { 
      orderList:[]
    }
  },
  created() {
    this.orderList = JSON.parse(localStorage.getItem('listCard'))
    console.log(this.orderList.length)
    addScript()
  },
  methods: { 
     exporter(){ 
      const logoPath = require('../assets/img/logo.png')
      let totalAmount=0
      let td=''
      this.orderList.forEach(e =>{
        totalAmount+=e.prixProtection
        td+= `<tr> 
                  <td>${e.voiture.immatriculation}</td>
                  <td>${moment(e.dateComplete).format('MM/DD/YYYY hh:mm')}</td>
                  <td>${e.toit}</td>
                  <td>${e.prixProtection}</td>
                </tr>
                `
      })
      td+=`<tr> 
                  <td colspan="3">Total</td>
                  <td>${totalAmount}</td>
                </tr>`
      const el = `<div style="margin-left: auto;margin-right: auto;margin-top:30px">
                  <div class="row">
                    <div class="col-1"></div>
                    <div class="col-10 text-center" style="border:1px solid">
                      <p><img src="${logoPath}"></p>
                      <h2 class="center"> Facture n°</h2>
                      <br/> 
                      <table class="table table-striped">
                        <thead>
                          <tr>
                            <th scope="col">N° immatriculation</th>
                            <th scope="col">Date</th>
                            <th scope="col">Protection</th>
                            <th scope="col">Prix (€)</th>
                          </tr>
                        </thead>
                        <tbody>
                          ${td}
                        </tbody>
                      </table>  
                    </div>
                  </div>
                  
                  </div>`
      html2pdf(el)
    }
  },
}
</script>
<style scoped> 
</style>
