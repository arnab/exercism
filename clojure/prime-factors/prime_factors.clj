(ns prime-factors)

(let [divisible? (comp zero? rem)]
  (defn of
    "returns the prime factors of n"
    [n]
    (loop [n n
           candidate 2
           acc []]
      (if (<= n 1) acc
          (if (divisible? n candidate)
            (recur (bigint (/ n candidate)) candidate (conj acc candidate))
            (recur n (inc candidate) acc))))))
