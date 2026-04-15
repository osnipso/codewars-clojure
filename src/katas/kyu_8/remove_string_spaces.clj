;; Kata: Remove String Spaces
;; URL:  https://www.codewars.com/kata/57eae20f5500ad98e50002c5
;; Kyu:  8
;;
;; Description:
;;   Write a function that removes all spaces from a string.
;;
;;   e.g. "8 j 8   mf 289 bjh" -> "8j8mf289bjh"
;;
;; Tags: Fundamentals, Strings
;;
;; Note: This is not an approved kata for Clojure (!)
;;

(ns katas.kyu-8.remove-string-spaces
  (:require [clojure.string :as str]))

;; --- Solution 1: clojure.string/replace ---

(defn solution-1
  [s]
  (str/replace s " " ""))

;; --- Solution 2: filter + apply ---

(defn solution-2
  [s]
  (apply str (filter #(not= % \space) s)))

;; --- Solution 3: reduce ---

(defn solution-3
  [s]
  (reduce #(if (= %2 \space) %1 (str %1 %2)) "" s))

;; --- Active solution (the one tests run against) ---

(def solution solution-1)
