(ns leap)

(defn- divisible? [a b]
  (= 0 (mod a b)))

(defn leap-year? [yr]
  (cond
   (divisible? yr 400) true
   (divisible? yr 100) false
   :else (divisible? yr 4)))
