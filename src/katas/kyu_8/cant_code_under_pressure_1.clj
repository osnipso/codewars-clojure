;; Kata: You Can't Code Under Pressure #1
;; URL:  https://www.codewars.com/kata/53ee5429ba190077850011d4
;; Kyu:  8
;;
;; Description:
;;   Code as fast as you can! You need to double the integer and return it.
;;
;; Tags: Fundamentals
;;

(ns katas.kyu-8.cant-code-under-pressure-1)

;; --- Solution 1: multiplyer operator ---

(defn solution-1
  [i]
  (* i 2))

;; --- Solution 2: sum itself ---

(defn solution-2
  [i]
  (+ i i))

;; --- Active solution (the one tests run against) ---

(def solution solution-1)
