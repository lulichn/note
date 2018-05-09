# MyToken

[include](../../src/erc20/MyToken.sol)

## デプロイ

```
> var abi = 略
undefined
>
> var bin = "0x" + "略"
undefined
```

```
> var myTokenFactory = eth.contract(abi)

> var myToken = myTokenFactory.new(1000, {from: eth.accounts[0], data: bin, gas: 1000000}, function(e, contract) {
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
TransactionHash: 0x1241aaa1232d5560b0646816c450609a3d9cd3530ce28fc1e8787be91e5c37e8
undefined
Address: 0x76e4663e88bd20bc86cb41672e358fe75005e06c
```

引数で `1000` トークンを指定しているので, 所持しているか確認する.

```
> myToken.balanceOf(eth.accounts[0])
1000
```

## 送金

`transfer` メソッドで送金する

```
> personal.unlockAccount(eth.accounts[0])
> myToken.transfer(eth.accounts[1], 200, {from: eth.accounts[0]})
"0x34f3087532b169937657ee2b2cbabe90be8374820edc7066594736a87958eae9"
```

送金できている事を確認

```
> myToken.balanceOf(eth.accounts[0])
800
> myToken.balanceOf(eth.accounts[1])
200
```

## 送金 (代理)

`transferFrom()` メソッドで送金する

```
> personal.unlockAccount(eth.accounts[0])
> myToken.transferFrom(eth.accounts[1], eth.accounts[0], 100, {from: eth.accounts[0]})
```

`transferFrom()` では `allowance` の値を確認しているので, 送金できない事を確認.

**エラーの確認方法は?**

```
> myToken.balanceOf(eth.accounts[0])
800
> myToken.balanceOf(eth.accounts[1])
200
```

`approve()` 関数で `eth.accounts[1]` が持つトークンを `eth.accounts[0]` が送金できるよう許可する

```
> personal.unlockAccount(eth.accounts[1])
> myToken.approve(eth.accounts[0], 100, {from: eth.accounts[1]})
"0xc7983874960501f20eb151952d4e72461cb55999f8308d33633a0dbdccbb058c"
```

`allowance` に値が存在する事を確認

```
> myToken.allowance(eth.accounts[1], eth.accounts[0])
100
```

再度, 送金を行う.

```
> personal.unlockAccount(eth.accounts[1])
> myToken.transferFrom(eth.accounts[1], eth.accounts[0], 100, {from: eth.accounts[0]})
"0x2108a6fe7a89ce7989ae95bc5da86797d972e0bc26ddb863ee9e80159fc3668c"
```

送金できている事を確認

```
> myToken.balanceOf(eth.accounts[0])
900
> myToken.balanceOf(eth.accounts[1])
100
```

`allowance` の値が更新されている事を確認

```
> myToken.allowance(eth.accounts[1], eth.accounts[0])
0
```
