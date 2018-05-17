pragma solidity ^0.4.23;

import { AddressCollection } from "./Collection.sol";

contract FooToken {
    string public name = "FooToken";
    
    string public symbol = "FOO";
    
    uint256 public decimals = 6;

    using AddressCollection for AddressCollection.Index;
    AddressCollection.Index internal addressCollectionIndex;

    struct EntityStruct { uint256 balance; }
    EntityStruct[] internal entityList;

    mapping (address => mapping (address => uint256)) public allowance;
    
    // 供給量 (初期値: 0)
    uint256 public totalSupply = 0;

    address public owner;
    address public minter;

    modifier isOwner {
        assert(owner == msg.sender);
        _;
    }
    
    modifier isMinter {
        assert(minter == msg.sender);
        _;
    }

    constructor(uint256 _value) public {
        owner = msg.sender;

        setBalance(msg.sender, _value);
        totalSupply = _value;
    }

    function getAddresses() public view returns(address[]) {
        return addressCollectionIndex.keys();
    }

    function balanceOf(address _address) public view returns (uint256) {
        bool exists;
        uint256 index;
        (exists, index) = addressCollectionIndex.get(_address);
        
        if (exists) {
            return entityList[index].balance;
        } else {
            return 0;
        }
    }

    function transfer(address _to, uint256 _value) public returns (bool success) {
        return transferFrom(msg.sender, _to, _value);
    }

    // FIXME!!!
    function transferFrom(address _from, address _to, uint256 _value) public returns (bool success) {
        require(balanceOf(_from) >= _value);
        require(balanceOf(_to) + _value >= balanceOf(_to));
 
        if (_from != msg.sender) {
            require(allowance[_from][msg.sender] >= _value);
            allowance[_from][msg.sender] -= _value;
        }

        setBalance(_from, balanceOf(_from) - _value);
        setBalance(_to, balanceOf(_to) + _value);

        emit Transfer(_from, _to, _value);
        return true;
    }

    function approve(address _spender, uint256 _value) public returns (bool success) {
        allowance[msg.sender][_spender] = _value;
        emit Approval(msg.sender, _spender, _value);
        return true;
    }

    function mint(address _to, uint256 _value) isMinter public returns (bool) {
        totalSupply += _value;
        setBalance(_to, balanceOf(_to) + _value);

        emit Transfer(address(0), _to, _value);
        return true;
    }

    event Transfer(address indexed _from, address indexed _to, uint256 _value);
    event Approval(address indexed _owner, address indexed _spender, uint256 _value);

    //
    // Only Owner
    //

    function setMinter(address _minter) isOwner public returns (bool) {
        minter = _minter;
        return true;
    }

    //
    // Internal
    //

    function setBalance(address _address, uint _balance) internal returns(bool) {
        bool exists;
        uint256 index;
        (exists, index) = addressCollectionIndex.get(_address);

        if (exists) {
            entityList[index].balance = _balance;
        } else {
            uint newIndex = entityList.push(EntityStruct({balance: _balance}));
            addressCollectionIndex.set(_address, newIndex - 1);
        }

        return true;
    }
}
