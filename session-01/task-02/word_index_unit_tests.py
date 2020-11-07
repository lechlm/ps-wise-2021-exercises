import unittest
import sys
from word_index import main as wi_main

# Import the 'main function from word_index.py as wi_main
from word_index import main as wi_main


class TestPythonVersion(unittest.TestCase):
    """ This is a sample unit test """

    def test_python_version(self):
        """ This is a simple example of a unittest that checks if you are running the right version of Python """
        self.assertEqual(sys.version_info.major, 3, msg="You are not running python 3")
        self.assertEqual(sys.version_info.minor, 7, msg="You are not running python 3.7")


class TestWordIndex(unittest.TestCase):
    """ Test the word index program """

    def test_word_index_file1(self):
        word_index = wi_main('../file1.txt')
        self.assertEqual(len(word_index), 15)
        self.assertTrue(1 in word_index["Auslesen"])

    def test_word_index_file2(self):
        word_index = wi_main('../file2.txt')
        self.assertEqual(len(word_index), 15)
        self.assertTrue(1 in word_index["Test"])

    def test_word_index_file3(self):
        word_index = wi_main('../file3.txt')
        self.assertEqual(len(word_index), 15)
        self.assertTrue(2 in word_index["Test"])


if __name__ == '__main__':
    unittest.main()


