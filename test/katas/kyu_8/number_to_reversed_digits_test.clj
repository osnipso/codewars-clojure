(ns katas.kyu-8.number-to-reversed-digits-test
  (:require [clojure.test :refer [deftest testing is]]
           [katas.kyu-8.number-to-reversed-digits :refer [solution]]))

(deftest solution-test
  (testing "number to reversed digits array"
    (is (= [0] (solution 0)))
    (is (= [1 3 2 5 3] (solution 35231)))))
