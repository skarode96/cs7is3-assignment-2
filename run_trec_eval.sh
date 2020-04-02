#!/usr/bin/env bash

"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/query_results
