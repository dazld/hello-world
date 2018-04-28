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
        pts (shuffle (points w h))
        [sx sy] (last pts)]

    (aset ctx "fillStyle" "rgba(32,32,32,0.05)")
    (aset ctx "lineWidth" 1)
    (.beginPath ctx)
    (.moveTo ctx sx sy)

    (doseq [[x y] pts]
      (let [rx (/ x w)
            ry (/ y h)]
        (aset ctx "strokeStyle" (rgba [rx ry 1])))
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

