(ns grep
  (:require [clojure.java.io :as io]))

(defn matched-lines [regex file]
  (with-open [reader (io/reader file)]
    (prn (filter #(re-find regex %1) (line-seq reader)))))

(defn process-files [regex files]
  (doseq [file files]
    (matched-lines regex file)))

(defn -main [pattern & files]
  (do
    (println (System/getProperty "user.dir"))
    (process-files (re-pattern pattern) files)
    (println "Done running")))
