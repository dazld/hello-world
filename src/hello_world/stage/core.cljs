(ns hello-world.stage.core)

(defonce canvas (.getElementById js/document "stage"))
(defonce ctx (.getContext canvas "2d"))

(def dims
  (let [dpr (.-devicePixelRatio js/window)
        w   (* dpr (aget js/window "innerWidth"))
        h   (* dpr (aget js/window "innerHeight"))]

    {:dpr    dpr
     :width  w
     :height h}))

(.setAttribute canvas "width" (:width dims))
(.setAttribute canvas "height" (:height dims))

(println (:width dims) (:height dims))
