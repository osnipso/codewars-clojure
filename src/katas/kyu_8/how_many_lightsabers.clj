;; Kata: How many lightsabers do you own?
;; URL:  https://www.codewars.com/kata/51f9d93b4095e0a7200001b8
;; Kyu:  8
;;
;; Description:
;;   Inspired by the development team at Vooza, write the function that
;;   - accepts the name of a programmer, and
;;   - returns the number of lightsabers owned by that person.
;;   The only person who owns lightsabers is Zach, by the way. He owns 18, which is an awesome number of lightsabers. Anyone else owns 0.
;;
;;   e.g. "anyone else" --> 0
;;        "Zach" --> 18
;;
;; Tags: Fundamentals
;;

(ns katas.kyu-8.how-many-lightsabers)

;; --- Solution 1: direct comparison ---

(defn solution-1
  [name]
  (if (= name "Zach")
    18
    0))

;; --- Active solution (the one tests run against) ---

(def solution solution-1)
