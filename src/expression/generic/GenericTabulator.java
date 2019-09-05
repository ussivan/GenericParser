package expression.generic;

import expression.exceptions.EvaluationException;
import expression.exceptions.ParsingException;
import expression.generic.worker.*;
import expression.operations.TripleExpression;
import expression.parser.ExpressionParser;
import expression.parser.Parser;

import java.util.Map;

public class GenericTabulator implements Tabulator {

    private static final Map<String, Worker<?>> WORKER_MAP = Map.of(
            "i", new IntWorker(true),
            "u", new IntWorker(false),
            "bi", new BigIntegerWorker(),
            "b", new ByteWorker(),
            "f", new FloatWorker(),
            "d", new DoubleWorker()
    );

    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws ParsingException {
        Worker<?> worker = WORKER_MAP.get(mode);
        if (worker == null) {
            throw new ParsingException("Incorrect mode name " + mode);
        }
        try {
            return solve(worker, expression, x1, x2, y1, y2, z1, z2);
        } catch (ParsingException e) {
            return new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        }
    }

    private <T> Object[][][] solve(Worker<T> worker, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws ParsingException {
        Object[][][] ans = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        TripleExpression<T> parsed = new ExpressionParser<>(worker).parse(expression);
        for (int i = 0; i <= x2 - x1; i++) {
            for (int j = 0; j <= y2 - y1; j++) {
                for (int l = 0; l <= z2 - z1; l++) {
                    try {
                        ans[i][j][l] = parsed.evaluate(worker.parseNumber(i + x1 + ""), worker.parseNumber(j + y1 + ""), worker.parseNumber(l + z1 + ""));
                    } catch (EvaluationException | ParsingException e) {
//                        ans[i][j][l] = null;
                    }
                }
            }
        }
        return ans;
    }
}
