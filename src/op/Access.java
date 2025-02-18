package op;

import expr.Id;
import expr.Op;
import inter.Expr;
import lexer.Tag;
import lexer.Word;
import lexer.Type;

public class Access extends Op {
    public Id array;
    public Expr index;

    public Access(Id a, Expr i, Type p) {
        super(new Word("[]", Tag.INDEX), p);
        array = a;
        index = i;
    }
    public Expr gen() {
        return new Access(array, index.reduce(), type);
    }
    public void jumping(int t, int f) {
        emitJumps(reduce().toString(), t, f);
    }
    public String toString() {
        return array.toString() + " [ " + index.toString() + " ]";
    }
}
