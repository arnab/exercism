(ns meetup)
(import 'java.util.Calendar)

(defn- dates-in [yr mm day-name]
  "returns a list of dates, given the year and month and day-name"
  )

(defn- schedule-by-week [dates pos]
  "returns the date from the list by it's position. nil if position is not in the list"
  )

(defn- schedule-by-range [dates date-range]
  "returns the first date that's also in the given range. nil if none match"
  )

;;; mon/tue... teenth
;;; first/second/third/fourth/last mon/tue day

;;; get last
;; (get v (dec (count v)))

(let [c (Calendar/getInstance)]
  (doto c
    (.set Calendar/YEAR 2013)
    (.set Calendar/MONTH 1) ; Feb is 1
    ;; (.set Calendar/DAY_OF_MONTH 1)
    ;; (.set Calendar/DAY_OF_WEEK Calendar/SATURDAY)
    )
  [(.getActualMinimum c Calendar/DAY_OF_MONTH)
   (.getActualMaximum c Calendar/DAY_OF_MONTH)
   c])
