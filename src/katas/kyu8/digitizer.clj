(ns kyu8.digitizer)

;;
;; Problem: Convert number to reversed array of digits
;;
;; Description: Given a random non-negative number, you have to return the digits of this number
;;              within an array in reverse order.
;;
;; Example (Input => Output):
;;
;;   35231 => [1 3 2 5 3]
;;   0     => [0]
;;
;; Tags:
;;   - Arrays
;;   - Fundamentals
;;

;; Solution 1: 0 as special case/loop
(defn digitize
  "Receive an integer number >= 0
  Return an array (vector) with the digits in reversed order"
  [in-num]
  (if (= 0 in-num)
    [0]
    (loop [number in-num
           result []]
      (if (= 0 number)
        result
        (recur (quot number 10) (conj result (mod number 10)))))))

;; Solution 2: no special cases/loop
(defn digitize
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

;; Solution 3: "idiomatic" with high order functions/lambda
(defn digitize
  [n]
  (reduce #(into [(Character/digit %2 10)] %1) [] (str n)))

;; Solution 4: "idiomatic" and optimized
(defn digitize
  [n]
  (vec (reverse (map #(Character/digit % 10) (str n)))))
