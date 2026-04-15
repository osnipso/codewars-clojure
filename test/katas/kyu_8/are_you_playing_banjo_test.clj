(ns katas.kyu-8.are-you-playing-banjo-test
  (:require [clojure.test :refer [deftest testing is]]
            [katas.kyu-8.are-you-playing-banjo :refer [solution]]))

(deftest solution-test
  (testing "are you playing banjo?"
    (is (= "Ralph Stanley plays banjo" (solution "Ralph Stanley")))
    (is (= "Béla Fleck does not play banjo" (solution "Béla Fleck")))))
