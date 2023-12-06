const { ethers } = require("ethers");
const fs = require("fs");

const provider = new ethers.providers.JsonRpcProvider("https://arb1.arbitrum.io/rpc");
const privateKey = "your-private-key"; // Replace with your wallet's private key
const wallet = new ethers.Wallet(privateKey, provider);

const main = async () => {
  const ElysiumTokenArtifact = fs.readFileSync("ElysiumOS/blockchain/contracts/ElysiumToken.sol", "utf8");
  const ElysiumTokenCompiled = JSON.parse(fs.readFileSync("ElysiumOS/blockchain/build/contracts/ElysiumToken.json", "utf8"));

  // Assuming ElysiumTokenCompiled contains ABI and bytecode after compilation
  const ElysiumTokenABI = ElysiumTokenCompiled.abi;
  const ElysiumTokenBytecode = ElysiumTokenCompiled.bytecode;

  const ElysiumTokenFactory = new ethers.ContractFactory(ElysiumTokenABI, ElysiumTokenBytecode, wallet);

  console.log("Deploying ElysiumToken contract...");
  const initialSupply = ethers.utils.parseUnits("1000000", 18); // 1,000,000 tokens with 18 decimals
  const ElysiumToken = await ElysiumTokenFactory.deploy(initialSupply);
  await ElysiumToken.deployed();

  console.log(`ElysiumToken contract deployed at address: ${ElysiumToken.address}`);
};

main().catch((error) => {
  console.error("An error occurred during contract deployment:", error);
  process.exit(1);
});
