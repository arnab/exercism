(ns dna)

(defn hamming-distance
  [s1 s2]
  (loop [[fst-s1 & rst-s1] s1
         [fst-s2 & rst-s2] s2
         distance-acc 0]
    (let [distance (if (= fst-s1 fst-s2) 0 1)
          distance-acc (+ distance-acc distance)]
      (if (some empty? [rst-s1 rst-s2])
        distance-acc
        (recur rst-s1 rst-s2 distance-acc)))))
