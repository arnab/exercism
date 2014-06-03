(ns drops
  (require [clojure.set :as set :only [intersection]]
           [clojure.string :as s :only [join]]))

(let [divisible? (comp zero? rem)]
  (defn- factors
    "returns the prime factors of n"
    [n]
    (loop [n n
           candidate 2
           acc []]
      (if (<= n 1) acc
          (if (divisible? n candidate)
            (recur (bigint (/ n candidate)) candidate (conj acc candidate))
            (recur n (inc candidate) acc))))))

(let [lang {3 "Pling" 5 "Plang" 7 "Plong"}]
  (defn convert
    [n]
    (let [ns (set (factors n))
          kws (set (keys lang))]
      (if-let [found-ns (seq (set/intersection ns kws))]
        (s/join (map lang found-ns))
        (str n)))))
