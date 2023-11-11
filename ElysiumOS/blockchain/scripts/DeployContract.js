const { ethers } = require("ethers");
const fs = require("fs");

const provider = new ethers.providers.JsonRpcProvider("http://localhost:8545");
const privateKey = "your-private-key"; // Replace with your wallet's private key
const wallet = new ethers.Wallet(privateKey, provider);

const main = async () => {
  const ElysiumTokenArtifact = fs.readFileSync("ElysiumOS/blockchain/contracts/ElysiumToken.sol", "utf8");
  const ElysiumTokenCompiled = ethers.utils.parseUnits(ElysiumTokenArtifact, "ether");

  // Assuming ElysiumTokenCompiled contains ABI and bytecode after compilation
  const ElysiumTokenABI = ElysiumTokenCompiled.abi;
  const ElysiumTokenBytecode = ElysiumTokenCompiled.bytecode;

  const ElysiumTokenFactory = new ethers.ContractFactory(ElysiumTokenABI, ElysiumTokenBytecode, wallet);

  console.log("Deploying ElysiumToken contract...");
  const ElysiumToken = await ElysiumTokenFactory.deploy(); // Add constructor arguments if needed
  await ElysiumToken.deployed();

  console.log(`ElysiumToken contract deployed at address: ${ElysiumToken.address}`);
};

main().catch((error) => {
  console.error(error);
  process.exitCode = 1;
});