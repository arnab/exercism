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

(defn schedule-in-week-num [dow pos mm yr]
  "returns the date from the list by it's position.
   nil if position is not in the list.
   e.g. Fourth Sunday of March 2013:
          (schedule-in-week-num 7 4 3 2013)"
  (simple-format
   (if (= -1 pos)
     (last (dates-in yr mm dow))
     (nth (dates-in yr mm dow) (dec pos)))))

(defn schedule-in-date-range [dow date-range mm yr]
  "returns the first date that's also in the given range. nil if none match.
   e.g. monteenth in May 2013: (schedule-in-date-range 1 (range 13 20) 5 2013)"
  (simple-format
   (first (filter #(contains? (set date-range) (t/day %))
                  (dates-in yr mm dow)))))

(defmacro schedule-in-date-range-fn [fname dow date-range]
  "creates fn to partially apply schedule-in-date-range.
   e.g. (schedule-in-date-range-fn 'monteenth' 1 teens)
        defines the monteenth fn, which can be fully applied with:
        (monteenth 5 2013)"
  `(intern *ns*
           (symbol ~fname)
           (partial schedule-in-date-range ~dow ~date-range)))

(let [days ["mon" "tues" "wednes" "thurs" "fri" "satur" "sun"]
      teens (range 13 20)]
  (doseq [d days]
    (let [fname (str d "teenth")
          dow (inc (.indexOf days d))]
      (do (schedule-in-date-range-fn fname dow teens)))))

(defmacro schedule-in-week-num-fn [fname dow pos]
  "creates fn to partially apply schedule-in-week-num.
   e.g. (schedule-in-week-num-fn 'third-tuesday' 2 3)
        creates third-tuesday, which can be fully applied with
        (third-tuesday 5 2013)"
  `(intern *ns*
           (symbol ~fname)
           (partial schedule-in-week-num ~dow ~pos)))

(let [pos-names ["first" "second" "third" "fourth"]
      days (map #(str % "day") ["mon" "tues" "wednes" "thurs" "fri" "satur" "sun"])]
  (doseq [pos-name pos-names
          day days]
    (let [fname (str pos-name "-" day)
          dow (inc (.indexOf days day))
          pos (inc (.indexOf pos-names pos-name))]
      (do (schedule-in-week-num-fn fname dow pos))))
  (doseq [day days]
    (let [fname (str "last-" day)
          dow (inc (.indexOf days day))
          pos -1]
      (do (schedule-in-week-num-fn fname dow pos)))))
