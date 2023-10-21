
(defproject tek "0.1.0"
  :description ""
  :url "http://example.com/FIXME"

  :clean-targets ["build" :target-path]

  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/clojurescript "1.11.60"]
                 ;; to use nodejs/require (remove if you don't need it)
                 [io.nervous/cljs-nodejs-externs "0.2.0"]
                 ;; to parse cli options (remove if you don't need it)
                 [org.clojure/tools.cli "1.0.219"]
                 [domina "1.0.3"]]

  :plugins [[lein-cljsbuild "1.1.8"]
            [lein-npm "0.6.1"]
            [lein-doo "0.1.11"]
            [org.bodil/lein-noderepl "0.1.11"]
            [lein-resource "17.06.1"]]

  :profiles {:dev {:dependencies [[lein-doo "0.1.6"]]}}

  :npm {:dependencies [[source-map-support "0.4.0"]
                       [express "4.18.2"]
                       [pug "3.0.2"]]
        :package {;; To distribute a node binary, set :bin
                  ; :bin {"cljs-express-domina" "bin/main.js"}
                  ;; To distribute a node library, set :main
                  ; :main "bin/main.js"
                  ;; To push to a publicly available npm name set :private
                  ; :private false
                  }}

  :hooks [leiningen.resource]

  :resource {
             :resource-paths [ ["src/public"
                                {
                                 :includes [#".*"]  ;; list of regex
                                 :excludes [#".*~"]  ;; list of regex
                                 :target-path "build/public" ;; directory to store files
                                 }]
                                ["src/views"
                                 {
                                  :includes [#".*"]  ;; list of regex
                                  :excludes [#".*~"]  ;; list of regex
                                  :target-path "build/views" ;; directory to store files
                                  }]]
             }

  :aliases {"build" [["cljsbuild" "once" "server" "client"]]
            "test" ["doo" "node" "test-node" "once"]
            "test-auto" ["doo" "node" "test-node" "auto"]}

  ;; This release-task does lein npm publish in addition to lein deploy
  :release-tasks [["vcs" "assert-committed"]
                  ["clean"]
                  ["build"]
                  ["change" "version"
                   "leiningen.release/bump-version" "release"]
                  ["vcs" "commit"]
                  ["vcs" "tag"]
                  ;; Uncomment the following line to distribute via npm
                  ; ["npm" "publish"]
                  ;; The following line deploys to a maven repo
                  ["deploy"]
                  ["change" "version"
                   "leiningen.release/bump-version"]
                  ["vcs" "commit"]
                  ["vcs" "push"]]

  :cljsbuild {:builds [{:id "server"
                        :source-paths ["src/tek/server"]
                        :compiler {:output-to "build/server/main.js"
                                   :output-dir "build/server/js"
                                   :optimizations :simple
                                   :target :nodejs
                                   :pretty-print false
                                   :source-map "build/server/main.js.map"}}
                       {:id "client"
                        :source-paths ["src/tek/client"]
                        :compiler {:output-to "build/public/js/main.js"
                                   :output-dir "build/public/js/js"
                                   :optimizations :advanced
                                   :pretty-print false
                                   :source-map "build/public/js/main.js.map"}}]})
