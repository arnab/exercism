(ns phone
  (:require [clojure.string :as str :only [join]]))

(defn- cleanse
  "select only the numbers from the given string"
  [num]
  (str/join ""
        (re-seq #"\d" num)))

(defn- valid?
  [num]
  (let [length (count num)]
    (or
       (= 10 length)
       (and (= 11 length)
            (= \1 (first num))))))

(defn- transform
  [num]
  "Format the phone number"
  (if (= 11 (count num))
    (str/join "" (drop 1 num))
    num))

(defn number
  [num]
  (let [num (cleanse num)]
    (if (valid? num)
      (transform num)
      "0000000000")))

(defn area-code
  [num]
  (re-find #"\d{3}" (number num)))

(defn pretty-print
  [num])
