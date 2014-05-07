(ns space-age)

(def orbital-period-ratios { :mercury 0.2408467 })

(defn on-earth [n]
  (/ n 31557600.0))

(defn on-mercury [n]
  (/ (on-earth n) (orbital-period-ratios :mercury)))
