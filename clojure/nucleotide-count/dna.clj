(ns dna)

(def default-counts {\A 0, \T 0, \C 0, \G 0})

(def valid-nucleotides
  (conj (set (keys default-counts)) \U))

(defn- extract-nucleotides
  [s]
  (char-array
   (clojure.string/join ""
                        (re-seq #"A|C|G|T|U" s))))

(defn nucleotide-counts
  [strand]
  (merge default-counts
         (frequencies (extract-nucleotides strand))))

(defn- valid-neucleotide?
  [n]
  (contains? valid-nucleotides n))

(defn count
  [nucleotide strand]
  (if (valid-neucleotide? nucleotide)
    (get (nucleotide-counts strand) nucleotide 0)
    (throw (IllegalArgumentException. "invalid nucleotide"))))
