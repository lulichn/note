# greeter

[include](../../src/contract/Greeter.sol)

```
$ make greeter
solc -o greeter --abi --bin 02_Greeter.sol
$ ls -1 ./greeter
Greeter.abi
Greeter.bin
Mortal.abi
Mortal.bin
```

like...

```
$ cat greeter/Greeter.abi | pbcopy
```

```
> abi = [{"constant":false,"inputs":[],"name":"kill","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[],"name":"greet","outputs":[{"name":"","type":"string"}],"payable":false,"stateMutability":"view","type":"function"},{"inputs":[{"name":"_greeting","type":"string"}],"payable":false,"stateMutability":"nonpayable","type":"constructor"}]
>
> bin = "0x" + "608060405234801561001057600080fd5b5060405161039b38038061039b83398101806040528101908080518201929190505050336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508060019080519060200190610089929190610090565b5050610135565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106100d157805160ff19168380011785556100ff565b828001600101855582156100ff579182015b828111156100fe5782518255916020019190600101906100e3565b5b50905061010c9190610110565b5090565b61013291905b8082111561012e576000816000905550600101610116565b5090565b90565b610257806101446000396000f30060806040526004361061004c576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806341c0e1b514610051578063cfae321714610068575b600080fd5b34801561005d57600080fd5b506100666100f8565b005b34801561007457600080fd5b5061007d610189565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156100bd5780820151818401526020810190506100a2565b50505050905090810190601f1680156100ea5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415610187576000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b565b606060018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156102215780601f106101f657610100808354040283529160200191610221565b820191906000526020600020905b81548152906001019060200180831161020457829003601f168201915b50505050509050905600a165627a7a72305820116a3cdfb9f0e29281d0b572e2b8eebf54905e49fcf2797b0e399a12cf5f43ae0029"
>
> var greeterFactory = eth.contract(abi)
```

```
> var greeter = greeterFactory.new("Hello world", {from: eth.accounts[0], data: bin, gas: 1000000}, function(e, contract){
  if (e) {
    console.error(e);
    return;
  }
  if (! contract.address) {
    console.log("TransactionHash: " + contract.transactionHash);
  } else {
    console.log("Address: " + contract.address);
  }
})

TransactionHash: 0xe2f80f1f8ade9a80bc07f608946560133f0874f35d614a0a695e048bf607889e
undefined
Address: 0xd0a3a67166020231680cd1c7b57f2d2285efe80d
```

`Address`: `0xd0a3a67166020231680cd1c7b57f2d2285efe80d`

```
> greeter.greet()
"Hello world"
```

```
> var greeter2 = eth.contract(abi).at("0xd0a3a67166020231680cd1c7b57f2d2285efe80d")
undefined
> greeter2.greet()
"Hello world"
```

```
> greeter.kill.sendTransaction({from:eth.accounts[0]})
"0xc1b9a82a52ecba0b0766ef4b4da37d695b6a34c0cad4bc56f39e704b500ed847"
```

```
> greeter.greet()
Error: new BigNumber() not a base 16 number:
    at L (bignumber.js:3:2876)
    at bignumber.js:3:8435
    at a (bignumber.js:3:389)
    at web3.js:1110:23
    at web3.js:1634:20
    at web3.js:826:16
    at map (<native code>)
    at web3.js:825:12
    at web3.js:4080:18

> greeter2.greet()
Error: new BigNumber() not a base 16 number:
    at L (bignumber.js:3:2876)
    at bignumber.js:3:8435
    at a (bignumber.js:3:389)
    at web3.js:1110:23
    at web3.js:1634:20
    at web3.js:826:16
    at map (<native code>)
    at web3.js:825:12
    at web3.js:4080:18
```

```
> eth.getCode(greeter.address)
"0x"
```
