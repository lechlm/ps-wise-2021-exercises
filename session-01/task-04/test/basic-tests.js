// Maybe use .strict
const assert = require('assert');
const wordIndex = require('../word-index');

describe('Basic Tests', function() {
    
    describe('Runs correct version of node.', function(){
        it('Version of node must be v10.23.0', function() {
            assert.equal(process.version, 'v10.23.0');
        });
    });
});

describe('Word Index Tests', function() {
    it('Test File 1', function() {
        word_index = wordIndex("/Users/lechlm/Documents/OWN/ps/ps-wise-2021-exercises/session-01/file1.txt");
        assert.equal(word_index.size, 15);
        assert.ok(word_index.get("Auslesen").has(1));
    });

    it('Test File 2', function() {
        word_index = wordIndex("/Users/lechlm/Documents/OWN/ps/ps-wise-2021-exercises/session-01/file2.txt");
        assert.equal(word_index.size, 15);
        assert.ok(word_index.get("Test").has(1));
    });

    it('Test File 3', function() {
        word_index = wordIndex("/Users/lechlm/Documents/OWN/ps/ps-wise-2021-exercises/session-01/file3.txt");
        assert.equal(word_index.size, 15);
        assert.ok(word_index.get("Test").has(1));
        assert.ok(word_index.get("Test").has(2));
    });
});