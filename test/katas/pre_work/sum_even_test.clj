(ns katas.pre-work.sum-even-test
  (:require [clojure.test :refer [deftest testing is]]
            [katas.pre-work.sum-even :refer [sum-even-solution]]))

(deftest sum-even-solution-test
  (testing "sum of even numbers in an array"
    (is (= 0 (sum-even-solution [])))
    (is (= 12 (sum-even-solution [1 2 3 4 5 6])))))
