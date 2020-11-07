const fs = require('fs');

const LINES_PER_PAGE = 45;
const MAX_SIZE_LINE = 80;
const STOP_FREQUENCY_LIMIT = 100;

function removePunctuation(word) {
    word = word.replace(",", "");
    word = word.replace("\r", "");
    if(word != "e.g." && word != "i.e.")
        word = word.replace(".", "");

    return word;
}

module.exports = function(file_path) {
    var word_count = new Map();
    var word_index = new Map();
    var line_count = 0;
    var page_number = 1;

    var data = fs.readFileSync(file_path, "utf8");
    
    var line = "";
    var lines = [];
    var line_size_count = 0;
    for (var i = 0; i < data.length; i++) {
        if (line_size_count == MAX_SIZE_LINE || data.charAt(i)=="\n") {
            lines.push(line);
            line = "";
            line_size_count = 0;

            if(data.charAt(i)=="\n")
                continue;
        }
        
        line_size_count++;
        line = line + data.charAt(i);
    }
    lines.push(line);

    lines.forEach(line => {
        line_count++;

        var words = line.split(" ");
        words.forEach(word => {
            word = removePunctuation(word);
            if(word.length==0) 
                return;
            
            if(!word_count.has(word)) {
                word_count.set(word, 0);
                word_index.set(word, new Set());
            }
            word_count.set(word, word_count.get(word) + 1);
            word_index.get(word).add(page_number);
        });

        if(line_count==LINES_PER_PAGE) {
            line_count = 0;
            page_number++;
        }
    });

    word_count.forEach((count, word) => {
        if (count > STOP_FREQUENCY_LIMIT)
            word_index.delete(word);
    });
    
    output = "";
    var words = Array.from(word_index.keys());
    words.sort((a,b) => {
        if(a.toLowerCase() < b.toLowerCase()) return -1;
        if(b.toLowerCase() < a.toLowerCase()) return 1;
        return 0;
    })
    words.forEach(word => {
        output += word + ": ";
        word_index.get(word).forEach(i => {
            output += i + ", ";
        });
        output += "\n";
    });
    console.log(output);

    return word_index;
}