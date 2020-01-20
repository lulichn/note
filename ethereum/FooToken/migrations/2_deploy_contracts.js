
var FooToken = artifacts.require("./FooToken.sol");
var FooTokenSale = artifacts.require("./FooTokenSale.sol");

module.exports = function(deployer, network, accounts) {
    const whiteList = [];

    var token, sale;

    return deployer
        .then(() => {
            return deployer.deploy(FooToken, 10000);
        }).then((instance) => {
            token = instance;
            return deployer.deploy(FooTokenSale, FooToken.address, accounts[5], whiteList);
        }).then((instance) => {
            sale = instance;
            token.setMinter(FooTokenSale.address);
            
            console.log("Token : " + FooToken.address);
            console.log("Sale  : " + FooTokenSale.address);
        });
};
