(ns hello-world.stage.core)

(defonce canvas (.getElementById js/document "stage"))
(defonce ctx (.getContext canvas "2d"))

(def dims
  {:width (aget js/window "innerWidth")
   :height (aget js/window "innerHeight")
   :dpr (aget js/window "devicePixelRatio")})


(.setAttribute canvas "width" (:width dims))
(.setAttribute canvas "height" (:height dims))

(println (:width dims) (:height dims))
