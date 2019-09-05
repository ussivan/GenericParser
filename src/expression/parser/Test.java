package expression.parser;

import expression.exceptions.EvaluationException;
import expression.exceptions.ParsingException;
import expression.generic.worker.IntWorker;
import expression.operations.TripleExpression;

public class Test {

	public void run() throws EvaluationException, ParsingException {
		ExpressionParser<Integer> e = new ExpressionParser<>(new IntWorker(true));
		try {
//			Scanner in = new Scanner(System.in);
//			String exp = in.nextLine();
			System.out.println(-11%(-1));
			int x = 0, y = 0, z = 0;
//			in.close();
//			System.out.println("parsing expression: " + exp);
			TripleExpression<Integer> c = e.parse("10 + 4 / 2 - 7");
			System.out.println(c.evaluate(x, y, z));
		} catch (Exception e2) {
			e2.printStackTrace();
//			System.out.println(e2.getMessage());
		}
	}

	public static void main(String[] args) throws EvaluationException, ParsingException {
		new Test().run();
	}

}
