(ns robot)

(def letters (map char (range (int \A) (inc (int \B)))))
(def numbers (range 5))
(def starting-vals [letters letters numbers])
(def name-seq (atom starting-vals))

(defn- format-name [name]
  (apply format "%s%s%03d" name))

(defn- generate-name []
  (format-name (map first @name-seq)))

(defn- inc-name-seq []
  (loop [part 2]
    (if (seq (@name-seq part))
      (swap! name-seq update-in [part] rest)
      (do
        (reset! name-seq
                (assoc-in @name-seq [part]
                          (starting-vals part)))
        (recur (dec part))))))

(defn- next-name []
  (let [name (generate-name)]
    (inc-name-seq)
    name))

(defn robot []
  (atom (next-name)))

(defn robot-name [robot]
  (or @robot
      (reset! robot (next-name))))

(defn reset-name [robot]
  (reset! robot nil))
