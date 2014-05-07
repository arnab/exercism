(ns space-age)

(def orbital-period-ratios
  {:mercury 0.2408467
   :venus 0.61519726
   :mars 1.8808158
   :jupiter 11.862615
   :saturn 29.447498
   :uranus 84.016846
   :neptune 164.79132})

(defn on-earth [n]
  (/ n 31557600.0))

(defn- on-planet [p]
  (fn [n]
    (/ (on-earth n)
       (orbital-period-ratios p))))

(defmacro on-planet-fn [p]
  `(intern *ns*
           (symbol (str "on-" (name ~p)))
           (on-planet ~p)))

(doseq [p (keys orbital-period-ratios)]
  (on-planet-fn p))
