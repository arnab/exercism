(ns beer
  (:use [clojure.string :only [join upper-case]]
        [clojure.pprint :only [cl-format]]))

(defn- pluralize [n s]
  (if (= n 0)
    (str "no more " s "s")
    (let [fmt-s (str "~D " s "~:P")]
      (cl-format nil fmt-s n))))

(defn- bottle-count-phrase [n]
  (str
   (pluralize n "bottle")
   " of beer"))

(defn- bottle-count-on-wall-phrase [n]
  (str (bottle-count-phrase n) " on the wall" ))

(defn- current-count-phrase [n]
  (str
   (bottle-count-on-wall-phrase n)
   ", "
   (bottle-count-phrase n)))

(defn- action-phrase [n]
  (cond
   (= n 0) "go to the store and buy some more, "
   :else (str "take "
              (if (= n 1) "it" "one")
              " down and pass it around, ")))

(defn- next-count-phrase [n]
  (cond
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
                            (next-count-phrase (mod (dec n) 100))))]))

(defn sing
  ([from] (sing from 0))
  ([from to]
     (join "\n"
           (map verse (range from (dec to) -1)))))
