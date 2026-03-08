# 🎬 CineTrack API | AI-Powered Movie Explorer

![Java](https://img.shields.io/badge/Java-17-orange.svg)
![Spring Boot](https://img.springframework.io/badges/spring-boot-3.svg)
![Docker](https://img.shields.io/badge/Docker-Ready-blue.svg)
![MySQL](https://img.shields.io/badge/MySQL-Database-lightgrey.svg)

CineTrack is a modern, full-stack movie library application built with a **Layered Architecture** in Spring Boot. It goes beyond standard CRUD operations by integrating external APIs and a smart recommendation engine to provide a seamless, cinematic user experience.

## ✨ Key Features

* **🤖 Smart Recommendation Engine:** Utilizes Java Stream API to analyze user preferences and suggest movies based on genre, employing a fail-fast mechanism for data safety.
* **🌐 TMDB API Integration:** Automatically fetches high-quality movie posters in the background via `RestTemplate` when a new movie is added. No manual URL entry is required.
* **🐳 Dockerized Infrastructure:** The entire stack (Backend + MySQL Database) is containerized using Docker Compose for a frictionless, one-click local setup.
* **🎨 Dynamic Glassmorphism UI:** A responsive vanilla HTML/CSS/JS frontend featuring dynamic background transitions (hover effects) powered by backend API data.
* **🛡️ Bulletproof Architecture:** Implements Constructor Injection (`@RequiredArgsConstructor`) for immutable dependencies and strict validation to prevent database pollution.

## 🛠️ Technology Stack

**Backend:**
* Java 17
* Spring Boot (Web, Data JPA)
* Lombok (For boilerplate reduction & safe dependency injection)
* RESTful API Architecture

**Frontend:**
* Vanilla HTML5, CSS3, JavaScript (Fetch API)
* CSS Variables & Glassmorphism Design

**Database & DevOps:**
* MySQL
* Docker & Docker Compose

## 🚀 Getting Started

Follow these instructions to get a copy of the project up and running on your local machine.

### Prerequisites
* [Docker Desktop](https://www.docker.com/products/docker-desktop) installed and running.
* A valid API Key from [TMDB (The Movie Database)](https://www.themoviedb.org/).

### Installation

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/YOUR_USERNAME/cinetrack-api.git](https://github.com/YOUR_USERNAME/cinetrack-api.git)
   cd cinetrack-api
