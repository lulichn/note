# プライベートネットワーク

cf. https://github.com/ethereum/go-ethereum/wiki/Private-network

ノードをメインネットワークに接続せず, プライベートな環境で利用します.

プライベートなネットワークでは容易に Ether の採掘もでき, Ethereum の動作を学習するには最適です.

## ネットワークIDの選択

プライベートネットワークをメインネットワークと分離するため `ネットワークID` を指定します.

メインネットワークの `ネットワークID` は `1` であり, 異なる `ネットワークID` を指定するとノードはメインネットワークに接続せずプライベートネットワークを形成します.

本書では `ネットワークID` に `15` を指定します.

※ Ethereum はメインネットワークとは別に, テストネットワークがあり異なる `ネットワークID` を使用しています.

以下に主に用いられている `ネットワークID` の一覧を示します.

| ID  | Network        |
|:---:|:--------------:|
| 1   | Public Main    |
| 3   | Ropsten (test) |
| 4   | Rinkeby (test) |

[How to select a network id or is there a list of network ids?](https://ethereum.stackexchange.com/questions/17051/how-to-select-a-network-id-or-is-there-a-list-of-network-ids)

## Genesisブロックの作成

ブロックチェーンの最初のブロックである `Genesis Block` を作成します.

[Operating a private network](https://github.com/ethereum/go-ethereum#operating-a-private-network) の `genesis.json` をベースに, 以下のパラメータを変更します.

`chainId`: 前項で決定した `ネットワークID` と同じ値を指定する

[include](../../src/genesis.json)

`geth init` コマンドで初期化を行います.

```
$ geth init --datadir /path/to/data genesis.json
```

### パラメータ

* `--datadir`: ブロックチェーンデータや各種ログを格納するディレクトリ
* `genesis.json`: 

### 実行ログ (省略)

```
$ geth init --datadir /path/to/data genesis.json
INFO [05-14|19:16:49] Writing custom genesis block
INFO [05-14|19:16:49] Persisted trie from memory database      nodes=0 size=0.00B time=1.909µs gcnodes=0 gcsize=0.00B gctime=0s livenodes=1 livesize=0.00B
INFO [05-14|19:16:49] Successfully wrote genesis state         database=lightchaindata                                                                                  hash=49c2dd…aff051
```

## ノードの起動

```
$ geth --networkid 15 --nodiscover --datadir /path/to/data console
```

### パラメータ

* `--networkid`: ネットワークID  
前述の `genesis.json` で指定した `chainId` と同じ値を指定する.
* `--nodiscover`: デフォルトの挙動では同じ `networkid` のノードを探しにいってしまうので抑制する.
* `--datadir`: ブロックチェーンデータや各種ログ
genesisブロックの初期化時と同じディレクトリを指定する
* `console`: 対話コンソールを開く

### 実行ログ (省略)

```
$ geth --networkid 15 --nodiscover --datadir /path/to/data console
Welcome to the Geth JavaScript console!

instance: Geth/v1.8.6-stable-12683fec/darwin-amd64/go1.10
modules: admin:1.0 debug:1.0 eth:1.0 miner:1.0 net:1.0 personal:1.0 rpc:1.0 txpool:1.0 web3:1.0

>
```

## RPC 経由で操作を行う

```
$ geth --networkid 15 --nodiscover --datadir /path/to/data
```

アッタチする

```
$ geth --datadir /path/to/data attach
Welcome to the Geth JavaScript console!

instance: Geth/v1.8.6-stable-12683fec/darwin-amd64/go1.10
modules: admin:1.0 debug:1.0 eth:1.0 miner:1.0 net:1.0 personal:1.0 rpc:1.0 txpool:1.0 web3:1.0

>
```
