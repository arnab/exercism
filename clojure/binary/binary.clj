(ns binary)

(defn- char->int
  "parses char as int, defaulting to 0"
  [c]
  (try
    (Integer/parseInt (str c))
    (catch NumberFormatException _ 0)))

(defn- positional-val
  "c * 2^i"
  [i c]
  (* (char->int c)
     (int (Math/pow 2 i))))

(defn to-decimal
  "converts a binary string (1010101) to decimal representation"
  [[& cs]]
  (reduce +
          (map-indexed positional-val (reverse cs))))
