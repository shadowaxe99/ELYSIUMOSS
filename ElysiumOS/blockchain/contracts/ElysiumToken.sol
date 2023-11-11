// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

import "@openzeppelin/contracts/token/ERC20/ERC20.sol";
import "@openzeppelin/contracts/access/Ownable.sol";

/**
 * @title ElysiumToken
 * @dev Implementation of the Elysium Token for the Elysium OS.
 * This ERC20 token is used within the Elysium OS ecosystem to facilitate
 * transactions, rewards, and governance.
 */
contract ElysiumToken is ERC20, Ownable {
    /**
     * @dev Sets the values for {name} and {symbol}, initializes {decimals} with
     * a default value of 18. Mints the initial supply to the deployer address.
     *
     * @param initialSupply The initial supply of tokens, in smallest unit considering decimals.
     */
    constructor(uint256 initialSupply) ERC20("ElysiumToken", "ELY") {
        _mint(msg.sender, initialSupply);
    }

    /**
     * @dev Function to mint tokens
     * Can only be called by the owner.
     *
     * @param to The address that will receive the minted tokens.
     * @param amount The amount of tokens to mint.
     */
    function mint(address to, uint256 amount) public onlyOwner {
        _mint(to, amount);
    }

    /**
     * @dev Function to burn tokens
     * Can only be called by the owner.
     *
     * @param from The address from which tokens will be burned.
     * @param amount The amount of tokens to burn.
     */
    function burn(address from, uint256 amount) public onlyOwner {
        _burn(from, amount);
    }
}