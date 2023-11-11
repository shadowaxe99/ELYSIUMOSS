-- Elysium OS Database Migration - Update 002
-- Apply necessary changes to the database schema to support new features

BEGIN TRANSACTION;

-- Add new table for managing AI Agents
CREATE TABLE IF NOT EXISTS ai_agents (
    agent_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    agent_name VARCHAR(255) NOT NULL,
    agent_type VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    creation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Add new table for storing tasks assigned to AI Agents
CREATE TABLE IF NOT EXISTS agent_tasks (
    task_id SERIAL PRIMARY KEY,
    agent_id INT NOT NULL,
    task_type VARCHAR(50) NOT NULL,
    task_description TEXT,
    task_status VARCHAR(50) NOT NULL,
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    FOREIGN KEY (agent_id) REFERENCES ai_agents(agent_id)
);

-- Add new table for virtual items in The Arena
CREATE TABLE IF NOT EXISTS virtual_items (
    item_id SERIAL PRIMARY KEY,
    item_name VARCHAR(255) NOT NULL,
    item_description TEXT,
    item_rarity VARCHAR(50),
    creation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Add new table for user inventory to track virtual items
CREATE TABLE IF NOT EXISTS user_inventory (
    inventory_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    item_id INT NOT NULL,
    quantity INT DEFAULT 1,
    acquisition_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (item_id) REFERENCES virtual_items(item_id)
);

-- Update users table to include new fields for Elysium OS features
ALTER TABLE users
ADD COLUMN if not exists arena_rank VARCHAR(50),
ADD COLUMN if not exists reputation_score INT DEFAULT 0;

-- Add new table for marketplace transactions
CREATE TABLE IF NOT EXISTS marketplace_transactions (
    transaction_id SERIAL PRIMARY KEY,
    seller_id INT NOT NULL,
    buyer_id INT NOT NULL,
    item_id INT NOT NULL,
    quantity INT NOT NULL,
    transaction_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (seller_id) REFERENCES users(user_id),
    FOREIGN KEY (buyer_id) REFERENCES users(user_id),
    FOREIGN KEY (item_id) REFERENCES virtual_items(item_id)
);

COMMIT;