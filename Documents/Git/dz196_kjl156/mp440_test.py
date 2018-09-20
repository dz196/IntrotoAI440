import unittest
from mp440 import gcd,rubiks

class TestGCD(unittest.TestCase):
    def test_example(self):
        self.assertAlmostEqual(gcd(27,27), 27)
        self.assertAlmostEqual(gcd(10,2), 2)
        self.assertAlmostEqual(gcd(276,239),1)
    def test_value(self):
        self.assertRaises(ValueError, gcd, -2,4)
        self.assertRaises(ValueError, gcd, 0,0)
    def test_types(self):
        self.assertRaises(TypeError, gcd, 2+5j,"yee")
        self.assertRaises(TypeError, gcd, True,False)
        self.assertRaises(TypeError, gcd, "GCD","GCD")
        self.assertRaises(TypeError, gcd, "1")

class TestRubiks(unittest.TestCase):
    def test_example(self):
        self.assertAlmostEqual(rubiks(1), 6)
        self.assertAlmostEqual(rubiks(2), 54)
        self.assertAlmostEqual(rubiks(3), 216)
        self.assertAlmostEqual(rubiks(4), 600)
    def test_value(self):
        self.assertRaises(ValueError, rubiks, -2)
        self.assertRaises(ValueError, rubiks, 0)
    def test_types(self):
        self.assertRaises(TypeError, rubiks, 2+5j)
        self.assertRaises(TypeError, rubiks, True)
        self.assertRaises(TypeError, rubiks, "Rubiks")
        self.assertRaises(TypeError, rubiks, "1")
if __name__ == '__main__':
    unittest.main()
