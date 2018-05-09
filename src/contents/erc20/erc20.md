# ERC-20 Token Standard

https://github.com/ethereum/EIPs/blob/master/EIPS/eip-20.md

## Spec

### メソッド
* name

    トークンの名前を返す.  
    例. MyToken

        function name() view returns (string name)

    *任意*

* symbol

    トークンのシンボルを返す.  
    例. HIX

        function symbol() view returns (string symbol)

    *任意*

* decimals

    トークンが使用する小数点以下の桁数を返す.  
    例. 8

        function decimals() view returns (uint8 decimals)

    *任意*

* totalSupply

    流通量を返す

        function totalSupply() view returns (uint256 totalSupply)

* balanceOf

    `_owner` が持つ量を返す

        function balanceOf(address _owner) view returns (uint256 balance)


* transfer

    `_to` に `_value` 分, 送金する

        function transfer(address _to, uint256 _value) returns (bool success)

* transferFrom

    `_from` から `_to` に `_value` 分, 送金する

        function transferFrom(address _from, address _to, uint256 _value) returns (bool success)

* approve

    `_spender` に `_value` 分, 送金することを許可する

        function approve(address _spender, uint256 _value) returns (bool success)

* allowance

    `_owner` が `_spender` に送金を許可している量を取得する

        function allowance(address _owner, address _spender) view returns (uint256 remaining)

### イベント

* Transfer

        event Transfer(address indexed _from, address indexed _to, uint256 _value)

* Approval

        event Approval(address indexed _owner, address indexed _spender, uint256 _value)

