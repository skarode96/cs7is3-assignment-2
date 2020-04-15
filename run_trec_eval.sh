#!/usr/bin/env bash

# BM25 with all Analyzers
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Custom-BM25 > output/Results-CUSTOM-BM25.txt
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Simple-BM25 > output/Results-Simple-BM25.txt
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/English-BM25 > output/Results-English-BM25.txt
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Standard-BM25 > output/Results-Standard-BM25.txt



# Boolean with all Analyzers
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Custom-Boolean > output/Results-CUSTOM-Boolean.txt
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Simple-Boolean > output/Results-Simple-Boolean.txt
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/English-Boolean > output/Results-English-Boolean.txt
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Standard-Boolean > output/Results-Standard-Boolean.txt




# Multi with all Analyzers
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Custom-Multi > output/Results-CUSTOM-Multi.txt
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Simple-Multi > output/Results-Simple-Multi.txt
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/English-Multi > output/Results-English-Multi.txt
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Standard-Multi > output/Results-Standard-Multi.txt




# Classic with all Analyzers
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Custom-Classic > output/Results-CUSTOM-Classic.txt
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Simple-Classic > output/Results-Simple-Classic.txt
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/English-Classic > output/Results-English-Classic.txt
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Standard-Classic > output/Results-Standard-Classic.txt


# LMDirichlet with all Analyzers
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Custom-Boolean > output/Results-CUSTOM-Boolean.txt
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Simple-Boolean > output/Results-Simple-Boolean.txt
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/English-Boolean > output/Results-English-Boolean.txt
"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Standard-Boolean > output/Results-Standard-Boolean.txt


