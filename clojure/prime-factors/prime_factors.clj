(ns prime-factors)

(let [divisible? (comp zero? rem)]
  (defn of
    "returns the prime factors of n"
    [n]
    (loop [n n
           candidate 2
           acc []]
      (let [remaining (bigint (/ n candidate))]
        (cond (= n 1) acc
              (= n candidate) (conj acc candidate)
              :else (if (divisible? n candidate)
                      (recur remaining candidate (conj acc candidate))
                      (recur n (inc candidate) acc)))))))
