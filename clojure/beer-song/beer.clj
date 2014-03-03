(ns beer
  (:use [clojure.string :only [join]]))

;; (def phrase-tmpl
;;   ["%s of beer on the wall, %s of beer."
;;    "Take one down and pass it around, %s of beer on the wall."
;;    ""])

;; (defn- count-things [count things-meta]
;;   (let [word (get things-meta :word "")]
;;     (cond
;;      (= 0 count) (str count " " word "s")
;;      (= 1 count) (str count " " word)
;;      :else (str count " " word "s"))))

;; (defn verse [n]
;;   (let [current-count (count-things n {:word "bottle"})
;;         next-count    (count-things (dec n) {:word "bottle"})]
;;     (format
;;      (join "\n" phrase-tmpl)
;;      current-count
;;      current-count
;;      next-count)))

(defn- pluralize [n word]
  (cond
   (= 0 n) (str "no more " word "s")
   (= 1 n) (str n " " word)
   :else (str n " " word "s"))  )

(defn- current-count-verse [n]
  (let [count n
        pluralized-count (pluralize n "bottle")]
    (str pluralized-count " of beer on the wall, "
         pluralized-count " of beer.")))

(defn- count-to-take-down [n]
  (if (= n 1) "it" "one"))

(defn- next-action-verse [n]
  (if (= n 0)
    (str "Go to the store and buy some more")
    (str "Take "
         (count-to-take-down n)
         " down and pass it around")))

(nth (cycle (range 99 -1 -1)) 100)

(defn- next-count-verse [n]
  (str (pluralize (dec n) "bottle")
       " of beer on the wall."))

(defn verse [n]
  (str
   (current-count-verse n) "\n"
   (next-action-verse n) ", "
   (next-count-verse n) "\n"))

(defn sing
  ([from] (sing from 0))
  ([from to]
     (join "\n"
           (map verse (range from (- to 1) -1)))))
