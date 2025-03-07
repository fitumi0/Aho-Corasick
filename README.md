# Aho-Corasick

This project is an implementation of the Aho-Corasick algorithm in Java. 
The Aho-Corasick algorithm is an efficient method for searching multiple patterns in a text, allowing simultaneous detection of all occurrences from a set of strings (dictionary) in a given text. 
[Learn more about the algorithm](https://en.wikipedia.org/wiki/Aho%E2%80%93Corasick_algorithm)

## Description

The Aho-Corasick algorithm builds a finite state machine similar to a prefix tree (trie), with additional links between internal nodes. 
These additional links allow for quick transitions between partial matches, ensuring linear complexity relative to the sum of all pattern lengths and the text length.

## Building and Running

1. Clone the repository:

   ```bash
   git clone https://github.com/fitumi0/Aho-Corasick.git
   ```

2. Navigate to the project directory:

   ```bash
   cd Aho-Corasick
   ```

3. Build the project using Maven:

   ```bash
   todo
   ```

4. Run the application:

   ```bash
   todo
   ```

## License

[MIT](LICENSE)

## Links

- [Aho-Corasick Algorithm - Wikipedia](https://en.wikipedia.org/wiki/Aho%E2%80%93Corasick_algorithm)
- [Ctrl+F on steroids - Aho-Corasick Algorithm (pt. 1)](https://www.youtube.com/watch?v=XWujo7KQL54)
- [Aho-Corasick Algorithm - JavaScript Implementation (pt. 2)](https://www.youtube.com/watch?v=jsgLCvOW6Vo)
