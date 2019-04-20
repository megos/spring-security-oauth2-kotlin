# spring-security-oauth2-kotlin
Spring Security OAuth2 example made with Kotlin

[![CircleCI](https://circleci.com/gh/megos/spring-security-oauth2-kotlin.svg?style=svg)](https://circleci.com/gh/megos/spring-security-oauth2-kotlin)

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
{"access_token":"[your_access_token]","token_type":"bearer","expires_in":43199,"scope":"read"}
```
3. GET resource
```bash
$ curl -H "Authorization: Bearer [your_access_token]" localhost:8080/hello
Hello!
```
