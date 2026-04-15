(ns kyu8.banjo)

;;
;; Problem: Are You Playing Banjo?
;;
;; Description: Create a function which answers the question "Are you playing banjo?".
;;              If your name starts with the letter "R" or lower case "r", you are playing banjo!
;;              The function takes a name as its only argument, and returns one of the following
;;              strings:
;;
;;              name + " plays banjo"
;;              name + " does not play banjo"
;;
;;              Names given are always valid strings.
;;
;; Tags:
;;   - Fundamentals
;;   - Strings
;;

;; Solution 1: Convert to lower case/if
(defn plays-banjo?
  [name]
  (str name (if (= (Character/toLowerCase (first name)) \r)
              " plays banjo"
              " does not play banjo")))

;; Solution 2: Hash set
(defn plays-banjo?
  [name]
  (str name (if (#{\R \r} (first name))
              " plays banjo"
              " does not play banjo")))
