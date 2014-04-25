(ns leap)

(defn- divisible? [a b]
  (= 0 (mod a b)))

(defn leap-year? [yr]
  (and (divisible? yr 4)
       (or (not (divisible? yr 100))
           (divisible? yr 400))))
