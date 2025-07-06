(ns kyu8.opposite-number)

;;
;; Problem: Opposite number
;;
;; Description: Very simple, given a number (integer / decimal / both depending on the language),
;;              find its opposite (additive inverse).
;;
;; Examples:
;;
;;   1: -1
;;   14: -14
;;   -34: 34
;;
;; Tags:
;;   - Fundamentals
;;

;; Solution 1: subtracting from zero
(defn opposite
  [number]
  (- 0 number))

;; Solution 2: simply... negating :)
(defn opposite
  [number]
  (- number))
