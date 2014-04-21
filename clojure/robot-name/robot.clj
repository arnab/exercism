(ns robot)

(def id-seq (ref 0))
(def name-seq (ref (list "A" "A" 0)))
(def robots (ref {}))

(defn- inc-name-seq [curr-seq]
  (list "A" "A" (inc (last curr-seq))))

(defn- format-name [name]
  (apply format "%s%s%03d" name))

(defn- build []
  {:name (format-name @name-seq)
   :id @id-seq})

(defn- manufacture []
  (let [new-robot (build)]
    (dosync
     (alter robots assoc @id-seq new-robot)
     (alter name-seq inc-name-seq)
     (alter id-seq inc))
    new-robot))

(defn robot []
  (manufacture))

(defn robot-name [robot]
  (:name (@robots (robot :id))))

;; (defn reset-name
;;   [robot])
