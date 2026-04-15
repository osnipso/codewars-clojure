;; Kata: Opposite number
;; URL:  https://www.codewars.com/kata/56dec885c54a926dcd001095
;; Kyu:  8
;;
;; Description:
;;   Very simple, given a number (integer / decimal / both depending on the language),
;;   find its opposite (additive inverse).
;;
;;   e.g. 1: -1
;;        14: -14
;;        -34: 34
;;
;; Tags: Fundamentals
;;

(ns katas.kyu-8.opposite-number)

;; --- Solution 1: subtracting from zero ---

(defn solution-1
  [n]
  (- 0 n))

;; --- Solution 2: simply... negating :) ---

(defn solution-2
  [n]
  (- n))

;; --- Active solution (the one tests run against) ---

(def solution solution-1)
