(ns robot)

(def name-seq (ref ["A" "A" 0]))
(defn- inc-name-seq [curr-seq]
  (update-in curr-seq [2] inc))

(defn- format-name [name]
  (apply format "%s%s%03d" name))

(defn- generate-name []
  (format-name @name-seq))

(defn- next-name []
  (let [name (generate-name)]
    (dosync (alter name-seq inc-name-seq))
    name))

(defn robot []
  (atom (next-name)))

(defn robot-name [robot]
  @robot)

(defn reset-name [robot]
  (reset! robot (next-name)))
