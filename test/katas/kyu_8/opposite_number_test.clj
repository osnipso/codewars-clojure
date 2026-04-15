(ns katas.kyu-8.opposite-number-test
  (:require [clojure.test :refer [deftest testing is]]
            [katas.kyu-8.opposite-number :refer [solution]]))

(deftest solution-test
  (testing "opposite number"
    (is (= -1 (solution 1)))
    (is (= -14 (solution 14)))
    (is (= 34 (solution -34)))))
