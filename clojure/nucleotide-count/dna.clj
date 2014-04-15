(ns dna)

(def default-counts {\A 0, \T 0, \C 0, \G 0})

(def valid-nucleotides
  (conj (set (keys default-counts)) \U))

(defn- validate-neucleotide
  [n]
  (if-not (contains? valid-nucleotides n)
    (throw (IllegalArgumentException.))))

(defn count
  [nucleotide strand]
  (validate-neucleotide nucleotide)
  (get (nucleotide-counts strand) nucleotide 0))

(defn- extract-nucleotides
  [s]
  (char-array
   (clojure.string/join ""
                        (re-seq #"A|C|G|T|U" s))))

(defn nucleotide-counts
  [strand]
  (merge default-counts
         (frequencies (extract-nucleotides strand))))
