(ns grains)

(defn square [n]
  (if (= n 1)
      1
      (* 2 (square (dec n)))))
