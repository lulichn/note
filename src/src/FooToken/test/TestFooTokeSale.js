var FooToken = artifacts.require("./FooToken.sol");
var FooTokenSale = artifacts.require("./FooTokenSale.sol");

function num(n) {
  return new web3.BigNumber(web3.toWei(n, 'ether'));
}

contract('FooTokenSale', async function(accounts) {
  var sale;

  describe('initialized correctly', () => {
    var token, sale;
    
    it ("should be correct token", async function() {
      token = await FooToken.deployed();
      sale  = await FooTokenSale.deployed();

      const expect = await token.address;
      const actual = await sale.token();

      assert.equal(actual, expect, "Token is");
    });

    it ("should be correct minter", async function() {
      const expect = await sale.address;
      const actual = await token.minter();

      assert.equal(actual, expect, "Token minter is");
    });

    it ("should be correct wallet", async function() {
      const expect = await accounts[5];
      const actual = await sale.wallet();

      assert.equal(actual, expect, "wallet is");
    });
  });

  it ('should add address into WhiteList', async function() {
    const sale = await FooTokenSale.deployed();

    const isAllow = await sale.whiteList(accounts[0]);
    assert(! isAllow, "is not allow");
    
    const isSuccess = await sale.addWhiteList([accounts[0]]);
    assert(isSuccess, 0, "is success");
    
    const isAllow2 = await sale.whiteList(accounts[0]);
    assert(isAllow2, "is allow");
  });

  it ('should buy token', async function() {
    const token = await FooToken.deployed();
    const sale = await FooTokenSale.deployed();
    
    const beforeAmount = await token.balanceOf(accounts[1]);
    assert.equal(beforeAmount.toNumber(), 0, "Init is 0");

    await sale.addWhiteList([accounts[1]]);
    await sale.buyToken.sendTransaction(accounts[1], {
        from: accounts[1],
        value: web3.toWei(1, "ether")
      });

    const afterAmount = await token.balanceOf(accounts[1]);
    assert.equal(afterAmount.toNumber(), 1000, "1000");

    const newBalanceOfBeneficiary = await web3.eth.getBalance(accounts[5]);
    assert.equal(web3.fromWei(newBalanceOfBeneficiary.toNumber(), "ether"), 101, "Received");
  });

  it ('should not buy token', async function() {
    const token = await FooToken.deployed();
    const sale = await FooTokenSale.deployed();
    
    const beforeAmount = await token.balanceOf(accounts[2]);
    assert.equal(beforeAmount.toNumber(), 0, "Init is 0");

    try {
      await sale.buyToken(accounts[2], {
        from: accounts[2],
        value: web3.toWei(1, "ether")
      });
    } catch (error) {
      revert = error.message.search('revert') >= 0;
      assert(revert, "Is revert");
    }
  });

  it ('one ether should not buy token', async function() {
    const token = await FooToken.deployed();
    const sale = await FooTokenSale.deployed();
    
    const beforeAmount = await token.balanceOf(accounts[3]);
    assert.equal(beforeAmount.toNumber(), 0, "Init is 0");

    await sale.addWhiteList([accounts[3]]);

    await sale.sendTransaction({
        from: accounts[3],
        value: web3.toWei(1, "ether")
      })
    
    const afterAmount = await token.balanceOf(accounts[3]);
    assert.equal(afterAmount.toNumber(), 1000, "1000");

        const newBalanceOfBeneficiary = await web3.eth.getBalance(accounts[5]);
    assert.equal(web3.fromWei(newBalanceOfBeneficiary.toNumber(), "ether"), 102, "Received");
  });
});
