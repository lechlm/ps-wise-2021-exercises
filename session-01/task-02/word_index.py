#!/usr/bin/env python

# Program Description:
#
# Word Index is a program that takes a plain text file as input and
# outputs all the words contained in it
# sorted alphabetically along with the page numbers on which they occur.
# The program assumes that a page is a
# sequence of 45 lines, each line has max 80 characters, and there is no
# hyphenation. Additionally, Word Index
# must ignore all words that occur more than 100 times.

####################################
# Free Flow, a.k.a. No Style
####################################

import collections
import string
import sys

# Global "constants"
LINES_PER_PAGE       =  45
MAX_SIZE_LINE        =  80
STOP_FREQUENCY_LIMIT = 100


# Defining a main method makes testing easier
def main(file_path):
    word_count = {}
    word_index = {}
    line_count = 0
    page_number = 1

    file_handle = open(file_path, "r")
    while True:
        line = file_handle.readline(MAX_SIZE_LINE)
        if not line:
            break

        line_count += 1

        words = line.split()
        for word in words:
            word = word.replace(',', '')
            if word != 'e.g.' and word != 'i.e.':
                for punctuation_char in string.punctuation:
                    word = word.replace(punctuation_char, '')

            if word == '':
                continue

            if word not in word_count.keys():
                word_count[word] = 0
                word_index[word] = []
            word_count[word] = word_count[word] + 1

            if page_number not in word_index[word]:
                word_index[word].append(page_number)

        if line_count == LINES_PER_PAGE:
            line_count = 0
            page_number = page_number + 1

    file_handle.close()

    for word, count in word_count.items():
        if count > STOP_FREQUENCY_LIMIT:
            word_index.pop(word)

    for word, index in collections.OrderedDict(sorted(word_index.items(), key=lambda i: i[0].casefold())).items():
        print(word + ": " + ", ".join(map(str, index)))

    return word_index


if __name__ == "__main__":
    main(sys.argv[1])
