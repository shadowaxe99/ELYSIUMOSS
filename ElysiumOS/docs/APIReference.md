# Elysium OS API Reference

## Overview
This document provides the API reference for Elysium OS, detailing the endpoints available for interacting with the system's core features: Automation Station, The Arena, and the Arbitrum Blockchain integration.

## Base URL
The base URL for all API endpoints is `https://api.elysiumos.com/v1`

## Authentication
All API requests require the use of a JWT token obtained after a successful login. This token must be included in the header of every request.

### POST /api/auth/login
- Description: Authenticate a user and retrieve a token.
- Request Body:
  ```json
  {
    "username": "user",
    "password": "password"
  }
  ```
- Response:
  ```json
  {
    "token": "jwt_token"
  }
  ```

## Automation Station

### POST /api/automation/create
- Description: Create a new automation task.
- Request Body:
  ```json
  {
    "name": "Task Name",
    "description": "Task Description",
    "script": "automation_script"
  }
  ```
- Response:
  ```json
  {
    "message": "AUTOMATION_CREATED",
    "taskId": "task_id"
  }
  ```

### GET /api/automation/tasks
- Description: Retrieve a list of all automation tasks.
- Response:
  ```json
  [
    {
      "taskId": "task_id",
      "name": "Task Name",
      "description": "Task Description",
      "status": "Task Status"
    }
  ]
  ```

## The Arena

### POST /api/arena/start
- Description: Start a new game in The Arena.
- Request Body:
  ```json
  {
    "gameType": "Game Type",
    "participants": ["participant1", "participant2"]
  }
  ```
- Response:
  ```json
  {
    "message": "GAME_STARTED",
    "gameId": "game_id"
  }
  ```

### GET /api/arena/games
- Description: Retrieve a list of all games in The Arena.
- Response:
  ```json
  [
    {
      "gameId": "game_id",
      "gameType": "Game Type",
      "status": "Game Status"
    }
  ]
  ```

## Blockchain Integration

### POST /api/blockchain/transaction
- Description: Initiate a new blockchain transaction.
- Request Body:
  ```json
  {
    "from": "sender_address",
    "to": "recipient_address",
    "amount": "amount",
    "token": "ElysiumToken"
  }
  ```
- Response:
  ```json
  {
    "message": "BLOCKCHAIN_TRANSACTION_INITIATED",
    "transactionId": "transaction_id"
  }
  ```

### GET /api/blockchain/transactions
- Description: Retrieve a list of all blockchain transactions.
- Response:
  ```json
  [
    {
      "transactionId": "transaction_id",
      "from": "sender_address",
      "to": "recipient_address",
      "amount": "amount",
      "token": "ElysiumToken",
      "status": "Transaction Status"
    }
  ]
  ```

## Error Handling
All endpoints return a standard error format in the case of a failure.

- Response:
  ```json
  {
    "error": "Error Type",
    "message": "Error Message"
  }
  ```

Please refer to the individual endpoint documentation for more detailed information on request and response formats.