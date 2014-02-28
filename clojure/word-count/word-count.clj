(ns phrase
  (:use [clojure.string :only [lower-case split blank?]]))

(defn- tokenize [str]
  (split (lower-case str) #"\W"))

(defn- filter-words [str]
  (filter (comp not blank?) (tokenize str)))

(defn- add-count-for [word counts]
  (assoc counts word (inc (get counts word 0))))

(defn- count [words counts]
  (if (empty? words)
    counts
    (count (rest words) (add-count-for (first words) counts))))

(defn word-count [str]
  (count (filter-words str) {}))
