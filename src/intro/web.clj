(ns intro.web
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.mock.request :as mock]
            [compojure.core :refer :all]
            [compojure.route :as route]))

(def 가짜요청 (mock/request :get "/"))

(defn 핸들러 [요청]
  {:status 200
   :body "안녕하세요, 클로저 웹앱입니다."})

(defroutes 라우터
  (GET "/" [] 핸들러)
  (POST "/echo-name" [name]
    (str "안녕하세요, " name "님!"))
  (route/not-found "찾을 수 없습니다"))

;; $ http :3000/
;; $ http -v --form POST :3000/echo-name name=김대현

(def 앱
  (wrap-defaults 라우터
    (dissoc site-defaults :security)))

(defn 웹서버실행
  [포트]
  (run-jetty #'앱 {:port 포트 :join? false}))

(def 서버 (웹서버실행 3000))

(.stop 서버)
