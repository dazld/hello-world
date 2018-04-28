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
        pts (partition 3 (shuffle (points w h 12 5)))]

    (doseq [[a b c d] pts]
      (let [[x y] a
            [x2 y2] b
            [x3 y3] c
            rx (/ x w)
            ry (/ y h)]
        (aset ctx "fillStyle" (rgba [rx ry 0.01]))
        (.beginPath ctx)
        (.moveTo ctx x y)
        (.lineTo ctx x2 y2)
        (.lineTo ctx x3 y3)
        (.lineTo ctx x y)
        (.closePath ctx)
        (.fill ctx)))))

(draw)

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  (swap! app-state update-in [:__figwheel_counter] inc))

