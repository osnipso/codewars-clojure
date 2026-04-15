;; Kata: Regex count lowercase letters
;; URL:  https://www.codewars.com/kata/56a946cd7bd95ccab2000055
;; Kyu:  8
;;
;; Description:
;;   Your task is simply to count the total number of lowercase letters in a string.
;;
;;   e.g. "abc" ===> 3
;;        "abcABC123" ===> 3
;;        "abcABC123!@#$%^&*()_-+=}{[]|':;?/>.<,~" ===> 3
;;        "" ===> 0
;;        "ABC123!@#$%^&*()_-+=}{[]|':;?/>.<,~" ===> 0
;;        "abcdefghijklmnopqrstuvwxyz"`===> 26
;;
;; Tags: Fundamentals, Regular Expressions, Algorithms
;;

(ns katas.kyu-8.regex-count-lowercase-letters)

;; --- Solution 1: count/re-seq ---

(defn solution-1
  [strng]
  (count (re-seq #"[a-z]" strng)))

;; --- Active solution (the one tests run against) ---

(def solution solution-1)
