;; Kata: Are You Playing Banjo?
;; URL:  https://www.codewars.com/kata/53af2b8861023f1d88000832
;; Kyu:  8
;;
;; Description:
;;   Create a function which answers the question "Are you playing banjo?".
;;   If your name starts with the letter "R" or lower case "r", you are playing banjo!
;;   function takes a name as its only argument, and returns one of the following
;;   strings:
;;
;;     name + " plays banjo"
;;     name + " does not play banjo"
;;
;;   Names given are always valid strings.
;;
;; Tags: Strings, Fundamentals
;;

(ns katas.kyu-8.are-you-playing-banjo)

;; --- Solution 1: Convert to lower case/if ---

(defn solution-1
  [name]
  (str name (if (= (Character/toLowerCase (first name)) \r)
              " plays banjo"
              " does not play banjo")))

;; --- Solution 2: Hash set ---

(defn solution-2
  [name]
  (str name (if (#{\R \r} (first name))
              " plays banjo"
              " does not play")))

;; --- Active solution (the one tests run against) ---

(def solution solution-1)
