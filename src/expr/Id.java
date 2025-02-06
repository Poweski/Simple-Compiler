package expr;

import inter.Expr;
import lexer.Type;
import lexer.Word;

public class Id extends Expr {
    public int offset;

    public Id(Word id, Type p, int b) {
        super(id, p);
        offset = b;
    }
}
