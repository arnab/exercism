(ns phrase
  (:use [clojure.string :only [lower-case split blank?]]))

(defn- tokenize [str]
  (split (lower-case str) #"\W"))

(defn- non-blank? [str]
  (not (blank? str)))

(defn- filter-words [str]
  (filter non-blank? (tokenize str)))

(defn- count [words counts]
  (if (empty? words)
    counts
    (count (rest words) (assoc counts (first words) (inc (get counts (first words) 0))))))

(defn word-count [str]
  (count (filter-words str) {}))
