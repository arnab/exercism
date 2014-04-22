(ns dna
  (:use [clojure.string :only [join]]))

(defn- validate-and-transcribe [strand]
  (case strand
    \G \C
    \C \G
    \T \A
    \A \U
    (throw (AssertionError.
            (format "Unknown DNA strand: %s" strand)))))

(defn to-rna [dna-seq]
  (join "" (map validate-and-transcribe dna-seq)))
