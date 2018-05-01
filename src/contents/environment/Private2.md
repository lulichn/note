## etherbase (coinbase) の設定

**etherbase**: 各ノードで採掘を行う際にその報酬を紐づけるEOAのアドレス

```
> miner.setEtherbase("0x87a049d2deb3f705c8ca456e53ac8848044b6294")
true
> eth.coinbase
"0x87a049d2deb3f705c8ca456e53ac8848044b6294"
```

## ether の採掘

```
> miner.start(thread_num)
```

* `thread_num`: スレッド数  
指定しない場合は CPU コア数

### 採掘の開始

```
> miner.start()
```

暫く待ちブロック高を確認すると, ブロックが採掘されている事が分かる.

```
> eth.blockNumber
43
```

### 採掘状況の確認

採掘処理が行われているか

```
> eth.mining
true
> eth.hashrate
0
```

0? 要確認

### ブロックの内容の確認

```
> eth.getBlock(10)
{
  difficulty: 131648,
  extraData: "0xd783010806846765746886676f312e31308664617277696e",
  gasLimit: 3172388,
  gasUsed: 0,
  hash: "0x611d15e241476b09bfdcb9db46422724a92b20c3489d30cfbdd5e6570e57737d",
  logsBloom: "0x00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
  miner: "0x87a049d2deb3f705c8ca456e53ac8848044b6294",
  mixHash: "0xa92718c1a095722185cc41711b871aaaccb814a592597d02316ba8b26893cbdc",
  nonce: "0x57c9c9915f04423e",
  number: 10,
  parentHash: "0x1f11cf0d38810ee4e56c3dacbe072808ce75516865a825464b4e97925b1cd719",
  receiptsRoot: "0x56e81f171bcc55a6ff8345e692c0f86e5b48e01b996cadc001622fb5e363b421",
  sha3Uncles: "0x1dcc4de8dec75d7aab85b567b6ccd41ad312451b948a7413f0a142fd40d49347",
  size: 535,
  stateRoot: "0x63a9c7474e2570f31ecabe92eeefb34b3fb1109b3e52f1410fccf52ee5fbf139",
  timestamp: 1524814400,
  totalDifficulty: 1444672,
  transactions: [],
  transactionsRoot: "0x56e81f171bcc55a6ff8345e692c0f86e5b48e01b996cadc001622fb5e363b421",
  uncles: []
}
```

### 採掘の停止

```
> miner.stop()
true
```

### 報酬の確認

```
> eth.getBalance("0x87a049d2deb3f705c8ca456e53ac8848044b6294")
8.995e+21
```

ここで表示される時の単位は `wei` となっており, `ether` で表示するには

```
> web3.fromWei(eth.getBalance("0x87a049d2deb3f705c8ca456e53ac8848044b6294"), "ether")
9445
```

となる.

`1 wei` =  `0.000000000000000001ether`


また, もう一方のアカウントには残高は無いままである.

```
> eth.getBalance("0x21a717aa47d94a0e6f760a2dc221cf1d1658e43f")
0
```

## ether の送信

### アカウントのロックの解除

```
> personal.unlockAccount("0x87a049d2deb3f705c8ca456e53ac8848044b6294")
Unlock account 0x87a049d2deb3f705c8ca456e53ac8848044b6294
Passphrase:
true
```

### 送金

送金額の設定は `wei` で行う必要がある.

```
> eth.sendTransaction({
from: "0x87a049d2deb3f705c8ca456e53ac8848044b6294",
to: "0x21a717aa47d94a0e6f760a2dc221cf1d1658e43f",
value: web3.toWei(5, "ether")
})
"0xafd9dc9b0c2bea8cd56bdffa7e08638d14869b0c086c6c89ae6293716714af7c"
```

実行結果としてトランザクションIDが返される.

```
0xafd9dc9b0c2bea8cd56bdffa7e08638d14869b0c086c6c89ae6293716714af7c
```

送信先に残高が増えている事が確認できる.

```
> eth.getBalance("0x21a717aa47d94a0e6f760a2dc221cf1d1658e43f")
5000000000000000000
> web3.fromWei(eth.getBalance("0x21a717aa47d94a0e6f760a2dc221cf1d1658e43f"), "ether")
5
```

### 手数料

先ほどとは逆に, アカウント `0x21a717aa47d94a0e6f760a2dc221cf1d1658e43f` が所持している `5 ether` の内 `3 ether` を送金する.

```
> eth.sendTransaction({
from: "0x21a717aa47d94a0e6f760a2dc221cf1d1658e43f",
to: "0x87a049d2deb3f705c8ca456e53ac8848044b6294",
value: web3.toWei(3, "ether")
})
"0xeb0a0f64bf360668539b50237832c3c55511bbb7124829cd6a0bc4b49b8c45f7"
```

送金後の残高を確認すると `2 ether` ではなく, `1.999622 ether` となる.

```
> web3.fromWei(eth.getBalance("0x21a717aa47d94a0e6f760a2dc221cf1d1658e43f"), "ether")
1.999622
```

差分の `0.000378 ether` (`378000000000000 wei`) がトランザクション手数料となっており, 採掘者の報酬に加えられる.

### トランザクション情報の確認

送金時のトランザクションIDの内容を確認すると以下となっている.

```
> eth.getTransaction('0xeb0a0f64bf360668539b50237832c3c55511bbb7124829cd6a0bc4b49b8c45f7')
{
  blockHash: "0x1f56a59ff20cb0858a011f0e3c019d3b2c8f4e1a11c7b62a999c87139e9e067a",
  blockNumber: 5548,
  from: "0x21a717aa47d94a0e6f760a2dc221cf1d1658e43f",
  gas: 90000,
  gasPrice: 18000000000,
  hash: "0xeb0a0f64bf360668539b50237832c3c55511bbb7124829cd6a0bc4b49b8c45f7",
  input: "0x",
  nonce: 0,
  r: "0xa7661e6b2658314d02caee055764df3d23c179862e100a366d7abb8bd5125662",
  s: "0x45e7e514118c5b815adfd79da48145cc2d67808879cef7806994fd38520a60d0",
  to: "0x87a049d2deb3f705c8ca456e53ac8848044b6294",
  transactionIndex: 0,
  v: "0x41",
  value: 3000000000000000000
}
```

* `from`: `eth.sendTransaction` のパラメータ `from` と対応
* `to`: `eth.sendTransaction` のパラメータ `to` と対応
* `value`: `eth.sendTransaction` のパラメータ `value` と対応
* `gas`: トランザクションの処理時の採掘者に対する作業のコスト `GAS` の使用量の最大値
* `gasPrice`: トランザクションの処理時に採掘者に支払う `1 gas` 当たりの手数料 (`wei`)

送金で使用された `gas` を求めるには

```
トランザクション手数料 378000000000000 wei / 1 gas 当たりの手数料 18000000000 wei = 21000
```

`21000 gas` が使用された事がわかる.

