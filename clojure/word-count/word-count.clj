(ns phrase
  (:use [clojure.string :only [lower-case split blank?]]))

(defn- tokenize [str]
  (split (lower-case str) #"\W"))

(defn- filter-words [str]
  (filter (comp not blank?) (tokenize str)))

(defn word-count [str]
  (frequencies (filter-words str)))
