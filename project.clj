(defproject clj-lqs "0.1.0"
  :description "Clojure client for Loganis Query Script API"
  :url "https://github.com/loganis/clj-lqs"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [http-kit "2.1.18"]
                 [org.clojure/data.json "0.2.4"]]
  :main clj-lqs.core
  :aot :all
  )
