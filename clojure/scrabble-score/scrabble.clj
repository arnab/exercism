(ns scrabble)

(let [scores [[#{"a" "e" "i" "o" "u" "l" "n" "r" "s" "t"} 1]
              [#{"d" "g"} 2]
              [#{"b" "c" "m" "p"} 3]
              [#{"f" "h" "v" "w" "y"} 4]
              [#{"k"} 5]
              [#{"j" "x"} 8]
              [#{"q" "z"} 10]]]

  (defn score-letter
    "scrabble score for a letter"
    [letter]
    (let [l (clojure.string/lower-case letter)]
      (loop [[[letters & [score]] & more-scores] scores]
        (if (contains? letters l) score
            (recur more-scores)))))

  (defn score-word
    "scrabble score for a word."
    [[& letters]]
    (reduce + (map score-letter letters))))
