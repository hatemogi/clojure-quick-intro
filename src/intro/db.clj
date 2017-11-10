(ns intro.db
  (:refer-clojure :exclude [update])
  (:require [clojure.java.jdbc :as jdbc]
            [korma.core :refer :all]
            [korma.db :refer [defdb transaction]]
            [camel-snake-kebab.core :refer [->kebab-case ->snake_case]]))

;; CREATE USER intro PASSWORD 'secret';
;; CREATE DATABASE intro OWNER intro TEMPLATE 'template0' \
;;   ENCODING 'UTF-8' LC_COLLATE 'C' LC_CTYPE 'C';

(def pg-db {:dbtype   "postgresql"
            :dbname   "intro"
            :host     "localhost"
            :user     "intro"
            :password "secret"})

(defn 모든테이블드롭 []
  (jdbc/execute! pg-db "DROP TABLE IF EXISTS 언어"))

;; (모든테이블드롭)

(def 언어-테이블
  (jdbc/create-table-ddl :언어
                         [[:이름 "VARCHAR(32) PRIMARY KEY"]
                          [:만든이 "VARCHAR(32) NOT NULL"]
                          [:공개연도 "INT NOT NULL"]]))

(jdbc/db-do-commands pg-db [언어-테이블])

(jdbc/query pg-db "SELECT * FROM 언어")
(jdbc/insert! pg-db :언어
  {:이름 "리스프" :만든이 "John McCarthy" :공개연도 1958})

(jdbc/insert-multi! pg-db :언어
  [:이름 :만든이 :공개연도]
  [["클로저" "Rich Hickey" 2007]
   ["루비" "松本 行弘" 1995]
   ["파이썬" "Guido van Rossum" 1991]
   ["자바" "James Gosling" 1995]
   ["스몰톡" "Alan Kay" 1972]
   ["얼랭" "Joe Armstrong" 1986]
   ["스칼라" "Martin Odersky" 2004]
   ["스위프트" "Chris Lattner" 2014]])



;;
;; Korma - Tasty SQL for Clojure <https://sqlkorma.com>
;;
(defdb korma-db
  (merge pg-db {:naming {:keys   ->kebab-case
                         :fields ->snake_case}}))

(defentity 언어)

; (delete 언어)

(select 언어 (order :공개연도 :desc))
