<template>
  <div>
    <form :action='urlAction' method='POST' id='checkout-form'>
    <input type='hidden' :value='putAmount' name='amount' /> 
    <input type='hidden' :value='getCommandeId' name='metadataId' /> 
    <input type='hidden' :value='sessionToken' name='customer_session' /> 
    <input type='hidden' :value='succesURL' name='success_url' /> 
    <input type='hidden' :value='cancelURL' name='cancel_url' /> 
    <script type="application/javascript"
            src='https://checkout.stripe.com/checkout.js'
            class='stripe-button'
            :data-key='publicKey'
            :data-amount='amount'  
            success_url='/'
            data-currency='eur'
            :data-label='btnLabel'
            data-name='Taxi Mask - Commande'
            data-description='Paroi de protection'
            :data-image='require(`../assets/img/logo.png`)'
            data-locale='auto'
            data-zip-code='false'>
            
    </script>
    </form>
  </div>
</template>
<style  >

 .stripe-button-el{ 
    background: #2d3753 !important;
    border-color: #2d3753 !important;
 }
 .stripe-button-el span{
   background: #2d3753 !important;
 }
</style>
<script>  
import {SERVER_URL, PAYMENT} from '../services/config-server';

export default {
  props:{
    amount: Number,
    commandeId: String
  },
   data() {
        return { 
          publicKey:'',
          btnLabel:'Payer avec une carte', 
          urlAction:'',
          sessionToken:''
        }
    },
    computed: {
      putAmount(){ 
        return parseFloat(this.amount)/100
      },
      getCommandeId(){
        return this.commandeId
      },
      actionUrl(){
        console.log("ccc="+this.commandeId)
       return `${SERVER_URL}/${PAYMENT}/charge-card?commandeIdd=${this.commandeId}`
      },
      succesURL(){
        return location.protocol.concat("//").concat(window.location.host).concat('/check-pay-success')
      },
      cancelURL(){
        return location.protocol.concat("//").concat(window.location.host).concat('/check-pay-cancel')
      },

       
    },
    created(){
      this.urlAction = `${SERVER_URL}/${PAYMENT}/charge-card?commandeId=${this.commandeId}`
      console.log(this.urlAction)
      const user = this.$store.state.auth.user
      this.sessionToken = user&& user.accessToken
      console.log(this.sessionToken)
       this.$store.dispatch('payment/getStripePublicKey').then( async res =>{ 
        this.publicKey =  res.data
        console.log(res.data)
      }).catch(err=>{
        alert(err)
      })
      console.log('jjjj'+this.commandeId)
    },
    methods: {  
    },
    
}
</script>