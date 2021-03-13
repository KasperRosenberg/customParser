package assignment0;
//Kasper Rosenberg karo5568
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Tokenizer {

	private static Map<Character, Token> symbols = null;
	private static HashSet<Integer> numbers = null;
	private static HashSet<Character> id = null;

	private Scanner scanner = null;
	private Lexeme current = null;
	private Lexeme next = null;

	public Tokenizer() {

		symbols = new HashMap<Character, Token>();
		numbers = new HashSet<Integer>();
		id = new HashSet<Character>();

		symbols.put('{', Token.LEFT_CURLY);
		symbols.put('}', Token.RIGHT_CURLY);
		symbols.put('=', Token.ASSIGN_OP);
		symbols.put(';', Token.SEMICOLON);
		symbols.put('+', Token.ADD_OP);
		symbols.put('-', Token.SUB_OP);
		symbols.put('*', Token.MULT_OP);
		symbols.put('/', Token.DIV_OP);
		symbols.put('(', Token.LEFT_PAREN);
		symbols.put(')', Token.RIGHT_PAREN);
		symbols.put(Scanner.EOF, Token.EOF);

		numbers.addAll(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
		id.addAll(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
				'r', 's', 't', 'u', 'v', 'x', 'y', 'z'));
	}

	/**
	 * Opens a file for tokenizing.
	 */
	void open(String fileName) throws IOException, TokenizerException {
		scanner = new Scanner();
		scanner.open(fileName);
		scanner.moveNext();
		next = extractLexeme();
	}

	/**
	 * Returns the current token in the stream.
	 */
	Lexeme current() {
		return current;
	}

	/**
	 * Moves current to the next token in the stream.
	 */
	void moveNext() throws IOException, TokenizerException {
		if (scanner == null)
			throw new IOException("No open file.");
		current = next;
		if (next.token() != Token.EOF)
			next = extractLexeme();
	}

	private void consumeWhiteSpaces() throws IOException {
		while (Character.isWhitespace(scanner.current())) {
			scanner.moveNext();
		}
	}

	private Lexeme extractLexeme() throws IOException, TokenizerException {
		consumeWhiteSpaces();
		Character ch = scanner.current();
//		StringBuilder strBuilder = new StringBuilder();
		if (ch == Scanner.EOF) {
			return new Lexeme(ch, Token.EOF);
		}
		else if (Character.isLetter(ch)) {
			if(id.contains(ch)){
				scanner.moveNext();
				return new Lexeme(ch, Token.IDENT);
			}
			else {
				throw new TokenizerException("Unknown character: " + String.valueOf(ch));
			}
		}							
		else if (Character.isDigit(ch)) {
			scanner.moveNext();
			return new Lexeme(ch, Token.INT_LIT);
		}
		else if (symbols.containsKey(ch)) {
			scanner.moveNext();
			return new Lexeme(ch, symbols.get(ch));
		}
		else {
			throw new TokenizerException("Unknown character: " + String.valueOf(ch));
		}
	}

	/**
	 * Closes the file and releases any system resources associated with it.
	 */
	public void close() throws IOException {
		if (scanner != null)
			scanner.close();
	}

//	public static void main(String[] args) {
//
//		try {
//			Tokenizer t = new Tokenizer();
//			t.open("D:\\Program\\Eclipse\\eclipse\\workspace\\Assignment0\\assignment0\\program2.txt");
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}
}
