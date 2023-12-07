package blockchain;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import contracts.ElysiumToken;
import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

public class BlockchainService {

    private final Web3j web3j;
    private final Credentials credentials;
    private final ContractGasProvider gasProvider;

    public BlockchainService(String ethereumNodeUrl, String privateKey) {
        this.web3j = Web3j.build(new HttpService(ethereumNodeUrl));
        this.credentials = Credentials.create(privateKey);
        this.gasProvider = new DefaultGasProvider();
    }

    public CompletableFuture<ElysiumToken> deployElysiumTokenContract() {
        try {
            return ElysiumToken.deploy(
                    web3j, credentials, gasProvider,
                    BigInteger.valueOf(1000000), // Initial supply
                    "ElysiumToken", // Token name
                    BigInteger.valueOf(18), // Decimals
                    "ELYS" // Token symbol
            ).sendAsync();
        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.failedFuture(e);
        }
    }

    public CompletableFuture<Uint256> getTokenBalance(String contractAddress, String ownerAddress) {
        ElysiumToken token = ElysiumToken.load(contractAddress, web3j, credentials, gasProvider);
        try {
            return token.balanceOf(new Address(ownerAddress)).sendAsync();
        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.failedFuture(e);
        }
    }

    public CompletableFuture<String> transferTokens(String contractAddress, String toAddress, BigInteger amount) {
        ElysiumToken token = ElysiumToken.load(contractAddress, web3j, credentials, gasProvider);
        try {
            return token.transfer(new Address(toAddress), new Uint256(amount)).sendAsync();
        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.failedFuture(e);
        }
    }

    // Additional blockchain-related methods can be added here

    // Close the connection to the Ethereum node
    public void shutdown() {
        web3j.shutdown();
    }
}