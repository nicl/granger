(defproject granger "0.1.0-SNAPSHOT"
  :description "API to store books."
  :url "https://github.com/nicl/granger"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.1"]
                 [clojurewerkz/elastisch "2.1.0"]
                 [ring/ring-json "0.3.1"]
                 [clj-http "1.0.1"]]
  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler granger.handler/app}
  :profiles {:dev {:dependencies [[ring/ring-mock "0.2.0"]]}})
