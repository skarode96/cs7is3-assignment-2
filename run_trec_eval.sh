#!/usr/bin/env bash

# BM25 with all Analyzers

"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/query_results.txt

#Best Custom + BM25 => map 0.3419
#"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Custom-BM25 > output/TrecResults-Custom-BM25.txt

#"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Simple-BM25 > output/TrecResults-Simple-BM25.txt
#"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/English-BM25 > output/TrecResults-English-BM25.txt
#"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Standard-BM25 > output/TrecResults-Standard-BM25.txt
#"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Stop-BM25 > output/TrecResults-Stop-BM25.txt
#"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Whitespace-BM25 > output/TrecResults-Whitespace-BM25.txt
#
## Boolean with all Analyzers
#"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Custom-Boolean > output/TrecResults-Custom-Boolean.txt
#"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Simple-Boolean > output/TrecResults-Simple-Boolean.txt
#"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/English-Boolean > output/TrecResults-English-Boolean.txt
#"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Standard-Boolean > output/TrecResults-Standard-Boolean.txt
#"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Stop-Boolean > output/TrecResults-Stop-Boolean.txt
#"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Whitespace-Boolean > output/TrecResults-Whitespace-Boolean.txt
#
#
#
## Multi with all Analyzers
#"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Custom-Multi > output/TrecResults-Custom-Multi.txt
#"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Simple-Multi > output/TrecResults-Simple-Multi.txt
#"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/English-Multi > output/TrecResults-English-Multi.txt
#"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Standard-Multi > output/TrecResults-Standard-Multi.txt
#"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Stop-Multi > output/TrecResults-Stop-Multi.txt
#"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Whitespace-Multi > output/TrecResults-Whitespace-Multi.txt
#
#
#
## Classic with all Analyzers
#"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Custom-Classic > output/TrecResults-Custom-Classic.txt
#"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Simple-Classic > output/TrecResults-Simple-Classic.txt
#"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/English-Classic > output/TrecResults-English-Classic.txt
#"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Standard-Classic > output/TrecResults-Standard-Classic.txt
#"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Stop-Classic > output/TrecResults-Stop-Classic.txt
#"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Whitespace-Classic > output/TrecResults-Whitespace-Classic.txt
#
#
#
## LMDirichlet with all Analyzers
#"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Custom-LMDirichlet > output/TrecResults-Custom-LMDirichlet.txt
#"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Simple-LMDirichlet > output/TrecResults-Simple-LMDirichlet.txt
#"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/English-LMDirichlet > output/TrecResults-English-LMDirichlet.txt
#"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Standard-LMDirichlet > output/TrecResults-Standard-LMDirichlet.txt
#"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Stop-LMDirichlet > output/TrecResults-Stop-LMDirichlet.txt
#"$(pwd)"/trec_eval.9.0/trec_eval -m all_trec "$(pwd)"/dataset/qrels.assignment2.part1 "$(pwd)"/output/Whitespace-LMDirichlet > output/TrecResults-Whitespace-LMDirichlet.txt
#
#
