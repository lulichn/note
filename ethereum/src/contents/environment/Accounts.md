# アカウントの管理

cf. https://github.com/ethereum/go-ethereum/wiki/Managing-your-accounts


## アカウントの種別

* EOA（Externally Owned Account)
* Contract

## アカウントの一覧を取得する

`geth account list` コマンドでアカウントの一覧を表示します.

```
$ geth account list --datadir /path/to/data
```

### パラメータ

* `--datadir`: ブロックチェーンデータや各種ログを格納するディレクトリ

### 実行結果

```
$ geth account list --datadir /path/to/data
INFO [05-14|19:26:34] Maximum peer count                       ETH=25 LES=0 total=25
```

## アカウントを作成する

`geth account new` コマンドでアカウントを作成します.

```
$ geth account new --datadir /path/to/data
```

### パラメータ

* `--datadir`: ブロックチェーンデータや各種ログを格納するディレクトリ

### 実行結果

```
$ geth account new --datadir /path/to/data
INFO [05-14|19:37:09] Maximum peer count                       ETH=25 LES=0 total=25
Your new account is locked with a password. Please give a password. Do not forget this password.
Passphrase:
Repeat passphrase:
Address: {8ed25ecc5c47139ad4ebcd8de779bacba199d6dd}

$ geth account list --datadir /path/to/data
INFO [05-14|19:44:17] Maximum peer count                       ETH=25 LES=0 total=25
Account #0: {8ed25ecc5c47139ad4ebcd8de779bacba199d6dd} keystore:///path/to/data/keystore/UTC--2018-05-14T10-37-15.756497384Z--8ed25ecc5c47139ad4ebcd8de779bacba199d6dd
```

---

# JavaScript コンソールでの操作


## アカウントの一覧を取得する

```
> eth.accounts
[]
```

## アカウントを作成する

```
> personal.newAccount("PASSWORD")
```

### パラメータ

* `PASSWORD`: EOAのパスワード

### 実行結果

```
> personal.newAccount("foobar")
"0x87a049d2deb3f705c8ca456e53ac8848044b6294"
> eth.accounts
["0x87a049d2deb3f705c8ca456e53ac8848044b6294"]
```
