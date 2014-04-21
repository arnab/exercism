(ns bob
  (:use [clojure.string :only [blank?]]))

(def unresponsive? blank?)

(defn- shouting? [s]
  (let [ascii-chars (re-seq #"[a-zA-Z]" s)]
    (and
     ascii-chars
     (every? #(Character/isUpperCase %)
             (apply str ascii-chars)))))

(defn- question? [s]
  (re-matches #".*\?\Z" s))

(defn response-for [s]
  (cond
   (unresponsive? s) "Fine. Be that way!"
   (shouting? s) "Woah, chill out!"
   (question? s) "Sure."
   :else "Whatever."))
