(ns katas.kyu-8.regex-count-lowercase-letters-test
  (:require [clojure.test :refer [deftest testing is]]
            [katas.kyu-8.regex-count-lowercase-letters :refer [solution]]))

(deftest solution-test
  (testing "count lowercase letters from string"
    (is (= 3 (solution "abc")))
    (is (= 3 (solution "abcABC123")))
    (is (= 3 (solution "abcABC123!@#$%^&*()_-+=}{[]|':;?/>.<,~")))
    (is (= 0 (solution "")))
    (is (= 0 (solution "ABC123!@#$%^&*()_-+=}{[]|':;?/>.<,~")))
    (is (= 26 (solution "abcdefghijklmnopqrstuvwxyz")))))
