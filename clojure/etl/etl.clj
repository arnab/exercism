(ns etl
  (:require [clojure.string :as str]))

(defn- merge-scores [result tiles score]
  (merge result
         (zipmap (map str/lower-case tiles)
                 (repeat score))))

(defn transform [defs]
  (loop [
         [[score tiles] & more_defs] (seq defs)
         acc {}]
    (if more_defs
      (recur more_defs (merge-scores acc tiles score))
      (merge-scores acc tiles score))))
