(ns school)

(defn add
  [db name grade]
  (merge-with concat db {grade [name]}))

(defn grade
  [db grade]
  (get db grade []))
