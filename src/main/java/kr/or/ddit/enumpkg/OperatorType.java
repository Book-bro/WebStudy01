package kr.or.ddit.enumpkg;

import kr.or.ddit.servlet04.RealOperator;

public enum OperatorType {
	PLUS('+', (l, r)->{return l+r;}),
	MINUS('-', (l, r)->l-r),
	MULTIPLY('*',(l, r)->l*r),
	DIVIDE('/',(l, r)->l/r);
	
	private char sign;
	private RealOperator realOperator;
	
	private OperatorType(char sign, RealOperator realOperator) {
		this.sign = sign;
		this.realOperator = realOperator;
	}
	
	public char getSign() {
		return sign;
	}

	public int operate(int leftOp, int rightOp) {
		return realOperator.operate(leftOp, rightOp);
	}
	
	public String getExpression(int leftOp, int rightOp) {
//		2 + 2 = 4
		return String.format("%d %s %d = %d", leftOp, sign, rightOp, realOperator.operate(leftOp, rightOp));
	}
}
