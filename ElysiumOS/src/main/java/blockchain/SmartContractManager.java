```java
package blockchain;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import contracts.ElysiumToken;
import java.math.BigInteger;

public class SmartContractManager {

    private final Web3j web3j;
    private final ContractGasProvider gasProvider;
    private final String privateKey;
    private final String contractAddress;

    public SmartContractManager(String ethereumNodeUrl, String privateKey, String contractAddress) {
        this.web3j = Web3j.build(new HttpService(ethereumNodeUrl));
        this.gasProvider = new DefaultGasProvider();
        this.privateKey = privateKey;
        this.contractAddress = contractAddress;
    }

    public ElysiumToken loadContract() {
        return ElysiumToken.load(contractAddress, web3j, Credentials.create(privateKey), gasProvider);
    }

    public String deployContract() throws Exception {
        return ElysiumToken.deploy(web3j, Credentials.create(privateKey), gasProvider).send().getContractAddress();
    }

    public BigInteger getTokenBalance(String ownerAddress) throws Exception {
        ElysiumToken contract = loadContract();
        return contract.balanceOf(ownerAddress).send();
    }

    public TransactionReceipt transferTokens(String recipientAddress, BigInteger amount) throws Exception {
        ElysiumToken contract = loadContract();
        return contract.transfer(recipientAddress, amount).send();
    }

    // Additional smart contract interaction methods can be added here

    // Clean up resources when done
    public void shutdown() {
        web3j.shutdown();
    }
}
```