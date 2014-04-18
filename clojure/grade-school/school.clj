(ns school)

(defn add
  [db name grade]
  (merge-with concat db {grade [name]}))

(defn grade
  [db grade]
  (get db grade []))

(defn- transform-values-in
  [m f]
  "walk kv sets and aply f on every v"
  (into {} (for [[k v] m] [k (f v)])))

(defn sorted
  [db]
  (into (sorted-map)
        (transform-values-in db sort)))
