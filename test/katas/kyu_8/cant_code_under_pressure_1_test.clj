(ns katas.kyu-8.cant-code-under-pressure-1-test
  (:require [clojure.test :refer [deftest testing is]]
            [katas.kyu-8.cant-code-under-pressure-1 :refer [solution]]))

(deftest solution-test
  (testing "double the integer and return it"
    (is (= 0 (solution 0)))
    (is (= 2 (solution 1)))))
