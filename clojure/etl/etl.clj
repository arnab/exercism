(ns etl
  (:require [clojure.string :as str]))

(defn- merge-scores [map tiles score]
  (merge map
         (zipmap tiles (repeat score))))

(defn transform [definitions]
  (loop [[
          [score tiles] & others] (seq definitions)
          acc {}]
    (if (first others)
      (recur others (merge-scores acc tiles score))
      (merge-scores acc tiles score)
      )))
