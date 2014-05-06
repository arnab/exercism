(ns meetup
  (:require [clj-time.core :as t]
            [clj-time.predicates :as tpr]))

(defn- simple-format [d]
  (when d
    [(t/year d) (t/month d) (t/day d)]))

(defn- dates-in
  "returns a list of dates, given the year and month and day-name.
   e.g. All dates in 2013 Feb: (dates-in 2014 4)
   All Mondays in 2013 Feb: (dates-in 2014 4 1)"
  ([yr mm]
     (loop [d (t/date-time yr mm)
            dates []]
       (if (= mm (t/month d))
         (recur (t/plus d (t/days 1)) (conj dates d))
         dates)))
  ([yr mm dow]
     (filter
      #(= dow (t/day-of-week %))
      (dates-in yr mm))))

(defn schedule-by-week [yr mm dow pos]
  "returns the date from the list by it's position.
   nil if position is not in the list.
   e.g. Fourth Sunday of March 2013:
          (schedule-by-week 2013 3 7 4)"
  (simple-format
   (nth (dates-in yr mm dow) (dec pos))))

(defn schedule-by-range [yr mm dow date-range]
  "returns the first date that's also in the given range. nil if none match.
   e.g. monteenth in May 2013: (schedule-by-range 2013 5 1 (range 13 20))"
  (simple-format
   (first (filter #(contains? date-range (t/day %))
                  (dates-in yr mm dow)))))

;; (def teens (range 13 20))
;; (defn monteenth [mm yr]
;;   (schedule-by-range yr mm 1 teens))
;; (defn first-monday [mm yr]
;;   (schedule-by-week yr mm 1 1))
;; (defmacro monteenth [mm yr]
;;   `(schedule-by-range ~yr ~mm 1 teens))
;; (macroexpand-1 `(monteenth 8 2013))

;; (defmacro first-monday [mm yr]
;;   `(schedule-by-week ~yr ~mm 1 1))
;; (defmacro first-tuesday [mm yr]
;;   `(schedule-by-week ~yr ~mm 2 1))
;; (defmacro second-monday [mm yr]
;;   `(schedule-by-week ~yr ~mm 1 2))
;; (defmacro second-tuesday [mm yr]
;;   `(schedule-by-week ~yr ~mm 2 2))

;; (defmacro def-schedule-fn [f-name dow ])
;; (def-schedule-fn "first-monday" 1 1)

;; (def days `("monday" "tuesday" "wednesday"
;;             "thursday" "friday" "saturday" "sunday"))
;; (range 1 8)
;; (let [f (for [d days]
;;             [(str "first-" d) (inc (.indexOf days d))])]
;;   f)
