#!/bin/bash

mvn clean package -DskipTests
mv ./target/newssearch-1.0.jar .