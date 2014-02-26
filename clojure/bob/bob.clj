(ns bob
  (:use [clojure.string :only [join blank?]]))

(defn- all-caps? [str]
  (let [ascii-chars (join "" (re-seq #"[a-zA-Z]" str))]
    (and
     (not (blank? ascii-chars))
     (every? #(Character/isUpperCase %) ascii-chars))))

(defn- question? [str]
  (re-matches #".*\?\Z" str))

(defn response-for [str]
  (cond
   (blank? str) "Fine. Be that way!"
   (all-caps? str) "Woah, chill out!"
   (question? str) "Sure."
   :else "Whatever."))
