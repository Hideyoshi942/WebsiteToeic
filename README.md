# Website to learn and practice Toeic exam using Vuejs and Spring Boot

Welcome to the TOEIC Learning and Practice Website project. This is a web application designed to help users learn and practice for the TOEIC exam through multiple-choice questions, listening and reading exercises, and additional helpful features. The application is built with Vue.js for the Front-End and Spring Boot for the Back-End, providing a smooth and efficient user experience.

## Mục lục

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
  - [Frontend Setup](#frontend-setup)
  - [Backend Setup](#backend-setup)
- [Usage](#usage)
- [Contact](#contact)

## Features

- **Comprehensive TOEIC Practice**: Includes listening and reading practice tests to help users improve their TOEIC score.
- **Practice Mode & Mock Test**: Users can practice freely or take timed mock tests to simulate the real TOEIC exam environment.
- **Track Progress**: Keep track of results, correct answers, and incorrect answers for each session.
- **Countdown Timer**: A countdown timer is available for each test to simulate real exam conditions.
- **Responsive Design**: The application is fully responsive and works seamlessly on both desktop and mobile devices.

## Technologies Used

- **Frontend**:
  - Vue.js: For building the interactive and dynamic user interface.
  - Axios: To make HTTP requests to the backend API.
  - Bootstrap 5: For responsive design and styling.
  - HTML/CSS: Basic frontend technologies for page structure and styling.

- **Backend**:
  - Spring Boot: A Java-based framework for building RESTful web applications and APIs.
  - SQLServer: For database management, storing user data, questions, and test results.
  - RESTful API: Used to handle API calls for fetching questions and submitting test results.


## Installation

- **Frontend Setup**:
  - Clone the repository:
  ```bash
    git clone https://github.com/Hideyoshi942/WebsiteToeic
  ```
  - Navigate to the frontend directory:
  ```bash
    cd WebsiteToeic
  ```
  - Install dependencies:
  ```bash
    npm install --global yarn
  ```
  - Start the development server:
  ```bash
    yarn serve
  ```
  The frontend will be available at http://localhost:8081.
- **Backend Setup**:
  - Clone the repository:
  ```bash
    git clone https://github.com/Hideyoshi942/WebsiteToeic
  ```
  - Navigate to the backend directory:
  ```bash
    cd WebsiteToeic
  ```
  - Install dependencies:
  ```bash
    mvn clean install
  ```
  - Run the Spring Boot application:
  ```bash
    mvn spring-boot:run
  ```
  The backend  will be available at http://localhost:8080.
  
- **Usage**:
  - Access the application:
    After the frontend and backend are running, open your browser and go to http://localhost:8081.
  - Create an Account / Log In:
    You can register or log in to start practicing TOEIC questions.
  - Start Practicing:
    - Select practice tests (Listening or Reading).
    - Choose to take a mock test or practice freely.
    - Timer will count down during mock tests to simulate the real exam.
  - View Test Results:
    After completing the practice or mock test, view your results including correct and incorrect answers, and overall performance.
## Contact
If you have any questions or need help with the application, feel free to reach out:
  - Email:unclekiet2424@gmail.com
  - GitHub Repository:https://github.com/Hideyoshi942/WebsiteToeic
