# PetStore Application

## Introduction

MicroProfile Starter has generated this MicroProfile application for you.

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Packaging and running the application

If you want to build an _??ber-jar_, execute the following command:

    ./gradlew build -Dquarkus.package.type=uber-jar

To run the application:

    java -jar build/petstore-runner.jar

The application can be also packaged using simple:

    ./gradlew build

## How to run test cases

    ./gradlew test

## How to run curl/wget 

##### Get all pets

    curl --location --request GET 'http://localhost:8080/v1/pets'

##### Add pet

    curl --location --request POST 'http://localhost:8080/v1/pets' --header 'Content-Type: application/json' --data-raw '{"petId": 1,"petType": "Cat","petName": "Kitty","petAge": 3}'

##### Update pet

    curl --location --request PATCH 'http://localhost:8080/v1/pets/1' --header 'Content-Type: application/json' --data-raw '{"petId": 1,"petType": "Cat","petName": "Kitty","petAge": 3}'

##### Delete pet

    curl --location --request DELETE 'http://localhost:8080/v1/pets'