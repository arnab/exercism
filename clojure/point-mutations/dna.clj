(ns dna)

(defn hamming-distance
  [s1 s2]
  (loop [[hd1 & tl1] s1
         [hd2 & tl2] s2
         acc-dist (atom 0)]
    (if-not (= hd1 hd2) (swap! acc-dist inc))
    (if (some empty? [tl1 tl2])
      @acc-dist
      (recur tl1 tl2 acc-dist))))
