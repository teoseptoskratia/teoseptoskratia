(ns teoseptoskratia.core
  (:require [cljs.nodejs :as node]
            [cljs.tools.cli :refer [parse-opts]]))

(node/enable-util-print!)

(def options-spec
  [["-h" "--help"]])

(defn run [args]
  (parse-opts args options-spec))

(def express (node/require "express"))
(def app (express))
(defn -main []
  (doto app
    (.get "/" (fn [req res]
                (.send res "Hello World")))
    (.listen 3000))
  (println "localhost:3000")
  (println (run (.-argv node/process))))

(set! *main-cli-fn* -main)
