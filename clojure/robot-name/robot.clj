(ns robot)

(def numbers (range 1000))

(def name-seq (atom ["A" "A" 1]))

(defn- max-for [part]
  999)

(defn- next-part [part]
  (partial inc))

(defn- inc-name-seq [curr-seq]
  (update-in curr-seq [2] inc)
  (loop [part 2]
    ;; which piece of the name seq are we dealing with
    (if (= (curr-seq part) (max-for part))
      (recur (dec part))
      (update-in curr-seq [part] next-part)))
  )

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
