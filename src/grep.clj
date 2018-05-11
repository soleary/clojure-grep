(ns grep
  (:require [clojure.java.io :as io]))

(defn print-matched-lines [regex file]
  (with-open [reader (io/reader file)]
    (doall
      (map println (filter #(re-find regex %1) (line-seq reader))))))

(defn process-files [regex files]
  (doseq [file files]
    (print-matched-lines regex file)))

(defn -main [pattern & files]
  (process-files (re-pattern pattern) files))
