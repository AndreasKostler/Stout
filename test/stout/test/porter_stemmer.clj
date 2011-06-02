(ns stout.test.porter-stemmer
  (:use [stout.porter-stemmer])
  (:use [clojure.test]))

(defn load-f [filename]
  (seq (.split (slurp filename) "\n")))

(defn stem [s]
  (pmap  porter-stemmer s))

(defn test-stemmer [o-filename c-filename]
  (let [orig (load-f o-filename)
         control (load-f c-filename)
         stemmed (stem orig)]
    (not-any? false? (map #(= %1 %2) control stemmed))))


(deftest measurement-tests
  (testing "porter-stemmer"
    (is (test-stemmer "test/stout/test/test-input.txt" "test/stout/test/test-output.txt") "Stemmed output doesn't match control data")))

