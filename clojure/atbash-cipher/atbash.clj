(ns atbash
  (require [clojure.string :as string :only [lower-case]]))

(let [letters (map char (range
                         (int \a)
                         (inc (int \z))))
      total-letters (dec (count letters))]
  (defn- transpose
    [c]
    (->> c
         (.indexOf letters)
         (- total-letters)
         (nth letters)))
  (defn encode
    [s]
    (->> s
         string/lower-case
         (map transpose)
         (apply str))))
