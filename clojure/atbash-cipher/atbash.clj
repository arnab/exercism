(ns atbash
  (require [clojure.string :as str :only [lower-case]]))

(let [letters (map char (range
                         (int \a)
                         (inc (int \z))))
      total-letters (dec (count letters))
      ]
  (defn transpose
    [c]
    (nth letters (- total-letters
                    (.indexOf letters c))))
  (defn encode
    [s]
    (apply str (map transpose (str/lower-case s)))))
