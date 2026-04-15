;; Kata: Convert number to reversed array of digits
;; URL:  https://www.codewars.com/kata/5583090cbe83f4fd8c000051
;; Kyu:  8
;;
;; Description:
;;   Given a random non-negative number, you have to return the digits of this number within an array in reverse order.
;;
;;   e.g. 35231 => [1,3,2,5,3]
;;        0     => [0]
;;
;; Tags: Arrays, Fundamentals
;;

(ns katas.kyu-8.number-to-reversed-digits)

;; --- Solution 1: recursive, 0 => [0] as special case ---

(defn solution-1
  "Receive an integer number >= 0
  Return an array (vector) with the digits in reversed order"
  [input-number]
  (if (= 0 input-number)
    [0]
    (loop [number input-number
           result []]
      (if (= 0 number)
        result
        (recur (quot number 10) (conj result (mod number 10)))))))

;; --- Solution 2: recursive, no special case ---

(defn solution-2
  "Receive an integer number >= 0
  Return an array (vector) with the digits in reversed order"
  [n]
  (loop [number n
         result []]
    (let [quotient (quot number 10)
          module (mod number 10)]
      (if (= 0 quotient)
        (conj result module)
        (recur quotient (conj result module))))))

;; --- Solution 3: reduce ---

(defn solution-3
  [n]
  (reduce #(into [(Character/digit %2 10)] %1) [] (str n)))

;; --- Solution 4: map ---

(defn solution-4
  [n]
  (vec (reverse (map #(Character/digit % 10) (str n)))))

;; --- Active solution (the one tests run against) ---

(def solution solution-1)
