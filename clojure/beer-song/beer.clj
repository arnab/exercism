(ns beer
  (:use [clojure.string :only [join]]))

(defn- first-stanza [n]
  (cond
   (= n 0) "No more bottles of beer on the wall, no more bottles of beer.\n"
   (= n 1) (str n " bottle of beer on the wall, " n " bottle of beer.\n")
   :else (str n " bottles of beer on the wall, " n " bottles of beer.\n")))

(defn- second-stanza [n]
  (cond
   (= n 0) "Go to the store and buy some more, "
   (= n 1) "Take it down and pass it around, "
   :else "Take one down and pass it around, "))

(defn- last-stanza [n]
  (cond
   (= n -1) "99 bottles of beer on the wall.\n"
   (= n 0) "no more bottles of beer on the wall.\n"
   (= n 1) (str n " bottle of beer on the wall.\n")
   :else (str n " bottles of beer on the wall.\n")))

(defn verse [n]
  (str
   (first-stanza n) (second-stanza n) (last-stanza (dec n))))

(defn sing
  ([from] (sing from 0))
  ([from to]
     (join "\n"
           (map verse (range from (- to 1) -1)))))
