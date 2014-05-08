(ns grains)

(defn square [num]
  (loop [n num acc 1]
   (if (= n 1)
      acc
      (recur (dec n) (*' 2 acc)))))
