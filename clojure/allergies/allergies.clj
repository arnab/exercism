(ns allergies)

;;; Ruby's word array
(defmacro %w [& args] `(map str '~args))

(def allergies (%w eggs peanuts shellfish strawberries
                 tomatoes chocolate pollen cats))

(defn list
  "list of allergies for the given score"
  [score]
  (loop [s score                        ; remaining score
         n (dec (count allergies))      ; potential allergy, position
         acc []]
    (if (or (zero? s) (neg? n)) (distinct acc)
        (let [allergy-score (Math/pow 2 n)
              allergy (nth allergies n)]
          (if (>= s allergy-score)
            (recur (- s allergy-score) n (cons allergy acc))
            (recur s (dec n) acc))))))

(defn allergic_to?
  "returns whether the given score includes the allergy"
  [score allergy]
  (contains? (set (list score)) allergy))
