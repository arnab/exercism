(ns bob)

(defn response-for [str]
  (cond
   (re-matches #"?$" str)
   :else "Whatever."))
