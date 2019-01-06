
initials :: String -> String -> String
initials first last = [f] ++ ". " ++ [l] ++ "."
    where (f:_) = first
          (l:_) = last

calcBmis :: [(Double, Double)] -> [Double]
calcBmis xs = [bmi w h | (w, h) <- xs]
    where bmi weight height = weight / height ^2

cylinder :: Double -> Double -> Double
cylinder r h =
    let sideArea = 2 * pi * r * h
        topArea = pi * r ^ 2
    in sideArea +2 * topArea


(do
    v0 :: a <- expr0 :: m a) :: m z




