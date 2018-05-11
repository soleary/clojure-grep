(ns grep
  (:require [clojure.java.io :as io]
            [ clojure.string :as st]))

(defn preprocess
  "Takes a seq of lines and returns little vectors of filename, line number, and line"
  [filename lines]
  (map vector (iterate identity filename) (iterate inc 1) lines))

(defn matches
  "Match a vector made by preprocess. The original contents of the line are always last"
  [regex linev]
  (re-find regex (last linev)))

(defn vecprint [v]
  (println (st/join ": " v)))

(defn print-matches [regex filename]
  (with-open [reader (io/reader filename)]
    (doall
      (map vecprint (filter #(matches regex %1) (preprocess filename (line-seq reader)))))))

(defn process-files [regex files]
  (doseq [filename files]
    (print-matches regex filename)))

(defn -main [pattern & files]
  (process-files (re-pattern pattern) files))
