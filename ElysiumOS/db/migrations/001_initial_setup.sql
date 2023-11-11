-- Elysium OS Database Initial Setup
-- Migration 001_initial_setup.sql

-- Create Users Table
CREATE TABLE IF NOT EXISTS users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash CHAR(64) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Create Automations Table
CREATE TABLE IF NOT EXISTS automations (
    automation_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(user_id),
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Create Arena Table
CREATE TABLE IF NOT EXISTS arena (
    arena_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(user_id),
    arena_name VARCHAR(255) NOT NULL,
    configuration JSONB,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Create Blockchain Transactions Table
CREATE TABLE IF NOT EXISTS blockchain_transactions (
    transaction_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(user_id),
    transaction_hash CHAR(66) NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Create Virtual Assets Table
CREATE TABLE IF NOT EXISTS virtual_assets (
    asset_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(user_id),
    asset_type VARCHAR(50) NOT NULL,
    asset_data JSONB,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Create Marketplace Transactions Table
CREATE TABLE IF NOT EXISTS marketplace_transactions (
    marketplace_transaction_id SERIAL PRIMARY KEY,
    seller_id INT REFERENCES users(user_id),
    buyer_id INT REFERENCES users(user_id),
    asset_id INT REFERENCES virtual_assets(asset_id),
    price DECIMAL(19, 4) NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Create Virtual Pets Table
CREATE TABLE IF NOT EXISTS virtual_pets (
    pet_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(user_id),
    pet_name VARCHAR(255) NOT NULL,
    pet_type VARCHAR(50) NOT NULL,
    pet_data JSONB,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Create AI Agents Table
CREATE TABLE IF NOT EXISTS ai_agents (
    agent_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(user_id),
    agent_name VARCHAR(255) NOT NULL,
    agent_type VARCHAR(50) NOT NULL,
    agent_data JSONB,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Create Sessions Table
CREATE TABLE IF NOT EXISTS sessions (
    session_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(user_id),
    session_token CHAR(64) NOT NULL,
    expires_at TIMESTAMP WITH TIME ZONE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Create Elysium Tokens Table
CREATE TABLE IF NOT EXISTS elysium_tokens (
    token_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(user_id),
    balance DECIMAL(19, 4) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Indexes and other constraints can be added here as needed

-- End of Migration 001_initial_setup.sql