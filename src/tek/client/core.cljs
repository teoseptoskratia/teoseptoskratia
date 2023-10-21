(ns tek.client.core
  (:require [clojure.browser.repl :as repl]
            [domina :as dom]
            [domina.events :as ev]))

(enable-console-print!)

(defn sayHi [] (dom/set-html! (dom/by-id "title") "Hi!"))
(ev/listen! (dom/by-id "btn") "click" sayHi)
