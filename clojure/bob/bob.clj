(ns bob
  (:use [clojure.string :only [join blank?]]))

(defn- all-caps? [s]
  (let [ascii-chars (join "" (re-seq #"[a-zA-Z]" s))]
    (and
     (not (blank? ascii-chars))
     (every? #(Character/isUpperCase %) ascii-chars))))

(defn- question? [s]
  (re-matches #".*\?\Z" s))

(defn response-for [s]
  (cond
   (blank? s) "Fine. Be that way!"
   (all-caps? s) "Woah, chill out!"
   (question? s) "Sure."
   :else "Whatever."))
