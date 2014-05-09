(ns gigasecond
  (:require [clj-time.core :as c-t]
            [clj-time.format :as c-f]))

(defn- format [t]
  (let [{:keys [years months days]} (c-f/instant->map t)]
    [years months days]))

(defn- to-time [y m d]
  (c-t/date-time y m d))

(defn- add-gigaseconds [t]
  (c-t/plus t
          ;; add in minutes rather than secs, to avoid int overflows in clj-time
          (c-t/minutes (/ (bigint 1e9) 60))))

(defn from
  "returns the gigasecond date (10**9 sec in the future) from the given date."
  [y m d]
  (format (add-gigaseconds (to-time y m d))))
