(ns intro.web
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.mock.request :as mock]))

(def 가짜요청 (mock/request :get "/"))

(defn 핸들러 [요청]
  {:status 200
   :body "안녕하세요, 클로저 웹앱입니다."})

(def 앱
  (wrap-defaults #'핸들러 site-defaults))

(defn 웹서버실행
  [포트]
  (run-jetty #'앱
             {:port 포트 :join? false}))

(def 서버 (웹서버실행 3000))

(.stop 서버)
