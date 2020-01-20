# Testnet

## geth

Ethereum wallet と同じ datadir を指定する

```
$ geth --datadir "~/Library/Ethereum/testnet" --testnet --syncmode "light" console
```

### アカウント周りについて(調査中メモ)
```
geth account list --datadir "~/Library/Ethereum/testnet"
```

https://github.com/ethereum/go-ethereum/wiki/Managing-your-accounts

```
> personal.newAccount("Znm1_GwXRwQKgxAG")
"0x591346dd961c2b41f8b8a92b81b8c141e02086ef"
```

## faucet で Ether を取得する

http://faucet.ropsten.be:3001/

```
Address 0xD4A359b6593aA1179A9C7fe8E5d2780E2B820423
TxHash 0x396f03db244f0d26871e8b1b30ff56712c535fcfa09dfefcecb9dd3ce132100e
```
