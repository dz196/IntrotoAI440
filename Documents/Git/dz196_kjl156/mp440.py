
'''
GCD algorithm
'''
def gcd(a, b):
    if type(a) not in [int] or type(b) not in [int]:
        raise TypeError("Number must be a real number")
    if a == 0 or b == 0:
        raise ValueError("Number must be non-zero")
    if a < 0 or b < 0:
        raise ValueError("Number must be non-negative")
    
    iter = min(a,b) + 1
    
    for x in reversed(range(1,iter)):
        if a % x == 0 and b % x == 0:
            return x

    return -1
'''
Rectangles on a rubik's cube
'''
def rubiks(n):
    if type(n) not in [int, float]:
        raise TypeError("Number must be a real number")
    if n == 0:
        raise ValueError("Number must be non-zero")
    if n < 0:
        raise ValueError("Number must be non-negative")
    
    inner_num_equation = lambda x : (x * (x + 1)) / 2
    inner_num = inner_num_equation(n)
    
    return 6 * (inner_num ** 2)

'''
Guessing a number
'''
def guess_unlimited(n, is_this_it):
    # The code here is only for illustrating how is_this_it() may be used 
    guess = n/2 
    if is_this_it(guess) == True:
        return guess
    return -1
        
'''
Guessing a number where you can only make two guesses that are larger
'''
def guess_limited(n, is_this_smaller):
    return -1
        
'''
Guessing a number, bonus problem
'''
def guess_limited_plus(n, is_this_smaller):
    return -1


        

