(ns meetup
  (:require [clj-time.core :as t]
            [clj-time.predicates :as tpr]))

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

(defn- schedule-by-week [yr mm dow pos]
  "returns the date from the list by it's position.
   nil if position is not in the list.
   e.g. Fourth Sunday of March 2013:
          (schedule-by-week 2013 3 7 4)"
  (get (dates-in yr mm dow) (dec pos)))

(defn- schedule-by-range [dates date-range]
  "returns the first date that's also in the given range. nil if none match"
  )

;;; mon/tue... teenth
;;; first/second/third/fourth/last mon/tue day

;;; get last
;; (get v (dec (count v)))
