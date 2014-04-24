(ns robot-name.test (:require [clojure.test :refer :all]))
(load-file "robot.clj")

(def robbie (robot/robot))
(def clutz  (robot/robot))

(deftest name-matches-expected-pattern
  (is (re-find (re-matcher #"[A-Z]{2}\d{3}" (robot/robot-name robbie)))))

(deftest name-is-persistent
  (is (= (robot/robot-name robbie) (robot/robot-name robbie))))

(deftest different-robots-have-different-names
  (is (not (= (robot/robot-name clutz) (robot/robot-name robbie)))))

(def army (repeatedly 998 #(robot/robot))) ; 2 were already created

(deftest handles-more-than-999-uniq-names
  (is (= 998 (count (distinct (map robot/robot-name army))))))

(deftest recycles-numbers-after-999-robots
  (is (= "AB001" (robot/robot-name (last army)))))

(def original-name (robot/robot-name robbie))
(robot/reset-name robbie)

(deftest new-name-matches-expected-pattern
  (is (re-find (re-matcher #"[A-Z]{2}\d{3}" (robot/robot-name robbie)))))

(deftest new-name-is-persistent
  (is (= (robot/robot-name robbie) (robot/robot-name robbie))))

(deftest new-name-is-different-than-old-name
  (is (not (= original-name (robot/robot-name robbie)))))

(run-tests)
