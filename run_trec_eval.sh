#!/usr/bin/env bash

# BM25 with all Analyzers
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Custom-BM25 > output/Results-CUSTOM-BM25
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Simple-BM25 > output/Results-Simple-BM25
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/English-BM25 > output/Results-English-BM25
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Standard-BM25 > output/Results-Standard-BM25



# Boolean with all Analyzers
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Custom-Boolean > output/Results-CUSTOM-Boolean
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Simple-Boolean > output/Results-Simple-Boolean
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/English-Boolean > output/Results-English-Boolean
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Standard-Boolean > output/Results-Standard-Boolean




# Multi with all Analyzers
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Custom-Multi > output/Results-CUSTOM-Multi
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Simple-Multi > output/Results-Simple-Multi
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/English-Multi > output/Results-English-Multi
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Standard-Multi > output/Results-Standard-Multi




# Classic with all Analyzers
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Custom-Classic > output/Results-CUSTOM-Classic
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Simple-Classic > output/Results-Simple-Classic
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/English-Classic > output/Results-English-Classic
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Standard-Classic > output/Results-Standard-Classic


# LMDirichlet with all Analyzers
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Custom-Boolean > output/Results-CUSTOM-Boolean
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Simple-Boolean > output/Results-Simple-Boolean
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/English-Boolean > output/Results-English-Boolean
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Standard-Boolean > output/Results-Standard-Boolean


