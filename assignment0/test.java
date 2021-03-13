////package assignment0;
////
////import java.io.IOException;
////
////public class test implements IParser{
////
////	Tokenizer t = null;
////
////	@Override
////	public void open(String fileName) throws IOException, TokenizerException {
////		// TODO Auto-generated method stub
////		t = new Tokenizer();
////		t.open(fileName);
////		t.moveNext();
////	}
////
////	@Override
////	public INode parse() throws IOException, TokenizerException, ParserException {
////		 if (t == null) {
////			    throw new IOException("No open file.");
////		 }else {
////			 return new AssignNode(t);
////		 }
////	}
////
////	@Override
////	public void close() throws IOException {
////		  if (t != null) {
////			     t.close();
////		  }
////	}
////
////	
////	class AssignNode implements INode {
////		Lexeme id;
////		Lexeme assign;
////		ExprNode expr;
////		Lexeme semi;
////
////		public AssignNode(Tokenizer t) {
////			try {
////				if(t.current().token() == Token.IDENT) {
////					id = t.current();
////					t.moveNext();				
////				}
////				if(t.current().token() == Token.ASSIGN_OP) {
////					assign = t.current();
////					t.moveNext();
////				}
////			}catch (Exception e) {
////				System.out.println(e);
////			}
////			if(t.current().token() != Token.EOF) {
////				expr = new ExprNode(t);
////			}
////						
////			try {
////				if(t.current().token() == Token.SEMICOLON) {
////					semi = t.current();
////					t.moveNext();
////				}
////			}catch (Exception e) {
////				System.out.println(e);
////			}
////		}
////
////		@Override
////		public Object evaluate(Object[] args) throws Exception {
////			// TODO Auto-generated method stub
////			return null;
////		}
////
////		@Override
////		public void buildString(StringBuilder b, int tabs) {
////			b.append("\t".repeat(tabs) + "AssignNode" + "\n");
////			if(id != null) {
////				b.append("\t".repeat(tabs + 1) + id.toString() + "\n");
////			}
////			if(assign != null) {
////				b.append("\t".repeat(tabs + 1) + assign.toString() + "\n");
////			}
////			if(expr != null) {
////				expr.buildString(b, tabs + 1);
////			}
////			if(semi != null) {
////				b.append("\t".repeat(tabs + 1) + semi.toString() + "\n");
////			}
////		}
////	}
////
////	class ExprNode implements INode {
////		TermNode term;
////		Lexeme op;
////		ExprNode expr;
////
////		public ExprNode(Tokenizer t) {
////			term = new TermNode(t);
////			if(t.current().token() != Token.EOF) {
////				try {
////					if(t.current().token() == Token.ADD_OP || t.current().token() == Token.SUB_OP) {
////						op = t.current();
////						t.moveNext();
////						if(t.current().token() != Token.EOF) {
////							expr = new ExprNode(t);
////						}
////					}
////				}catch (Exception e) {
////					System.out.println(e);
////				}
////			}
////		}
////
////		@Override
////		public Object evaluate(Object[] args) throws Exception {
////			// TODO Auto-generated method stub
////			return null;
////		}
////
////		@Override
////		public void buildString(StringBuilder b, int tabs) {
////			b.append("\t".repeat(tabs) + "ExprNode" + "\n");
////			if(term != null) {
////				term.buildString(b, tabs + 1);
////			}
////			if(op != null) {
////				b.append("\t".repeat(tabs + 1) + op.toString() + "\n");
////			}
////			if( expr != null) {
////				expr.buildString(b, tabs + 1);
////			}
////		}
////	}
////	
////	class TermNode implements INode {
////		FactorNode factor;
////		Lexeme op;
////		TermNode term;
////		
////		public TermNode(Tokenizer t) {
////			factor = new FactorNode(t);
////			if(t.current().token() != Token.EOF) {
////				try {
////					if(t.current().token() == Token.MULT_OP || t.current().token() == Token.DIV_OP) {
////						op = t.current();
////						t.moveNext();
////						if(t.current().token() != Token.EOF) {
////							term = new TermNode(t);
////						}
////					}
//////					if(t.current().token() != Token.EOF
//////							&& (t.current().token() != Token.ADD_OP 
//////							&& t.current().token() != Token.SUB_OP)) {
//////						term = new TermNode(t);
//////					}
////				}catch (Exception e) {
////					System.out.println(e);
////				}
////			}
////		}
////
////		@Override
////		public Object evaluate(Object[] args) throws Exception {
////			// TODO Auto-generated method stub
////			return null;
////		}
////
////		@Override
////		public void buildString(StringBuilder b, int tabs) {
////			b.append("\t".repeat(tabs) + "TermNode" + "\n");
////			if(factor != null) {
////				factor.buildString(b, tabs + 1);
////			}
////			if(op != null) {
////				b.append("\t".repeat(tabs + 1) + op.toString() + "\n");
////			}
////			if(term != null) {
////				term.buildString(b, tabs + 1);
////			}
////		}
////	}
////
////	class FactorNode implements INode {
////		Lexeme nr;
////		Lexeme let;
////		Lexeme lPar;
////		ExprNode expr;
////		Lexeme rPar;
////
////		public FactorNode(Tokenizer t) {
////			try {
////				if(t.current().token() == Token.INT_LIT) {
////					nr = t.current();
////					t.moveNext();
////				}
////				if(t.current().token() == Token.IDENT) {
////					let = t.current();
////					t.moveNext();
////				}else {
////					if(t.current().token() == Token.LEFT_PAREN){
////						lPar = t.current();
////						t.moveNext();
////					}
////					if(t.current().token() == Token.INT_LIT || t.current().token() == Token.IDENT
////							|| t.current().token() == Token.LEFT_PAREN) {
////						expr = new ExprNode(t);
////						
////						if(t.current().token() == Token.RIGHT_PAREN) {
////							rPar = t.current();
////							t.moveNext();
////						}
////					}
//////					if(t.current().token() == Token.RIGHT_PAREN) {
//////						rPar = t.current();
//////						t.moveNext();
//////					}
////				}
////			}catch (Exception e) {
////				System.out.println(e);
////			}
////		}
////
////		@Override
////		public Object evaluate(Object[] args) throws Exception {
////			// TODO Auto-generated method stub
////			return null;
////		}
////
////		@Override
////		public void buildString(StringBuilder b, int tabs) {
////			b.append("\t".repeat(tabs) + "FactorNode" + "\n");
////			if(nr != null) {
////				b.append("\t".repeat(tabs + 1) + nr.toString() + "\n");
////			}
////			if(let != null) {
////				b.append("\t".repeat(tabs + 1) + let.toString() + "\n");
////			}
////			if(lPar != null){
////				b.append("\t".repeat(tabs + 1) + lPar.toString() + "\n");
////			}
////			if(expr != null) {
////				expr.buildString(b, tabs + 1);
////			}
////			if(rPar != null) {
////				b.append("\t".repeat(tabs + 1) + rPar.toString() + "\n");
////			}
////		}
////	}
////
////
////}
//// Hussein Alabdali
//// hual0441
//
//package PropAssignment0;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//
//public class Tokenizer {
//    private static Map<Character, Token> symbols = null;
//    private static HashSet<Integer> numbers = null;
//    private static HashSet<Character> letters = null;
//
//    private Scanner scanner = null;
//    private Lexeme current = null;
//    private Lexeme next = null;
//
//
//    public Tokenizer() {
//
//	symbols = new HashMap<Character, Token>();
//	numbers = new HashSet<Integer>();
//	letters = new HashSet<Character>();
//
//	symbols.put('{', Token.LEFT_CURLY);
//	symbols.put('}', Token.RIGHT_CURLY);
//	symbols.put('=', Token.ASSIGN_OP);
//	symbols.put(';', Token.SEMICOLON);
//	symbols.put('+', Token.ADD_OP);
//	symbols.put('-', Token.SUB_OP);
//	symbols.put('*', Token.MULT_OP);
//	symbols.put('/', Token.DIV_OP);
//	symbols.put('(', Token.LEFT_PAREN);
//	symbols.put(')', Token.RIGHT_PAREN);
//	symbols.put(Scanner.EOF, Token.EOF);
//
//	numbers.addAll(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
//
//
//	letters.addAll(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 
//			'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
//
//    }
//
//    /**
//     * Opens a file for tokenizing.
//     */
//    void open(String fileName) throws IOException, TokenizerException {
//	scanner = new Scanner();
//	scanner.open(fileName);
//	scanner.moveNext();
//	next = extractLexeme();
//    }
//
//    /**
//     * Returns the current token in the stream.
//     */
//    Lexeme current(){
//	return current;
//    }
//
//    /**
//     * Moves current to the next token in the stream.
//     */
//    void moveNext() throws IOException, TokenizerException {
//	if (scanner == null)
//	    throw new IOException("No open file.");
//	current = next;
//	if (next.token() != Token.EOF)
//	    next = extractLexeme();
//    }
//
//    private void consumeWhiteSpaces() throws IOException {
//    while (Character.isWhitespace(scanner.current())){
//	    scanner.moveNext();
//	}
//    }
//
//    private Lexeme extractLexeme() throws IOException, TokenizerException {
//	consumeWhiteSpaces();
//	
//	Character ch = scanner.current();
//
//	if (ch == Scanner.EOF) {
//	    return new Lexeme(ch, Token.EOF);
//	}
//	else if(Character.isLetter(ch)) {
//		if(letters.contains(ch)){
//			scanner.moveNext();
//			return new Lexeme(ch, Token.IDENT);
//		}else {
//			throw new TokenizerException("Unknown character: " + String.valueOf(ch));
//		}
//	}else if(Character.isDigit(ch)) {
//		scanner.moveNext();
//		return new Lexeme(ch, Token.INT_LIT);	
//	}else if(symbols.containsKey(ch)) {
//		scanner.moveNext();
//		return new Lexeme(ch, symbols.get(ch));
//	}
//	else
//	    throw new TokenizerException("Unknown character: " + String.valueOf(ch));
//    }
//
//
//    public void close() throws IOException {
//		if (scanner != null)
//			scanner.close();
//    }
//
////    public static void main(String[] args) {
////    
////	     try {
////	        Tokenizer t = new Tokenizer();
////	        t.open("C:\\Users\\Administrator\\Documents\\Prop\\program2.txt");
////	     } catch (Exception e) {
////	        System.out.println(e);
////	     }
////    }
//}
//
//
//
//
//
//
