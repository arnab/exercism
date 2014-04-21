(ns robot)

(def id-seq (ref 0))
(def name-seq (ref (list "A" "A" 0)))
(def robots (ref {}))

(defn- inc-name-seq [curr-seq]
  (list "A" "A" (inc (last curr-seq))))

(defn- format-name [name]
  (apply format "%s%s%03d" name))

(defn- generate-name []
  (format-name @name-seq))

(defn- generate-name-and-inc-seq []
  (let [name (generate-name)]
    (dosync (alter name-seq inc-name-seq))
    name))

(defn- build []
  {:name (generate-name)
   :id @id-seq})

(defn- manufacture []
  (let [new-robot (build)]
    (dosync
     (alter robots assoc @id-seq new-robot)
     (alter name-seq inc-name-seq)
     (alter id-seq inc))
    new-robot))

(defn- change-name [robot new-name]
  (let [id (robot :id)]
    (dosync
     (alter robots assoc-in [id :name] new-name))
    (@robots id)))

(defn robot []
  (manufacture))

(defn robot-name [robot]
  (or (:name ( @robots (robot :id)))
      (:name (change-name robot (generate-name-and-inc-seq)))))

(defn reset-name [robot]
  (let [original-name (robot :name)]
    (change-name robot nil)
    original-name))
