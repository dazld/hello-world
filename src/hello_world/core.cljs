(ns hello-world.core
  (:require [hello-world.stage.core :refer [ctx dims]]
            [hello-world.utils.core :refer [rgba points]]
            ["simplex-noise" :as simplex]))


(enable-console-print!)
(defonce app-state (atom {:text "Hello world!"}))
(def noise (new simplex))

(defn setup []
  (aset ctx "fillStyle" "black")
  (.fillRect ctx 0 0 (:width dims) (:height dims)))

(defn draw []
  (let [w (:width dims)
        h (:height dims)
        pts (partition 3 (shuffle (points w h 120 5)))]

    (doseq [[a b c] pts]
      (let [[x y] a
            [x2 y2] b
            [x3 y3] c
            rx (/ x w)
            ry (/ y h)]
        (aset ctx "strokeStyle" (rgba [rx ry 0.01]))
        (.beginPath ctx)
        (.moveTo ctx x y)
        (.lineTo ctx x2 y2)
        (.lineTo ctx x3 y3)
        (.lineTo ctx x y)
        (.closePath ctx)
        (.stroke ctx)))))

(setup)
(draw)

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  (swap! app-state update-in [:__figwheel_counter] inc))

