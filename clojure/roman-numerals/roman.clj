(ns roman)

(let [nums {1 "I", 4 "IV", 5 "V", 9 "IX",
            10 "X", 40 "XL", 50 "L", 90 "XC",
            100 "C", 400 "CD", 500 "D" 900 "CM",
            1000 "M"}
      steps (reverse (sort (keys nums)))]
  (defn- next-step
    "returns the highest number from the above numerals list,
     which is smaller than or equal to the given number. i.e. The next step"
    [n]
    (let [next-num
          (first (drop-while #(> % n) steps))]
      [next-num (nums next-num)])))

(defn numerals
  "converts decimal number to roman"
  [n]
  (loop [n n
         acc ""]
    (if (zero? n)
      acc
      (let [[x s] (next-step n)]
        (recur (- n x) (str acc s))))))
