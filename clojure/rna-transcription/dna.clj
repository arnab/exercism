(ns dna)

(defn to-rna [dna-seq]
  (let
      [dna-to-rna {"G" "C", "C" "G", "T" "A", "A" "U"}]
    (dna-to-rna dna-seq)))
