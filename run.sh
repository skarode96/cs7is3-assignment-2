#!/bin/bash

sh compile.sh

export SVR_HOME="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

rm -rf Index

JAVA_MAIN='ie.tcd.newssearch.App'
JAVA_TUNE='-server -Xmx4g'

java ${JAVA_TUNE} -cp .:${SVR_HOME}/newssearch-1.0.jar ${JAVA_MAIN}

sh run_trec_eval.sh
