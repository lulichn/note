# SingleNumRegister

## スマートコントラクトの作成


[include](../../src/contract/SingleNumRegister.sol)

### Compile

```
$ solc --abi --bin SingleNumRegister.so

======= SingleNumRegister.sol:SingleNumRegister =======
Binary:
608060405234801561001057600080fd5b5060df8061001f6000396000f3006080604052600436106049576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806360fe47b114604e5780636d4ce63c146078575b600080fd5b348015605957600080fd5b5060766004803603810190808035906020019092919050505060a0565b005b348015608357600080fd5b50608a60aa565b6040518082815260200191505060405180910390f35b8060008190555050565b600080549050905600a165627a7a72305820cc90b2d2ff05e721b24e436726e02072ab2c457f4b0bdc20b3148fe5387a92430029
Contract JSON ABI
[{"constant":false,"inputs":[{"name":"x","type":"uint256"}],"name":"set","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[],"name":"get","outputs":[{"name":"retVal","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"}]
```

### Contract アカウントの作成

前回作成した `EOA アカウント` ではなく今回は `Contract アカウント` を作成する.

```
> var bin = "0x608060405234801561001057600080fd5b5060df8061001f6000396000f3006080604052600436106049576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806360fe47b114604e5780636d4ce63c146078575b600080fd5b348015605957600080fd5b5060766004803603810190808035906020019092919050505060a0565b005b348015608357600080fd5b50608a60aa565b6040518082815260200191505060405180910390f35b8060008190555050565b600080549050905600a165627a7a72305820cc90b2d2ff05e721b24e436726e02072ab2c457f4b0bdc20b3148fe5387a92430029"
> var abi = [{"constant":false,"inputs":[{"name":"x","type":"uint256"}],"name":"set","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[],"name":"get","outputs":[{"name":"retVal","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"}]
>
> bin
"0x608060405234801561001057600080fd5b5060df8061001f6000396000f3006080604052600436106049576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806360fe47b114604e5780636d4ce63c146078575b600080fd5b348015605957600080fd5b5060766004803603810190808035906020019092919050505060a0565b005b348015608357600080fd5b50608a60aa565b6040518082815260200191505060405180910390f35b8060008190555050565b600080549050905600a165627a7a72305820cc90b2d2ff05e721b24e436726e02072ab2c457f4b0bdc20b3148fe5387a92430029"
> abi
[{
    constant: false,
    inputs: [{
        name: "x",
        type: "uint256"
    }],
    name: "set",
    outputs: [],
    payable: false,
    stateMutability: "nonpayable",
    type: "function"
}, {
    constant: true,
    inputs: [],
    name: "get",
    outputs: [{
        name: "retVal",
        type: "uint256"
    }],
    payable: false,
    stateMutability: "view",
    type: "function"
}]
```

```
> personal.unlockAccount(eth.accounts[0])
Unlock account 0x191ef02ed7c9709e97edc3762f29912e15eedb66
Passphrase:
true
> var contract = eth.contract(abi)
undefined
> var myContractReturned = contract.new(
    {from: eth.accounts[0], data: bin, gas: 1000000},
    function(err, myContract) {
        if (!err) {
            if(!myContract.address) {
                console.log(myContract.transactionHash);
            } else {
                console.log(myContract.address);
            }
        } else {
            console.log(err);
        }
    }
)
0xfe496cc8d5e6baf57b3314165a9451ebf2bc9b98afca14730056efa5026deeb3
undefined
0x76e4663e88bd20bc86cb41672e358fe75005e06c
```

TODO: 上手くかけない


```
> myContractReturned
{
  abi: [{
      constant: false,
      inputs: [{...}],
      name: "set",
      outputs: [],
      payable: false,
      stateMutability: "nonpayable",
      type: "function"
  }, {
      constant: true,
      inputs: [],
      name: "get",
      outputs: [{...}],
      payable: false,
      stateMutability: "view",
      type: "function"
  }],
  address: "0x76e4663e88bd20bc86cb41672e358fe75005e06c",
  transactionHash: "0xfe496cc8d5e6baf57b3314165a9451ebf2bc9b98afca14730056efa5026deeb3",
  allEvents: function(),
  get: function(),
  set: function()
}
```

```
> personal.unlockAccount(eth.accounts[0])
Unlock account 0x191ef02ed7c9709e97edc3762f29912e15eedb66
Passphrase:
true
> myContractReturned.set.sendTransaction(100, {from: eth.accounts[0]})
"> eth.getTransaction('0xf94568833c40025644f6d242e8175b0b32dc87f8a4e33123847f39784d15ad6f')
{
  blockHash: "0x729493d9368f6ac891b503a8c62d7e6a50151a836b466328e806d0dae657dac3",
  blockNumber: 3502,
  from: "0xeaf624f6357abdf10016c41487a290271607f6dc",
  gas: 90000,
  gasPrice: 18000000000,
  hash: "0xf94568833c40025644f6d242e8175b0b32dc87f8a4e33123847f39784d15ad6f",
  input: "0x60fe47b10000000000000000000000000000000000000000000000000000000000000064",
  nonce: 7,
  r: "0xdeb2b07a58b165efb1913690d128be1311f09143d5152552280f0fd3a73e680a",
  s: "0xb68e0dd22b9ee769704356a8c8b8a2ad482a0f96cec8fbb1e6e9b6fc39a3f13",
  to: "0x76e4663e88bd20bc86cb41672e358fe75005e06c",
  transactionIndex: 0,
  v: "0x42",
  value: 0
}"
```

```
> myContractReturned.get()
100
```

```
> eth.getTransaction('0xf94568833c40025644f6d242e8175b0b32dc87f8a4e33123847f39784d15ad6f')
{
  blockHash: "0x729493d9368f6ac891b503a8c62d7e6a50151a836b466328e806d0dae657dac3",
  blockNumber: 3502,
  from: "0xeaf624f6357abdf10016c41487a290271607f6dc",
  gas: 90000,
  gasPrice: 18000000000,
  hash: "0xf94568833c40025644f6d242e8175b0b32dc87f8a4e33123847f39784d15ad6f",
  input: "0x60fe47b10000000000000000000000000000000000000000000000000000000000000064",
  nonce: 7,
  r: "0xdeb2b07a58b165efb1913690d128be1311f09143d5152552280f0fd3a73e680a",
  s: "0xb68e0dd22b9ee769704356a8c8b8a2ad482a0f96cec8fbb1e6e9b6fc39a3f13",
  to: "0x76e4663e88bd20bc86cb41672e358fe75005e06c",
  transactionIndex: 0,
  v: "0x42",
  value: 0
}
```

```
> myContractReturned.set.sendTransaction(2000, {from: eth.accounts[0]})
"0x278154f71e202556a99e4e27d766d64605f7ac7cffeeb42f1d5354fdcec3f182"
```

```
> myContractReturned.get()
2000
```

