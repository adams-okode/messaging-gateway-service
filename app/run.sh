#!/bin/sh
export $(cat .env | xargs)
./gradlew bootRun