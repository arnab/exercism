(ns grains)

(defn square [num]
  (loop [n num acc 1]
   (if (= n 1)
      acc
      (recur (dec n) (*' 2 acc)))))

(defn total []
  (let [squares (map square (range 1 65))]
    (reduce + squares)))
