// blockchain.js - Handles interactions with the Arbitrum blockchain for Elysium OS

const Web3 = require('web3');
const ElysiumTokenContract = require('../blockchain/contracts/ElysiumToken.sol');

// Initialize web3 instance connected to the Arbitrum network
const web3 = new Web3(new Web3.providers.HttpProvider('https://arbitrum-rinkeby.infura.io/v3/YOUR_INFURA_PROJECT_ID'));

// Elysium Token contract instance
const elysiumToken = new web3.eth.Contract(ElysiumTokenContract.abi, 'CONTRACT_ADDRESS');

// Function to initiate a blockchain transaction
function initiateBlockchainTransaction(fromAddress, toAddress, amount, privateKey) {
  const transactionObject = {
    from: fromAddress,
    to: toAddress,
    value: web3.utils.toWei(amount.toString(), 'ether'),
    gas: 3000000 // Set gas limit
  };

  return web3.eth.accounts.signTransaction(transactionObject, privateKey)
    .then(signedTx => web3.eth.sendSignedTransaction(signedTx.rawTransaction))
    .then(receipt => {
      console.log('Transaction successful with hash:', receipt.transactionHash);
      return receipt.transactionHash;
    })
    .catch(err => {
      console.error('Transaction failed:', err);
      throw err;
    });
}

// Function to deploy a smart contract
function deploySmartContract(contractData, fromAddress, privateKey) {
  const deployTx = {
    data: contractData,
    arguments: [], // Constructor arguments if any
    from: fromAddress,
    gas: 3000000 // Set gas limit
  };

  return web3.eth.accounts.signTransaction(deployTx, privateKey)
    .then(signedTx => web3.eth.sendSignedTransaction(signedTx.rawTransaction))
    .then(receipt => {
      console.log('Contract deployed at address:', receipt.contractAddress);
      return receipt.contractAddress;
    })
    .catch(err => {
      console.error('Contract deployment failed:', err);
      throw err;
    });
}

// Function to interact with a smart contract
function callSmartContractFunction(contractInstance, functionName, params, fromAddress) {
  return contractInstance.methods[functionName](...params)
    .send({ from: fromAddress })
    .then(receipt => {
      console.log(`Function ${functionName} executed with transaction hash:`, receipt.transactionHash);
      return receipt.transactionHash;
    })
    .catch(err => {
      console.error(`Error executing function ${functionName}:`, err);
      throw err;
    });
}

// Export functions for use in other modules
module.exports = {
  initiateBlockchainTransaction,
  deploySmartContract,
  callSmartContractFunction
};