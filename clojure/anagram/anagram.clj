(ns anagram
  (use [clojure.string :only [lower-case]]))

(defn- same-sorted-seq? [w other]
  (=
   (sort (lower-case w))
   (sort (lower-case other))))

(defn- anagram? [w other]
  (and
   (not= w other)
   (same-sorted-seq? w other)))

(defn anagrams-for [w potentials]
  (filter #(anagram? w %) potentials))
