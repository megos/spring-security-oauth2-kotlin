# spring-security-oauth2-kotlin
Spring Security OAuth2 example made with Kotlin

## Requirement
- Java 1.8
- Gradle

## Setup

Install dependencies
```
$ gradle dependencies
```

## Usage

1. Launch server
```bash
$ gradle bootRun
```
2. Exec auth
```bash
$ curl -X POST -d client_id=client_id -d client_secret=client_secret -d grant_type=password -d username=user -d password=password http://localhost:8080/oauth/token
```
