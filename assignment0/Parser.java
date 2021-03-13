package assignment0;
//Kasper Rosenberg karo5568
import java.io.IOException;

public class Parser implements IParser {
	Tokenizer t = null;

	@Override
	public void open(String fileName) throws IOException, TokenizerException {
		t = new Tokenizer();
		t.open(fileName);
		t.moveNext();
	}

	@Override
	public INode parse() throws IOException, TokenizerException, ParserException {
		if (t == null) {
			throw new IOException("No open file.");
		} else {
			return new AssignmentNode(t);
		}
	}

	@Override
	public void close() throws IOException {
		if (t != null) {
			t.close();
		}
	}

	class AssignmentNode implements INode {
		ExpressionNode eNode = null;
		Lexeme id;
		Lexeme assignOp;
		Lexeme semicolon;

		public AssignmentNode(Tokenizer t) {
			try {
				if (t.current().token() == Token.IDENT) {
					id = t.current();
					t.moveNext();
				}
				if (t.current().token() == Token.ASSIGN_OP) {
					assignOp = t.current();
					t.moveNext();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			if (t.current().token() != Token.EOF) {
				eNode = new ExpressionNode(t);
			}
			try {
				if (t.current().token() == Token.SEMICOLON) {
					semicolon = t.current();
					t.moveNext();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		@Override
		public Object evaluate(Object[] args) throws Exception {
			return null;
		}

		@Override
		public void buildString(StringBuilder builder, int tabs) {
			builder.append("\t".repeat(tabs) + "ASSIGNMENTNODE" + "\n");
			if (id != null) {
				builder.append("\t".repeat(tabs + 1) + id.toString() + "\n");
			}
			if (assignOp != null) {
				builder.append("\t".repeat(tabs + 1) + assignOp.toString() + "\n");
			}
			if (eNode != null) {
				eNode.buildString(builder, tabs + 1);
			}
			if (semicolon != null) {
				builder.append("\t".repeat(tabs + 1) + semicolon.toString() + "\n");
			}
		}
	}

	class ExpressionNode implements INode {
		TermNode tNode = null;
		ExpressionNode eNode = null;
		Lexeme operator;

		public ExpressionNode(Tokenizer t) {
			tNode = new TermNode(t);
			if (t.current().token() != Token.EOF) {
				try {
					if (t.current().token() == Token.ADD_OP || t.current().token() == Token.SUB_OP) {
						operator = t.current();
						t.moveNext();
						if (t.current().token() != Token.EOF) {
							eNode = new ExpressionNode(t);
						}
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}

		@Override
		public Object evaluate(Object[] args) throws Exception {
			return null;
		}

		@Override
		public void buildString(StringBuilder builder, int tabs) {
			builder.append("\t".repeat(tabs) + "EXPRESSIONNODE" + "\n");
			if (tNode != null) {
				tNode.buildString(builder, tabs + 1);
			}
			if (operator != null) {
				builder.append("\t".repeat(tabs + 1) + operator.toString() + "\n");
			}
			if (eNode != null) {
				eNode.buildString(builder, tabs + 1);
			}
		}
	}

	class TermNode implements INode {
		FactorNode fNode = null;
		TermNode tNode = null;
		Lexeme operator;

		public TermNode(Tokenizer t) {
			fNode = new FactorNode(t);
			if (t.current().token() != Token.EOF) {
				try {
					if (t.current().token() == Token.MULT_OP || t.current().token() == Token.DIV_OP) {
						operator = t.current();
						t.moveNext();
						if (t.current().token() != Token.EOF) {
							tNode = new TermNode(t);
						}
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}

		@Override
		public Object evaluate(Object[] args) throws Exception {
			return null;
		}

		@Override
		public void buildString(StringBuilder builder, int tabs) {
			builder.append("\t".repeat(tabs) + "TERMNODE" + "\n");
			if (fNode != null) {
				fNode.buildString(builder, tabs + 1);
			}
			if (operator != null) {
				builder.append("\t".repeat(tabs + 1) + operator.toString() + "\n");
			}
			if (tNode != null) {
				tNode.buildString(builder, tabs + 1);
			}
		}
	}

	class FactorNode implements INode {
		ExpressionNode eNode = null;
		Lexeme num;
		Lexeme id;
		Lexeme leftPar;
		Lexeme rightPar;

		public FactorNode(Tokenizer t) {
			try {
				if (t.current().token() == Token.INT_LIT) {
					num = t.current();
					t.moveNext();
				}
				if (t.current().token() == Token.IDENT) {
					id = t.current();
					t.moveNext();
				} else {
					if (t.current().token() == Token.LEFT_PAREN) {
						leftPar = t.current();
						t.moveNext();
					}
					if (t.current().token() == Token.INT_LIT || t.current().token() == Token.IDENT
							|| t.current().token() == Token.LEFT_PAREN) {
						eNode = new ExpressionNode(t);
						
						if (t.current().token() == Token.RIGHT_PAREN) {
							rightPar = t.current();
							t.moveNext();
						}
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		@Override
		public Object evaluate(Object[] args) throws Exception {
			return null;
		}

		@Override
		public void buildString(StringBuilder builder, int tabs) {
			builder.append("\t".repeat(tabs) + "FACTORNODE" + "\n");
			if (num != null) {
				builder.append("\t".repeat(tabs + 1) + num.toString() + "\n");
			}
			if (id != null) {
				builder.append("\t".repeat(tabs + 1) + id.toString() + "\n");
			}
			if (leftPar != null) {
				builder.append("\t".repeat(tabs + 1) + leftPar.toString() + "\n");
			}
			if (eNode != null) {
				eNode.buildString(builder, tabs + 1);
			}
			if (rightPar != null) {
				builder.append("\t".repeat(tabs + 1) + rightPar.toString() + "\n");
			}
		}
	}
}
