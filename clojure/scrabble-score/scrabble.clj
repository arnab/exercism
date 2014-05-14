(ns scrabble)

(defn score-letter
  "scrabble score for a letter"
  [letter]
  (let [l (clojure.string/lower-case letter)]
    (cond
     (contains? #{"a" "e" "i" "o" "u" "l" "n" "r" "s" "t"} l) 1
     (contains? #{"d" "g"} l) 2
     (contains? #{"b" "c" "m" "p"} l) 3
     (contains? #{"f" "h" "v" "w" "y"} l) 4
     (contains? #{"k"} l) 5
     (contains? #{"j" "x"} l) 8
     (contains? #{"q" "z"} l) 10
     :else (throw (IllegalArgumentException. (str "Unknown letter: " l)))
     )))

(defn score-word
  "scrabble score for a word."
  [[& letters]]
  (reduce + (map score-letter letters)))
