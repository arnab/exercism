(ns phrase)

(defn- tokenize [s]
  (re-seq #"\w+" (clojure.string/lower-case s)))

(defn word-count [s]
  (frequencies (tokenize s)))
