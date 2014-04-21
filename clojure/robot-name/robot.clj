(ns robot)

(def name-seq (atom (list "A" "A" 1)))

(defn- format-name
  [name-seq]
  (apply format "%s%s%03d" @name-seq))

(defn- inc-name-seq
  [curr-seq]
  (list "A" "A" (inc (last curr-seq))))

(defn- generate-name
  []
  (swap! name-seq inc-name-seq)
  (format-name name-seq))

(defn robot
  []
  {:name (generate-name)})

(defn robot-name
  [robot]
  (:name robot))

;; (defn reset-name
;;   [robot])
