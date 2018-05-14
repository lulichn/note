# MetaCoin

http://truffleframework.com/docs/getting_started/project

```
$ mkdir MetaCoin
$ cd MetaCoin
$ truffle unbox metacoin
Downloading...
Unpacking...
Setting up...
Unbox successful. Sweet!

Commands:

  Compile contracts: truffle compile
  Migrate contracts: truffle migrate
  Test contracts:    truffle test
```

```
$ ls -1
contracts
migrations
test
truffle-config.js
truffle.js
```

```
$ truffle test
Compiling ./contracts/ConvertLib.sol...
Compiling ./contracts/MetaCoin.sol...
Compiling ./contracts/Migrations.sol...
Compiling ./test/TestMetacoin.sol...
Compiling truffle/Assert.sol...
Compiling truffle/DeployedAddresses.sol...


  TestMetacoin
    ✓ testInitialBalanceUsingDeployedContract (58ms)
    ✓ testInitialBalanceWithNewMetaCoin (71ms)

  Contract: MetaCoin
    ✓ should put 10000 MetaCoin in the first account
    ✓ should call a function that depends on a linked library
    ✓ should send coin correctly (83ms)


  5 passing (1s)
```