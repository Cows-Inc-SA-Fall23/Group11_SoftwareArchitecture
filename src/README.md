# Group 11 Software Architecture Project

This project is a comprehensive software architecture solution, developed by
Group 11. It includes an API, a control panel container, a publisher, and a
subscriber. The project also includes Docker configurations for production and
development environments.

## Project Structure

- `src/`: This is the main directory where all the source code for the project
  resides.
  - `api/`: Contains the source code for the API that handles the communication
    between the different components of the project.
  - `controlPanelContainer/`: Contains the source code for the control panel
    container, which provides a user interface for interacting with the system.
  - `publisher/`: Contains the source code for the publisher, which is
    responsible for publishing messages to the system.
  - `subscriber/`: Contains the source code for the subscriber, which is
    responsible for receiving and processing messages from the publisher.
  - `docker-compose.yml`: This file is used to define and run multi-container
    Docker applications. It specifies the services, networks, and volumes for
    the application.
  - `docker-compose.prod.yml`: This is a Docker Compose file specifically for
    the production environment.
  - `docker-hive-master/`: Contains Docker configurations for the HDFS and Hive
    services.
- `report/`: Contains LaTeX files for the project report and reflections. It
  also includes a Docker Compose file and a Dockerfile for building the report
  in a Docker container.
- `images/`: Contains images used in the project.
- `.github/workflows/`: Contains GitHub Actions workflows for continuous
  integration and continuous deployment (CI/CD) and for submitting the project
  (handin).

## Prerequisites

To run this project, you will need:

- Docker
- Docker Compose
- A LaTeX distribution if you want to build the report locally

## Running the Project

1. Clone the repository.
2. Navigate to the `src/` directory.
3. Run `docker-compose up` to start the services.

## Building the Report

To build the report, navigate to the `report/` directory and run
`docker-compose -f docker-compose-latex.yml up`.
