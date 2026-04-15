(ns katas.kyu-8.remove-string-spaces-test
  (:require [clojure.test :refer [deftest testing is]]
            [katas.kyu-8.remove-string-spaces :refer [solution]]))

(deftest solution-test
  (testing "removes all spaces from a string"
    (is (= "8j8mf289bjh" (solution "8 j 8   mf 289 bjh")))
    (is (= "helloworld" (solution "hello world")))
    (is (= "" (solution "")))
    (is (= "nospaces" (solution "nospaces")))))
