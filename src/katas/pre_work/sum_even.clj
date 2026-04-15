;;
;; Problem: Sum of Even Numbers in a Sequence
;;
;; Task: Given a sequence of positive integers, calculate the sum of all even numbers within the
;;       sequence.
;;
;; Examples:
;;
;;   Input: []
;;   Output: 0
;;
;;   Input: [1, 2, 3, 4, 5, 6]
;;   Output: 12 (2 + 4 + 6 = 12)
;;
;; Constraints:
;; - The input will always be a sequence of positive integers.
;; - The sequence may be empty.
;; - The numbers in the sequence will not exceed the maximum value for a standard integer type.
;;

(ns katas.pre-work.sum-even)

;; --- Solution 1: "Standard" recursion ---

(defn sum-even-solution-1
  [in-seq]
  (if (empty? in-seq)
    0  ;; End recursion
    (let [first-element (first in-seq)
          seq-rest (rest in-seq)]
      (if (even? first-element)
        (+ first-element (sum-even-solution-1 seq-rest))
        (sum-even-solution-1 seq-rest)))))

;; --- Solution 2: Idiomatic recursion (avoid stack overflow) ---

(defn sum-even-solution-2
  [in-seq]
  (loop [sum 0
         seq in-seq]
    (if (empty? seq)
      sum  ;; End recursion
      (let [first-element (first seq)
            seq-rest (rest seq)]
        (if (even? first-element)
          (recur (+ sum first-element) seq-rest)
          (recur sum seq-rest))))))

;; --- Solution 3: Idiomatic simple (uses high-order functions) ---

(defn sum-even-solution-3
  [in-seq]
  (reduce + (filter even? in-seq)))

;; --- Active solution (the one tests run against) ---

(def sum-even-solution sum-even-solution-1)
