(ns dna)

(defn hamming-distance
  [s1 s2]
  (count (filter identity (map not= s1 s2))))
