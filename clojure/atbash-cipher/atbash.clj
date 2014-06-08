(ns atbash
  (require [clojure.string :as string :only [lower-case join]]))

(let [alphabet (map #(str (char %)) (range
                                    (int \a)
                                    (inc (int \z))))
      alphabet-set (set alphabet)
      total (dec (count alphabet))]
  (defn- transpose
    [c]
    (if (contains? alphabet-set c)
      (let [complement (- total (.indexOf alphabet c))]
        (nth alphabet complement))
      c))
  (defn encode
    [s]
    (->> s
         string/lower-case
         (re-seq #"\w")
         (map transpose)
         (partition 5 5 [])
         (map #(apply str %))
         (string/join " "))))
