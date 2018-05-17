<template>
  <div class="tokenuser">
    <div class="content">
      <p v-if="contractAddress">The TokenSale contract is deployed at {{contractAddress}}</p>
      <p v-if="!contractAddress">No contracts found</p>
      <p v-if="account">Current account: {{account}}</p>
      <p v-if="!account">No accounts found</p>

      <table class="hover">
        <thead>
          <tr>
            <th>address</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="user in whiteList" v-bind:key="user.address">
            <td>{{user.address}}</td>
          </tr>
        </tbody>
      </table>
      
      <input v-model="inputAddress" type="string">
      <button @click="addWhiteList">Add WhiteList</button>
    </div>
    <div class="message" v-if="message">{{message}}</div>
  </div>
</template>

<script>
import Web3 from 'web3'
import contract from 'truffle-contract'
import artifacts from '../../build/contracts/FooTokenSale.json'
const FooTokenSale = contract(artifacts)

export default {
  name: 'FooTokenSale',
  data() {
    return {
      message: null,
      contractAddress: null,
      account: null,

      whiteList: [],
      inputAddress: null,
    }
  },
  created() {
    if (typeof web3 !== 'undefined') {
      console.warn("Using web3 detected from external source. If you find that your accounts don't appear or you have 0 Fluyd, ensure you've configured that source properly. If using MetaMask, see the following link. Feel free to delete this warning. :) http://truffleframework.com/tutorials/truffle-and-metamask")
      // Use Mist/MetaMask's provider
      web3 = new Web3(web3.currentProvider)
    } else {
      console.warn("No web3 detected. Falling back to http://127.0.0.1:8545. You should remove this fallback when you deploy live, as it's inherently insecure. Consider switching to Metamask for development. More info here: http://truffleframework.com/tutorials/truffle-and-metamask")
      // fallback - use your fallback strategy (local node / hosted node + in-dapp id mgmt / fail)
      web3 = new Web3(new Web3.providers.HttpProvider("http://127.0.0.1:8545"))
    }

    FooTokenSale.setProvider(web3.currentProvider)
    web3.eth.getAccounts((err, accs) => {
      if (err != null) {
        console.error(err)
        this.message = "There was an error fetching your accounts. Do you have Metamask, Mist installed or an Ethereum node running? If not, you might want to look into that"
        return
      }

      if (accs.length == 0) {
        this.message = "Couldn't get any accounts! Make sure your Ethereum client is configured correctly."
        return
      }
      this.account = accs[0];
      FooTokenSale.deployed()
        .then((instance) => instance.address)
        .then((address) => {
          this.contractAddress = address
          this.updateWhiteList()
        })
    })
  },
  methods: {
    addWhiteList() {
      const list = [this.inputAddress]
      this.message = "Transaction started";
      return FooTokenSale.deployed()
        .then((instance) => {
          return instance.addWhiteList(list, {from: this.account})
        }).then(() => {
          this.message = "Transaction done"
          this.updateWhiteList()
        })
        .catch((e) => {
          console.error(e)
          this.message = "Transaction failed"
        })
    },

    updateWhiteList() {
      FooTokenSale.deployed()
        .then((instance) => {
          Promise.resolve()
            .then(() => {
              return instance.getWhiteList()
            }).then((whiteList) => {
              return whiteList.map((address) => {
                return {address: address}
              })
            }).then((whiteList) => {
              this.whiteList = whiteList
            })
          })
    },
  },
}
</script>

<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
.content {
  padding: 13px 13px 39px 13px;
}
.message {
  background: #eee;
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  text-align: center;
  font-size: 13px;
  line-height: 1;
  padding: 13px;
}
</style>
