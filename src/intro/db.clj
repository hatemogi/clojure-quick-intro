(ns intro.db
  (:refer-clojure :exclude [update])
  (:require [clojure.java.jdbc :as jdbc]
            [korma.core :refer [defentity]]
            [korma.db :refer [defdb]]
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
  (jdbc/execute! pg-db "DROP TABLE IF EXISTS 참가자"))

;; (모든테이블드롭)

(def 참가자-테이블
  (jdbc/create-table-ddl :참가자
                         [[:이메일 "VARCHAR(256) PRIMARY KEY"]
                          [:이름    "VARCHAR(32) NOT NULL"]
                          [:신청일시 "TIMESTAMP NOT NULL DEFAULT now()"]]))

(jdbc/db-do-commands pg-db [참가자-테이블])

(jdbc/query pg-db "SELECT * FROM 참가자")
(jdbc/insert! pg-db :참가자 {:이메일 "rich@clojure" :이름 "리치히키"})

;;
;; Korma - Tasty SQL for Clojure <https://sqlkorma.com>
;;

(defdb korma-db
  (merge pg-db {:naming {:keys   ->kebab-case
                         :fields ->snake_case}}))

(defentity 참가자)

(select 참가자)
