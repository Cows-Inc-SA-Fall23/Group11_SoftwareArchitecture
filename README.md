# Group 11 Software Architecture Project

This project is a comprehensive software architecture solution, developed by
Group 11. It includes several components: a control panel container, a
development Tomcat server, a feed tracking container, a lab system container,
and a sensors manager container. Each component has its own Dockerfile for
containerization. The project also includes Docker configurations for both
production and development environments, as well as Docker configurations for
HDFS and Hive services. The project is managed using GitHub Actions for
continuous integration and continuous deployment (CI/CD).

## Project Structure

- `src/`: The main directory where all the source code for the project resides.
  - `controlPanelContainer/`: Contains the source code for the control panel
    container. This is a user interface that allows users to interact with the
    system.
  - `dev-tomcat/`: Contains the source code and Dockerfile for the development
    Tomcat server.
  - `feedTrackingContainer/`: Contains the source code and Dockerfile for the
    feed tracking container. This component is responsible for tracking the feed
    data.
  - `labSystemContainer/`: Contains the source code and Dockerfile for the lab
    system container. This component is responsible for managing the lab system.
  - `sensorsManagerContainer/`: Contains the source code and Dockerfile for the
    sensors manager container. This component is responsible for managing the
    sensors.
  - `docker-compose.yml` and `docker-compose.prod.yml`: These files are used to
    define and run the application as a multi-container Docker application. They
    specify the services, networks, and volumes that make up the application.
    The `.prod.yml` file is specifically for configurations in a production
    environment.
  - `docker-hive-master/`: Contains Docker configurations for the HDFS (Hadoop
    Distributed File System) and Hive services. These are big data technologies
    used for storing and analyzing large datasets.
- `report/`: Contains LaTeX files for the project report and reflections. It
  also includes a Docker Compose file and a Dockerfile for building the report
  in a Docker container.
- `images/`: Contains images used in the project. These could be diagrams,
  screenshots, or any other visual aids used in the documentation or the user
  interface.
- `.github/workflows/`: Contains configuration files for GitHub Actions, which
  is a tool for automating software workflows. This is used for continuous
  integration and continuous deployment (CI/CD), which automates the process of
  testing and deploying the application. It's also used for submitting the
  project (handin).

## Prerequisites

To run this project, you will need:

- Docker
- Docker Compose
- A LaTeX distribution if you want to build the report locally

To push the project to the server, you will need:

- A GitHub account
- The SDU VPN client
- A local Github action runner (see
  [this guide](https://docs.github.com/en/actions/hosting-your-own-runners/adding-self-hosted-runners))

## Running the Project

1. Clone the repository.
2. Navigate to the `src/` directory.
3. Run `docker-compose up` to start the services.

## Building the Report

To build the report, navigate to the `report/` directory and run
`docker-compose -f docker-compose-latex.yml up`.
