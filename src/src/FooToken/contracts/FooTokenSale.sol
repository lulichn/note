pragma solidity ^0.4.17;

import "./FooToken.sol";

contract FooTokenSale {
    address public owner = 0x0;

    FooToken public token;
    address public wallet;

    // ホワイトリスト(仮)
    mapping (address => bool) public whiteList;
    
    modifier isOwner {
        assert(owner == msg.sender);
        _;
    }

    constructor(FooToken _token, address _wallet, address[] addresses) public {
        owner = msg.sender;

        token = _token;
        wallet = _wallet;

        // each はない(ように見える)
        for (uint i = 0; i < addresses.length; i++) {
            whiteList[addresses[i]] = true;
        }
    }

    // loopの是非
    // http://solidity.readthedocs.io/en/develop/security-considerations.html#gas-limit-and-loops
    function addWhiteList(address[] addresses) isOwner public returns (bool) {
        
        for (uint i = 0; i < addresses.length; i++) {
            whiteList[addresses[i]] = true;
        }

        return true;
    }

    // fallback function
    // https://solidity.readthedocs.io/en/v0.4.23/contracts.html#fallback-function
    function () payable public {
        buyToken(msg.sender);
    }

    function buyToken(address beneficiary) payable public {
        require(beneficiary != 0x0);
        require(whiteList[beneficiary]);
        
        // wei (1 ether = 1000000000000000000 wei)
        uint256 weiAmount = msg.value;

        // 1 ether => 1000 tokens
        uint256 tokens = weiAmount / 1000000000000000;

        token.mint(beneficiary, tokens);
        
        forwardFunds();
    }

    function forwardFunds() internal {
        wallet.transfer(msg.value);
    }
}
