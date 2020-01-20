# EOS (EOS)

https://etherscan.io/address/0x86fa049857e0209aa7d9e616f7eb3b3b78ecfdb0

## Spec
* *name*: `0x0000000000000000000000000000000000000000000000000000000000000000`
* *symbol*: `0x454f530000000000000000000000000000000000000000000000000000000000`  
  -> `EOS`
* *decimals*: 18
* *totalSupply*: `1000000000000000000000000000`

## Reading
### ds-note
`contract DSNote`
* doc
  * https://github.com/dapphub/ds-note
  * https://dapp.tools/dappsys/ds-note.html

### ds-auth
`contract DSAuthority`
`contract DSAuthEvents`
`contract DSAuth is DSAuthEvents`
* doc
  * https://github.com/dapphub/ds-auth
  * https://dapp.tools/dappsys/ds-auth.html

### ds-stop
`contract DSStop is DSAuth, DSNote`
* doc
  * https://github.com/dapphub/ds-stop

### ds-math
`contract DSMath`
* doc
  * https://github.com/dapphub/ds-math

### erc20
`contract ERC20`

### ds-token
`contract DSTokenBase is ERC20, DSMath`
(*) `contract DSToken is DSTokenBase(0), DSStop`
* doc
  * https://github.com/dapphub/ds-token

## Constructor Arguments

```
454f530000000000000000000000000000000000000000000000000000000000

-----Decoded View---------------
Found 1 constructor arguments :
Arg [0] : 454f530000000000000000000000000000000000000000000000000000000000

```
