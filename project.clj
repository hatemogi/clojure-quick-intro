(defproject clojure-quick-intro "0.1.0-SNAPSHOT"
  :description "클로저 소개 발표용 코드"
  :url "https://github.com/hatemogi/clojure-quick-intro"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-beta4"]
                 [org.clojure/core.async "0.3.443"]
                 [ring "1.6.3"]
                 [ring/ring-defaults "0.3.1"]
                 [compojure "1.6.0"]
                 [hiccup "1.0.5"]
                 [org.clojure/java.jdbc "0.7.3"]
                 [org.postgresql/postgresql "42.1.4"]
                 [korma "0.4.3"]
                 [camel-snake-kebab "0.4.0"]]
  :main ^:skip-aot intro.core
  :target-path "target/%s"
  :repl-options {:init-ns intro}
  :profiles {:uberjar {:aot :all}
             :dev {:dependencies [[ring/ring-spec "0.0.3"]
                                  [ring/ring-mock "0.3.1"]
                                  [org.clojure/test.check "0.9.0"]]}})
