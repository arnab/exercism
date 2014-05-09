(ns gigasecond
  (:require [clj-time.core :as c-t]))

(defn- format [time]
  [(c-t/year time)
   (c-t/month time)
   (c-t/day time)])

(defn- to-time [y m d]
  (c-t/date-time y m d))

(defn- add-gigaseconds [time]
  (c-t/plus time
          ;; add in minutes rather than secs, to avoid int overflows in clj-time
          (c-t/minutes (/ (bigint 1e9) 60))))

(defn from
  "returns the gigasecond date (10**9 sec in the future) from the given date."
  [y m d]
  (format (add-gigaseconds (to-time y m d))))
