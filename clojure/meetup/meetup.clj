(ns meetup
  (:require [clj-time.core :as t]
            [clj-time.predicates :as tpr]
            [clojure.tools.namespace.repl :as repl :only [refresh]]))

(repl/refresh)

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

(defn schedule-in-week-num [dow pos mm yr]
  "returns the date from the list by it's position.
   nil if position is not in the list.
   e.g. Fourth Sunday of March 2013:
          (schedule-in-week-num 7 4 3 2013)"
  (simple-format
   (nth (dates-in yr mm dow) (dec pos))))

(defn schedule-in-date-range [dow date-range mm yr]
  "returns the first date that's also in the given range. nil if none match.
   e.g. monteenth in May 2013: (schedule-in-date-range 1 (range 13 20) 5 2013)"
  (simple-format
   (first (filter #(contains? (set date-range) (t/day %))
                  (dates-in yr mm dow)))))

(def teens (range 13 20))
(def monteenth    (partial schedule-in-date-range 1 teens))
(def tuesteenth   (partial schedule-in-date-range 2 teens))
(def wednesteenth (partial schedule-in-date-range 3 teens))
(def thursteenth  (partial schedule-in-date-range 4 teens))
(def friteenth    (partial schedule-in-date-range 5 teens))
(def saturteenth  (partial schedule-in-date-range 6 teens))
(def sunteenth    (partial schedule-in-date-range 7 teens))
