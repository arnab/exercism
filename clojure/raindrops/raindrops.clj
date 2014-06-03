(ns drops
  (require [clojure.set :as set :only [intersection]]
           [clojure.string :as s :only [join]]))

;;; from the previous exercise: http://git.io/3ziOAQ
(load-file "../prime-factors/prime_factors.clj")

(let [lang {3 "Pling" 5 "Plang" 7 "Plong"}]
  (defn convert
    [n]
    (let [factors (set (prime-factors/of n))]
      (if-let [found-ns (seq (set/intersection factors (set (keys lang))))]
        (s/join (map lang found-ns))
        (str n)))))
