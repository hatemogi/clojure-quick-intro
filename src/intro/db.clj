(ns intro.db
  (:refer-clojure :exclude [update])
  (:require [clojure.java.jdbc :as jdbc]
            [korma.core :refer :all]))
