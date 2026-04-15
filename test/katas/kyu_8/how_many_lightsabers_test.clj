(ns katas.kyu-8.how-many-lightsabers-test
  (:require [clojure.test :refer [deftest testing is]]
            [katas.kyu-8.how-many-lightsabers :refer [solution]]))

(deftest solution-test
  (testing "how many lightsabers a person (programmer) own"
    (is (= 18 (solution "Zach")))
    (is (= 0 (solution "Anybody")))))
