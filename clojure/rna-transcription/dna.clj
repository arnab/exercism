(ns dna
  (:use [clojure.string :only [join]]))

(def dna-to-rna {"G" "C", "C" "G", "T" "A", "A" "U"})

(defn- valid-dna-strand? [strand]
  (contains? dna-to-rna strand))

(defn- validate-dna-seq [strand]
  ;; clojure.contrib.except/throw-if-not would be suitable
  (when-not (valid-dna-strand? strand)
    (throw (AssertionError.
            (format "Unknown DNA strand: %s" strand)))))

(defn- validate-and-transcribe [strand-as-char]
  (let [strand (str strand-as-char)]
    (validate-dna-seq strand)
    (dna-to-rna strand)))

(defn to-rna [dna-seq]
  (join "" (map validate-and-transcribe dna-seq)))
