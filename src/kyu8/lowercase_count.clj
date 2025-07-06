(ns kyu8.lowercase-count)

;;
;; Problem: Regex count lowercase letters
;;
;; Description: Your task is simply to count the total number of lowercase letters in a string.
;;
;; Examples:
;;
;;   "abc" ===> 3
;;   "abcABC123" ===> 3
;;   "abcABC123!@#$%^&*()_-+=}{[]|':;?/>.<,~" ===> 3
;;   "" ===> 0
;;   "ABC123!@#$%^&*()_-+=}{[]|':;?/>.<,~" ===> 0
;;   "abcdefghijklmnopqrstuvwxyz"`===> 26
;;
;; Tags:
;;   - Algorithms
;;   - Fundamentals
;;   - Regular Expressions
;;

;; Solution
(defn lowercase_count
  [strng]
  (count (re-seq #"[a-z]" strng)))
