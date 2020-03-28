#!/bin/bash

export SVR_HOME="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

JAVA_MAIN='ie.tcd.newssearch.App'
JAVA_TUNE='-server -Xms3072m -Xmx4096m'

java ${JAVA_TUNE} -cp .:${SVR_HOME}/newssearch-1.0.jar ${JAVA_MAIN}