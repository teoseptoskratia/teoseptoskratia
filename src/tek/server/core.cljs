(ns tek.server.core
  (:require [cljs.nodejs :as node]))

(node/enable-util-print!)

(def express (node/require "express"))
(defn -main []
  (let [[port app] [3000 (express)]]
    (doto app
      (.set "view engine" "pug")
      (.set "views" "build/views")
      (.use (.static express "build/public"))
      (.get "/" (fn [req res]
                  (.render res "index" (clj->js {:title "Welcome!" :message "Hello World!"}))))
      (.listen port (fn []
                       (println "localhost:" port))))))

(set! *main-cli-fn* -main)
