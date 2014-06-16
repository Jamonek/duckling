(
;; Temperature
  
; latent temperature
"number as temp"
(dim :number)
{:dim :temperature
 :latent true
 :val {:temperature (:val %1)}}

"<latent temp> temp"
[(dim :temperature) #"(?i)(grados?)|°"]
(dissoc %1 :latent)

"<temp> Celsius"
[(dim :temperature) #"(?i)(cent(i|í)grados?|c(el[cs]?(ius)?)?\.?)"]
(-> %1
    (assoc-in [:val :unit] "C")
    (dissoc :latent))

"<temp> Fahrenheit"
[(dim :temperature) #"(?i)f(ah?reh?n(h?eit)?)?\.?"]
(-> %1
    (assoc-in [:val :unit] "F")
    (dissoc :latent))

"<latent temp> temp bajo cero"
[(dim :temperature) #"(?i)((grados?)|°)?( bajo cero)"]
(-> %1
    (dissoc :latent)
    (assoc-in [:val :temperature] (* -1 (-> %1 :val :temperature))))

;; Distance

; latent distance
"number as distance"
(dim :number)
{:dim :distance
 :latent true
 :val {:distance (:val %1)}}

"<latent dist> km"
[(dim :distance) #"(?i)k(il(ó|o))?m?(etro)?s?"]
(-> %1
    (dissoc  :latent)
    (assoc-in [:val :distance] (* 1000 (-> %1 :val :distance)))
    (assoc-in [:val :unit] "meters"))

"<dist> meters"
[(dim :distance) #"(?i)m(etros?)?"]
(-> %1
    (assoc-in [:val :unit] "meters")
    (dissoc :latent))

"<dist> centimeters"
[(dim :distance) #"(?i)(cm|cent(í|i)m(etros?))"]
(-> %1
    (assoc-in [:val :unit] "centimeters")
    (dissoc :latent))


"<dist> miles"
[(dim :distance) #"(?i)miles?"]
(-> %1
    (assoc-in [:val :unit] "miles")
    (dissoc :latent))

)