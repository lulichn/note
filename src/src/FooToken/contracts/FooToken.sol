pragma solidity ^0.4.17;

// https://solidity.readthedocs.io/en/v0.4.23/contracts.html#visibility-and-getters
contract FooToken {
    // Token の名前
    // 可視性が public の為 getter が生える
    // http://solidity.readthedocs.io/en/v0.4.23/contracts.html#visibility-and-getters
    string public name = "FooToken";
    
    // シンボル
    string public symbol = "FOO";
    
    // 桁数
    uint256 public decimals = 6;
    
    // 指定したアカウントが持つ量を返す
    // balanceOf(address arg1) public returns(uint256) が生える
    mapping (address => uint256) public balanceOf;
    
    // balanceOf(address arg1, address args) public returns(uint256) が生える
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
        balanceOf[msg.sender] = _value;
        totalSupply = _value;
    }

    function transfer(address _to, uint256 _value) public returns (bool success) {
        return transferFrom(msg.sender, _to, _value);
    }

    function transferFrom(address _from, address _to, uint256 _value) public returns (bool success) {
        require(balanceOf[_from] >= _value);
        require(balanceOf[_to] + _value >= balanceOf[_to]);
 
        if (_from != msg.sender) {
            require(allowance[_from][msg.sender] >= _value);
            allowance[_from][msg.sender] -= _value;
        }

        balanceOf[_from] -= _value;
        balanceOf[_to] += _value;

        emit Transfer(_from, _to, _value);
        return true;
    }

    function approve(address _spender, uint256 _value) public returns (bool success) {
        allowance[msg.sender][_spender] = _value;
        emit Approval(msg.sender, _spender, _value);
        return true;
    }

    function setMinter(address _minter) isOwner public returns (bool) {
        minter = _minter;
        return true;
    }

    function mint(address _to, uint256 _value) isMinter public returns (bool) {
        totalSupply += _value;
        balanceOf[_to] += _value;

        emit Transfer(address(0), _to, _value);
        return true;
    }

    event Transfer(address indexed _from, address indexed _to, uint256 _value);
    event Approval(address indexed _owner, address indexed _spender, uint256 _value);
}
