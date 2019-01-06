

doubleMe x = x + x
doubleUs x y = doubleMe x + doubleMe y

doubleSmall x = if x > 100 then x else doubleMe x

boomBangs xs = [ if x < 10 then "Boom" else "Bang" | x <- xs, odd x]

removeNonUppercase :: [Char] -> [Char]
removeNonUppercase st = [c | c <- st, elem c ['A'..'Z']]

addThree :: Int -> Int -> Int -> Int
addThree x y z = x + y + z

factorial :: Int -> Int
factorial 0 = 1
factorial n = n * factorial (n - 1)

addVectors :: (Double, Double) -> (Double, Double) -> (Double, Double)
-- addVectors a b = (fst a + fst b, snd a + snd b)
addVectors (a1, a2) (b1, b2) = (a1 + b1, a2 + b2)

thd :: (a, b, c) -> c
thd (_, _, z) = z

head' :: [a] -> a
head' [] = error "Empty"
head' (head:_) = head

firstLetter :: String -> String
firstLetter "" = "Empty"
firstLetter all@(x:xs) = "All:" ++ all ++ ",First:" ++ [x]

bmiTell :: Double -> String
bmiTell bmi
    | bmi <= 18.5 = "karui"
    | bmi <= 25.0 = "Hutuu"
    | bmi <= 30.0 = "debui"
    | otherwise   = "yabai"

max' :: (Ord a) => a -> a -> a
max' a b
    | a > b = a
    | otherwise  b

--initials :: String -> String -> String
--initials first last = [f] ++ ". " ++ [l] ++ "."
--  where (f:_) = first
--        (l:_) = last

