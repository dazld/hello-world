(ns hello-world.core
  (:require [hello-world.stage.core :refer [ctx dims]]
            [hello-world.utils.core :refer [rgba points]]
            [goog.array :as garray]
            ["simplex-noise" :as simplex]))

(enable-console-print!)
(defonce app-state (atom {:text "Hello world!"}))
(def noise (new simplex))

(defn noisy-shuffle [coll]
  (let [a (to-array coll)
        [x _] (last a)]
    (garray/shuffle a #(Math.abs (.noise2D noise 0 (/ (.now js/Date) 100000))))
    (vec a)))

(defn setup []
  (aset ctx "fillStyle" "black")
  (.fillRect ctx 0 0 (:width dims) (:height dims)))

(defn draw []
  (setup)
  (let [w (:width dims)
        h (:height dims)
        pts (partition 3 (noisy-shuffle (points w h 50 5)))]

    (doseq [[a b c] pts]
      (let [[x y] a
            [x2 y2] b
            [x3 y3] c
            rx (/ x w)
            ry (/ y h)]
        (aset ctx "lineWidth" 4)
        (aset ctx "strokeStyle" (rgba [0 ry 1]))
        (.beginPath ctx)
        (.moveTo ctx x y)
        (.lineTo ctx x2 y2)
        (.lineTo ctx x3 y3)
        (.lineTo ctx x y)
        (.closePath ctx)
        (.stroke ctx))))
  (swap! app-state assoc :last-anim (.requestAnimationFrame js/window draw)))

(draw)

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  (.cancelAnimationFrame js/window (:last-anim @app-state))
  (swap! app-state update-in [:__figwheel_counter] inc))

