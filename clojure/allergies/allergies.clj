(ns allergies)

;;; Ruby's word array
(defmacro %w [& args] `(map str '~args))

(def allergies (%w eggs peanuts shellfish strawberries
                 tomatoes chocolate pollen cats))

(defn list
  "list of allergies for the given score"
  [score]
  (loop [s score
         allergy-num (dec (count allergies))
         acc []]
    (cond
     (<= s 0) acc
     (< allergy-num 0) acc
     :else (let [allergy-score (Math/pow 2 allergy-num)
                 allergy (nth allergies allergy-num)]
             (if (>= s allergy-score)
               (recur (- s allergy-score) (dec allergy-num) (conj acc allergy))
               (recur s (dec allergy-num) acc))))))

(defn allergic_to?
  "returns whether the given score includes the allergy"
  [score allergy]
  (contains? (list score) allergy))
