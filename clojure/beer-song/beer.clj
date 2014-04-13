(ns beer
  (:use [clojure.string :only [join upper-case]]))

(defn- current-count-phrase [n]
  (cond
   (= n 0) "no more bottles of beer on the wall, no more bottles of beer"
   (= n 1) (str n " bottle of beer on the wall, " n " bottle of beer")
   :else (str n " bottles of beer on the wall, " n " bottles of beer")))

(defn- action-phrase [n]
  (cond
   (= n 0) "go to the store and buy some more, "
   (= n 1) "take it down and pass it around, "
   :else "take one down and pass it around, "))

(defn- next-count-phrase [n]
  (cond
   (= n -1) "99 bottles of beer on the wall"
   (= n 0) "no more bottles of beer on the wall"
   (= n 1) (str n " bottle of beer on the wall")
   :else (str n " bottles of beer on the wall")))

(defn- make-sentense [[fst & rst]]
  (str (apply str (upper-case fst) rst) ".\n"))

(defn verse [n]
  (join "" [
            (make-sentense (current-count-phrase n))
            (make-sentense (str
                            (action-phrase n)
                            (next-count-phrase (dec n))))]))

(defn sing
  ([from] (sing from 0))
  ([from to]
     (join "\n"
           (map verse (range from (dec to) -1)))))
