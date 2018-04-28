(ns hello-world.core
    (:require [hello-world.stage.core :refer [ctx dims]]
              [hello-world.utils.core :refer [rgba points]]))


(enable-console-print!)
(defonce app-state (atom {:text "Hello world!"}))
(println @app-state)

(defn setup []
  (aset ctx "fillStyle" "black")
  (.fillRect ctx 0 0 (:width dims) (:height dims)))




(defn draw []
  (setup)
  (let [w (:width dims)
        h (:height dims)
        pts (partition 2 (shuffle (points w h 300 15)))]

    (aset ctx "fillStyle" "rgba(32,32,32,0.05)")


    (doseq [[a b] pts]
      (let [[x y] a
            [x2 y2] b
            rx (/ x w)
            ry (/ y h)]
        (aset ctx "strokeStyle" (rgba [rx ry 1]))
        (.beginPath ctx)
        (.moveTo ctx x y)
        (.lineTo ctx x2 y2)
        (.stroke ctx)
        (.closePath ctx)))))




(draw)

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  (swap! app-state update-in [:__figwheel_counter] inc))

