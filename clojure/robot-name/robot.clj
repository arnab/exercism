(ns robot)

(def name-seq (atom ["A" "A" 0]))
(defn- inc-name-seq [curr-seq]
  (update-in curr-seq [2] inc))

(defn- format-name [name]
  (apply format "%s%s%03d" name))

(defn- generate-name []
  (format-name @name-seq))

(defn- next-name []
  (let [name (generate-name)]
    (swap! name-seq inc-name-seq)
    name))

(defn robot []
  (atom (next-name)))

(defn robot-name [robot]
  (or @robot
      (reset! robot (next-name))))

(defn reset-name [robot]
  (reset! robot nil))
