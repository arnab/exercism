(defn- valid?
  "returns truthy/falsey value if a triangle can exist, given sorted sides."
  [[x y z]]
  (> (+ x y) z))

(defn- uniq-sides-count
  [[& sides]]
  (count (distinct sides)))

(defn- triangle-type
  [[& sides]]
  (get [:equilateral :isosceles :scalene]
       (dec (uniq-sides-count sides))))

(defn triangle
  [& sides]
  (if (valid? (sort sides))
    (triangle-type sides)
    :illogical))
