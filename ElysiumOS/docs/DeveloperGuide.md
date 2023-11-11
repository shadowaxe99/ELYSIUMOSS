# Elysium OS Developer Guide

Welcome to the Elysium OS Developer Guide. This document is intended for developers who are interested in contributing to the Elysium OS project or building applications on top of it. Below you will find guidelines and best practices for working with the Elysium OS codebase, as well as an overview of its architecture and key components.

## Getting Started

Before you begin, ensure you have the following prerequisites installed on your system:

- Java Development Kit (JDK) 11 or higher
- Git for version control
- An Integrated Development Environment (IDE) like IntelliJ IDEA or Eclipse
- Docker for containerization and deployment
- Access to the Arbitrum blockchain network

Clone the Elysium OS repository using Git:

```
git clone https://github.com/elysium-os/ElysiumOS.git
cd ElysiumOS
```

## Project Structure

The Elysium OS project is structured as follows:

- `src/`: Contains all the Java source code for the project.
  - `main/java/`: The main application code.
    - `ElysiumOS.java`: The main application entry point.
    - `config/`: Configuration classes for different modules.
    - `automation/`: Code related to the Automation Station feature.
    - `arena/`: Code for managing The Arena gameplay.
    - `blockchain/`: Blockchain service and smart contract management.
    - `world3d/`: Rendering of 3D worlds and scenes.
  - `resources/`: Resources such as images and property files.
- `docs/`: Documentation for the project.
- `ui/`: Front-end web assets including HTML, CSS, and JavaScript files.
- `api/`: Back-end API setup with REST controllers and services.
- `blockchain/`: Smart contracts and related scripts for the Arbitrum blockchain.
- `world3d/`: 3D models, textures, and scene scripts.
- `db/`: Database schema and migration files.
- `build/`: Compiled application artifacts.
- `deploy/`: Deployment configurations and scripts.

## Building the Project

To build Elysium OS, run the following command from the root directory:

```
./gradlew build
```

This will compile the Java code, run tests, and generate the `ElysiumOS.jar` file in the `build/` directory.

## Running the Application

To start the Elysium OS application, execute the following command:

```
java -jar build/ElysiumOS.jar
```

## Contributing Guidelines

We welcome contributions from the community. Here are some ways you can contribute:

- Reporting bugs
- Suggesting enhancements
- Writing documentation
- Submitting pull requests with new features or bug fixes

Please follow our coding standards and submit your code for review via GitHub pull requests.

## Testing

Automated tests are an essential part of Elysium OS. Ensure that your code is well-tested and that all tests pass before submitting a pull request.

Run the tests using:

```
./gradlew test
```

## Deployment

Elysium OS can be deployed using Docker and Kubernetes. Refer to the `Dockerfile` and `kubernetes/deployment.yaml` for deployment configurations.

To build a Docker image, run:

```
docker build -t elysium-os .
```

To deploy to Kubernetes, apply the deployment configuration:

```
kubectl apply -f deploy/kubernetes/deployment.yaml
```

## Documentation

Keep the documentation up to date. When adding new features or making changes that affect how users interact with Elysium OS, update the relevant documentation in the `docs/` directory.

## Support

If you need help or have questions, please open an issue on the GitHub repository.

Thank you for contributing to Elysium OS and helping us build the operating system for a new era.