(ns robot)

(def letters (map char (range
                        (int \A)
                        (inc (int \Z)))))
(def numbers (range 1000))
(def name-seq (atom (for [l1 letters
                          l2 letters
                          n numbers]
                      (format "%s%s%03d" l1 l2 n))))

(defn- next-name []
  (let [name (first @name-seq)]
    (swap! name-seq rest)
    name))

(defn robot []
  (atom (next-name)))

(defn robot-name [robot]
  (or @robot
      (reset! robot (next-name))))

(defn reset-name [robot]
  (reset! robot nil))
