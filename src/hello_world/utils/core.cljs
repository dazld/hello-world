(ns hello-world.utils.core
  (:require [clojure.string :refer [join]]))

(defn floor [n]
  (Math/floor n))

(defn rgba [color]
  (let [comps (->> color
                   (map #(* 255 %))
                   (map floor))]
    (str "rgba(" (join "," comps) ", 0.3)")))

(defn points [w h npW npH]
  (let [iW (/ w npW)
        iH (/ h npH)
        osW (/ iW 2)
        osH (/ iH 2)]
    (for [x (range 0 w iW)
          y (range 0 h iH)]
      [(+ x osW)
       (+ y osH)])))
