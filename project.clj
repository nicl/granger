(defproject granger "0.1.0-SNAPSHOT"
  :description "API to store books."
  :url "https://github.com/nicl/granger"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [compojure "1.1.1"]
                 [congomongo "0.3.2"]]
  :plugins [[lein-ring "0.7.3"] [lein-swank "1.4.0"]]
  :ring {:handler granger.handler/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.3"]]}})
