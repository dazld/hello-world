(ns hello-world.core
    (:require [hello-world.stage.core :refer [ctx dims]]))

(enable-console-print!)
(defonce app-state (atom {:text "Hello world!"}))
(println @app-state)

(defn setup []
  (.clearRect ctx 0 0 (:width dims) (:height dims)))


(defn points [w h]
  (let [iW (/ w 500)
        iH (/ h 4)
        osW (/ iW 2)
        osH (/ iH 2)]
    (for [x (range 0 w iW)
          y (range 0 h iH)]
      [(+ x osW)
       (+ y osH)])))



(defn draw []
  (setup)
  (let [w (:width dims)
        h (:height dims)
        pts (shuffle (points w h))
        [sx sy] (first pts)]

    (aset ctx "fillStyle" "rgba(32,32,32,0.05)")
    (.beginPath ctx)
    (.moveTo ctx sx sy)

    (doseq [[x y] pts]
      (let [rx (/ x w)
            ry (/ y h)]
        (aset ctx "strokeStyle" (str "rgba(" (clojure.string/join "," [(* 255 rx) (* 255 ry) (* 255 ry)  0.4  ]) ")")))
      (.lineTo ctx x y)
      (.stroke ctx)
      (.closePath ctx)
      (.beginPath ctx)
      (.moveTo ctx x y)))

  (.closePath ctx))


(draw)

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  (swap! app-state update-in [:__figwheel_counter] inc))

